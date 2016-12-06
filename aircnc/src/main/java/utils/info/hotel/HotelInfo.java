package utils.info.hotel;

import java.util.List;


/**
 * Abstract of hotel info<br>
 * Immutable object.<br>
 * All setter parameters will be assumed checked already, so checks are skipped.
 * <br>
 * 
 * @author jqwu
 *
 */
public abstract class HotelInfo extends HotelInfoTemplate{
	
	/**
	 * mark a HotelInfo instance is valid or not. <br>
	 */
	protected boolean isValid;

	/**
	 * buffered blank string
	 */
	private static final String BLANK = "";

	
	/**
	 * Default initialization <br>
	 * <br>
	 * <b>Default value:</b>
	 * <ul>
	 * <li>String fields : blank string <br>
	 * <li>instance fields : null <br>
	 * <li>integer fileds : 0 <br>
	 * </ul>
	 * 
	 * @param type
	 */
	protected HotelInfo() {
		id = 0;
		name = BLANK;
		scope = BLANK;
		location = BLANK;
		introduction = BLANK;
		star = 1;
		grade = 0.0;
		equipment = BLANK;
		isValid = true;
	}

	public int getId() {
		if (isValid())
			return id;
		return 0;
	}

	public abstract String getName();
	
	public String getScope() {
		if (isValid())
			return scope;
		return null;
	}
	
	public String getLocation() {
		if (isValid())
			return location;
		return null;
	}
	
	public String getIntroduction() {
		if (isValid())
			return introduction;
		return null;
	}

	public int getStar() {
		if (isValid())
			return star;
		return Integer.MIN_VALUE;
	}

	public double getGrade(){
		if(isValid())
			return grade;
		return Double.MIN_VALUE;
	}
	
	public String getEquipment(){
		if(isValid())
			return equipment;
		return null;
	}

	public boolean isValid() {
		return isValid;
	}

	public void invalidate() {
		isValid = false;
	}

}
