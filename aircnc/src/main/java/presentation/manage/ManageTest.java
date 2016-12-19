package presentation.manage;

import java.time.LocalDate;

import presentation.manage.manager.impl.HotelManageInfoManagerImpl;
import presentation.manage.manager.impl.MarketManageInfoManagerImpl;
import presentation.manage.manager.impl.MemberManageInfoImpl;
import vo.hotel.HotelVo;
import vo.hotel.HotelVoBuilder;
import vo.market.MarketVo;
import vo.market.MarketVoBuilder;
import vo.member.ContactVoBuilder;
import vo.member.MemberVo;
import vo.member.MemberVoBuilder;

/**
 * 这是一个迟早要删掉的类，我就不用英文勒，这里就模拟的是member的逻辑层
 * 
 * @author paranoia
 *
 */
public class ManageTest {

	public ManageTest() {
		
	}

	public static void getUserData() {
		MemberVoBuilder builder = new MemberVoBuilder("PERSONAL").setId("00002222").setName("hhh")
				.setBirthday(LocalDate.parse("1998-04-17"))
				.setContactInfo(new ContactVoBuilder().setEmail("12345@qq.com").setFixedPhone("0511-12344444")
						.setMobilePhone("13822222222").getContactInfo())
				.setCredit(0);
		MemberVo memberVo = builder.getMemberInfo();
		MemberManageInfoImpl.getInstance().setUser(memberVo);
	}

	public static void getHotelData() {
		HotelVoBuilder builder = new HotelVoBuilder().setName("速八酒店").setGrade(4.5).setScope("市中心")
				.setLocation("新街口").setStar(4);
		HotelVo hotelVo = builder.getHotelInfo();
		HotelManageInfoManagerImpl.getInstance().setHotel(hotelVo);
	}
	
	public static void getMarketData() {
		MarketVoBuilder builder = new MarketVoBuilder().setID("66666666").setName("孟阳");
		MarketVo marketVo = builder.getMarketInfo();
		MarketManageInfoManagerImpl.getInstance().setMarket(marketVo);
	}
}
