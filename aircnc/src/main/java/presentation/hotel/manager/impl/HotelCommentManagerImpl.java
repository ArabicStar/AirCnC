package presentation.hotel.manager.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.hotel.manager.HotelCommentManager;
import presentation.hotel.model.CommentModel;
import vo.order.comment.CommentVo;
import vo.order.comment.CommentVoBuilder;

public class HotelCommentManagerImpl implements HotelCommentManager{

	private static HotelCommentManagerImpl instance;
	
	private List<CommentVo> comments;
	
	private ObservableList<CommentModel> commentData;
	
	public static final HotelCommentManager launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelCommentManagerImpl();
	}
	
	public static final HotelCommentManager getInstance(){
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	public static boolean isLaunched(){
		if(instance == null)
			return false;
		else
			return true;
	}
	
	@Override
	public boolean setComment(List<CommentVo> list) {
		if(list!=null){
			this.comments=list;
			return true;
		}
		return false;
	}

	@Override
	public ObservableList<CommentModel> getCommentList() {
		
		commentData = FXCollections.observableArrayList();
		Iterator<CommentVo> it = comments.iterator();
		while(it.hasNext())
			commentData.add(new CommentModel(it.next()));
		return commentData;
	}

	

}
