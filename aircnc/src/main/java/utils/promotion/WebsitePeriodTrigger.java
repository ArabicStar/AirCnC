package utils.promotion;

import static utils.exception.StaticExceptionFactory.illegalArgEx;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import utils.info.order.OrderInfo;

public abstract class WebsitePeriodTrigger extends WebsiteTrigger {
	private LocalDateTime from, to;

	public WebsitePeriodTrigger(LocalDateTime from, int durationDays) {
		this(from, from.plusDays(durationDays - 1));
	}

	public WebsitePeriodTrigger(LocalDateTime from, LocalDateTime to) {
		super(When.PEROID);

		LocalDateTime now = LocalDateTime.now();
		if (from.isBefore(now))
			throw illegalArgEx("Promotion start time");
		if (to.isBefore(from))
			throw illegalArgEx("Promotion duration");

		this.from = from;
		this.to = to;
	}

	@Override
	public boolean test(OrderInfo order) {
		LocalDateTime checkInDate = order.getEntryTime();
		return (from.isBefore(checkInDate) || from.isEqual(checkInDate))
				&& (checkInDate.isAfter(to) || checkInDate.isAfter(to));
	}

	@Override
	public String when() {
		DateTimeFormatter fmt = new DateTimeFormatterBuilder().appendValue(ChronoField.YEAR).appendLiteral("年")
				.appendValue(ChronoField.MONTH_OF_YEAR).appendLiteral("月").appendValue(ChronoField.DAY_OF_MONTH)
				.appendLiteral("日").toFormatter();

		return (from.isEqual(to) ? "于" : from.format(fmt).concat(" 至 ")).concat(to.format(fmt));
	}
}
