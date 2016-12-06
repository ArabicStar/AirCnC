package utils.exception;

import java.rmi.RemoteException;

import javax.xml.ws.WebServiceException;

public class StaticExceptionFactory {
	private static final String RMI_EXCEPTION_PROMPT = " - enconter rmi exception";
	private static final String DUPLICATE_SINGLETON_EX_PROMPT = " - Singleton instance has existed already.";
	private static final String SINGLETON_NOT_EXISTS_EX_PROMPT = " - Singleton instance has not been launched yet";
	private static final String ILLEGAL_IDENTIFIER_EX_PROMPT = " - Illegal or invalid identifier:";

	public static final WebServiceException packedRmiEx(RemoteException re) {
		return new WebServiceException(getPromptHead().append(RMI_EXCEPTION_PROMPT).toString(), re);
	}

	public static final IllegalStateException duplicateSingletonEx() {
		return new IllegalStateException(getPromptHead().append(DUPLICATE_SINGLETON_EX_PROMPT).toString());
	}

	public static final IllegalStateException singletonNotExistsEx() {
		return new IllegalStateException(getPromptHead().append(SINGLETON_NOT_EXISTS_EX_PROMPT).toString());
	}

	public static final IllegalArgumentException illegalArgEx(String idName) {
		return new IllegalArgumentException(
				getPromptHead().append(ILLEGAL_IDENTIFIER_EX_PROMPT).append(idName).toString());
	}

	private static final StringBuilder getPromptHead() {
		return new StringBuilder(getExceptionMethodName());
	}

	private static final String getExceptionMethodName() {
		StackTraceElement[] stack = new Throwable().getStackTrace();
		return stack[3].getMethodName();
	}

	private StaticExceptionFactory() {
	}
}
