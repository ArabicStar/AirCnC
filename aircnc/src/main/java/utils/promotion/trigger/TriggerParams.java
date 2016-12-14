package utils.promotion.trigger;

public enum TriggerParams {
	FROM, TO, LEVEL_THRESHOLD, TARGET_TRADE_AREA, BIRTHDAY, ENTERPRISE, ROOM_NUM_THRESHOLD;

	public String paramName() {
		return this.name().toLowerCase();
	}
}
