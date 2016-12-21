package utils.info.order.comment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CommentInfoTemplate {
	
	protected int hotelId;
	protected String memberId;
	protected String content;
	protected int grade;
	protected LocalDate checkInTime;
	protected LocalDateTime commentTime;
	protected String orderId_c;
	
//	private static final int ID_BOUND = 100000000;
	private static final String ID_PATTERN = "[0-9]{8}";
//	private static final String ID_FORMAT_STRING = "%08d";
//	private static final int WRONG_ID_MARKER = -1;

	public static final boolean checkID(String s) {
		return s != null && s.matches(ID_PATTERN);
	}
	
	public static final boolean checkID(int i) {
		return i != 0;
	}

	public static final boolean checkCommentContent(String content) {
		return content != null;
	}

//	public static final int convertID2Num(String id) {
//		if (!checkID(id)) {
//			return WRONG_ID_MARKER;
//		}
//		return Integer.valueOf(id);
//	}
//
//	public static final String formatID(int i) {
//		if (i <= 0 && i >= ID_BOUND)
//			return null;
//
//		return String.format(ID_FORMAT_STRING, i);
//	}
	
	
}
