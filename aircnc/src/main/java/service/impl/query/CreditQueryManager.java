package service.impl.query;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.illegalArgEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.List;
import java.util.stream.Collectors;

import data.dao.query.CreditQueryDao;
import po.member.credit.CreditChangePo;
import service.query.CreditQueryService;
import utils.info.member.MemberInfoTemplate;
import vo.member.credit.CreditChangeVo;
import vo.member.credit.CreditChangeVoBuilder;

public final class CreditQueryManager implements CreditQueryService {
	private static CreditQueryService instance;

	public static CreditQueryService launch(CreditQueryDao dao) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new CreditQueryManager(dao);
	}

	public static final CreditQueryService getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	private CreditQueryManager(CreditQueryDao queryDao) {
		this.queryDao = queryDao;
	}

	private CreditQueryDao queryDao;

	@Override
	public int getMemberCredit(String memberId) {
		if (!MemberInfoTemplate.checkID(memberId))
			throw illegalArgEx("member id");

		return queryDao.getMemberCredit(memberId);
	}

	@Override
	public List<CreditChangeVo> searchByMemberId(String memberId) {
		if (!MemberInfoTemplate.checkID(memberId))
			throw illegalArgEx("member id");

		List<CreditChangePo> poList = queryDao.searchByMemberId(memberId);
		if (poList == null)
			return null;

		return poList.stream().map(po -> new CreditChangeVoBuilder(po).getCreditChangeInfo())
				.collect(Collectors.toList());
	}

}
