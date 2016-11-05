package data.datahelper.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import po.UserPo;
import data.datahelper.UserDataHelper;

public class UserDataTxtHelper implements UserDataHelper{

	public Map<Integer,UserPo> getUserData() {
		Map<Integer, UserPo> map = new HashMap<Integer, UserPo>();
		File file = new File("TxtData/user.txt");
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(
					file), "UTF-8");
			BufferedReader br = new BufferedReader(reader);
			String str = br.readLine();
			
			while (str != null) {
				
				String[] data = str.split(";");
				
				int userId = Integer.valueOf(data[0]);
				String username=data[1];
				String phone=data[2];
				int credit=Integer.valueOf(data[3]);
				
				UserPo userPo=new UserPo(userId, username, phone, credit);
				map.put(userId, userPo);
				
				str = br.readLine();
				
			}

			br.close();
			
			return map;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateUserData(Map<Integer, UserPo> map) {
		//写入用户数据
		File file = new File("TxtData/user.txt");
		try {		
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter writer = new BufferedWriter(fw);
		
			//对map进行遍历
			Iterator<Map.Entry<Integer, UserPo>> iterator = map.entrySet().iterator();
			while(iterator.hasNext()){
				Map.Entry<Integer, UserPo> entry = iterator.next();
				UserPo userPo = entry.getValue();
				String str = userPo.getId()+";"+userPo.getUsername()+";"+userPo.getPhone()+";"+userPo.getCredit();
				writer.write(str);
				writer.write("\r\n");
			}
			
			writer.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
