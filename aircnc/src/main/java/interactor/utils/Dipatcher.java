package interactor.utils;

import static interactor.utils.AlertHelper.*;

import java.rmi.RemoteException;

@FunctionalInterface
public interface Dipatcher<Handler, rType> {
	public rType dipatch()throws Exception;

	public static <Handler, rType> rType execute(String title, Dipatcher<Handler, rType> d) {
		try {
			return d.dipatch();
		} catch (Exception e) {
			e.printStackTrace();
			
			if (e.getCause() != null && e.getCause() instanceof RemoteException)
				alertFail(title, title + "Failed - Network Error, please check you network");
			else
				alertFail(title, title + "Internal Error");

			return null;
		}
	}
}
