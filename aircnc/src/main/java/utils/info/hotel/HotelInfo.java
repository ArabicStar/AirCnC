package utils.info.hotel;


public abstract class HotelInfo extends HotelInfoTemplate{
	protected boolean isValid;

	private static final String BLANK = "";

	protected HotelInfo() {
		id = BLANK;
		name = BLANK;
		scope = BLANK;
		location = BLANK;
		introduction = BLANK;
		star = 1;
		grade = 0.0;
//		comments = new List<CommentVo>;
		isValid = true;
	}

	public String getID() {
		if (isValid())
			return id;
		return null;
	}

	public abstract String getName();

	public int getStar() {
		if (isValid())
			return star;
		return Integer.MIN_VALUE;
	}

	public double getGrage(){
		if(isValid())
			return grade;
		return Double.MIN_VALUE;
	}
	
	public String getIntro(){
		if(isValid())
			return introduction;
		return null;
	}

	public boolean isValid() {
		return isValid;
	}

	public void invalidate() {
		isValid = false;
	}

}
