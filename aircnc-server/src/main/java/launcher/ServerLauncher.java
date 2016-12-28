package launcher;

import utils.proxy.AuthenticatePolicy.Client;

public class ServerLauncher {
	private static void launch(Client clientId) {
		LoggerLauncher.launch();
		HibernateLauncher.launch();
		RmiLauncher.launch();
		TimerTaskLauncher.launch();
	}

	public static void main(String[] args) {
		ServerLauncher.launch(ServerProperties.CLIENT_IDENTIFIER);
	}
}
