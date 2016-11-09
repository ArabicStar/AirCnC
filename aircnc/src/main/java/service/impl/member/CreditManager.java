package service.impl.member;

import data.dao.CreditDao;
import data.dao.MemberDao;
import po.member.MemberPo;
import po.member.credit.CreditChangePoBuilder;
import service.member.MemberCreditService;
import utils.info.member.credit.ActionProperties;
import utils.info.member.credit.ActionType;
import vo.member.MemberVo;
import vo.member.MemberVoBuilder;

public class CreditManager implements MemberCreditService {
	private MemberDao memberDao;
	private CreditDao creditDao;

	@Override
	public MemberVo changeCredit(String id, int creditDelta, ActionType actionType) {
		if (validateOperation(actionType, creditDelta))
			return MemberVoBuilder.getInvalidInfo();

		MemberPo memberPo = memberDao.findMember(id);
		if (null == memberPo)
			return MemberVoBuilder.getInvalidInfo();

		CreditChangePoBuilder builder = new CreditChangePoBuilder(memberPo, actionType);
		builder.setCreditChange(creditDelta);
		builder.setProperties(collectProperties(actionType));

		creditDao.addCreditChange(builder.getCreditChangeInfo());

		return new MemberVoBuilder(memberPo).getMemberInfo();
	}

	private ActionProperties collectProperties(ActionType actionType) {
		return null;
	}

	private static final boolean validateOperation(ActionType actionType, int creditDelta) {
		return actionType.validateChangeValue(creditDelta);
	}
}
