package data.dao;

import po.member.MemberPo;
import po.member.credit.CreditChangePo;

public interface CreditDao {
	public MemberPo addCreditChange(CreditChangePo aChange);
}
