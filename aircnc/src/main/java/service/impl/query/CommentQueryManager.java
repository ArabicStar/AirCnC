package service.impl.query;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.illegalArgEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.List;
import java.util.stream.Collectors;

import data.dao.query.CommentQueryDao;
import service.query.CommentQueryService;
import vo.order.comment.CommentVo;
import vo.order.comment.CommentVoBuilder;

public class CommentQueryManager implements CommentQueryService {
	/* Singleton */
	private static CommentQueryManager instance;

	public static CommentQueryManager launch(CommentQueryDao dao) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new CommentQueryManager(dao);
	}

	public static CommentQueryManager getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* Singleton */

	private CommentQueryDao dao;

	private CommentQueryManager(CommentQueryDao dao) {
		this.dao = dao;
	}

	@Override
	public List<CommentVo> getHotelComments(int hotelId) {
		if (hotelId < 0)
			throw illegalArgEx("hotelId");

		return dao.findByHotelId(hotelId).stream().map(po -> new CommentVoBuilder(po).getCommentInfo())
				.collect(Collectors.toList());
	}

}
