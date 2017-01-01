package interactor.impl.manage;

import static interactor.utils.AlertHelper.alertFail;
import static interactor.utils.Dipatcher.execute;
import static interactor.utils.TitleGetter.getTitle;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;
import static utils.exception.StaticExceptionFactory.unknownEx;

import interactor.manage.ManageHotelInteractor;
import interactor.utils.AlertHelper;
import presentation.manage.accessor.impl.HotelManageInfoAccessorImpl;
import presentation.manage.manager.impl.HotelManageInfoManagerImpl;
import service.manage.ManageHotelService;
import utils.info.hotel.HotelInfo;
import vo.hotel.HotelVoBuilder;

public class ManageHotelCourier implements ManageHotelInteractor{
	/* singleton */
	private static ManageHotelInteractor instance;

	public static final ManageHotelInteractor launch(ManageHotelService handler) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new ManageHotelCourier(handler);
	}

	public static final ManageHotelInteractor getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	/* singleton */

	private ManageHotelService handler;

	private ManageHotelCourier(ManageHotelService handler) {
		this.handler = handler;
	}
	@Override
	public boolean AddHotelInfo() {
		String title = getTitle();
		HotelInfo info = execute(title, () -> {
			HotelInfo tmp = handler.AddHotelInfo(new HotelVoBuilder(HotelManageInfoAccessorImpl.getInstance()
					.getAddedHotelVo()), HotelManageInfoAccessorImpl.getInstance().getPasswordHash());

			if (tmp == null)
				throw unknownEx();
			return tmp;
		});
		
		HotelInfo newInfo = execute(title, () -> {
			String name = info.getName();
			return handler.getHotelInfoByName(name);
		});

		if (info != null)
			AlertHelper.alertSuccess(title, String.format("添加酒店成功！该账号ID是%s", String.valueOf(newInfo.getId())));
		else
			AlertHelper.alertFail(title, "添加失败！");
		HotelManageInfoManagerImpl.getInstance().setHotel(new HotelVoBuilder(info).getHotelInfo());
		return info != null;
	}

	@Override
	public boolean ModifyHotelInfo() {
		String title = getTitle();
		HotelInfo modified = HotelManageInfoAccessorImpl.getInstance().getModifiedHotelVo();

		boolean res = execute(title, () -> {
			String name = HotelManageInfoAccessorImpl.getInstance().getHotelName();
			if (name != null)
				return handler.ModifyHotelInfo(modified);

			alertFail(title, "Not input the modified info yet");
			return null;
		});

		HotelManageInfoManagerImpl.getInstance().setHotel(res ? new HotelVoBuilder(modified).getHotelInfo() : null);
		return modified != null;
	}

	@Override
	public boolean getHotelInfo() {
		String title = getTitle();

		HotelInfo info = execute(title, () -> {
			int id = HotelManageInfoAccessorImpl.getInstance().getHotelId();
			return handler.getHotelInfo(id);
		});
		
		if(info != null){
			HotelManageInfoManagerImpl.getInstance().setHotel(new HotelVoBuilder(info).getHotelInfo());
			return true;
		}else
			return false;
	}

	@Override
	public boolean deleteHotelInfo() {
		String title = getTitle();
		boolean valid = execute(title, () -> {
			int id = HotelManageInfoAccessorImpl.getInstance().getHotelId();
			boolean tmp = handler.deleteHotelInfo(id);

			if (tmp == false)
				throw unknownEx();
			return tmp;
		});

		if (valid != false)
			AlertHelper.alertSuccess(title, "删除酒店成功");
		else
			AlertHelper.alertFail(title, "删除失败！");
		HotelManageInfoManagerImpl.getInstance().setHotel(null);
		return valid != false;
	}

}
