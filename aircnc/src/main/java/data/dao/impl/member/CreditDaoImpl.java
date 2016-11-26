package data.dao.impl.member;

import data.dao.member.CreditDao;
import data.dao.member.MemberDao;
import po.member.MemberPo;
import po.member.MemberPoBuilder;
import po.member.credit.CreditChangePo;
import service.query.CreditChangeQueryService;
import vo.member.credit.CreditChangeVo;

import static data.hibernate.Hibernator.execute;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * Implemention of CreditDao.<br>
 * 
 * @author ClevelandAlto
 *
 */
public class CreditDaoImpl implements CreditDao, CreditChangeQueryService {
	private MemberDao memberDao;

	public CreditDaoImpl(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Override
	public MemberPo changeCredit(CreditChangePo aChange) {
		if (aChange == null)
			return null;

		MemberPo po = memberDao.findMember(aChange.getMemberId());
		// existence check
		if (po == null)
			return null;

		// consistency check
		if (po.getCredit() != aChange.getBeforeCredit())
			// return invalid MemberPo instance to mark
			return MemberPoBuilder.invalidInfo();

		// build a new MemberPo instance with changed credit value
		MemberPo newOne = new MemberPoBuilder(po).setCredit(aChange.getAfterCredit()).getMemberInfo();

		return execute(session -> {
			// Not using MemberDao.updateMember() to assure transaction
			// integrity. When encounter exception, rollback can be invoked
			// properly.
			session.update(newOne);// update member credit value
			session.save(aChange);// add credit change record
			return newOne;
		});
	}

	@Override
	public List<CreditChangeVo> searchByMemberId(String memberId) {
		if (!MemberPo.checkID(memberId))
			throw new IllegalArgumentException(
					"MemberCreditDaoImpl.searchByMemberId : null or invalid member id string");

		if (!memberDao.existsMember(memberId))
			return null;

		int numMemId = MemberPo.convertID2Num(memberId);

		return execute(session -> {
			Criteria criteria = session.createCriteria(CreditChangePo.class);
			Criterion c1 = Restrictions.eq("numMemId", numMemId);
			criteria.add(c1);

			@SuppressWarnings("unchecked")
			List<CreditChangeVo> l = criteria.list();

			return l;
		});
	}
}
