package data.dao.impl.member;

import static data.hibernate.Hibernator.execute;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import data.dao.member.CreditDao;
import data.dao.query.CreditQueryDao;
import po.member.MemberPo;
import po.member.MemberPoBuilder;
import po.member.credit.CreditChangePo;

/**
 * Implemention of <b>CreditDao</b> and <b>CreditQueryDao</b>.<br>
 * 
 * @author ClevelandAlto
 *
 */
public enum CreditDaoImpl implements CreditDao, CreditQueryDao {
	INSTANCE;

	@Override
	public MemberPo changeCredit(final CreditChangePo changePo) {
		if (changePo == null)
			return null;

		return execute(session -> {
			// retrive member po
			MemberPo mem = session.get(MemberPo.class, parseId(changePo.getMemberId()));
			// existence check
			if (mem == null)
				return null;

			// consistency check
			if (mem.getCredit() != changePo.getBeforeCredit())
				// return invalid MemberPo instance to mark
				return MemberPoBuilder.invalidInfo();

			// update member credit value
			mem.setCredit(changePo.getAfterCredit());
			// add credit change record
			session.save(changePo);
			return mem;
		});
	}

	@Override
	public List<CreditChangePo> searchByMemberId(final String memberId) {
		if (!MemberPo.checkID(memberId))
			throw new IllegalArgumentException("CreditDaoImpl.searchByMemberId : null or invalid member id string");

		int numMemId = parseId(memberId);

		return execute(session -> {
			// use null to discriminate from the condition where member has no
			// credit change record.
			if (session.get(MemberPo.class, numMemId) == null)
				return null;

			Criteria criteria = session.createCriteria(CreditChangePo.class);
			// by member id
			Criterion memIdCriterion = Restrictions.eq("numMemId", numMemId);
			criteria.add(memIdCriterion);

			@SuppressWarnings("unchecked")
			List<CreditChangePo> list = criteria.list();

			return list;
		});
	}

	/* parse an id string. if invalid, throw IAE. */
	private static final int parseId(final String id) {
		if (!MemberPo.checkID(id))
			throw new IllegalArgumentException("CreditDaoImpl.parseId - Wrong ID");

		return Integer.parseInt(id);
	}
}
