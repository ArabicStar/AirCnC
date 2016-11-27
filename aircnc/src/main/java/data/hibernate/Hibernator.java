package data.hibernate;

import static data.hibernate.HibernateSessionFactory.getSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public interface Hibernator {
	public static <R> R execute(HibernateOperation<R> op) {
		//System.out.println(1);
		// normal
		R result = null;
		Session session = getSession();

		Transaction ts = null;

		try {
			ts = session.beginTransaction();

			result = op.execute(session);

			ts.commit();

		} catch (HibernateException he) {
			// exception
			he.printStackTrace();

			if (ts != null)
				ts.rollback();

		} finally {
			// close
			session.close();
		}

		return result;
	}
}
