package utils.info.hotel;

/**
 * Abstract of hotel info object.<br>
 * Define fields contained in HotelInfo, and checker for them.<br>
 * 
 * @author jqwu
 * 
 */
public class HotelInfoTemplate {

	/**
	 * id int <br>
	 */
	protected int id;
	
	/**
	 * name string <br>
	 */
	protected String name;
	
	/**
	 * scope string <br>
	 */
	protected String scope;
	
	/**
	 * location string <br>
	 */
	protected String location;
	
	/**
	 * introduction string <br>
	 */
	protected String introduction;
	
	/**
	 * star int 1~7 <br>
	 */
	protected int star;
	
	/**
	 * grade double 0~5.0 <br>
	 */
	protected double grade;
	
	/**
	 * equipment String 0~5.0 <br>
	 */
	protected String equipment;


	/**
	 * Check name string. <br>
	 * 
	 * @param name
	 *            name string
	 * @return if given string is a valid name string <br>
	 */
	public static final boolean checkHotelName(String name) {
		return name != null && name.length() != 0 && !name.contains("\\s");
	}
	
	/**
	 * Check id int. <br>
	 * 
	 * @param id
	 *            id int
	 * @return if given id is a valid id int <br>
	 */
	public static final boolean checkID(int id) {
		return id>0;
	}
	
	/**
	 * Check star integer. <br>
	 * 
	 * @param star
	 *            star integer
	 * @return if given star is a valid star integer <br>
	 */
	public static final boolean checkHotelStar(int star){
		return star >= 1 && star <= 7;
	}

}
