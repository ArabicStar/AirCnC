package service.impl.manage;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.illegalArgEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;
import static utils.exception.StaticExceptionFactory.unsupportedOpEx;

import data.dao.member.MemberDao;
import po.member.MemberPo;
import po.member.MemberPoBuilder;
import service.manage.ManageMemberService;
import utils.info.member.MemberInfo;
import vo.member.MemberVoBuilder;

public class ManageMemberManager implements ManageMemberService{
	private static ManageMemberService instance;

	public static ManageMemberService launch(MemberDao dao) {
		if (instance != null)
			throw duplicateSingletonEx();

		instance = new ManageMemberManager(dao);
		return instance;
	}

	public static ManageMemberService getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	private MemberDao dao;
	
	private ManageMemberManager(MemberDao dao) {
		this.dao = dao;
	}
	
	@Override
	public boolean ModifyMemberInfo(MemberInfo memberInfo) {
		if (dao == null)
			throw unsupportedOpEx("manage member advanced info");

		if (memberInfo == null || !memberInfo.isValid())
			throw illegalArgEx("null or invalid member info");

		final MemberPo po = dao.findMember(memberInfo.getId());

		if (po == null)// not exist
			return false;

		int comp = MemberVoBuilder.compareMemberInfo(memberInfo, po);

		// modify basic info: denied
		if ((comp & 1) != 0)
			throw unsupportedOpEx("update basic member info");

		// no modification: return
		if ((comp & 2) == 0)
			return true;

		return dao.updateMember((new MemberPoBuilder(memberInfo).setPasswordHash(po.getPasswordHash()).getMemberInfo()));
	}

	@Override
	public MemberInfo getMemberInfo(String id) {
		if (dao == null)
			throw unsupportedOpEx("get manage member info");

		if (!MemberInfo.checkID(id))
			throw illegalArgEx("Member id");

		final MemberPo po = dao.findMember(id);

		return po == null ? null : new MemberVoBuilder(dao.findMember(id)).getMemberInfo();
	}

}
