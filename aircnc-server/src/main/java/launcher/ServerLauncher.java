package launcher;

import plugins.LevelCalc;
import utils.proxy.AuthenticatePolicy.Client;

public class ServerLauncher {
	private static void launch(Client clientId) {
		LoggerLauncher.launch();
		HibernateLauncher.launch();
		LevelCalc.refresh();
		RmiLauncher.launch();
		TimerTaskLauncher.launch();
	}

	public static void main(String[] args) {
		ServerLauncher.launch(ServerProperties.CLIENT_IDENTIFIER);
	}
}
