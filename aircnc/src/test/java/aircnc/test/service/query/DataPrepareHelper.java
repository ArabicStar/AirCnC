package aircnc.test.service.query;

import java.util.ArrayList;
import java.util.List;

import data.dao.impl.hotel.HotelDaoImpl;
import data.dao.impl.member.CreditDaoImpl;
import data.dao.impl.member.MemberDaoImpl;
import data.dao.member.MemberDao;
import data.dao.query.CreditQueryDao;
import data.dao.query.HotelQueryDao;
import javafx.util.converter.LocalDateStringConverter;
import po.member.MemberPo;
import po.member.MemberPoBuilder;
import service.impl.member.MemberInfoManager;
import service.impl.query.CreditQueryManager;
import service.impl.query.HotelQueryManager;
import service.query.CreditQueryService;
import service.query.HotelQueryService;
import service.query.MemberQueryService;
import utils.info.member.ContactInfo;
import vo.member.ContactVoBuilder;

public class DataPrepareHelper {
	/* test data */
	private static final List<MemberPo> testData = new ArrayList<>();
	private static final String[] testType = new String[] { "Personal", "Personal", "Personal", "Business",
			"Business" };
	private static final ContactInfo c = new ContactVoBuilder().getContactInfo();
	private static final int[] testCredit = new int[] { 100, 1000, 999, 8, 29102784 };
	private static final String[] testName = new String[] { "AA", "BB", "CC", "DD", "EE" };
	private static final String[] testID = new String[] { "11111111", "22222222", "33333333", "44444444", "55555555" };
	private static final String[] testExtra = new String[] { "1998-01-01", "1973-02-11", "2000-01-12",
			"Microsoft Ltd,.Co.", "Apple Inc." };
	private static final int testPass = "12345678".hashCode();
	static {
		for (int i = 0; i < 5; i++) {
			MemberPoBuilder b = new MemberPoBuilder(testType[i]).setName(testName[i]).setContactInfo(c)
					.setCredit(testCredit[i]).setId(testID[i]).setEnterprise(testExtra[i]).setPasswordHash(testPass);
			if (i <= 2)
				b.setBirthday(new LocalDateStringConverter().fromString(testExtra[i]));
			testData.add(b.getMemberInfo());
		}
	}
	/* test data */
	private static final CreditQueryDao creditDao = CreditDaoImpl.INSTANCE;
	private static final HotelQueryDao hotelDao = HotelDaoImpl.INSTANCE;
	private static final MemberDao memberDao = MemberDaoImpl.INSTANCE;
	public static final CreditQueryService cqs = CreditQueryManager.launch(creditDao);
	public static final HotelQueryService hqs = HotelQueryManager.launch(hotelDao);
	public static final MemberQueryService mqs = MemberInfoManager.launch(memberDao, null, null, null, null);

	public static final void prepareTestStatistic() {
		testData.forEach(memberDao::addMember);
	}

	public static final void dumpTestStatistic() {
		testData.forEach(d -> memberDao.deleteMember(d.getId()));
	}

	public static final String testID(int i) {
		return testID[i];
	}

	public static final String testName(int i) {
		return testName[i];
	}

	public static final MemberPo testData(int i) {
		return testData.get(i);
	}
}
