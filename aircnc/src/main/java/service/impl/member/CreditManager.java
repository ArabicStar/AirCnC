package service.impl.member;

import data.dao.CreditDao;
import data.dao.MemberDao;
import po.member.MemberPo;
import po.member.credit.CreditChangePoBuilder;
import service.member.MemberCreditService;
import utils.info.member.credit.ChangeAction;
import vo.member.MemberVo;
import vo.member.MemberVoBuilder;

public class CreditManager implements MemberCreditService {
	private MemberDao memberDao;
	private CreditDao creditDao;

	@Override
	public MemberVo changeCredit(String id, int creditDelta, ChangeAction action) {
		if (action.validateChangeValue(creditDelta))
			return MemberVoBuilder.getInvalidInfo();

		MemberPo memberPo = memberDao.findMember(id);
		if (null == memberPo)
			return MemberVoBuilder.getInvalidInfo();

		CreditChangePoBuilder builder = new CreditChangePoBuilder(memberPo, action.getActionType());
		builder.setCreditChange(creditDelta);
		builder.setProperties(action.getProperties());

		creditDao.addCreditChange(builder.getCreditChangeInfo());

		return new MemberVoBuilder(memberPo).getMemberInfo();
	}

}
