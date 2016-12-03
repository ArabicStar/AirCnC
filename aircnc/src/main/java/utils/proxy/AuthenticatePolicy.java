package utils.proxy;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation of auth policy.<br>
 * Use it on methods which need authentication controll.<br>
 * 
 * @author ClevelandAlto
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AuthenticatePolicy {
	/**
	 * Client identifier.<br>
	 * 
	 * @author ClevelandAlto
	 *
	 */
	public enum Client {
		/**
		 * Client for user
		 */
		USER, //
		/**
		 * Client for hotel worker
		 */
		HOTEL, //
		/**
		 * Client for website manager
		 */
		MARKET, //
		/**
		 * Client for system manager
		 */
		MANAGE, //
		/**
		 * Server
		 */
		SERVER, //
		/**
		 * All
		 */
		ALL//
	}

	public Client[] value();
}
