package utils.info.member.credit;

/**
 * Define types of credit change actions.<br>
 * Also, define string pattern will display on UI.<br>
 * 
 * @author ClevelandAlto
 *
 */
public enum ActionType {
	/**
	 * charge gain <br>
	 */
	CHARGE("进行线下充值，金额为%f元，", ChangeResultType.UP, 1), //
	/**
	 * execute order gain <br>
	 */
	ORDER_EXECUTION("于%s的订单%s已执行，", ChangeResultType.UP, 2), //
	/**
	 * cancel order reduce <br>
	 */
	ORDER_CANCEL("于%s的订单%s已撤销，因距离最后订单执行时间不足6小时，", ChangeResultType.DOWN, 2), //
	/**
	 * overdue order reduce <br>
	 */
	ORDER_OVERDUE("于%s的订单%s因下单后未及时入住，", ChangeResultType.DOWN, 2), //
	/**
	 * appeal abnormal order recover <br>
	 */
	ORDER_APPEAL("于%s的异常订单%s申诉已通过，", ChangeResultType.RECOVER, 2), //
	/**
	 * delay overdue order recover <br>
	 */
	ORDER_DELAY("于%s的订单%s已办理延迟入住，", ChangeResultType.RECOVER, 2);
	/**
	 * format string
	 */
	private String formatString;
	/**
	 * action result, define in enum ChangeResultType
	 * 
	 * @see ChangeResultType
	 */
	private ChangeResultType result;
	/**
	 * relate to charge or order, determine which field(money or orderId) will
	 * work in CreditChangeInfoBuilder <br>
	 * 
	 * @see CreditChangeInfoBuilder
	 */
	private int relateTo = 0;

	/**
	 * money related bit marker
	 */
	private static final int MONEY_RELATED = 1;
	/**
	 * order related bit marker
	 */
	private static final int ORDER_RELATED = 2;
	/**
	 * <b>"%d"</b> string buffer
	 */
	private static final String INTEGER_FORMAT_TOKEN_BUFFER = "%d";

	private ActionType(String format_string, ChangeResultType result, int relatedTo) {
		this.formatString = format_string;
		this.result = result;
		this.relateTo = relatedTo;
	}

	public ChangeResultType getResult() {
		return result;
	}

	public String getFormatString() {

		return new StringBuilder(formatString).append(result.getVerb()).append(INTEGER_FORMAT_TOKEN_BUFFER).toString();
	}

	/**
	 * Check credit change value.<br>
	 * 
	 * @param changeValue
	 *            credit change value <br>
	 * @return if credit change value valid <br>
	 */
	public boolean checkChangeValue(int changeValue) {
		return result.checkChangeValue(changeValue);
	}

	/**
	 * Check if this action type if related to charge.<br>
	 * 
	 * @return if this action type if related to charge <br>
	 */
	public boolean isMoneyRelated() {
		return (relateTo & MONEY_RELATED) != 0;
	}

	/**
	 * Check if this action type if related to order.<br>
	 * 
	 * @return if this action type if related to order <br>
	 */
	public boolean isOrderRelated() {
		return (relateTo & ORDER_RELATED) != 0;
	}
}
