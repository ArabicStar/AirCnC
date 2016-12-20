package presentation.manage.manager.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.manage.manager.HotelManageInfoManager;
import presentation.manage.model.HotelManageModel;
import presentation.manage.model.ManageCommentModel;
import vo.hotel.HotelVo;
import vo.order.comment.CommentVo;

public class HotelManageInfoManagerImpl implements HotelManageInfoManager{
	
	private static HotelManageInfoManager instance;
	
	private HotelVo vo;
	private ObservableList<HotelManageModel> hotelInfo;
	private List<CommentVo> commentList;
	private ObservableList<ManageCommentModel> comments;
	
	public static final HotelManageInfoManager launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelManageInfoManagerImpl();
	}
	
	public static final HotelManageInfoManager getInstance(){
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	@Override
	public boolean setHotel(HotelVo vo){
		if(vo!=null){
			this.vo = vo;
			return true;
		}
		return false;
	}
	
	public static boolean isLaunched(){
		if(instance == null)
			return false;
		else
			return true;
	}

	/**
	 * wrap into the HotelManageModel
	 */
	@Override
	public ObservableList<HotelManageModel> getHotelInfoList() {
		hotelInfo = FXCollections.observableArrayList();
		hotelInfo.add(new HotelManageModel(vo));
		return hotelInfo;
	}

	@Override
	public HotelManageModel getHotelInfo() {
		if(vo == null)
			return null;
		
		return new HotelManageModel(vo);
	}

	@Override
	public boolean setComment(List<CommentVo> list) {
		if(list == null)
			return false;
		this.commentList = list;
		return true;
	}

	@Override
	public ObservableList<ManageCommentModel> getCommentList() {
		Iterator<CommentVo> it = commentList.iterator();		
		comments = FXCollections.observableArrayList();
		
		while(it.hasNext()){
			comments.add(new ManageCommentModel(it.next()));
		}
		
		return comments;
	}

}
