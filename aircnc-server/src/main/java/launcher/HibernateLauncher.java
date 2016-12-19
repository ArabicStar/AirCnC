package launcher;

import data.hibernate.HibernateSessionFactory;
import utils.logger.Log;

public class HibernateLauncher {
	public static final void launch() {
		// just urge JVM to load hibernate lib, and try it works or not.
		try {
			HibernateSessionFactory.getSessionFactory();
		} catch (Exception e) {
			Log.f("Hibernate service shut down.");
			System.exit(-1);
		}

		Log.d("Hiberbate launched");
	}

	private HibernateLauncher() {
	}
}
