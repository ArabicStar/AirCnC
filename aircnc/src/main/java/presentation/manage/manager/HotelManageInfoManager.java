package presentation.manage.manager;

import java.util.List;

import javafx.collections.ObservableList;
import presentation.manage.model.HotelManageModel;
import presentation.manage.model.ManageCommentModel;
import vo.hotel.HotelVo;
import vo.order.comment.CommentVo;

public interface HotelManageInfoManager {
	
	public boolean setHotel(HotelVo vo);
	
	public boolean setComment(List<CommentVo> list);
	
	public ObservableList<HotelManageModel> getHotelInfoList();
	
	public HotelManageModel getHotelInfo();
	
	public ObservableList<ManageCommentModel> getCommentList();
	
}
