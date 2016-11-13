package data.dao.impl;

import java.util.ArrayList;
import java.util.List;

import data.dao.MemberDao;
import javafx.util.converter.LocalDateStringConverter;
import po.member.MemberPo;
import po.member.MemberPoBuilder;
import utils.info.member.ContactInfo;
import vo.member.ContactVoBuilder;

public class MemberDaoImpl implements MemberDao {
	/* test data */
	private int avaliableID = 10000000;
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
					.setCredit(testCredit[i]).setID(testID[i]).setEnterprise(testExtra[i]).setPasswordHash(testPass);
			if (i <= 2)
				b.setBirthday(new LocalDateStringConverter().fromString(testExtra[i]));
			testData.add(b.getMemberInfo());
		}
	}
	/* test data */

	@Override
	public boolean addMember(MemberPo po) {
		if (po == null)
			return false;

		if (existsMember(po.getID()))
			return false;

		return testData.add(po);
	}

	@Override
	public boolean deleteMember(String id) {
		if (!existsMember(id))
			return false;

		return testData.remove(findMember(id));
	}

	@Override
	public boolean updateMember(MemberPo po) {
		if (po == null)
			return false;

		MemberPo old = findMember(po.getID());
		if (old == null)
			return false;

		deleteMember(old.getID());
		addMember(po);
		return true;
	}

	@Override
	public MemberPo findMember(final String id) {
		for (MemberPo p : testData)
			if (p.getID().equals(id))
				return p;

		return null;
	}

	@Override
	public boolean existsMember(String id) {
		return testData.parallelStream().map(MemberPo::getID).anyMatch(s -> s.equals(id));
	}

	@Override
	public int getAvaliableID() {
		return avaliableID++;
	}

}
