package utils.info.order.comment;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CommentInfoTemplate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2031280794186367520L;
	protected long id;
	protected LocalDateTime commentTime;
	protected String content;
	protected int grade;

	public static final boolean checkId(long id) {
		return id >= 0L;
	}
}
