package service.impl.promotion;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.illegalArgEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.time.LocalDate;

import data.dao.member.MemberDao;
import data.dao.query.HotelQueryDao;
import service.query.HotelQueryService;
import service.query.MemberQueryService;
import utils.info.hotel.HotelInfo;
import utils.info.member.MemberInfo;
import utils.promotion.OrderRelatedInfoHelper;

public class OrderRelateInfoManager implements OrderRelatedInfoHelper {
	/* Singleton */
	private static OrderRelateInfoManager instance;

	public static OrderRelateInfoManager launch(MemberDao memberQuery, HotelQueryDao hotelQuery) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new OrderRelateInfoManager(memberQuery, hotelQuery);
	}

	public static OrderRelateInfoManager getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* Singleton */

	/**
	 * @param memberQuery
	 * @param hotelQuery
	 */
	private OrderRelateInfoManager(MemberDao memberQuery, HotelQueryDao hotelQuery) {
		super();
		this.memberQuery = memberQuery;
		this.hotelQuery = hotelQuery;
	}

	private MemberDao memberQuery;
	private HotelQueryDao hotelQuery;

	@Override
	public int getMemberLevel(String memberId) {
		if (!MemberInfo.checkID(memberId))
			throw illegalArgEx("Member id");

		MemberInfo info = null;
		// FIXME: Member's level calculate
		return (info = memberQuery.findMember(memberId)) == null ? Integer.MIN_VALUE : (info.getCredit() / 100);
	}

	@Override
	public String getMemberEnterprise(String memberId) {
		if (!MemberInfo.checkID(memberId))
			throw illegalArgEx("Member id");

		MemberInfo info = null;
		return (info = memberQuery.findMember(memberId)) == null ? null : info.getEnterprise();
	}

	@Override
	public LocalDate getMemberBirthday(String memberId) {
		if (!MemberInfo.checkID(memberId))
			throw illegalArgEx("Member id");

		MemberInfo info = null;
		return (info = memberQuery.findMember(memberId)) == null ? null : info.getBirthday();
	}

	@Override
	public String getHotelScope(int hotelId) {
		if (!HotelInfo.checkID(hotelId))
			throw illegalArgEx("Hotel id");

		HotelInfo info = null;
		return (info = hotelQuery.searchById(hotelId)) == null ? null : info.getScope();
	}

}
