package aircnc.test.po;

import java.time.LocalDate;
import static org.junit.Assert.assertEquals;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import data.dao.impl.order.CommentDaoImpl;
import data.dao.order.CommentDao;
import data.dao.query.CommentQueryDao;
import po.order.comment.CommentPo;
import po.order.comment.CommentPoBuilder;
import vo.order.comment.CommentVo;

public class CommentPoTest {
	CommentQueryDao commentQueryDao;
	CommentDao commentDao;
	
	@Before
	public void startUp() {
		commentQueryDao = CommentDaoImpl.INSTANCE;
		commentDao = CommentDaoImpl.INSTANCE;
	}
	
	public void showInfo(CommentVo vo) {
		System.out.println(vo.getHotelId());
		System.out.println(vo.getMemberId());
		System.out.println(vo.getMemberName());
		System.out.println(vo.getMemberLevel());
		System.out.println(vo.getGrade());
		System.out.println("content " + vo.getContent());
		System.out.println(vo.getCheckInTime());
		System.out.println(vo.getCommentTime());
	}
	
	public void showInfo(CommentPo po) {
		System.out.println(po.getHotelId());
		System.out.println(po.getMemberId());
		System.out.println(po.getGrade());
		System.out.println(po.getContent());
		System.out.println(po.getCheckInTime());
		System.out.println(po.getCommentTime());
	}
	
	@Test
	public void testPoToVo() {
		CommentPo po = new CommentPoBuilder().setCheckInTime(LocalDate.now())
				.setCommentTime(LocalDateTime.now()).setContent("嘻嘻哈哈")
				.setHotelID(10000000).setMemberID("12456743").getCommentInfo();
		showInfo(po);
//		showInfo(po.toCommentVo());
	}
	
	@Test
	public void testAddComment() {
		CommentPo po = new CommentPoBuilder().setCheckInTime(LocalDate.now())
				.setCommentTime(LocalDateTime.now()).setContent("嘻嘻哈哈").setOrderId("12340921")
				.setHotelID(10000000).setMemberID("12456743").setGrade(4).
				getCommentInfo();
		commentDao.addComment(po);
	}
	
	
	@Test
	public void testGetCommentByOrderId() {
		CommentPo po = commentDao.getCommentByOrderId("12340921");
		assertEquals("12456743", po.getMemberId());
	}
	
}
