package utils.info.comment;

public class CommentInfoTemplate {
	
	protected String hotelId;
	protected String memberId;
	protected String content;
	protected int grade;
	protected String checkInTime;
	protected String commentTime;
	
	private static final int ID_BOUND = 100000000;
	private static final String ID_PATTERN = "[0-9]{8}";
	private static final String ID_FORMAT_STRING = "%08d";
	private static final int WRONG_ID_MARKER = -1;

	public static final boolean checkID(String s) {
		return s != null && s.matches(ID_PATTERN);
	}

	public static final boolean checkCommentContent(String content) {
		return content != null && content.length() != 0;
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
