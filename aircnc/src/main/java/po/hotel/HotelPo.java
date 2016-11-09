package po.hotel;

public class HotelPo {
	
	private String id;
	
	private String hotelName;
	
	private String position;//地址
	
	private String scope;//商圈
	
	private int star;//星级
	
	private String introduction;
	
//	private 房型
	
//	private 设施
	
//	private 优惠策略
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getScope(){
		return scope;
	}
}
