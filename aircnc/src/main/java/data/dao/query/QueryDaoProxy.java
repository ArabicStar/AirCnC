package data.dao.query;

import static data.dao.rmi.RmiHazarder.hazard;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;

import data.dao.rmi.query.RemoteCommentQueryDao;
import data.dao.rmi.query.RemoteCreditQueryDao;
import data.dao.rmi.query.RemoteHotelQueryDao;
import data.dao.rmi.query.RemoteOrderQueryDao;
import data.dao.rmi.query.RemotePromotionQueryDao;
import po.hotel.HotelPo;
import po.member.credit.CreditChangePo;
import po.order.OrderPo;
import po.order.comment.CommentPo;
import po.promotion.PromotionPo;
import utils.info.order.OrderStatus;

public final class QueryDaoProxy implements CreditQueryDao, OrderQueryDao, PromotionQueryDao, HotelQueryDao, CommentQueryDao {
	/* Singleton */
	private static QueryDaoProxy instance;

	public static QueryDaoProxy launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new QueryDaoProxy();
	}

	public static QueryDaoProxy getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* Singleton */

	private QueryDaoProxy() {

	}

	/*
	 *******************************
	 ******* CreditQueryDao*******
	 *******************************
	 */
	private RemoteCreditQueryDao remoteCreditQueryDao;

	public void loadRemoteCreditQueryDao(RemoteCreditQueryDao remoteCreditQueryDao) {
		this.remoteCreditQueryDao = remoteCreditQueryDao;
	}

	@Override
	public List<CreditChangePo> searchByMemberId(String memberId) {

		return hazard(() -> {
			return remoteCreditQueryDao.searchByMemberId(memberId);
		});
	}

	@Override
	public int getMemberCredit(String memberId) {

		return hazard(() -> {
			return remoteCreditQueryDao.getMemberCredit(memberId);
		});
	}

	/*
	 *******************************
	 ******* OrderQueryDao*******
	 *******************************
	 */
	private RemoteOrderQueryDao remoteOrderQueryDao;

	public void loadRemoteOrderQueryDao(RemoteOrderQueryDao remoteOrderQueryDao) {

		this.remoteOrderQueryDao = remoteOrderQueryDao;
	}

	@Override
	public List<OrderPo> searchByMember(String memberId) {

		return hazard(() -> remoteOrderQueryDao.searchByMember(memberId));
	}

	@Override
	public List<OrderPo> searchByHotel(int hotelId) {
		return hazard(() -> remoteOrderQueryDao.searchByHotel(hotelId));
	}

	@Override
	public List<OrderPo> searchByStatus(OrderStatus status) {
		return hazard(() -> remoteOrderQueryDao.searchByStatus(status));
	}

	/*
	 ************************************
	 ******* PromotionQueryDao*******
	 ************************************
	 */
	private RemotePromotionQueryDao remotePromotionQueryDao;

	public void loadRemotePromotionQueryDao(RemotePromotionQueryDao remotePromotionQueryDao) {
		this.remotePromotionQueryDao = remotePromotionQueryDao;
	}

	@Override
	public Set<PromotionPo> getHotelAllPromotions(int hotelId) {
		return hazard(() -> remotePromotionQueryDao.getHotelAllPromotions(hotelId));

	}

	@Override
	public Set<PromotionPo> getWebsiteAllPromotions() {
		return hazard(() -> remotePromotionQueryDao.getWebsiteAllPromotions());
	}

	@Override
	public PromotionPo getHotelPromotion(long id) {
		return hazard(() -> remotePromotionQueryDao.getHotelPromotion(id));
	}

	@Override
	public PromotionPo getWebsitePromotion(long id) {
		return hazard(() -> remotePromotionQueryDao.getWebsitePromotion(id));
	}

	/*
	 *******************************
	 ******* HotelQueryDao*******
	 *******************************
	 */
	private RemoteHotelQueryDao remoteHotelQueryDao;

	public void loadRemoteHotelQueryDao(RemoteHotelQueryDao remoteHotelQueryDao) {
		this.remoteHotelQueryDao = remoteHotelQueryDao;
	}

	@Override
	public HotelPo searchById(int hotelId) {
		return hazard(() -> remoteHotelQueryDao.searchById(hotelId));
	}

	@Override
	public HotelPo searchByName(String name) {
		return hazard(() -> remoteHotelQueryDao.searchByName(name));
	}

	@Override
	public List<HotelPo> searchByCriteria(DetachedCriteria dc) {
		return hazard(() -> remoteHotelQueryDao.searchByCriteria(dc));
	}
	
	/*
	 *******************************
	 ******* CommentQueryDao*******
	 *******************************
	 */
	private RemoteCommentQueryDao remoteCommentQueryDao;

	public void loadRemoteCommentQueryDao(RemoteCommentQueryDao remoteCommentQueryDao) {
		this.remoteCommentQueryDao = remoteCommentQueryDao;
	}

	@Override
	public List<CommentPo> findByHotelId(int hotelId){
		return hazard(() -> remoteCommentQueryDao.findByHotelId(hotelId));
	}
}
