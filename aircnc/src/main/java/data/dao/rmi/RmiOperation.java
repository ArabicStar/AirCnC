package data.dao.rmi;

import java.rmi.RemoteException;

@FunctionalInterface
public interface RmiOperation<T> {
	public T doSomething() throws RemoteException;
}
