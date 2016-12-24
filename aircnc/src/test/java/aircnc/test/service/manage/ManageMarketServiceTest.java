package aircnc.test.service.manage;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;

import data.dao.market.MarketDao;
import service.manage.ManageMarketService;
import utils.info.market.MarketInfo;
import vo.market.MarketVoBuilder;

/**
 * public MarketVo AddMarketInfo(MarketVoBuilder newMarketInfo, int
 * passwordHash);
 * 
 * public boolean ModifyMarketInfo(MarketInfo marketInfo);
 * 
 * public MarketInfo getMarketInfo(String id);
 * 
 * public boolean deleteMarketInfo(String id);
 * 
 * @author paranoia
 *
 */
public class ManageMarketServiceTest {
	
	private ManageMarketService mar = DataPrepareHelper.marService;
	private MarketDao marDao = DataPrepareHelper.marketDao;

	private String addedId;
	
	private MarketInfo info;
	
	public ManageMarketServiceTest() {
	
	}
	
	@Test
	public void testAddMarketInfo() {
		MarketVoBuilder b = new MarketVoBuilder().setName("RocketBoy");
		
		MarketInfo v = mar.AddMarketInfo(b, "12345678".hashCode());
		
		this.info = v;
		
		addedId = v.getId();
		
		assertEquals(true, marDao.existsMarket(addedId));
	}

	@Test
	public void testModifyMarketInfo() {
		
		MarketVoBuilder b = new MarketVoBuilder().setName("YouYou");
		
		MarketInfo v = mar.AddMarketInfo(b, "12345678".hashCode());
		
		this.info = v;
		
		addedId = v.getId();
		
		b.setName("JamLee");
		
		mar.ModifyMarketInfo(b.getMarketInfo());
		
		assertEquals("JamLee", b.getMarketInfo().getName());
	}

	@Test
	public void testGetMarketInfo() {
		
		MarketVoBuilder b = new MarketVoBuilder().setName("YaoYao");
		
		MarketInfo v = mar.AddMarketInfo(b, "12345678".hashCode());
		
		this.info = v;
		
		String addedId = v.getId();
		
		MarketInfo info = mar.getMarketInfo(addedId);
		assertEquals(info.getName(), this.info.getName());
		
		try {
			mar.deleteMarketInfo(addedId);
		} catch (Exception e) {
			
		}
		
	}

	@Test
	public void testDeleteMarketInfo() {
		boolean res1 = false, res2 = false;
		
		MarketVoBuilder b = new MarketVoBuilder().setName("Lucio");
		
		MarketInfo v = mar.AddMarketInfo(b, "12345678".hashCode());
		
		this.info = v;
		
		String addedId = v.getId();
		
		try {
			res1 = mar.deleteMarketInfo(addedId);
			res2 = mar.deleteMarketInfo("22222222");
		} catch (Exception e) {
		}
		assertEquals(true, res1);
		assertEquals(false, res2);
	}

	@After
	public void tearDown() {
		try {
			marDao.deleteMarket(addedId);
		} catch (Exception e) {
		}
	}
}
