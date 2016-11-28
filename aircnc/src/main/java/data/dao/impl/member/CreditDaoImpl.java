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
	@Override
	public MemberPo changeCredit(CreditChangePo aChange) {
		if (aChange == null)
			return null;

		return execute(session -> {
			MemberPo mem = session.get(MemberPo.class, parseId(aChange.getMemberId()));
			// existence check
			if (mem == null)
				return null;

			// consistency check
			if (mem.getCredit() != aChange.getBeforeCredit())
				// return invalid MemberPo instance to mark
				return MemberPoBuilder.invalidInfo();

			// update member credit value
			mem.setCredit(aChange.getAfterCredit());
			// add credit change record
			session.save(aChange);
			return mem;
		});
	}

	@Override
	public List<CreditChangeVo> searchByMemberId(String memberId) {
		if (!MemberPo.checkID(memberId))
			throw new IllegalArgumentException(
					"MemberCreditDaoImpl.searchByMemberId : null or invalid member id string");

		int numMemId = parseId(memberId);

		return execute(session -> {
			// use null to discriminate from the condition where member has no
			// credit change record.
			if (session.get(MemberPo.class, numMemId) == null)
				return null;

			Criteria criteria = session.createCriteria(CreditChangePo.class);
			Criterion c1 = Restrictions.eq("numMemId", numMemId);
			criteria.add(c1);

			@SuppressWarnings("unchecked")
			List<CreditChangeVo> l = criteria.list();

			return l;
		});
	}

	/* parse an id string. if invalid, throw IAE. */
	private static final int parseId(final String id) {
		if (!MemberPo.checkID(id))
			throw new IllegalArgumentException("Wrong ID");

		return Integer.parseInt(id);
	}
}
