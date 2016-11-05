package data.datahelper;

import java.util.Map;

import po.UserPo;

public interface UserDataHelper {
	
	/**
	 * @return	从数据文件中读取用户数据
	 */
	public Map<Integer, UserPo> getUserData();
	
	/**
	 * 向数据文件中写入用户数据
	 * @param list
	 */
	public void updateUserData(Map<Integer, UserPo> map);

}
