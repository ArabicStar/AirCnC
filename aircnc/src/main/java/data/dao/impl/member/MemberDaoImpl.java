package data.dao.impl.member;

import static utils.exception.StaticExceptionFactory.*;
import static data.hibernate.Hibernator.execute;

import data.dao.member.MemberDao;
import po.member.MemberPo;
import po.member.MemberPoBuilder;

/**
 * Implemention of MemberDao.<br>
 * 
 * @author ClevelandAlto
 *
 */
public enum MemberDaoImpl implements MemberDao {
	INSTANCE;

	@Override
	public boolean addMember(final MemberPo po) {
		if (po == null)
			return false;

		return execute(session -> {
			Boolean flag = Boolean.FALSE;

			// should not exist yet
			MemberPo test = (MemberPo) session.get(MemberPo.class, po.getId());
			if (flag = Boolean.valueOf((test == null))) {
				// save associated ContactPo firstly
				session.save(po.getContact());
				// save MemberPo
				session.save(po);
			}

			return flag;
		});
	}

	@Override
	public boolean deleteMember(final String id) {
		int numId = parseId(id);

		return execute(session -> {
			Boolean flag = Boolean.FALSE;// for performance

			MemberPo delMem = (MemberPo) session.get(MemberPo.class, numId);
			if (flag = Boolean.valueOf((delMem != null)))// check existence
			{
				// delete member po firstly
				session.delete(delMem);
				// then delete associated contact po
				session.delete(delMem.getContact());
			}

			return flag;
		});
	}

	@Override
	public boolean updateMember(final MemberPo po) {
		if (po == null)
			return false;

		return execute(session -> {
			Boolean flag = Boolean.FALSE;

			MemberPo mem = session.get(MemberPo.class, parseId(po.getId()));
			if (flag = Boolean.valueOf(mem != null))
				MemberPoBuilder.updatePo(po, mem);

			return flag;
		});
	}

	@Override
	public MemberPo findMember(final String idString) {
		int numId = parseId(idString);
		return execute(session -> (MemberPo) session.get(MemberPo.class, numId));
	}

	@Override
	public boolean existsMember(final String idString) {
		int numId = parseId(idString);
		return execute(session -> (MemberPo) session.get(MemberPo.class, numId) != null);
	}

	/* parse an id string. if invalid, throw IAE. */
	private static final int parseId(final String id) {
		if (!MemberPo.checkID(id))
			throw illegalArgEx("Member id", id);

		return Integer.parseInt(id);
	}
}
