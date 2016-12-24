package aircnc.test.service.order;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import service.order.OrderInfoService;
import service.order.OrderOperationService;
import service.query.OrderQueryService;
import utils.info.member.MemberInfo;
import utils.info.order.OrderInfo;
import vo.hotel.HotelVo;
import vo.order.OrderVo;

public class OrderOperationServiceTest {
	private OrderOperationService service = DataPrepareHelper.orderOperation;
	private HotelVo testHotel = DataPrepareHelper.getTestHotel();
	private MemberInfo testMember = DataPrepareHelper.getTestMember();
	private OrderVo testOrder = DataPrepareHelper.getTestOrder();
	private OrderQueryService orderQuery = DataPrepareHelper.orderQueryService;
	private OrderInfoService orderInfo = DataPrepareHelper.orderInfoService;

	@Before
	public void setUp() throws Exception {
		// PromotionVoBuilder b = new PromotionVoBuilder(Scope.Hotel);
		// b.when(HotelWhen.ENTERPRISE).setParam(TriggerParams.ENTERPRISE,
		// "NJU");
		// b.how(How.CONST).setParam(ApplierParams.AMOUNT, 30.0);
		// PromotionVo vo1 = b.setHotelId(1).getPromotionInfo();
		//
		// b = new PromotionVoBuilder(Scope.Website);
		// b.when(WebsiteWhen.LEVEL).setParam(TriggerParams.LEVEL_THRESHOLD, 3);
		// b.how(How.CONST).setParam(ApplierParams.AMOUNT, 20.0);
		// PromotionVo vo2 = b.getPromotionInfo();
		//
		// PromotionPo po1 = new
		// PromotionPoBuilder(vo1).setId(2).getPromotionInfo();
		// PromotionPo po2 = new
		// PromotionPoBuilder(vo2).setId(2).getPromotionInfo();
		//
		// po1 = hotelPromotionDao.findHotelPromotion(3);
		// po2 = webPromotionDao.findWebsitePromotion(3);
		//
		// vo1 = new PromotionVoBuilder(po1).getPromotionInfo();
		// vo2 = new PromotionVoBuilder(po2).getPromotionInfo();
		// System.out.println();
		// hotelPromotionDao.addHotelPromotion((HotelPromotionPo) po1);
		// webPromotionDao.addWebsitePromotion((WebsitePromotionPo) po2);
		// hotelPromotionDao.updateHotelPromotion((HotelPromotionPo) po1);
		// webPromotionDao.updateWebsitePromotion((WebsitePromotionPo) po2);
		// hot = new
		// HotelPoBuilder().setName("tiantian").setStar(2).setPasswordHash(51).getHotelInfo();
		// hotelDao.addHotel(hot);
		// MemberPo m = new
		// MemberPoBuilder("Personal").setName("zz").setPasswordHash(123).setCredit(100).setId("87654321")
		// .setContactInfo(new
		// ContactPoBuilder().getContactInfo()).setBirthday(LocalDate.ofYearDay(1998,
		// 69))
		// .getMemberInfo();
		// // memberDao.addMember(m);
		// MemberPo m = memberDao.findMember("87654321");
		// // System.out.println(m);
		// HotelPo hot = hotelDao.findHotelById(1);
		//
		// HotelPromotionPo hp = hotelPromotionDao.findHotelPromotion(3);
		// WebsitePromotionPo wp = webPromotionDao.findWebsitePromotion(3);
		// Set<PromotionPo> ps = new HashSet<>();
		// ps.add(hp);
		// ps.add(wp);
		// // hot = new HotelPoBuilder(hot).setPasswordHash(123).getHotelInfo();
		// OrderPo order = new
		// OrderPoBuilder().setMember(m).setHotel(hot).setPeopleNumber(1).setHasChildren(false)
		// .setRoomType("single").setRoomNumber(1).setOriginalPrice(100).setDiscountPrice(80)
		// .setEntryTime(LocalDateTime.now().plusDays(1)).setLastTime(LocalDateTime.now().plusDays(2))
		// .setPromotions(ps).setStatus(OrderStatus.EXECUTED).setOrderId("2016122300010002").setStayDays(3)
		// .setAppeal("fuck").getOrderInfo();
		// CommentPo c = new
		// CommentPoBuilder(order).setCommentTime(LocalDateTime.now()).setGrade(3).setContent("123")
		// .getCommentInfo();
		// order.setComments(c);
		// // orderDao.addOrder(order);
		// orderDao.updateOrder(order);
		// System.err.println(order);
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testMakeOrder() {
		OrderInfo info = orderInfo.findOrder("2016122400020000");
		System.out.println(info);
		// OrderVo order = new
		// OrderVoBuilder().setMember(testMember).setHotel(testHotel).setPeopleNumber(1)
		// .setHasChildren(false).setRoomType("单人间").setRoomNumber(1).setOriginalPrice(100)
		// .setEntryTime(LocalDateTime.now().plusDays(1)).setLastTime(LocalDateTime.now().plusDays(2))
		// .setStatus(OrderStatus.UNEXECUTED).setStayDays(3).getOrderInfo();
		// OrderInfo o = service.makeOrder(order);
		// System.out.println(o);
	}

	@Test
	public void testCancelOrder() {
		// service.cancelOrder(testOrder);
	}

	@Test
	public void testExecuteOrder() {
		// service.executeOrder(testOrder);
	}

	@Test
	public void testDelayOrder() {
		// service.delayOrder(testOrder);
	}

	@Test
	public void testOverdueOrder() {
		// service.overdueOrder(testOrder);
	}

	@Test
	public void testAppealOrder() {
		// OrderVo vo = new
		// OrderVoBuilder(testOrder).setAppeal("FUCK").getOrderInfo();
		// service.appealOrder(vo);
	}

	@Test
	public void testCommentOrder() {
		// CommentVo cv = new
		// CommentVoBuilder(testOrder).setCommentTime(LocalDateTime.now()).setContent("123").setGrade(4)
		// .getCommentInfo();
		// OrderVo vo = new
		// OrderVoBuilder(testOrder).setComment(cv).getOrderInfo();
		// service.commentOrder(vo);
	}

	@Test
	public void testApproveAppeal() {
		// OrderVo vo = new
		// OrderVoBuilder(testOrder).setAppeal("FUCK").getOrderInfo();
		// System.out.println(service.approveAppeal(vo));
	}

}
