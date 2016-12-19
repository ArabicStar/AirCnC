package utils.promotion.trigger;

import static utils.info.member.MemberInfoTemplate.formatID;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import utils.parameter.ParametersList;
import utils.promotion.Describer;
import utils.promotion.trigger.hotel.HotelWhen;
import utils.promotion.trigger.website.WebsiteWhen;

public final class TriggerTemplates {
	private static final String DURING_PERIOD = "DURING_PERIOD";
	private static final String LEVEL = "LEVEL";
	private static final String TRADE_AREA = "TRADE_AREA";
	private static final String BIRTHDAY = "BIRTHDAY";
	private static final String ENTERPRISE = "ENTERPRISE";
	private static final String MULTI_ROOMS = "MULTI_ROOMS";

	private TriggerTemplates() {
	}

	public static final ParametersList getParametersTemplate(WebsiteWhen when) {
		switch (when.name()) {
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
		periodParams.addParameter(TriggerParams.FROM.paramName(), LocalDateTime.class, //
				(list, from) -> {
					LocalDateTime to = list.getParameterValue(TriggerParams.TO.paramName());

					return (from.isAfter(LocalDateTime.now()) || from.isEqual(LocalDateTime.now()))
							&& (to == null ? true : from.isBefore(to));

				});

		// to date time, should after start date time
		periodParams.addParameter(TriggerParams.TO.paramName(), LocalDateTime.class, //
				(list, to) -> {
					LocalDateTime from = list.getParameterValue(TriggerParams.FROM.paramName());

					return (from == null ? true : to.isAfter(from)) && to.isAfter(LocalDateTime.now());
				});

		return periodParams;
	}

	private static final ParametersList levelProperties() {
		ParametersList levelParams = new ParametersList();

		levelParams.addParameter(TriggerParams.LEVEL_THRESHOLD.paramName(), Integer.class, (list, i) -> i > 0);

		return levelParams;
	}

	private static final ParametersList tradeAreaProperties() {
		ParametersList tradeAreaParams = new ParametersList();

		tradeAreaParams.addParameter(TriggerParams.TARGET_TRADE_AREA.paramName(), String.class);

		return tradeAreaParams;
	}

	public static final Criterion getCritierionTemplate(WebsiteWhen when) {
		switch (when.name()) {
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
					&& (checkInTime.isBefore(to) || checkInTime.isEqual(to));
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
		switch (when.name()) {
		case DURING_PERIOD:
			return periodDescriber();
		case LEVEL:
			return levelDesciber();
		case TRADE_AREA:
			return tradeAreaDescriber();
		default:
			return null;
		}
	}

	private static final Describer levelDesciber() {
		return pms -> new StringBuilder(pms.getParameterValue(TriggerParams.LEVEL_THRESHOLD.paramName())).append("级会员")
				.toString();
	}

	private static final Describer tradeAreaDescriber() {
		return pms -> new StringBuilder("于")
				.append((String) pms.getParameterValue(TriggerParams.TARGET_TRADE_AREA.paramName())).toString();
	}

	private static final Describer periodDescriber() {
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
		switch (when.name()) {
		case DURING_PERIOD: // General used
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
				(list, s) -> s != null && s.length() > 0);

		return enterpriseParams;
	}

	private static final ParametersList multiRoomsParameters() {
		ParametersList multiParams = new ParametersList();

		multiParams.addParameter(TriggerParams.ROOM_NUM_THRESHOLD.paramName(), Integer.class, (list, i) -> i > 0);

		return multiParams;
	}

	public static final Criterion getCritierion(HotelWhen when) {
		switch (when.name()) {
		case DURING_PERIOD:// General used
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
		switch (when.name()) {
		case DURING_PERIOD:// General used
			return periodDescriber();
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
		return params -> params.getParameterValue(TriggerParams.ROOM_NUM_THRESHOLD.paramName()) + "间及以上";
	}
}
