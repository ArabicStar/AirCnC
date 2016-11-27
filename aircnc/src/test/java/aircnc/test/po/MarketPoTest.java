package aircnc.test.po;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import data.dao.market.MarketDao;
import po.market.MarketPo;

public class MarketPoTest {

//	private MarketDao dao ;
//
//	/**
//	 * 这里是管理营销信息的测试用例
//	 */
//
//	@Test
//	public void MarketPoTest1(){
//
//		//具体客户信息为：ID（00000003），密码（user），名称（小红）
//
//		/*
//		 * 更新营销信息
//		 * 修改 user3 名字为 “张三”
//		 */
//		MarketPo marketPo = dao.findMarket("00000003");
//		marketPo.setName("张三");
//
//		assertEquals(marketPo.getName(), "张三");
//
//	}
//
//	@Test
//	public void MarketPoTest2(){
//
//		//具体客户信息为：ID（00000003），密码（user），名称（小红）
//
//		/*
//		 * 更新营销信息
//		 * 修改 user3 密码为 “123456”
//		 */
//		MarketPo marketPo = dao.findMarket("00000003");
//		marketPo.setPasswordHash("123456".hashCode());
//
//		assertEquals(marketPo.getPasswordHash(),"123456".hashCode());
//
//	}
//
//	@Test
//	public void MarketPoTest3(){
//
//		//具体客户信息为：ID（00000003），密码（user），名称（小红）
//
//		/*
//		 * 更新营销信息
//		 * 修改 user3 名字改为“李四” 密码改为 “1234567”
//		 */
//		MarketPo marketPo = dao.findMarket("00000003");
//		marketPo.setName("李四");
//		marketPo.setPasswordHash("1234567".hashCode());
//
//		assertEquals(marketPo.getName(), "李四");
//		assertEquals(marketPo.getPasswordHash(),"1234567".hashCode());
//
//	}
//
//	@Test
//	public void MarketPoTest4(){
//		/*
//		 * 查询营销信息
//		 * 查询Id为00000003的用户信息
//		 */
//		MarketPo marketPo = dao.findMarket("00000003");
//		marketPo.setName("李四");
//		marketPo.setPasswordHash("1234567".hashCode());
//
//		assertEquals(marketPo.getName(), "李四");
//		assertEquals(marketPo.getPasswordHash(),"1234567".hashCode());
//	}
//
//	@Test
//	public void MarketPoTest5(){
//		/*
//		 * 查询营销信息
//		 * 查询Id为00000030的用户信息
//		 */
//	}
//
//	@Test
//	public void MarketPoTest6(){
//		/*
//		 * 添加营销信息
//		 * 添加一个 Id为00001003 姓名为“王二” 密码为“market1”的用户
//		 */
//	}
//
//	@Test
//	public void MarketPoTest7(){
//		/*
//		 * 添加营销信息
//		 * 添加一个 Id为00001003 姓名为空 密码为“market1”的用户
//		 * 返回信息不足的错误信息
//		 */
//	}
//
//	@Test
//	public void MarketPoTest8(){
//		/*
//		 * 删除营销信息
//		 * 删除一个 Id为00000003的用户
//		 */
//	}
//
//	@Test
//	public void MarketPoTest9(){
//		/*
//		 * 删除营销信息
//		 * 删除一个 Id为00000030的用户
//		 * 返回未查找到该用户的错误信息
//		 */
//	}

}
