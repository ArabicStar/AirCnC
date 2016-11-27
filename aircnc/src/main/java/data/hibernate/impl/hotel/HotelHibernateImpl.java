package data.hibernate.impl.hotel;

import static data.hibernate.HibernateSessionFactory.getSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import data.hibernate.HotelHibernator;
import po.hotel.HotelPo;

/**
 * Implement of HotelHibernator interface<br>
 * 
 * @see data.hibernate.hotel.HotelHibernator
 * 
 * @author jqwu
 *
 */
public class HotelHibernateImpl implements HotelHibernator{

	@Override
	public HotelPo findHotel(int id) {
		Session session = getSession();

		HotelPo hotel = null;
		Transaction ts = null;

		try {
			// normal
			ts = session.beginTransaction();

			hotel = (HotelPo) session.get(HotelPo.class, id);

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

		return hotel;
	}

	@Override
	public boolean deleteHotel(int id) {
		boolean flag = false;
		Session session = getSession();

		Transaction ts = null;
		HotelPo deleted = null;

		try {
			// nomarl
			ts = session.beginTransaction();

			deleted = (HotelPo) session.get(HotelPo.class, id);
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
	public boolean updateHotel(HotelPo po) {
		boolean flag = false;
		Session session = getSession();

		Transaction ts = null;
		HotelPo hotel = null;

		try {
			// normal
			ts = session.beginTransaction();

			hotel = (HotelPo) session.get(HotelPo.class, po.getId());

			if (!(flag = (hotel == null)))
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
	public boolean addHotel(HotelPo po) {
		boolean flag = false;
		Session session = getSession();

		Transaction ts = null;
		try {
			// normal
			ts = session.beginTransaction();

			if (!(flag = session.contains(po))) {
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
		HotelPo tmp = null;
		try {
			// normal
			ts = session.beginTransaction();

			tmp = (HotelPo) session.get(HotelPo.class, id);

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
