package launcher;

import utils.proxy.AuthenticatePolicy.Client;

public abstract class ClientLauncher {
	private static final void launch(Client clientId) {
		LoggerLauncher.launch();
		RmiLauncher.launch();
		DaoLauncher.launch(clientId);
		ServiceLauncher.launch(clientId);
		InteractorLauncher.launch();
		UILauncher.launch();
	}

	public static void main(String[] args) {
		ClientLauncher.launch(ClientProperties.CLIENT_IDENTIFIER);
	}
}
