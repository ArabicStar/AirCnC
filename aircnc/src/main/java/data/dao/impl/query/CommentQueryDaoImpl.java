package data.dao.impl.query;

import static data.hibernate.Hibernator.execute;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import data.dao.query.CommentQueryDao;
import po.order.comment.CommentPo;
import utils.info.hotel.HotelInfo;

public enum CommentQueryDaoImpl implements CommentQueryDao {
	INSTANCE;

	@Override
	public List<CommentPo> findByHotelId(int hotelId) {
		if (!HotelInfo.checkID(hotelId))
			return null;

		return execute(session -> {
			Criteria c = session.createCriteria(CommentPo.class);
			c.add(Restrictions.eq("hotel", hotelId));

			@SuppressWarnings("unchecked")
			List<CommentPo> list = (List<CommentPo>) c.list();
			return list;
		});
	}
}
