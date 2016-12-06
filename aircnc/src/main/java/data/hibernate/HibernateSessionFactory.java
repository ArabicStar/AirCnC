package data.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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
	 * For concurrent use<br>
	 */
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<>();
	/**
	 * ServiceRegistry instance;
	 */
	private static StandardServiceRegistry serviceRegistry;
	/**
	 * SessionFactory instance<br>
	 */
	private static SessionFactory factory;

	private static String configFile = CONFIG_FILE_LOCATION;
	/* Default initialize */
	static {
		try {
			serviceRegistry = new StandardServiceRegistryBuilder().configure(configFile).build();
			factory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		} catch (Exception ex) {
			StandardServiceRegistryBuilder.destroy(serviceRegistry);
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
			serviceRegistry = new StandardServiceRegistryBuilder().configure(configFile).build();
			factory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			StandardServiceRegistryBuilder.destroy(serviceRegistry);
			e.printStackTrace();
		}
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
	 * Get currently used SessionFactory.<br>
	 * 
	 * @return currently used SessionFactory instance<r>
	 */
	public static SessionFactory getSessionFactory() {
		return factory;
	}

	/**
	 * Get currently used StandardServiceRegistry.<br>
	 * 
	 * @return currently used StandardSerivceRegistry instance<br>
	 */
	public static StandardServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}
//
//	// test
//	public static void main(String[] args) {
//
//	}
}
