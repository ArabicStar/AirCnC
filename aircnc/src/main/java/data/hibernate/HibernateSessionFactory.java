package data.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

/**
 * Helper for Hibernate Session, to auto open session and assure concurrent
 * safety<br>
 * 
 * @author ClevelandAlto
 *
 */

public class HibernateSessionFactory {
	/**
	 * Default config file path<br>
	 */
	private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";
	/**
	 * For concurrent<br>
	 */
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<>();
	/**
	 * Configuration instance<br>
	 */
	private static final Configuration configuration = new Configuration();
	/**
	 * SessionFactory instance<br>
	 */
	private static SessionFactory factory;

	private static String configFile = CONFIG_FILE_LOCATION;
	static {
		// Default initialization
		try {
			configuration.configure(configFile);
			factory = configuration.buildSessionFactory();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private HibernateSessionFactory() {
	}

	/**
	 * Get a session instance. Concurrent safe.<br>
	 * 
	 * @return opened session
	 */
	public static Session getSession() {
		Session session = threadLocal.get();

		if (session == null || !session.isOpen()) {
			if (factory == null)
				rebuildSessionFactory();

			session = (factory != null) ? factory.openSession() : null;
			threadLocal.set(session);
		}
		return session;
	}

	/**
	 * Reconfigure the factory<r>
	 */
	public static void rebuildSessionFactory() {
		try {
			configuration.configure(configFile);
			factory = configuration.buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get currently used SessionFactory<br>
	 * 
	 * @return currently used SessionFactoryinstance<r>
	 */
	public static SessionFactory getSessionFactory() {
		return factory;
	}

	/**
	 * Customize config file.<br>
	 * <i>rebuildSessionFactory()</i> should be called before reuse.<br>
	 * 
	 * @param configFile
	 */
	public static void serConfigFile(String configFile) {
		HibernateSessionFactory.configFile = configFile;
		factory = null;
	}

	/**
	 * Get currently used Configuration<br>
	 * 
	 * @return currently used Configuration instance<br>
	 */
	public static Configuration getConfiguration() {
		return configuration;
	}

	// test
	// public static void main(String[] args) {
	// }
}
