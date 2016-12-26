package service.impl.hotel;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.illegalArgEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;
import static utils.exception.StaticExceptionFactory.unsupportedOpEx;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import data.dao.hotel.HotelDao;
import po.hotel.HotelPo;
import po.hotel.HotelPoBuilder;
import service.hotel.HotelAccountService;
import service.hotel.HotelOrderService;
import service.order.OrderOperationService;
import service.query.OrderQueryService;
import utils.info.hotel.HotelInfo;
import utils.info.hotel.Room;
import utils.info.hotel.RoomBuilder;
import utils.info.order.OrderInfo;
import utils.info.order.OrderStatus;
import vo.order.OrderVo;

public class HotelOrderManager implements HotelOrderService{
	/****** singleton ******/
	private static HotelOrderManager instance;

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
	public static HotelOrderManager launch(final HotelDao hotelDao,final HotelAccountService accountService,
			final OrderQueryService orderQueryService,final OrderOperationService orderOperationService) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelOrderManager(hotelDao, accountService, orderQueryService,
				orderOperationService);
	}

	/**
	 * Get singleton instance.<br>
	 * 
	 * @return singleton instance
	 * @throws IllegalStateException
	 *             if singleton doesn't exist yet <br>
	 */
	public static HotelOrderManager getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	private HotelDao hotelDao;
	private HotelAccountService accountService;
	private OrderQueryService orderQueryService;
	private OrderOperationService orderOperationService;


	public HotelOrderManager(HotelDao hotelDao,HotelAccountService accountService,
			OrderQueryService orderQueryService,OrderOperationService orderOperationService) {
		this.accountService = accountService;
		this.hotelDao = hotelDao;
		this.orderQueryService = orderQueryService;
		this.orderOperationService = orderOperationService;

	}
	
	@Override
	public boolean executeOrder(OrderInfo order) {
		if (!accountService.isLogined())
			throw new IllegalStateException("No Hotel Login");

		HotelPo po = (HotelPo) accountService.getCurrentAccount();

		if (order.getHotel().getId() != po.getId())
			throw new IllegalArgumentException("Incorresponding Hotel");
		
		Room room = po.getRooms().stream().filter(r->r.getName().equals(order.getRoomType())).iterator().next();
		if((room.getRoomNum()-order.getRoomNumber())<0||
				(!orderOperationService.executeOrder(order).isValid()))
			return false;
	
		room = new RoomBuilder(room).setRoomNum(room.getRoomNum()-order.getRoomNumber()).getRoomInfo();
		Set<Room> rooms = new HashSet<Room>();
		rooms.add(room);
		po.setRooms(rooms);
		
		return hotelDao.updateHotel(new HotelPoBuilder(po).getHotelInfo());
	}

	@Override
	public boolean delayOrder(OrderInfo order) {
		if (!accountService.isLogined())
			throw new IllegalStateException("No Hotel Login");

		HotelPo po = (HotelPo) accountService.getCurrentAccount();

		if (order.getHotel().getId() != po.getId())
			throw new IllegalArgumentException("Incorresponding Hotel");
		
		Room room = po.getRooms().stream().filter(r->r.getName().equals(order.getRoomType())).iterator().next();
		if((room.getRoomNum()-order.getRoomNumber())<0||
				(!orderOperationService.delayOrder(order).isValid()))
			return false;
	
		room = new RoomBuilder(room).setRoomNum(room.getRoomNum()-order.getRoomNumber()).getRoomInfo();
		Set<Room> rooms = new HashSet<Room>();
		rooms.add(room);
		po.setRooms(rooms);
		
		return hotelDao.updateHotel(new HotelPoBuilder(po).getHotelInfo());
		
		
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


}
