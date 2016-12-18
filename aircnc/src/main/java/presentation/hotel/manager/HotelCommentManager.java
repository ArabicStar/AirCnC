package presentation.hotel.manager;

import java.util.List;

import javafx.collections.ObservableList;
import presentation.hotel.model.CommentModel;
import presentation.member.model.SearchHotelsModel;
import vo.hotel.HotelVo;
import vo.order.comment.CommentVo;

public interface HotelCommentManager {

	public boolean setComment(List<CommentVo> list);
	
	public ObservableList<CommentModel> getCommentList();
}
