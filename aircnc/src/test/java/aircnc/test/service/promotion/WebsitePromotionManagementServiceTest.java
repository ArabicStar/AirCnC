package aircnc.test.service.promotion;

import static org.junit.Assert.fail;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import service.promotion.WebsitePromotionManagementService;
import utils.info.promotion.PromotionInfoTemplate.Scope;
import utils.promotion.applier.ApplierParams;
import utils.promotion.applier.How;
import utils.promotion.trigger.TriggerParams;
import utils.promotion.trigger.website.WebsiteWhen;
import vo.promotion.PromotionVo;
import vo.promotion.PromotionVoBuilder;

public class WebsitePromotionManagementServiceTest {
	private WebsitePromotionManagementService service = null;// DataPrepareHelper.websitePromotionManagermenetService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddWebsitePromotion() {
		PromotionVoBuilder builder = new PromotionVoBuilder(Scope.Website);

		LocalDateTime now = LocalDateTime.now();
		builder.when(WebsiteWhen.DURING_PERIOD).setParam(TriggerParams.FROM, now.plusDays(1)).setParam(TriggerParams.TO,
				now.plusDays(5));
		builder.how(How.PERCENT_OFF).setParam(ApplierParams.PERCENT, 0.7);
		builder.setName("Period");
		PromotionVo vo = builder.getPromotionInfo();
		// service.addWebsitePromotion(vo);
	}

	@Test
	public void testDeleteWebsitePromotion() {
		fail("尚未实现");
	}

	@Test
	public void testUpdateWebsitePromotion() {
		fail("尚未实现");
	}

	@Test
	public void testGetWebsiteAllPromotions() {
		fail("尚未实现");
	}

	@Test
	public void testGetWebsiteActivePromotion() {
		fail("尚未实现");
	}

}
