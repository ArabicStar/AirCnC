package launcher;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import utils.logger.Log;

public class LoggerLauncher {
	private LoggerLauncher() {
	}

	public static final void launch() {
		boolean flag = true;
		try {
			PropertyConfigurator.configure("log4j.properties");
		} catch (Exception e) {
			flag = false;
		}
		final Logger logger = Logger.getLogger("AirCnc Client Logger");
		Log.launchLogger(logger);
		Log.i(flag ? "Logger launched" : "Logger launch failed");
	}
}
