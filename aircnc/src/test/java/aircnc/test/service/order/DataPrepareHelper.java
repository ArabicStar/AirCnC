package aircnc.test.service.order;

import data.dao.impl.hotel.HotelDaoImpl;
import data.dao.impl.member.CreditDaoImpl;
import data.dao.impl.member.MemberDaoImpl;
import data.dao.impl.order.OrderDaoImpl;
import data.dao.impl.query.PromotionQueryDaoImpl;
import data.dao.member.CreditDao;
import data.dao.member.MemberDao;
import data.dao.order.OrderDao;
import data.dao.query.HotelQueryDao;
import data.dao.query.OrderQueryDao;
import data.dao.query.PromotionQueryDao;
import service.impl.member.MemberCreditManager;
import service.impl.member.MemberInfoManager;
import service.impl.order.OrderInfoManager;
import service.impl.order.OrderOperationManager;
import service.impl.promotion.HotelPromotionApplicationManager;
import service.impl.promotion.HotelPromotionInfoManager;
import service.impl.promotion.PromotionApplicationManager;
import service.impl.promotion.WebsitePromotionApplicationManager;
import service.impl.promotion.WebsitePromotionInfoManager;
import service.impl.query.HotelQueryManager;
import service.impl.query.OrderQueryManager;
import service.member.MemberCreditService;
import service.order.OrderInfoService;
import service.order.OrderOperationService;
import service.promotion.HotelPromotionApplicationService;
import service.promotion.HotelPromotionInfoService;
import service.promotion.PromotionApplicationService;
import service.promotion.WebsitePromotionApplicationService;
import service.promotion.WebsitePromotionInfoService;
import service.query.HotelQueryService;
import service.query.MemberQueryService;
import service.query.OrderQueryService;
import utils.info.member.MemberInfo;
import vo.hotel.HotelVo;
import vo.order.OrderVo;
import vo.order.OrderVoBuilder;

public class DataPrepareHelper {
	private static final MemberDao memberDao = MemberDaoImpl.INSTANCE;
	private static final CreditDao creditDao = CreditDaoImpl.INSTANCE;
	private static final HotelQueryDao hotelQueryDao = HotelDaoImpl.INSTANCE;
	private static final PromotionQueryDao promotionQueryDao = PromotionQueryDaoImpl.INSTANCE;

	private static final HotelPromotionInfoService hotelPromInfoService = HotelPromotionInfoManager
			.launch(promotionQueryDao);
	private static final WebsitePromotionInfoService websitePromInfoService = WebsitePromotionInfoManager
			.launch(promotionQueryDao);
	private static final HotelPromotionApplicationService hotelPromApp = HotelPromotionApplicationManager
			.launch(hotelPromInfoService);
	private static final WebsitePromotionApplicationService webPromApp = WebsitePromotionApplicationManager
			.launch(websitePromInfoService);

	private static final OrderDao orderDao = OrderDaoImpl.INSTANCE;
	private static final HotelQueryService hotelQuery = HotelQueryManager.launch(hotelQueryDao);
	private static final MemberQueryService memberQuery = MemberInfoManager.launch(memberDao, null, null, null);
	private static final MemberCreditService creditService = MemberCreditManager.launch(memberDao, creditDao);
	private static final PromotionApplicationService promApp = PromotionApplicationManager.launch(hotelPromApp,
			webPromApp);
	public static final OrderOperationService orderOperation = OrderOperationManager.launch(orderDao, hotelQuery,
			memberQuery, creditService, promApp);

	private static final OrderQueryDao orderQueryDao = OrderDaoImpl.INSTANCE;
	public static final OrderQueryService orderQueryService = OrderQueryManager.launch(orderQueryDao);
	public static final OrderInfoService orderInfoService = OrderInfoManager.launch(orderDao);

	private static HotelVo testHotel;
	private static MemberInfo testMember;
	private static OrderVo testOrder;

	public static final HotelVo getTestHotel() {
		if (testHotel != null)
			return testHotel;

		return testHotel = hotelQuery.findById(2);
	}

	public static final MemberInfo getTestMember() {
		if (testMember != null)
			return testMember;

		return testMember = memberQuery.searchById("44445555");
	}

	public static final OrderVo getTestOrder() {
		if (testOrder != null)
			return testOrder;

		return testOrder = new OrderVoBuilder(orderQueryDao.searchByMember("44445555").get(0)).getOrderInfo();
	}
	// public static final void foo() {
	// MemberPo po = new
	// MemberPoBuilder("Business").setEnterprise("NJU").setCredit(200).setId("44445555")
	// .setName("xiaoli").setPasswordHash(51).setContactInfo(new
	// ContactPoBuilder().getContactInfo())
	// .getMemberInfo();
	// memberDao.addMember(po);
	// }
}
