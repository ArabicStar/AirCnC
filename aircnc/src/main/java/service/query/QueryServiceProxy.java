package service.query;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.List;

import utils.condition.Condition;
import utils.info.member.MemberInfo;
import utils.info.order.OrderStatus;
import utils.proxy.AccessSecureProxy;
import utils.proxy.AuthenticatePolicy;
import utils.proxy.AuthenticatePolicy.Client;
import vo.hotel.HotelVo;
import vo.member.credit.CreditChangeVo;
import vo.order.OrderVo;
import vo.order.comment.CommentVo;

public class QueryServiceProxy extends AccessSecureProxy
		implements CommentQueryService, CreditQueryService, HotelQueryService, MemberQueryService, OrderQueryService {

	/* Singleton */
	private static QueryServiceProxy instance;

	public static QueryServiceProxy launch(Client clientId) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new QueryServiceProxy(clientId);
	}

	public static QueryServiceProxy getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* Singleton */

	private QueryServiceProxy(Client clientId) {
		super(clientId);
	}

	/*
	 ***********************************
	 ******* OrderQueryService*******
	 ***********************************
	 */
	private OrderQueryService order;

	@AuthenticatePolicy({ Client.ALL })
	public void loadOrderQueryService(OrderQueryService orderQueryService) {
		checkAuthentication();

		this.order = orderQueryService;
	}

	@Override
	@AuthenticatePolicy({ Client.USER, Client.MANAGE })
	public List<OrderVo> getMemberOrders(String memberId) {
		checkAuthentication();

		return order.getMemberOrders(memberId);
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL, Client.MANAGE })
	public List<OrderVo> getHotelOrders(int hotelId) {
		checkAuthentication();

		return order.getHotelOrders(hotelId);
	}

	@Override
	@AuthenticatePolicy({ Client.MARKET, Client.MANAGE })
	public List<OrderVo> getOrdersOfStatus(OrderStatus status) {
		checkAuthentication();

		return order.getOrdersOfStatus(status);
	}

	/*
	 **************************************
	 ******* MemberQueryService*******
	 **************************************
	 */
	private MemberQueryService member;

	@AuthenticatePolicy({ Client.MANAGE, Client.MARKET, Client.USER })
	public void loadMemberQueryService(MemberQueryService memberQueryService) {
		checkAuthentication();

		this.member = memberQueryService;
	}

	@Override
	@AuthenticatePolicy({ Client.USER, Client.MARKET, Client.MANAGE })
	public MemberInfo searchById(String id) {
		checkAuthentication();

		return member.searchById(id);
	}

	/*
	 ***********************************
	 ******* HotelQueryService*******
	 ***********************************
	 */
	private HotelQueryService hotel;

	@AuthenticatePolicy({ Client.USER, Client.MANAGE, Client.MARKET })
	public void loadHotelQueryService(HotelQueryService hotelQueryService) {
		checkAuthentication();

		this.hotel = hotelQueryService;
	}

	@Override
	@AuthenticatePolicy({ Client.USER, Client.MANAGE, Client.MARKET })
	public HotelVo findById(int hotelId) {
		checkAuthentication();

		return hotel.findById(hotelId);
	}

	@Override
	@AuthenticatePolicy({ Client.USER, Client.MANAGE })
	public HotelVo findByName(String name) {
		checkAuthentication();

		return hotel.findByName(name);
	}

	@Override
	@AuthenticatePolicy({ Client.USER })
	public List<HotelVo> findByCondition(Condition cond) {
		checkAuthentication();

		return hotel.findByCondition(cond);
	}

	/*
	 ***********************************
	 ******* CreditQueryService*******
	 ***********************************
	 */
	private CreditQueryService credit;

	@AuthenticatePolicy({ Client.USER, Client.MARKET, Client.MANAGE })
	public void loadCreditQueryService(CreditQueryService creditQueryService) {
		checkAuthentication();

		this.credit = creditQueryService;
	}

	@Override
	@AuthenticatePolicy({ Client.USER, Client.MARKET, Client.MANAGE })
	public int getMemberCredit(String memId) {
		checkAuthentication();

		return credit.getMemberCredit(memId);
	}

	@Override
	@AuthenticatePolicy({ Client.USER, Client.MANAGE })
	public List<CreditChangeVo> searchByMemberId(String memberId) {
		checkAuthentication();

		return credit.searchByMemberId(memberId);
	}

	/*
	 ***************************************
	 ******* CommentQueryService*******
	 ***************************************
	 */
	private CommentQueryService comment;

	@AuthenticatePolicy({ Client.USER, Client.HOTEL })
	public void loadCommentQueryService(CommentQueryService commentQueryService) {
		checkAuthentication();

		this.comment = commentQueryService;
	}

	@Override
	@AuthenticatePolicy({ Client.USER, Client.HOTEL })
	public List<CommentVo> getHotelComments(int hotelId) {
		checkAuthentication();

		return comment.getHotelComments(hotelId);
	}

}
