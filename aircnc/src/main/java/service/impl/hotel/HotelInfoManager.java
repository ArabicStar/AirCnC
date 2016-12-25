package service.impl.hotel;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.illegalArgEx;
import static utils.exception.StaticExceptionFactory.illegalStateException;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;
import static utils.exception.StaticExceptionFactory.unsupportedOpEx;

import java.util.List;
import java.util.Set;

import data.dao.hotel.HotelDao;
import po.hotel.HotelPo;
import po.hotel.HotelPoBuilder;
import po.member.MemberPo;
import po.member.MemberPoBuilder;
import service.hotel.HotelAccountService;
import service.hotel.HotelInfoService;
import service.promotion.HotelPromotionManagementService;
import service.query.CommentQueryService;
import utils.info.hotel.HotelInfo;
import vo.hotel.HotelVoBuilder;
import vo.order.comment.CommentVo;
import vo.promotion.PromotionVo;

public class HotelInfoManager implements HotelInfoService {

	/****** singleton ******/
	private static HotelInfoManager instance;

	/**
	 * Singleton instance initializer.<br>
	 * Parameters are daos or services depended on in some methods. They are
	 * alternative, <b>null</b> is allowed. In that case, when call methods
	 * which need the null dependency, {@code IllegalStateException} will be
	 * thrown to notify a unsupported operation is attempted.<br>
	 * Specific dependencies of each methods will be explained in methods'
	 * comments.<br>
	 * 
	 * @param hotelDao
	 *            dao hotel
	 * @param accountService
	 *            account service
	 * @param hotelQueryDao
	 *            hotel query dao
	 * @param orderQueryService
	 *            order query service
	 * @param hotelPromotionManagementService
	 *            hotel promotion management service
	 * @return initialized instance
	 * 
	 * @throws IllegalStateException
	 *             singleton has existed already <br>
	 */
	public static HotelInfoManager launch(final HotelDao hotelDao, final HotelAccountService accountService,
			final HotelPromotionManagementService promotionManagementService,
			final CommentQueryService commentQueryService) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelInfoManager(hotelDao, accountService, promotionManagementService,
				commentQueryService);
	}

	/**
	 * Get singleton instance.<br>
	 * 
	 * @return singleton instance
	 * @throws IllegalStateException
	 *             if singleton doesn't exist yet <br>
	 */
	public static HotelInfoManager getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	private HotelDao hotelDao;
	private HotelAccountService accountService;
	private HotelPromotionManagementService promotionManageService;
	private CommentQueryService commentQueryService;

	public HotelInfoManager(HotelDao hotelDao, HotelAccountService accountService,
			HotelPromotionManagementService promotionManagementService, CommentQueryService commentQueryService) {
		this.accountService = accountService;
		this.hotelDao = hotelDao;
		this.promotionManageService = promotionManagementService;
		this.commentQueryService = commentQueryService;
	}

	@Override
	public HotelInfo getHotelInfo(String name) {
		if (hotelDao == null)
			throw unsupportedOpEx("get hotel info");

		if (!HotelInfo.checkHotelName(name))
			throw illegalArgEx("Hotel name");

		final HotelPo po = hotelDao.findHotelByName(name);

		return po == null ? null : new HotelVoBuilder(po).getHotelInfo();
	}

	@Override
	public List<CommentVo> getHotelComment(int id) {
		if (commentQueryService == null)
			throw unsupportedOpEx("get hotel orders by status");
		return commentQueryService.getHotelComments(id);
	}

	@Override
	public Set<PromotionVo> getHotelActivePromotion(int id) {
		return promotionManageService.getHotelActivePromotion(id);
	}

	@Override
	public boolean updateInfo(HotelInfo modifiedInfo) {
		if (!accountService.isLogined())
			throw new IllegalStateException("No Hotel Login");

		HotelPo po = (HotelPo) accountService.getCurrentAccount();

		if (modifiedInfo.getId() != po.getId())
			throw new IllegalArgumentException("Incorresponding Hotel Info");

		return hotelDao
				.updateHotel(new HotelPoBuilder(modifiedInfo).setPasswordHash(po.getPasswordHash()).getHotelInfo());
	}
	
	@Override
	public boolean updatePassword(int oldPwdHash, int newPwdHash) {
		if (accountService == null || hotelDao == null)
			throw unsupportedOpEx("update password");

		if (!accountService.isLogined())
			throw illegalStateException("Not logged in yet");

		HotelPo po = (HotelPo) accountService.getCurrentAccount();

		if (po.getPasswordHash() != oldPwdHash)
			return false;

		return hotelDao.updateHotel(new HotelPoBuilder(po).setPasswordHash(newPwdHash).getHotelInfo());
	}
}
