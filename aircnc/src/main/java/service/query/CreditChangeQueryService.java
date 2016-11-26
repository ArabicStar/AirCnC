package service.query;

import java.util.List;

import vo.member.credit.CreditChangeVo;

public interface CreditChangeQueryService {
	public List<CreditChangeVo> searchByMemberId(String memberId);
}
