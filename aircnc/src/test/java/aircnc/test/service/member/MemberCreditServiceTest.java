package aircnc.test.service.member;

import static aircnc.test.service.member.DataPrepareHelper.dumpTestStatistic;
import static aircnc.test.service.member.DataPrepareHelper.prepareTestStatistic;
import static aircnc.test.service.member.DataPrepareHelper.testData;
import static aircnc.test.service.member.DataPrepareHelper.testID;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import po.member.MemberPo;
import service.member.MemberCreditService;
import utils.info.order.OrderStatus;
import vo.member.MemberVo;
import vo.order.OrderVoBuilder;

public class MemberCreditServiceTest {
	public MemberCreditService cs = DataPrepareHelper.creditService;

	@Before
	public void setUp() throws Exception {
		prepareTestStatistic();
	}

	@After
	public void tearDown() throws Exception {
		dumpTestStatistic();
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
			res = cs.gainByOrderExecution(new OrderVoBuilder().setOrderId("12345678").setOriginalPrice(price)
					.setStatus(OrderStatus.UNEXECUTED).getOrderInfo());
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
			res = cs.reduceByOverdue(new OrderVoBuilder().setOrderId("12345678").setOriginalPrice(price)

					.setStatus(OrderStatus.UNEXECUTED).getOrderInfo());
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
			res = cs.reduceByCancel(new OrderVoBuilder().setOrderId("12345678").setOriginalPrice(price)

					.setStatus(OrderStatus.UNEXECUTED).getOrderInfo());
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
			res = cs.recoverByDelay(new OrderVoBuilder().setOrderId("12345678").setOriginalPrice(price)
					.setStatus(OrderStatus.ABNORMAL).getOrderInfo());
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
			res = cs.recoverByAppeal(new OrderVoBuilder().setOrderId("12345678").setOriginalPrice(price)
					.setStatus(OrderStatus.ABNORMAL).getOrderInfo());
			assertEquals(beforeCre + (int) (price), res.getCredit());
		}
	}

}
