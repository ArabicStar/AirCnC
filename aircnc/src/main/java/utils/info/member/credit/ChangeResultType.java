package utils.info.member.credit;

import java.util.function.Predicate;

/**
 * Define three types of credit changes.<br>
 * Simultaneously, define bound of change value.<br>
 * 
 * @author ClevelandAlto
 *
 */
enum ChangeResultType {
	/**
	 * gain
	 */
	UP("增加", i -> i > 0),
	/**
	 * reduce
	 */
	DOWN("扣除", i -> i < 0),
	/**
	 * recover
	 */
	RECOVER("恢复", i -> true);

	/**
	 * verb string which will be used on ui <br>
	 */
	private String verb;
	/**
	 * credit change value checker <br>
	 */
	private Predicate<Integer> checker;

	private ChangeResultType(String verb, Predicate<Integer> checker) {
		this.verb = verb;
		this.checker = checker;
	}

	/**
	 * Get action type correspondent verb.<br>
	 * 
	 * @return verb <br>
	 */
	public String getVerb() {
		return verb;
	}

	/**
	 * Check credit change value.<br>
	 * 
	 * @param changeValue
	 *            credit change value <br>
	 * @return if change value is valid bb
	 */
	public boolean checkChangeValue(int changeValue) {
		return checker.test(changeValue);
	}
}