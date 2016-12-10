package utils.proxy;

import java.lang.reflect.Method;
import java.rmi.Remote;

import utils.proxy.AuthenticatePolicy.Client;

/**
 * Proxy of service or dao which offer authentication controll.<br>
 * <b>Use it as follow:</b><br>
 * <ul>
 * <li>Extends this class.</li>
 * <li>Override constructor of which parameters include a Client identifier,
 * then call super constructor. For example: <br>
 * <blockquote>
 * <code> public FooServiceProxy(Client clientId, OtherParams other){<br>
 * <blockquote>super(clientId);</blockquote>
 * }</code></blockquote></li>
 * <li>add {@code @AuthenticatePolicy(...)} annotation on methods which need
 * authentication controll.</li>
 * <li>call {@code checkAuthenticatoin()} at methods very begin.</li>
 * </ul>
 * Then authentication check will be done automatically according to given auth
 * policy in annotion and given client identifier in constructor's parameters.
 * {@link IllegalStateException} will be thrown if attemp to make a non-granted
 * operation.<br>
 * 
 * @author ClevelandAlto
 * @see utils.proxy.AuthenticatePolicy
 * @see utils.proxy.AuthenticatePolicy.Client
 */
public abstract class AccessSecureProxy {

	/**
	 * Current client identifier<br>
	 */
	private Client currentClientId;

	/**
	 * Default constructor defines current client.<br>
	 * 
	 * @param clientId
	 *            client identifier <br>
	 */
	protected AccessSecureProxy(Client clientId) {
		this.currentClientId = clientId;
	}

	/**
	 * Check authentication according to annotation and {@code currentClientId}
	 * <br>
	 * 
	 * @throws IllegalStateException
	 *             when non-granted operation is attempted.<br>
	 */
	protected void checkAuthentication() {
		AuthenticatePolicy auth = getAuthPolicy();
		if (auth == null)
			return;

		Client[] authTo = auth.value();
		for (Client authed : authTo)
			if (currentClientId == authed || authed == Client.ALL)
				return;

		throw new IllegalStateException("No Authentication Operation");
	}

	/**
	 * Get currently called method annotation of {@code AuthenticatePolicy}.<br>
	 * 
	 * @return {@code AuthenticatePolicy} annotation on currently call method
	 */
	protected AuthenticatePolicy getAuthPolicy() {
		StackTraceElement[] stack = new Throwable().getStackTrace();
		String methodName = stack[2].getMethodName();

		Method[] ms = this.getClass().getMethods();

		for (Method m : ms)
			if (m.getName().equals(methodName))
				return m.getAnnotation(AuthenticatePolicy.class);

		return null;
	}
}
