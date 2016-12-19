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
		test();
		
		commentData = FXCollections.observableArrayList();
		Iterator<CommentVo> it = comments.iterator();
		while(it.hasNext())
			commentData.add(new CommentModel(it.next()));
		return commentData;
	}
	
	public void test(){
		comments = new ArrayList<CommentVo>();
		CommentVoBuilder builder = new CommentVoBuilder().setMemberID("38492072").setContent("环境极差，找了几个同学一起来晚，半夜还撞鬼了。").
				setCheckInTime(LocalDate.of(2016, 5, 30)).setCommentTime(LocalDateTime.now()).setHotelID(4)
				.setMemberName("小手表").setMemberLevel(3).setGrade(3);
		comments.add(builder.getCommentInfo());
		builder.setContent("这边真好玩，就当我完了一晚上鬼屋了，还特别便宜，隔壁有几个男生叫了一晚上，胆子真小啊。").
			setCheckInTime(LocalDate.of(2016, 8, 20)).setMemberID("47263544").setMemberLevel(5).setGrade(5);
		comments.add(builder.getCommentInfo());
		builder.setContent("最近的孩子怎么这么喧嚣，大晚上不睡觉在那边瞎嚷嚷，唉，人老了就只想早睡觉啊。").setMemberID("28785733");
		comments.add(builder.getCommentInfo());
		comments.add(builder.getCommentInfo());
	}
	

}
