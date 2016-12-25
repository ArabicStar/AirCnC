package utils.promotion.trigger;

import java.io.Serializable;

public enum TriggerParams implements Serializable{
	FROM, TO, LEVEL_THRESHOLD, TARGET_TRADE_AREA, BIRTHDAY, ENTERPRISE, ROOM_NUM_THRESHOLD;

	public String paramName() {
		return this.name().toLowerCase();
	}
}
