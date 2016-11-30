package aircnc.test.service.member;

import static aircnc.test.service.member.DataPrepareHelper.dumpTestStatistic;
import static aircnc.test.service.member.DataPrepareHelper.prepareTestStatistic;
import static aircnc.test.service.member.DataPrepareHelper.testData;
import static aircnc.test.service.member.DataPrepareHelper.testID;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.dao.impl.member.CreditDaoImpl;
import data.dao.impl.member.MemberDaoImpl;
import data.dao.member.CreditDao;
import data.dao.member.MemberDao;
import po.member.MemberPo;
import service.impl.member.MemberCreditManager;
import service.member.MemberCreditService;
import utils.info.order.OrderStatus;
import vo.member.MemberVo;
import vo.order.OrderVo;

public class MemberCreditServiceTest {

	public MemberCreditService cs;
	public MemberDao memberDao;
	public CreditDao creditDao;

	@Before
	public void setUp() throws Exception {
		memberDao = new MemberDaoImpl();
		creditDao = new CreditDaoImpl();
		cs = new MemberCreditManager(memberDao, creditDao);
		prepareTestStatistic(memberDao);
	}

	@After
	public void tearDown() throws Exception {
		dumpTestStatistic(memberDao);
	}

	@Test
	public void testGainByCharge() {
		int money, beforeCre;
		MemberPo toTest;
		MemberVo res;
		for (int i = 0; i < 5; i++) {
			money = 10 * i + 1;
			toTest = testData(i);
			beforeCre = toTest.getCredit();
			res = cs.gainByCharge(money, testID(i));
			assertEquals(money * 10 + beforeCre, res.getCredit());
		}
	}

	@Test
	public void testGainByOrderExecution() {
		int beforeCre, price;
		MemberPo toTest;
		MemberVo res;
		for (int i = 0; i < 5; i++) {
			toTest = testData(i);
			beforeCre = toTest.getCredit();
			price = (i + 1) * 100;
			res = cs.gainByOrderExecution(new OrderVo().setUserId(toTest.getNumId()).setOrderId("12345678")
					.setPrice(price).setStatus(OrderStatus.UNEXECUTED));
			assertEquals(price + beforeCre, res.getCredit());
		}
	}

	@Test
	public void testReduceByOverdue() {
		int beforeCre, price;
		MemberPo toTest;
		MemberVo res;
		for (int i = 0; i < 5; i++) {
			toTest = testData(i);
			beforeCre = toTest.getCredit();
			price = (i + 1) * 100;
			res = cs.reduceByOverdue(new OrderVo().setUserId(toTest.getNumId()).setOrderId("12345678").setPrice(price)
					.setStatus(OrderStatus.UNEXECUTED));
			assertEquals(beforeCre - price, res.getCredit());
		}
	}

	@Test
	public void testReduceByCancel() {
		int beforeCre, price;
		MemberPo toTest;
		MemberVo res;
		for (int i = 0; i < 5; i++) {
			toTest = testData(i);
			beforeCre = toTest.getCredit();
			price = (i + 1) * 100;
			res = cs.reduceByCancel(new OrderVo().setUserId(toTest.getNumId()).setOrderId("12345678").setPrice(price)
					.setStatus(OrderStatus.UNEXECUTED));
			assertEquals(beforeCre - (int) (price * 0.5), res.getCredit());
		}
	}

	@Test
	public void testRecoverByDelay() {
		int beforeCre, price;
		MemberPo toTest;
		MemberVo res;
		for (int i = 0; i < 5; i++) {
			toTest = testData(i);
			beforeCre = toTest.getCredit();
			price = (i + 1) * 100;
			res = cs.recoverByDelay(new OrderVo().setUserId(toTest.getNumId()).setOrderId("12345678").setPrice(price)
					.setStatus(OrderStatus.ABNORMAL));
			assertEquals(beforeCre + (int) (price * 0.5), res.getCredit());
		}
	}

	@Test
	public void testRecoverByAppeal() {
		int beforeCre, price;
		MemberPo toTest;
		MemberVo res;
		for (int i = 0; i < 5; i++) {
			toTest = testData(i);
			beforeCre = toTest.getCredit();
			price = (i + 1) * 100;
			res = cs.recoverByAppeal(new OrderVo().setUserId(toTest.getNumId()).setOrderId("12345678").setPrice(price)
					.setStatus(OrderStatus.ABNORMAL));
			assertEquals(beforeCre + (int) (price), res.getCredit());
		}
	}

}
