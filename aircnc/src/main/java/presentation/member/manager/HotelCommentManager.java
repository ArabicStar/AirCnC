package presentation.member.manager;

import java.util.List;

import javafx.collections.ObservableList;
import presentation.member.model.CommentModel;
import vo.order.comment.CommentVo;

public interface HotelCommentManager {
	public boolean setComment(List<CommentVo> list);
	
	public ObservableList<CommentModel> getCommentList();
}
