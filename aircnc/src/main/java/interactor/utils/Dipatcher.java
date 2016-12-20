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
				alertFail(title, title + "网络错误！");
			else
				alertFail(title, title + "内部错误！");

			return null;
		}
	}
}
