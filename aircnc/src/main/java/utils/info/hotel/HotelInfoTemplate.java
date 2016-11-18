package utils.info.hotel;

import java.time.LocalDate;
import java.util.List;


public class HotelInfoTemplate {

	protected String id;
	protected String name;
	protected String scope;
	protected String location;
	protected String introduction;
	protected int star;
	protected double grade;//评分
//	protected List<PromotionInfo> promomtions;
//	protected List<RoomInfo> rooms;
	

	private static final int ID_BOUND = 100000000;
	private static final String ID_PATTERN = "[0-9]{8}";
	private static final String ID_FORMAT_STRING = "%08d";
	private static final int WRONG_ID_MARKER = -1;

	public static final boolean checkID(String s) {
		return s != null && s.matches(ID_PATTERN);
	}

	public static final boolean checkHotelName(String name) {
		return name != null && name.length() != 0 && !name.contains("\\s");
	}
	
	public static final boolean checkHotelScope(String scope){
		return true;
	}
	
	public static final boolean checkHotelLocation(String location){
		return true;
	}
	
	public static final boolean checkHotelIntro(String intro){
		return true;
	}
	
	public static final boolean checkHotelStar(int star){
		return star >= 1 && star <= 7;
	}
	
	public static final boolean checkHotelGrade(double grade){
		return grade <= 5 && grade > 0;
	}

	public static final int convertID2Num(String id) {
		if (!checkID(id))
			return WRONG_ID_MARKER;

		return Integer.valueOf(id);
	}

	public static final String formatID(int i) {
		if (i <= 0 && i >= ID_BOUND)
			return null;

		return String.format(ID_FORMAT_STRING, i);
	}
}
