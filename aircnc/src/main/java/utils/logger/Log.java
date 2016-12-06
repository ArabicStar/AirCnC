package utils.logger;

import org.apache.log4j.Logger;

public class Log {
	private static Logger logger;

	private Log() {
	}

	public static final void launchLogger(final Logger logger) {
		if (Log.logger == null && logger != null)
			Log.logger = logger;
	}

	public static final void i(String msg) {
		logger.info(msg);
	}

	public static final void d(String msg) {
		logger.debug(msg);
	}

	public static final void w(String msg) {
		logger.warn(msg);
	}

	public static final void e(String msg) {
		logger.error(msg);
	}

	public static final void e(String msg, Throwable t) {
		logger.error(msg, t);
	}

	public static final void f(String msg) {
		logger.fatal(msg);
	}

	public static final void f(String msg, Throwable t) {
		logger.fatal(msg, t);
	}

}
