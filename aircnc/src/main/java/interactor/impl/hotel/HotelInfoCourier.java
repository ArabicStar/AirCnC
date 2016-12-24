package interactor.impl.hotel;

import static interactor.utils.AlertHelper.alertFail;
import static interactor.utils.AlertHelper.alertSuccess;
import static interactor.utils.Dipatcher.execute;
import static interactor.utils.TitleGetter.getTitle;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import interactor.hotel.HotelInfoInteractor;
import interactor.utils.Title;
import presentation.hotel.accessor.impl.InfoModifyAccessorImpl;
import presentation.hotel.manager.impl.HotelCommentManagerImpl;
import presentation.hotel.manager.impl.HotelRoomManagerImpl;
import presentation.hotel.manager.impl.InfoManagerImpl;
import presentation.member.accessor.impl.SupremeSearchAccessorImpl;
import presentation.member.manager.impl.SearchHotelManagerImpl;
import service.hotel.HotelAccountService;
import service.hotel.HotelInfoService;
import utils.info.hotel.HotelInfo;
import utils.info.hotel.Room;
import vo.hotel.HotelVo;
import vo.hotel.HotelVoBuilder;
import vo.order.comment.CommentVo;

public class HotelInfoCourier implements HotelInfoInteractor {
	private static HotelInfoInteractor instance;

	public static HotelInfoInteractor launch(HotelInfoService handler, HotelAccountService helper) {
		/* singleton */
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelInfoCourier(handler, helper);
	}

	public static HotelInfoInteractor getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	/* singleton */

	private HotelInfoService handler;
	private HotelAccountService helper;

	private HotelInfoCourier(HotelInfoService handler, HotelAccountService helper) {
		this.handler = handler;
		this.helper = helper;
	}
	
	@Override
	@Title("获取酒店信息")
	public void getHotelInfo() {
		String title = getTitle();

		HotelInfo info = execute(title, () -> {
			String name = getCurrentName();
			if (name != null)
				return handler.getHotelInfo(name);

			alertFail(title, "还没有登录哦");
			return null;
		});

		InfoManagerImpl.getInstance().setHotel(new HotelVoBuilder(info).getHotelInfo());
		InfoModifyAccessorImpl.getInstance().setHotel(new HotelVoBuilder(info).getHotelInfo());
		
	}

	@Override
	@Title("获取酒店评论")
	public void getHotelComments() {
		String title = getTitle();

		List<CommentVo> list = execute(title, () -> {
			int id = getCurrentId();
			if (id != Integer.MIN_VALUE)
				return handler.getHotelComment(id);

			alertFail(title, "还没有登录哦");
			return null;
		});

		HotelCommentManagerImpl.getInstance().setComment(list);
		
	}

	@Override
	@Title("获取酒店房间")
	public void getHotelRooms() {
		String title = getTitle();

		Set<Room> set = execute(title, () -> {
			String name = getCurrentName();
			if (name!=null)
				return handler.getHotelInfo(name).getRooms();

			alertFail(title, "还没有登录哦");
			return null;
		});

		HotelRoomManagerImpl.getInstance().setRooms(set);
		
	}

	@Override
	@Title("Update Hotel Passwords")
	public void updatePassword() {
//		String title = getTitle();
//
//		execute(title, () -> {
//			int id = getCurrentId();
//			if (id != Integer.MIN_VALUE)
//
//				if (!handler.updatePassword(InfoModifyAccessorImpl.getInstance().getOldPasswordHash(),
//						InfoModifyAccessorImpl.getInstance().getNewPasswordHash()))
//					alertFail(title, "Wrong password");
//				else
//					alertSuccess(title, "Update password succeed");
//			return null;
//		});
		
	}

	@Override
	@Title("修改酒店信息")
	public void updateHotel() {
		String title = getTitle();
		
		execute(title, () -> {
			int id = getCurrentId();
			if (id != Integer.MIN_VALUE)

				if (!handler.updateInfo(InfoModifyAccessorImpl.getInstance().getModifyHotelInfo()))
					alertFail(title, "修改失败");
				else
					alertSuccess(title, "修改成功");
			return null;
		});
		
	}
	
	@Override
	@Title("搜索酒店")
	public void getHotelsByCondition() {
		String title = getTitle();
		List<HotelVo> hotels = execute(title,()->{
			List<HotelVo> list = handler.findByCondition(SupremeSearchAccessorImpl.getInstance().getCondition());
			if(list==null||list.isEmpty()){
				alertFail(title, "没有符合条件的酒店");
				return null;
			}else{
				return list;
			}
		});
				
		SearchHotelManagerImpl.getInstance().setHotel(hotels);
		
	}
	
	
	private String getCurrentName() {
		HotelInfo curAcc = helper.getCurrentAccount();
		return curAcc == null ? null : StringUtils.deleteWhitespace(curAcc.getName());
	}
	
	private int getCurrentId() {
		HotelInfo curAcc = helper.getCurrentAccount();
		return curAcc == null ? Integer.MIN_VALUE : curAcc.getId();
	}

}
