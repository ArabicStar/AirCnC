package rmi.remote;

import static utils.exception.StaticExceptionFactory.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Set;

import data.dao.impl.member.CreditDaoImpl;
import data.dao.impl.order.OrderDaoImpl;
import data.dao.impl.query.CommentQueryDaoImpl;
import data.dao.impl.query.PromotionQueryDaoImpl;
import data.dao.query.CommentQueryDao;
import data.dao.query.CreditQueryDao;
import data.dao.query.OrderQueryDao;
import data.dao.query.PromotionQueryDao;
import data.dao.rmi.query.RemoteCommentQueryDao;
import data.dao.rmi.query.RemoteCreditQueryDao;
import data.dao.rmi.query.RemoteOrderQueryDao;
import data.dao.rmi.query.RemotePromotionQueryDao;
import po.member.credit.CreditChangePo;
import po.order.OrderPo;
import po.order.comment.CommentPo;
import po.promotion.PromotionPo;
import rmi.RemoteHelper;
import utils.info.order.OrderStatus;

public class QueryDaoRemoteObj extends UnicastRemoteObject
		implements RemoteCreditQueryDao, RemotePromotionQueryDao, RemoteCommentQueryDao, RemoteOrderQueryDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 828312069380719877L;
	/* Singleton */
	/**
	 * Singleton instance
	 */
	private static QueryDaoRemoteObj instance;

	public static final void launch() throws RemoteException {
		if (instance != null)
			throw duplicateSingletonEx();

		final CreditQueryDao creditQuery = CreditDaoImpl.INSTANCE;
		final PromotionQueryDao promotionQuery = PromotionQueryDaoImpl.INSTANCE;
		final CommentQueryDao commentQuery = CommentQueryDaoImpl.INSTANCE;
		final OrderQueryDao orderQuery = OrderDaoImpl.INSTANCE;

		instance = new QueryDaoRemoteObj(creditQuery, promotionQuery, commentQuery, orderQuery);

		RemoteHelper.bindRemoteObj("RemoteQueryDao", instance);
	}

	/* Singleton */

	/**
	 * @param creditQuery
	 * @param promotionQuery
	 * @param commentQuery
	 * @param orderQuery
	 * @throws RemoteException
	 */
	private QueryDaoRemoteObj(CreditQueryDao creditQuery, PromotionQueryDao promotionQuery,
			CommentQueryDao commentQuery, OrderQueryDao orderQuery) throws RemoteException {
		super();
		this.creditQuery = creditQuery;
		this.promotionQuery = promotionQuery;
		this.commentQuery = commentQuery;
		this.orderQuery = orderQuery;
	}

	/*
	 ********************************
	 ******* CreditQueryDao*******
	 ********************************
	 */
	private CreditQueryDao creditQuery;

	@Override
	public List<CreditChangePo> searchByMemberId(String memberId) throws RemoteException {
		return creditQuery.searchByMemberId(memberId);
	}

	@Override
	public int getMemberCredit(String memberId) throws RemoteException {
		return creditQuery.getMemberCredit(memberId);
	}

	/*
	 ************************************
	 ******* PromotionQueryDao*******
	 ************************************
	 */
	private PromotionQueryDao promotionQuery;

	@Override
	public Set<PromotionPo> getHotelAllPromotions(int hotelId) throws RemoteException {
		return promotionQuery.getHotelAllPromotions(hotelId);
	}

	@Override
	public Set<PromotionPo> getWebsiteAllPromotions() throws RemoteException {
		return promotionQuery.getWebsiteAllPromotions();
	}

	@Override
	public PromotionPo getHotelPromotion(long id) throws RemoteException {
		return promotionQuery.getHotelPromotion(id);
	}

	@Override
	public PromotionPo getWebsitePromotion(long id) throws RemoteException {
		return promotionQuery.getWebsitePromotion(id);
	}

	/*
	 ************************************
	 ******* CommentQueryDao*******
	 ************************************
	 */
	private CommentQueryDao commentQuery;

	@Override
	public List<CommentPo> findByHotelId(int hotelId) throws RemoteException {
		return this.commentQuery.findByHotelId(hotelId);
	}

	/*
	 *********************************
	 ******* OrderQueryDao*******
	 *********************************
	 */
	private OrderQueryDao orderQuery;

	@Override
	public List<OrderPo> searchByMember(String memberId) throws RemoteException {
		return orderQuery.searchByMember(memberId);
	}

	@Override
	public List<OrderPo> searchByHotel(int hotelId) throws RemoteException {
		return orderQuery.searchByHotel(hotelId);
	}

	@Override
	public List<OrderPo> searchByStatus(OrderStatus status) throws RemoteException {
		return orderQuery.searchByStatus(status);
	}

}
