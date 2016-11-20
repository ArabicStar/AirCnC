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
	UP("增加", i -> i > 0), DOWN("扣除", i -> i < 0), RECOVER("恢复", i -> true);

	private String verb;
	private Predicate<Integer> checker;

	private ChangeResultType(String verb, Predicate<Integer> checker) {
		this.verb = verb;
		this.checker = checker;
	}

	public String getVerb() {
		return verb;
	}

	public boolean checkChangeValue(int changeValue) {
		return checker.test(changeValue);
	}
}