package data.dao;

import po.UserPo;

public interface UserDao {
	
	/**
	 * @param userId
	 * @return	获取用户信息
	 */
	public UserPo getUser(int userId);
	
	/**
	 * @param userPo
	 * @return	更新用户信息
	 */
	public boolean updateUser(UserPo userPo);

}
