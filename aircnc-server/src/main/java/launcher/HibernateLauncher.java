package launcher;

import data.hibernate.HibernateSessionFactory;
import utils.logger.Log;

public class HibernateLauncher {
	public static final void launch() {
		// just urge JVM to load hibernate lib, and try it works or not.
		HibernateSessionFactory.getSessionFactory();
		Log.i("Hiberbate has been launched successfuly.");
	}

	private HibernateLauncher() {
	}
}
