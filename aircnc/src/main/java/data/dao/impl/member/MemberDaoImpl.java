package data.dao.impl.member;

import static data.hibernate.Hibernator.execute;

import data.dao.member.MemberDao;
import po.member.MemberPo;
import po.member.MemberPoBuilder;

/**
 * Implemention of MemberDao.<br>
 * 
 * @see MemberDao
 * @author ClevelandAlto
 *
 */
public class MemberDaoImpl implements MemberDao {

	@Override
	public boolean addMember(final MemberPo po) {
		if (po == null)
			return false;

		// should not exist yet
		if (existsMember(po.getId()))
			return false;

		return execute(session -> {
			// save associated ContactPo first
			session.save(po.getContact());
			// save MemberPo
			session.save(po);

			return true;
		});
	}

	@Override
	public boolean deleteMember(final String id) {
		return execute(session -> {
			Boolean flag = Boolean.FALSE;// for performance

			MemberPo deleted = (MemberPo) session.get(MemberPo.class, parseId(id));
			if (flag = Boolean.valueOf((deleted != null)))// check existence
			{
				session.delete(deleted);
				session.delete(deleted.getContact());
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
		return execute(session -> {
			return (MemberPo) session.get(MemberPo.class, parseId(idString));
		});
	}

	@Override
	public boolean existsMember(final String idString) {
		return execute(session -> {
			return (MemberPo) session.get(MemberPo.class, parseId(idString)) != null;
		});
	}

	/* parse an id string. if invalid, throw IAE. */
	private static final int parseId(final String id) {
		if (!MemberPo.checkID(id))
			throw new IllegalArgumentException("Wrong ID");

		return Integer.parseInt(id);
	}
}
