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

	public Map<String,OrderPo> getOrderData() {
		File file = new File("TxtData/order.txt");
		Map<String,OrderPo> map = new HashMap<String, OrderPo>();
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(
					file), "UTF-8");
			BufferedReader br = new BufferedReader(reader);
			String str = br.readLine();
			
			while (str != null) {
				
				String[] data = str.split(";");
				String orderId = String.valueOf(data[0]);
				int hotelId = Integer.valueOf(data[1]) ;
				int orderUserId = Integer.valueOf(data[2]);
				int orderStatus = Integer.valueOf(data[3]);
				String orderEntryTime = data[4];
				String orderLastTime = data[5];
				String orderInfo=data[6];
				int orderPrice = Integer.valueOf(data[7]);
				
				/**
				 * FIXME:这些参数都没有加上去
				 */
				OrderPo orderPo=new OrderPo();
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

	public void updateOrderData(Map<String, OrderPo> map) {
		//写入数据
		File file = new File("TxtData/order.txt");
		try {		
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter writer = new BufferedWriter(fw);
			
			//对map进行遍历
			Iterator<Map.Entry<String,OrderPo>> iterator = map.entrySet().iterator();
			while(iterator.hasNext()){
				Map.Entry<String, OrderPo> entry = iterator.next();
				OrderPo orderPo = entry.getValue();
				/**
				 * FIXME:这个方法不完整
				 */
//				String str = orderPo.getOrderId()+";"+orderPo.getHotelId()+";"+orderPo.getUserId()+";"
//			+orderPo.getStatus()+";"+orderPo.getEntryTime()+";"+orderPo.getLastTime()+";"+orderPo.getOrderInfo()+";"+orderPo.getPrice();
//				writer.write(str);
//				writer.write("\r\n");
			}
			
			writer.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
