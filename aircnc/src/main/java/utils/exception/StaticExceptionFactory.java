package utils.exception;

import java.rmi.RemoteException;

public class StaticExceptionFactory {
	private static final String RMI_EXCEPTION_PROMPT = "enconter rmi exception";
	private static final String DUPLICATE_SINGLETON_EX_PROMPT = "Singleton instance has existed already.";
	private static final String SINGLETON_NOT_EXISTS_EX_PROMPT = "Singleton instance has not been launched yet";
	private static final String ILLEGAL_IDENTIFIER_EX_PROMPT = "Illegal or invalid identifier or object:";
	private static final String BUILDER_NOT_READY_EX_PROMPT = "Lack info.";
	private static final String INCONSISTENT_STATUS_EX_PROMPT = "Inconsistent identifier or type:";
	private static final String ILLEGAL_STATE_EX_PROMPT = "Illegal state:";
	private static final String UNSUPPORTED_OPERATION_EX_PROMPT = "Unsupported operation, may caused by null dependent objects.";
	private static final String UNKNOWN_EXCEPTION = "Unknown Exception.";
	private static final String ACCESSOR_NOT_READY_EX_PROMPT = "Lack info.";

	public static final IllegalStateException packedRmiEx(RemoteException re) {
		return new IllegalStateException(getPromptHead().append(RMI_EXCEPTION_PROMPT).toString(), re);
	}

	public static final IllegalStateException duplicateSingletonEx() {
		return new IllegalStateException(getPromptHead().append(DUPLICATE_SINGLETON_EX_PROMPT).toString());
	}

	public static final IllegalStateException singletonNotExistsEx() {
		return new IllegalStateException(getPromptHead().append(SINGLETON_NOT_EXISTS_EX_PROMPT).toString());
	}

	public static final IllegalStateException builderNotReadyEx() {
		return new IllegalStateException(getPromptHead().append(BUILDER_NOT_READY_EX_PROMPT).toString());
	}

	public static final IllegalStateException illegalStateException(String status) {
		return new IllegalStateException(getPromptHead().append(ILLEGAL_STATE_EX_PROMPT).append(status).toString());
	}

	public static final IllegalArgumentException illegalArgEx(String idName) {
		return new IllegalArgumentException(
				getPromptHead().append(ILLEGAL_IDENTIFIER_EX_PROMPT).append(idName).toString());
	}

	public static final IllegalArgumentException inconsistentStatusEx() {
		return new IllegalArgumentException(getPromptHead().append(INCONSISTENT_STATUS_EX_PROMPT).toString());
	}

	public static final UnsupportedOperationException unsupportedOpEx(String opName) {
		return new UnsupportedOperationException(
				getPromptHead().append(UNSUPPORTED_OPERATION_EX_PROMPT).append(opName).toString());
	}

	public static final Exception unknownEx() {
		return new Exception(getPromptHead().append(UNKNOWN_EXCEPTION).toString());
	}

	private static final StringBuilder getPromptHead() {
		return new StringBuilder(getExceptionMethodName()).append(" - ");
	}

	private static final String getExceptionMethodName() {
		StackTraceElement[] stack = new Throwable().getStackTrace();
		return stack[3].getMethodName();
	}
	
	public static final IllegalStateException accessorNotReadyEx() {
		return new IllegalStateException(getPromptHead().append(ACCESSOR_NOT_READY_EX_PROMPT).toString());
	}

	private StaticExceptionFactory() {
	}
}
