package launcher;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import utils.logger.Log;

public class LoggerLauncher {
	private LoggerLauncher() {
	}

	public static final void launch() {
		PropertyConfigurator.configure("log4j.properties");
		final Logger logger = Logger.getLogger("AirCnc Hotel Logger");
		Log.launchLogger(logger);
		Log.i("Logger launch succeed");
	}
}
