package presentation.manage.manager.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.manage.manager.ManageHotelCommentManager;
import presentation.manage.model.ManageCommentModel;
import vo.order.comment.CommentVo;

public class ManageHotelCommentManagerImpl implements ManageHotelCommentManager {

	private static ManageHotelCommentManager instance;

	private List<CommentVo> comments;

	private ObservableList<ManageCommentModel> commentData = FXCollections.observableArrayList();

	public static final ManageHotelCommentManager launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new ManageHotelCommentManagerImpl();
	}

	public static final ManageHotelCommentManager getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	public static boolean isLaunched() {
		if (instance == null)
			return false;
		else
			return true;
	}

	@Override
	public boolean setComment(List<CommentVo> list) {
		if (list != null) {
			this.comments = list;
			return true;
		}
		return false;
	}

	@Override
	public ObservableList<ManageCommentModel> getCommentList() {
		commentData.clear();
		Iterator<CommentVo> it = comments.iterator();
		while (it.hasNext())
			commentData.add(new ManageCommentModel(it.next()));
		return commentData;
	}
}
