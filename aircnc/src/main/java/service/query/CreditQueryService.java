package service.query;

import java.util.List;

import vo.member.credit.CreditChangeVo;

public interface CreditQueryService {
	public int getMemberCredit(String memId);

	public List<CreditChangeVo> searchByMemberId(String memberId);
}
