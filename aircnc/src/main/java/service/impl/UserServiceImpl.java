package service.impl;

import data.dao.UserDao;
import data.dao.impl.UserDaoImpl;
import data.datahelper.DataFactory;
import data.datahelper.impl.DataFactoryImpl;
import po.UserPo;
import service.UserService;
import vo.UserVo;

public class UserServiceImpl implements UserService{
	
	private UserDao userDao;
	
	public UserServiceImpl(){
		userDao = UserDaoImpl.getInstance();
	}

	public UserVo getUser(int userId) {
		UserPo userPo = userDao.getUser(userId);
		return new UserVo(userPo);
	}

	public boolean addUserCredit(int userId,int credit) {
		UserPo userPo = userDao.getUser(userId);
		userPo.setCredit(userPo.getCredit()+credit);
		return userDao.updateUser(userPo);
	}

}
