package utils.info.member.credit;

import utils.info.member.credit.CreditChangeTemplate.ChangeResultType;

public enum ActionType {
	CHARGE("进行线下充值，金额为%f元，", ChangeResultType.UP), //
	ORDER_EXECUTION("于%s的订单%s已执行，", ChangeResultType.UP), //
	ORDER_CANCEL("于%s的订单%s已撤销，因距离最后订单执行时间不足6小时，", ChangeResultType.DOWN), //
	ORDER_OVERDUE("于%s的订单%s因下单后未及时入住，", ChangeResultType.DOWN), //
	ORDER_APPEAL("于%s的异常订单%s申诉已通过，", ChangeResultType.RECOVER), //
	ORDER_DELAY("于%s的订单%已办理延迟入住，", ChangeResultType.RECOVER);
	private String formatString;
	private ChangeResultType result;

	private ActionType(String format_string, ChangeResultType result) {
		this.formatString = format_string;
		this.result = result;
	}

	public ChangeResultType getResult() {
		return result;
	}

	public String getFormatString() {
		return formatString;
	}

	public boolean validateChangeValue(int changeValue) {
		return result.validateChangeValue(changeValue);
	}
}
