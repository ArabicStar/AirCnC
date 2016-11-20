package utils.info.member.credit;

public enum ActionType {
	CHARGE("进行线下充值，金额为%f元，", ChangeResultType.UP, 1), //
	ORDER_EXECUTION("于%s的订单%s已执行，", ChangeResultType.UP, 2), //
	ORDER_CANCEL("于%s的订单%s已撤销，因距离最后订单执行时间不足6小时，", ChangeResultType.DOWN, 2), //
	ORDER_OVERDUE("于%s的订单%s因下单后未及时入住，", ChangeResultType.DOWN, 2), //
	ORDER_APPEAL("于%s的异常订单%s申诉已通过，", ChangeResultType.RECOVER, 2), //
	ORDER_DELAY("于%s的订单%已办理延迟入住，", ChangeResultType.RECOVER, 2);
	private String formatString;
	private ChangeResultType result;
	private int relateTo = 0;
	private static final int MONEY_RELATED = 1;
	private static final int ORDER_RELATED = 2;

	private ActionType(String format_string, ChangeResultType result, int relatedTo) {
		this.formatString = format_string;
		this.result = result;
		this.relateTo = relatedTo;
	}

	public ChangeResultType getResult() {
		return result;
	}

	public String getFormatString() {
		return formatString;
	}

	public boolean checkChangeValue(int changeValue) {
		return result.checkChangeValue(changeValue);
	}

	public boolean isMoneyRelated() {
		return (relateTo & MONEY_RELATED) != 0;
	}

	public boolean isOrderRelated() {
		return (relateTo & ORDER_RELATED) != 0;
	}
}
