package vo;

import java.util.Vector;

import po.HotelPo;

public class HotelVo extends Vector<String>{
	public HotelVo(HotelPo hotelPo){
		this.add(hotelPo.getId());
		this.add(hotelPo.getHotelName());
		this.add(hotelPo.getScope());
	}
	
	
	public HotelVo(String hotelNo,String hotelName,String scope) {
		this.add(hotelNo);
		this.add(hotelName);
		this.add(scope);
	}
	
	public String getHotelNo(){
		return this.get(0);
	}
	
	public String getHotelName(){
		return this.get(1);
	}
	
	public String getHotelScope(){
		return this.get(2);
	}

}
