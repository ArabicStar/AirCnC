package utils.promotion.trigger;

import static utils.info.member.MemberInfoTemplate.formatID;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import utils.promotion.Describer;
import utils.promotion.trigger.hotel.HotelWhen;
import utils.promotion.trigger.website.WebsiteWhen;
import utils.property.ParametersList;

public final class TriggerTemplates {
	private TriggerTemplates() {
	}

	public static final ParametersList getParametersTemplate(WebsiteWhen when) {
		switch (when) {
		case DURING_PERIOD:
			return periodProperties();
		case LEVEL:
			return levelProperties();
		case TRADE_AREA:
			return tradeAreaProperties();
		default:
			return null;
		}
	}

	private static final ParametersList periodProperties() {
		ParametersList periodParams = new ParametersList();

		// from date time, should after today
		periodParams.addParameter(TriggerParams.FROM.paramName(), LocalDateTime.class,
				t -> t.isAfter(LocalDateTime.now()) || t.isEqual(LocalDateTime.now()));

		// to date time, should after start date time
		periodParams.addParameter(TriggerParams.TO.paramName(), LocalDateTime.class,
				t -> t.isAfter(periodParams.getParameterValue(TriggerParams.FROM.paramName())));

		return periodParams;
	}

	private static final ParametersList levelProperties() {
		ParametersList levelParams = new ParametersList();

		levelParams.addParameter(TriggerParams.LEVEL_THRESHOLD.paramName(), Integer.class, i -> i > 0);

		return levelParams;
	}

	private static final ParametersList tradeAreaProperties() {
		ParametersList tradeAreaParams = new ParametersList();

		tradeAreaParams.addParameter(TriggerParams.TARGET_TRADE_AREA.paramName(), String.class);

		return tradeAreaParams;
	}

	public static final Criterion getCritierionTemplate(WebsiteWhen when) {
		switch (when) {
		case DURING_PERIOD:
			return periodCritierion();
		case LEVEL:
			return levelCriterion();
		case TRADE_AREA:
			return tradeAreaCriterion();
		default:
			return null;
		}
	}

	private static final Criterion periodCritierion() {
		return (paramList, order, helper) -> {

			LocalDateTime from = paramList.getParameterValue(TriggerParams.FROM.paramName());
			LocalDateTime to = paramList.getParameterValue(TriggerParams.TO.paramName());
			LocalDateTime checkInTime = order.getEntryTime();
			return (from.isBefore(checkInTime) || from.isEqual(checkInTime))
					&& (checkInTime.isAfter(to) || checkInTime.isAfter(to));
		};
	}

	private static final Criterion levelCriterion() {
		return (paramList, order, helper) -> helper.getMemberLevel(formatID(order.getUserId())) >= (int) paramList
				.getParameterValue(TriggerParams.LEVEL_THRESHOLD.paramName());
	}

	private static final Criterion tradeAreaCriterion() {
		return (paramList, order, helper) -> helper.getHotelScope(order.getHotelId())
				.equals(paramList.getParameterValue(TriggerParams.TARGET_TRADE_AREA.paramName()));
	}

	public static final Describer getDescriber(WebsiteWhen when) {
		switch (when) {
		case DURING_PERIOD:
			return periodDesciber();
		case LEVEL:
			return levelDescriber();
		case TRADE_AREA:
			return tradeAreaDescriber();
		default:
			return null;
		}
	}

	private static final Describer periodDesciber() {
		return pms -> new StringBuilder(pms.getParameterValue(TriggerParams.LEVEL_THRESHOLD.paramName())).append("级会员")
				.toString();
	}

	private static final Describer levelDescriber() {
		return pms -> new StringBuilder("于")
				.append((String) pms.getParameterValue(TriggerParams.TARGET_TRADE_AREA.paramName())).toString();
	}

	private static final Describer tradeAreaDescriber() {
		return pms -> {
			LocalDateTime from = pms.getParameterValue("from");
			LocalDateTime to = pms.getParameterValue("to");
			DateTimeFormatter fmt = new DateTimeFormatterBuilder().appendValue(ChronoField.YEAR).appendLiteral("年")
					.appendValue(ChronoField.MONTH_OF_YEAR).appendLiteral("月").appendValue(ChronoField.DAY_OF_MONTH)
					.appendLiteral("日").toFormatter();

			return (from.isEqual(to) ? "于" : from.format(fmt).concat(" 至 ")).concat(to.format(fmt));
		};
	}

	public static final ParametersList getParametersTemplate(HotelWhen when) {
		switch (when) {
		case PERIOD: // General used
			return periodProperties();
		case BIRTHDAY:
			return birthdayParameters();
		case ENTERPRISE:
			return enterpriseParamerters();
		case MULTI_ROOMS:
			return multiRoomsParameters();
		default:
			return null;
		}
	}

	private static final ParametersList birthdayParameters() {
		return null;
	}

	private static final ParametersList enterpriseParamerters() {
		ParametersList enterpriseParams = new ParametersList();

		enterpriseParams.addParameter(TriggerParams.ENTERPRISE.paramName(), String.class,
				s -> s != null && s.length() > 0);

		return enterpriseParams;
	}

	private static final ParametersList multiRoomsParameters() {
		ParametersList multiParams = new ParametersList();

		multiParams.addParameter(TriggerParams.ROOM_NUM_THRESHOLD.paramName(), Integer.class, i -> i > 0);

		return multiParams;
	}

	public static final Criterion getCritierion(HotelWhen when) {
		switch (when) {
		case PERIOD:// General used
			return periodCritierion();
		case BIRTHDAY:
			return birthdayCriterion();
		case ENTERPRISE:
			return enterpriseCriterion();
		case MULTI_ROOMS:
			return multiRoomsCriterion();
		default:
			return null;
		}
	}

	private static final Criterion birthdayCriterion() {
		return (paramList, order, helper) -> {
			LocalDate birthday = helper.getMemberBirthday(formatID(order.getUserId()));
			int month = birthday.getMonthValue();
			int date = birthday.getDayOfMonth();

			LocalDate now = LocalDate.now();
			int nowMon = now.getMonthValue();
			int nowDate = now.getDayOfMonth();

			return month == nowMon && date == nowDate;
		};
	}

	private static final Criterion enterpriseCriterion() {
		return (paramList, order, helper) -> helper.getMemberEnterprise(formatID(order.getUserId()))
				.equals(paramList.getParameterValue(TriggerParams.ENTERPRISE.paramName()));
	}

	private static final Criterion multiRoomsCriterion() {
		return (paramList, order, helper) -> order
				.getRoomNumber() >= (int) paramList.getParameterValue(TriggerParams.ROOM_NUM_THRESHOLD.paramName());
	}

	public static final Describer getDescriber(HotelWhen when) {
		switch (when) {
		case PERIOD:// General used
			return periodDesciber();
		case BIRTHDAY:
			return birthdayDescriber();
		case ENTERPRISE:
			return enterpriseDescriber();
		case MULTI_ROOMS:
			return multiRoomsDescriber();
		default:
			return null;
		}
	}

	private static final Describer birthdayDescriber() {
		return params -> "生日特惠";
	}

	private static final Describer enterpriseDescriber() {
		return params -> params.getParameterValue(TriggerParams.ENTERPRISE.paramName()) + "会员专享";
	}

	private static final Describer multiRoomsDescriber() {
		return params -> params.getParameterValue(TriggerParams.ROOM_NUM_THRESHOLD.paramName()) + "间及以上优惠";
	}
}
