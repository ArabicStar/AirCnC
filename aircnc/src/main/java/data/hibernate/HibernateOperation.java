package data.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;

@FunctionalInterface
public interface HibernateOperation<R> {
	public R execute(Session session) throws HibernateException;
}
