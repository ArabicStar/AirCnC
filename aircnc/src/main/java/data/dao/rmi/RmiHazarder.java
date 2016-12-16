package data.dao.rmi;

import static utils.exception.StaticExceptionFactory.*;
import java.rmi.RemoteException;

public final class RmiHazarder {

	public static <T> T hazard(RmiOperation<T> op) {
		try {
			return op.doSomething();
		} catch (RemoteException e) {
			throw packedRmiEx(e);
		}
	}

	private RmiHazarder() {
	}
}
