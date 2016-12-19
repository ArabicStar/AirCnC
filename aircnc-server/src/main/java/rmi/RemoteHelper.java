package rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import utils.logger.Log;

public enum RemoteHelper {
	INSTANCE;
	private static final int PORT = 8888;
	private static final String URL_HEADER = new StringBuilder("rmi://localhost:").append(PORT).append("/").toString();
	static {
		try {
			LocateRegistry.createRegistry(PORT);
		} catch (RemoteException e) {
			// Fatal error, exit forcely
			Log.f("FATAL - Rmi bind failed, system exits", e);
			System.exit(1);
		}
	}

	public static final void bindRemoteObj(String objName, UnicastRemoteObject obj) {
		String url = new StringBuilder(URL_HEADER).append(objName).toString();
		try {
			Naming.bind(url, obj);
		} catch (MalformedURLException | RemoteException | AlreadyBoundException e) {
			// Fatal error, exit forcely
			Log.f("FATAL - Rmi bind failed, system exits", e);
		}
	}
}
