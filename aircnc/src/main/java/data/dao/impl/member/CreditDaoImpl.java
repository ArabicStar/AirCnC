package data.dao.impl.member;

import static data.hibernate.Hibernator.execute;
import static utils.exception.StaticExceptionFactory.*;
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

		int numMemId = parseId(changePo.getMemberId());

		return execute(session -> {
			// retrive member po
			MemberPo mem = session.get(MemberPo.class, numMemId);
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

	@Override
	public int getMemberCredit(String memberId) {
		int numMemId = parseId(memberId);

		return execute(session -> {
			MemberPo po;
			if ((po = session.get(MemberPo.class, numMemId)) == null)
				return MemberPo.WRONG_CREDIT;

			return po.getCredit();
		});
	}

	/* parse an id string. if invalid, throw IAE. */
	private static final int parseId(final String id) {
		if (!MemberPo.checkID(id))
			throw illegalArgEx("member id");

		return Integer.parseInt(id);
	}
}
