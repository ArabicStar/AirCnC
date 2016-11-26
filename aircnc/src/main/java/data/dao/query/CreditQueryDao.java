package data.dao.query;

import java.util.List;

import po.member.credit.CreditChangePo;

public interface CreditQueryDao {
	public List<CreditChangePo> searchByMemberId(String memberId);
}
