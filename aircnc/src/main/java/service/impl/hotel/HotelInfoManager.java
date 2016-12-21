package service.impl.hotel;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.illegalArgEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;
import static utils.exception.StaticExceptionFactory.unsupportedOpEx;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.criterion.DetachedCriteria;

import data.dao.hotel.HotelDao;
import data.dao.query.HotelQueryDao;
import po.hotel.HotelPo;
import po.hotel.HotelPoBuilder;
import service.hotel.HotelAccountService;
import service.hotel.HotelInfoService;
import service.promotion.HotelPromotionManagementService;
import service.query.CommentQueryService;
import service.query.HotelQueryService;
import service.query.OrderQueryService;
import utils.condition.Condition;
import utils.condition.ConditionBuilder;
import utils.info.hotel.HotelInfo;
import utils.info.member.MemberInfo;
import utils.info.order.OrderStatus;
import vo.hotel.HotelVo;
import vo.hotel.HotelVoBuilder;
import vo.order.OrderVo;
import vo.order.comment.CommentVo;
import vo.promotion.PromotionVo;

public class HotelInfoManager implements HotelInfoService ,HotelQueryService{
	
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
	public static HotelInfoManager launch(final HotelDao hotelDao, final HotelQueryDao hotelQueryDao,final HotelAccountService accountService,
			final OrderQueryService orderQueryService,final HotelPromotionManagementService promotionManagementService,
			CommentQueryService commentQueryService) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelInfoManager(hotelDao, hotelQueryDao,accountService, orderQueryService,
				promotionManagementService,commentQueryService);
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
	private HotelQueryDao hotelQueryDao;
	private HotelAccountService accountService;
	private OrderQueryService orderQueryService;
	private HotelPromotionManagementService promotionManageService;
	private CommentQueryService commentQueryService;


	public HotelInfoManager(HotelDao hotelDao, HotelQueryDao hotelQueryDao,HotelAccountService accountService,
			OrderQueryService orderQueryService,HotelPromotionManagementService promotionManagementService,
			CommentQueryService commentQueryService) {
		this.accountService = accountService;
		this.hotelDao = hotelDao;
		this.promotionManageService = promotionManagementService;
		this.orderQueryService = orderQueryService;
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
	
	/* Buffered member order query service */
	private int bufferedId = Integer.MIN_VALUE;
	private List<OrderVo> bufferedOrderList;

	@Override
	public List<OrderVo> getHotelAllOrders(final int id) {
		if (orderQueryService == null)
			throw unsupportedOpEx("get hotel orders");

		if (!HotelInfo.checkID(id))
			throw illegalArgEx("Hotel id");

		/* different id from buffered one */
		if (bufferedId == Integer.MIN_VALUE || bufferedId!=id) {
			// get
			List<OrderVo> res = orderQueryService.getHotelOrders(id);

			// given id not exists, return
			if (res == null)
				return null;

			// exists, refresh buffer
			bufferedId = id;
			bufferedOrderList = res;

			return bufferedOrderList;
		}

		return bufferedOrderList = orderQueryService.getHotelOrders(id);
	}

	@Override
	public List<OrderVo> getHotelOrdersByStatus(int id, OrderStatus status)  {

		if (orderQueryService == null)
			throw unsupportedOpEx("get hotel orders by status");

		return bufferedOrderList.stream().filter(o -> o.getStatus() == status).collect(Collectors.toList());
	}

	@Override
	public List<CommentVo> getHotelComment(int id) {
		if (orderQueryService == null)
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
	public HotelVo findById(int hotelId) {
		HotelPo po = hotelDao.findHotelById(hotelId);
		
		return po == null ? null : new HotelVoBuilder(po).getHotelInfo();
	}

	@Override
	public HotelVo findByName(String name) {
		HotelPo po = hotelDao.findHotelByName(name);
		return po == null ? null : new HotelVoBuilder(po).getHotelInfo();
	}

	@Override
	public List<HotelVo> findByCondition(Condition cond) {
		DetachedCriteria dc = DetachedCriteria.forClass(HotelPo.class);
		dc = ConditionBuilder.parseCondition(dc, cond);
				
		hotelQueryDao.searchByCriteria(dc);
		return null;
	}

	@Override
	public Set<PromotionVo> getHotelAllPromotions(int hotelId) {
		return promotionManageService.getHotelAllPromotions(hotelId);
	}



}
