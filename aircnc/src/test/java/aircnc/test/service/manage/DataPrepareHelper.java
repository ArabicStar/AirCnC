package aircnc.test.service.manage;

import java.util.ArrayList;
import java.util.List;

import data.dao.hotel.HotelDao;
import data.dao.impl.hotel.HotelDaoImpl;
import data.dao.impl.market.MarketDaoImpl;
import data.dao.impl.member.MemberDaoImpl;
import data.dao.market.MarketDao;
import data.dao.member.MemberDao;
import javafx.util.converter.LocalDateStringConverter;
import po.hotel.HotelPo;
import po.hotel.HotelPoBuilder;
import po.market.MarketPo;
import po.market.MarketPoBuilder;
import po.member.MemberPo;
import po.member.MemberPoBuilder;
import service.impl.manage.ManageHotelManager;
import service.impl.manage.ManageMarketManager;
import service.impl.manage.ManageMemberManager;
import service.manage.ManageHotelService;
import service.manage.ManageMarketService;
import service.manage.ManageMemberService;
import utils.info.member.ContactInfo;
import vo.member.ContactVoBuilder;

public class DataPrepareHelper {
	/* member test data */
	private static final List<MemberPo> memberData = new ArrayList<>();
	private static final String[] memberTestType = new String[] { "Personal", "Personal", "Personal", "Business",
			"Business" };
	private static final ContactInfo c = new ContactVoBuilder().getContactInfo();
	private static final int[] memberTestCredit = new int[] { 100, 1000, 999, 8, 29102784 };
	private static final String[] memberTestName = new String[] { "AA", "BB", "CC", "DD", "EE" };
	private static final String[] memberTestID = new String[] { "11111111", "22222222", "33333333", "44444444", "55555555" };
	private static final String[] testExtra = new String[] { "1998-01-01", "1973-02-11", "2000-01-12",
			"Microsoft Ltd,.Co.", "Apple Inc." };
	private static final int memberTestPass = "12345678".hashCode();
	static {
		for (int i = 0; i < 2; i++) {
			MemberPoBuilder b = new MemberPoBuilder(memberTestType[i]).setName(memberTestName[i]).setContactInfo(c)
					.setCredit(memberTestCredit[i]).setId(memberTestID[i]).setEnterprise(testExtra[i]).setPasswordHash(memberTestPass);
			if (i <= 2)
				b.setBirthday(new LocalDateStringConverter().fromString(testExtra[i]));
			memberData.add(b.getMemberInfo());
		}
	}
	
	/* hotel test data */
	
	private static final List<HotelPo> hotelData = new ArrayList<>();
	private static final String[] hotelTestName = new String[] { "阿拉伯之星", "速八酒店"};
	private static final int[] hotelTestID = new int[] { 11112222, 22223333};
	private static final Double[] hotelTestGrade = new Double[] { 3.5, 4.5};
	private static final String[] hotelTestEquip = new String[] { "wifi;停车场", "24小时热水;叫醒服务"};
	private static final String[] hotelTestScope = new String[] { "栖霞区", "玄武区"};
	private static final String[] hotelTestLocation = new String[] { "仙林大道", "玄武湖"};
	private static final String[] hotelTestIntro = new String[] { "我们特别棒", "我们真的特别棒"};
	private static final int hotelTestPass = "12345678".hashCode();
	static {
		for (int i = 0; i < 2; i++) {
			HotelPoBuilder b = new HotelPoBuilder().setID(hotelTestID[i]).setGrade(hotelTestGrade[i])
					.setEquipment(hotelTestEquip[i]).setStar(7)
					.setName(hotelTestName[i]).setScope(hotelTestScope[i]).setLocation(hotelTestLocation[i])
					.setIntro(hotelTestIntro[i]).setPasswordHash(hotelTestPass);
			hotelData.add(b.getHotelInfo());
		}
	}
	
	/* market test data */
	private static final List<MarketPo> marketData = new ArrayList<>();
	private static final String[] marketTestName = new String[] { "Linkin", "Roy"};
	private static final String[] marketTestID = new String[] { "33334444", "44445555"};
	private static final int marketTestPass = "12345678".hashCode();
	static {
		for (int i = 0; i < 5; i++) {
			MarketPoBuilder b = new MarketPoBuilder().setName(marketTestName[i])
					.setID(marketTestID[i]).setPasswordHash(marketTestPass);
			marketData.add(b.getMarketInfo());
		}
	}
	
	/* test data */

	public static final MemberDao memberDao = MemberDaoImpl.INSTANCE;
	public static final HotelDao hotelDao = HotelDaoImpl.INSTANCE;
	public static final MarketDao marketDao = MarketDaoImpl.INSTANCE;
	public static final ManageMemberService memService = ManageMemberManager.launch(memberDao);
	public static final ManageHotelService hotService = ManageHotelManager.launch(hotelDao);
	public static final ManageMarketService marService = ManageMarketManager.launch(marketDao);

	public static final void prepareTestStatistic() {
		memberData.forEach(memberDao::addMember);
		hotelData.forEach(hotelDao::addHotel);
		marketData.forEach(marketDao::addMarket);
	}

	public static final void dumpTestStatistic() {
		memberData.forEach(d -> memberDao.deleteMember(d.getId()));
		hotelData.forEach(d -> hotelDao.deleteHotel(d.getId()));
		marketData.forEach(d -> marketDao.deleteMarket(d.getId()));
	}

	public static final String MemberTestID(int i) {
		return memberTestID[i];
	}

	public static final String MembertestName(int i) {
		return memberTestName[i];
	}

	public static final MemberPo MemberTestData(int i) {
		return memberData.get(i);
	}
	
	public static final int HotelTestID(int i) {
		return hotelTestID[i];
	}

	public static final String HoteltestName(int i) {
		return memberTestName[i];
	}

	public static final HotelPo HotelTestData(int i) {
		return hotelData.get(i);
	}
	
	public static final String MarketTestID(int i) {
		return marketTestID[i];
	}

	public static final String MarkettestName(int i) {
		return marketTestName[i];
	}

	public static final MarketPo MarketTestData(int i) {
		return marketData.get(i);
	}
}
