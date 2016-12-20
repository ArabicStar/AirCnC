package presentation.manage.manager;

import java.util.List;

import javafx.collections.ObservableList;
import presentation.manage.model.ManageCommentModel;
import vo.order.comment.CommentVo;

public interface ManageHotelCommentManager {
	
	public boolean setComment(List<CommentVo> list);
	
	public ObservableList<ManageCommentModel> getCommentList();
}
