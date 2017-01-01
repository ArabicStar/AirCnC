package data.dao.impl.query;

import static data.hibernate.Hibernator.execute;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import data.dao.query.CommentQueryDao;
import po.hotel.HotelPo;
import po.member.MemberPo;
import po.order.OrderPo;
import po.order.comment.CommentPo;
import utils.info.hotel.HotelInfo;

public enum CommentQueryDaoImpl implements CommentQueryDao {
	INSTANCE;

	@Override
	public List<CommentPo> findByHotelId(int hotelId) {
		if (!HotelInfo.checkID(hotelId))
			return null;

		return execute(session -> {
			HotelPo hotel = session.get(HotelPo.class, hotelId);

			Criteria c = session.createCriteria(CommentPo.class);
			c.add(Restrictions.eq("hotel", hotel));

			@SuppressWarnings("unchecked")
			List<CommentPo> list = (List<CommentPo>) c.list();
			
			for(CommentPo po:list){
				OrderPo order=(OrderPo)po.getRelOrder();
				order.setMember(session.get(MemberPo.class, order.getMemberId()));
			}
			return list;
		});
	}
}
