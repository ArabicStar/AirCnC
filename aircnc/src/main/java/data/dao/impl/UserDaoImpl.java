package data.dao.impl;

import java.util.List;
import java.util.Map;

import po.UserPo;
import data.dao.UserDao;
import data.datahelper.DataFactory;
import data.datahelper.UserDataHelper;
import data.datahelper.impl.DataFactoryImpl;
import data.datahelper.impl.UserDataTxtHelper;

public class UserDaoImpl implements UserDao{
	
	private Map<Integer,UserPo> map;
	
	private UserDataHelper userDataHelper;
	
	private DataFactory dataFactory;
	
	private static UserDaoImpl userDataServiceImpl;
	
	public static UserDaoImpl getInstance(){
		if(userDataServiceImpl == null){
			userDataServiceImpl = new UserDaoImpl();
		}
		return userDataServiceImpl;
	}
	
	public UserDaoImpl(){
		if(map == null){
			dataFactory = new DataFactoryImpl();
			userDataHelper = dataFactory.getUserDataHelper();
			map = userDataHelper.getUserData();
		}
	}
	
	public UserPo getUser(int userId) {
		return map.get(userId);
	}

	public boolean updateUser(UserPo userPo) {
		int userId = userPo.getId();
		if(map.get(userId) != null){
			map.put(userId, userPo);
			userDataHelper.updateUserData(map);
			return true;
		}
		return false;

	}

}
