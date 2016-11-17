package aircnc.test.service.promotion;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import service.impl.promotion.PromotionServiceImpl;
import service.promotion.PromotionService;

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
