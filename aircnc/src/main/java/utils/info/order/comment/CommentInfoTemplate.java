package utils.info.order.comment;

import java.time.LocalDateTime;

public class CommentInfoTemplate {
	protected long id;
	protected LocalDateTime commentTime;
	protected String content;
	protected int grade;

	public static final boolean checkId(long id) {
		return id >= 0L;
	}
}
