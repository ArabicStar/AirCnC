package aircnc.test.service.promotion;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import po.UserPo;
import po.order.OrderPo;
import service.impl.promotion.PromotionServiceImpl;
import service.promotion.PromotionService;
import vo.order.OrderVo;

public class PromotionServiceTest {
	private PromotionService promotion;
	
	@Before
	public void init() {
		promotion = new PromotionServiceImpl();
	}

	@Test
	public void giveDiscountsTest() {
		assertEquals(promotion.giveDiscounts(null, 1.1), null);
	}

	@Test
	public void returnMoneyTest() {
		assertEquals(promotion.returnMoney(null, 200, 300), null);
	}
}
