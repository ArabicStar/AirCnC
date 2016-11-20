package data.hibernate.impl.member;

import static data.hibernate.HibernateSessionFactory.getSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import data.hibernate.MemberHibernator;
import po.member.MemberPo;

/**
 * Implemention of MemberHibernator interface<br>
 * 
 * @see data.hibernate.member.MemberHibernator
 * 
 * @author ClevelandAlto
 *
 */
public class MemberHibernatorImpl implements MemberHibernator {

	@Override
	public MemberPo findMember(int id) {
		Session session = getSession();

		MemberPo member = null;
		Transaction ts = null;

		try {
			// normal
			ts = session.beginTransaction();

			member = (MemberPo) session.get(MemberPo.class, id);

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

		return member;
	}

	@Override
	public boolean deleteMember(int id) {
		boolean flag = false;
		Session session = getSession();

		Transaction ts = null;
		MemberPo deleted = null;

		try {
			// nomarl
			ts = session.beginTransaction();

			deleted = (MemberPo) session.get(MemberPo.class, id);
			if (!(flag = (deleted == null)))
				session.delete(deleted);

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

		return flag;
	}

	@Override
	public boolean updateMember(MemberPo po) {
		boolean flag = false;
		Session session = getSession();

		Transaction ts = null;
		MemberPo member = null;

		try {
			// normal
			ts = session.beginTransaction();

			member = (MemberPo) session.get(MemberPo.class, po.getId());

			if (!(flag = (member == null)))
				session.update(po);

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

		return flag;
	}

	@Override
	public boolean addMember(MemberPo po) {
		boolean flag = false;
		Session session = getSession();

		Transaction ts = null;
		try {
			// normal
			ts = session.beginTransaction();

			if (!(flag = session.contains(po))) {
				session.save(po.getContact());
				session.save(po);
			}

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

		return !flag;
	}

	@Override
	public boolean existId(int id) {
		Session session = getSession();
		Transaction ts = null;
		MemberPo tmp = null;
		try {
			// normal
			ts = session.beginTransaction();

			tmp = (MemberPo) session.get(MemberPo.class, id);

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

		return tmp != null;
	}

}
