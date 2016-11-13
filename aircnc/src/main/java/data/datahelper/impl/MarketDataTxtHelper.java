package data.datahelper.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import data.datahelper.MarketDataHelper;
import po.market.MarketPo;

public class MarketDataTxtHelper implements MarketDataHelper{

	public ArrayList<MarketPo> getMarketData() {
		ArrayList<MarketPo> marketData = new ArrayList<MarketPo>();
		File file = new File("TxtData/user.txt");
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(
					file), "UTF-8");
			BufferedReader br = new BufferedReader(reader);
			String str = br.readLine();

			while (str != null) {

				String[] data = str.split(";");

				String marketId = data[0];
				String username=data[1];
				String password=data[2];

				MarketPo userPo=new MarketPo();
				marketData.add(userPo);

				str = br.readLine();

			}

			br.close();

			return marketData;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateMarketData(ArrayList<MarketPo> marketData) {
		//写入用户数据
		File file = new File("TxtData/user.txt");
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter writer = new BufferedWriter(fw);

			//对ArrayList进行遍历
			Iterator<MarketPo> iterator = marketData.iterator();
			while(iterator.hasNext()){
				MarketPo entry = iterator.next();
				MarketPo marketPo = entry;
				String str = marketPo.getId()+";"+marketPo.getName()+";"+marketPo.getPasswordHash();
				writer.write(str);
				writer.write("\r\n");
			}

			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
