package rmi.remote;

import static utils.exception.StaticExceptionFactory.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Set;

import data.dao.impl.member.CreditDaoImpl;
import data.dao.impl.query.CommentQueryDaoImpl;
import data.dao.impl.query.PromotionQueryDaoImpl;
import data.dao.query.CommentQueryDao;
import data.dao.query.CreditQueryDao;
import data.dao.query.PromotionQueryDao;
import data.dao.rmi.query.RemoteCommentQueryDao;
import data.dao.rmi.query.RemoteCreditQueryDao;
import data.dao.rmi.query.RemotePromotionQueryDao;
import po.member.credit.CreditChangePo;
import po.order.comment.CommentPo;
import po.promotion.PromotionPo;
import rmi.RemoteHelper;

public class QueryDaoRemoteObj extends UnicastRemoteObject implements RemoteCreditQueryDao
, RemotePromotionQueryDao, RemoteCommentQueryDao {
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
		
		instance = new QueryDaoRemoteObj(creditQuery, promotionQuery, commentQuery);

		RemoteHelper.bindRemoteObj("RemoteQueryDao", instance);
	}

	/* Singleton */

	/**
	 * @param creditQuery
	 * @param promotionQuery
	 * @throws RemoteException
	 */
	private QueryDaoRemoteObj(CreditQueryDao creditQuery, PromotionQueryDao promotionQuery
			, CommentQueryDao commentQueryDao) throws RemoteException {
		super();
		this.creditQuery = creditQuery;
		this.promotionQuery = promotionQuery;
		
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

}
