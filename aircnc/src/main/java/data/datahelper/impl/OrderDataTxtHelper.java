package data.datahelper.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import data.datahelper.OrderDataHelper;
import po.order.OrderPo;

public class OrderDataTxtHelper implements OrderDataHelper{

	public Map<Integer,OrderPo> getOrderData() {
		File file = new File("TxtData/order.txt");
		Map<Integer,OrderPo> map = new HashMap<Integer, OrderPo>();
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(
					file), "UTF-8");
			BufferedReader br = new BufferedReader(reader);
			String str = br.readLine();
			
			while (str != null) {
				
				String[] data = str.split(";");
				int orderId = Integer.valueOf(data[0]);
				int hotelId = Integer.valueOf(data[1]) ;
				int orderUserId = Integer.valueOf(data[2]);
				int orderStatus = Integer.valueOf(data[3]);
				String orderEntryTime = data[4];
				String orderLastTime = data[5];
				String orderInfo=data[6];
				int orderPrice = Integer.valueOf(data[7]);
				
				OrderPo orderPo=new OrderPo(orderId, hotelId, orderUserId, orderStatus, orderEntryTime, orderLastTime,orderInfo,orderPrice, 1, 2, 0);
				map.put(orderId, orderPo);
				
				str = br.readLine();
				
			}

			br.close();
			
			return map;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateOrderData(Map<Integer,OrderPo> map) {
		//写入数据
		File file = new File("TxtData/order.txt");
		try {		
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter writer = new BufferedWriter(fw);
			
			//对map进行遍历
			Iterator<Map.Entry<Integer,OrderPo>> iterator = map.entrySet().iterator();
			while(iterator.hasNext()){
				Map.Entry<Integer,OrderPo> entry = iterator.next();
				OrderPo orderPo = entry.getValue();
				String str = orderPo.getId()+";"+orderPo.getHotelId()+";"+orderPo.getUserId()+";"
			+orderPo.getStatus()+";"+orderPo.getEntryTime()+";"+orderPo.getLastTime()+";"+orderPo.getOrderInfo()+";"+orderPo.getPrice();
				writer.write(str);
				writer.write("\r\n");
			}
			
			writer.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
