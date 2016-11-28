package utils.proxy;

import java.lang.reflect.Method;

import utils.proxy.AuthenticatePolicy.Client;

public abstract class AccessSecureProxy {

	private Client currentClientId;

	protected AccessSecureProxy(Client clientId) {
		this.currentClientId = clientId;
	}

	public void checkAuthentication() {
		AuthenticatePolicy auth = getAuthPolicy();
		if (auth == null)
			return;

		Client[] authTo = auth.value();
		for (Client authed : authTo)
			if (currentClientId == authed || authed == Client.ALL)
				return;

		throw new IllegalStateException("No Authentication Operation");
	}

	public AuthenticatePolicy getAuthPolicy() {
		StackTraceElement[] stack = new Throwable().getStackTrace();
		Method method = null;
		try {
			method = this.getClass().getMethod(stack[2].getMethodName());
		} catch (NoSuchMethodException e) {
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return method.getAnnotation(AuthenticatePolicy.class);
	}
}
