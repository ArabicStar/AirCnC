package interactor.impl.member;

import static interactor.utils.Dipatcher.execute;
import static interactor.utils.TitleGetter.getTitle;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import interactor.member.HotelSearchInteractor;
import interactor.utils.Title;
import presentation.manage.accessor.impl.HotelManageInfoAccessorImpl;
import presentation.manage.manager.impl.HotelManageInfoManagerImpl;
import presentation.member.accessor.impl.HotelSearchAccessorImpl;
import presentation.member.manager.impl.SearchHotelManagerImpl;
import service.promotion.HotelPromotionInfoService;
import service.query.CommentQueryService;
import service.query.HotelQueryService;
import vo.hotel.HotelVo;
import vo.order.comment.CommentVo;
import vo.promotion.PromotionVo;

public class HotelSearchCourier implements HotelSearchInteractor {
	private static HotelSearchInteractor instance;

	public static HotelSearchInteractor launch(HotelQueryService handler, CommentQueryService commentHelper,
			HotelPromotionInfoService promotionHelper) {
		/* singleton */
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelSearchCourier(handler, commentHelper, promotionHelper);
	}

	public static HotelSearchInteractor getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	/* singleton */

	private HotelQueryService handler;
	private CommentQueryService commentHelper;
	private HotelPromotionInfoService promotionHelper;

	/**
	 * @param handler
	 * @param commentHelper
	 * @param promotionHelper
	 */
	private HotelSearchCourier(HotelQueryService handler, CommentQueryService commentHelper,
			HotelPromotionInfoService promotionHelper) {
		super();
		this.handler = handler;
		this.commentHelper = commentHelper;
		this.promotionHelper = promotionHelper;
	}

	@Override
	@Title("Search Hotel By Id")
	public boolean searchById() {
		String title = getTitle();

		HotelVo hotel = attachMoreInfo(
				execute(title, () -> handler.findById(HotelManageInfoAccessorImpl.getInstance().getHotelId())));

		if (hotel != null) {
			HotelManageInfoManagerImpl.getInstance().setHotel(hotel);
			return true;
		}
		return false;
	}

	@Override
	@Title("Search Hotel By Condition")
	public boolean searchByCondition() {
		String title = getTitle();
		List<HotelVo> hotels = execute(title, //
				() -> handler.findByCondition//
				(HotelSearchAccessorImpl.getInstance().getCondition()))//
						.stream().peek(this::attachMoreInfo).collect(Collectors.toList());

		if (hotels != null) {
			SearchHotelManagerImpl.getInstance().setHotel(hotels);
			return true;
		}
		return false;
	}

	private HotelVo attachMoreInfo(HotelVo vo) {
		List<CommentVo> comment = commentHelper.getHotelComments(vo.getId());
		Set<PromotionVo> promotions = promotionHelper.getUserAvailableHotelPromotions(vo.getId());

		vo.setComments(comment);
		vo.setPromotions(promotions);

		return vo;
	}
}
