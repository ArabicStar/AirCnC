package data.dao.impl.member;

import static data.hibernate.Hibernator.execute;

import data.dao.member.MemberDao;
import po.member.MemberPo;

/**
 * Implemention of MemberDao.<br>
 * @see MemberDao
 * @author ClevelandAlto
 *
 */
public class MemberDaoImpl implements MemberDao {

	@Override
	public boolean addMember(final MemberPo po) {
		if (po == null)
			return false;

		return execute(session -> {
			boolean flag = false;

			// member id shoud not exist
			if (flag = (!existsMember(po.getId()))) {
				// persist associated ContactPo first
				session.persist(po.getContact());
				// the save MemberPo
				session.save(po);
			}

			return flag;
		});
	}

	@Override
	public boolean deleteMember(final String id) {
		return execute(session -> {
			Boolean flag = Boolean.FALSE;// for performance

			MemberPo deleted = (MemberPo) session.get(MemberPo.class, parseId(id));
			if (flag = Boolean.valueOf((deleted != null)))// check existence
				session.delete(deleted);

			return flag;
		});
	}

	@Override
	public boolean updateMember(final MemberPo po) {
		if (po == null)
			return false;

		return execute(session -> {
			Boolean flag = Boolean.FALSE;

			MemberPo member = (MemberPo) session.get(MemberPo.class, parseId(po.getId()));
			if (flag = Boolean.valueOf(member != null))// check existence
				session.update(po);

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
			return (MemberPo) session.get(MemberPo.class, parseId(idString)) == null;
		});
	}

	/* parse an id string. if invalid, throw IAE. */
	private static final int parseId(final String id) {
		if (!MemberPo.checkID(id))
			throw new IllegalArgumentException("Wrong ID");

		return Integer.parseInt(id);
	}
}
