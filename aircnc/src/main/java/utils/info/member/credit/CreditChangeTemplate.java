package utils.info.member.credit;

import java.time.Instant;
import java.util.function.Predicate;

public abstract class CreditChangeTemplate {
	protected enum ChangeResultType {
		UP("增加", i -> i > 0), DOWN("扣除", i -> i < 0), RECOVER("恢复", i -> true);

		private String verb;
		private Predicate<Integer> validation;

		private ChangeResultType(String verb, Predicate<Integer> validation) {
			this.verb = verb;
			this.validation = validation;
		}

		public String getVerb() {
			return verb;
		}

		public boolean validateChangeValue(int changeValue) {
			return validation.test(changeValue);
		}
	}

	protected static final String STRING_CACHE = "信用值%d";

	protected String memberID;
	protected Instant timeInstant;
	protected ChangeAction action;
	protected int beforeCredit = Integer.MIN_VALUE;
	protected int afterCredit = Integer.MIN_VALUE;
}
