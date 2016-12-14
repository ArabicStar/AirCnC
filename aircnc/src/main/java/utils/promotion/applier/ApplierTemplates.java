package utils.promotion.applier;

import utils.promotion.Describer;
import utils.property.ParametersList;
import vo.order.OrderVoBuilder;

public abstract class ApplierTemplates {

	public static final ParametersList getParametersTemplate(How how) {
		switch (how) {
		case CONST:
			ParametersList params1 = new ParametersList();
			params1.addParameter(ApplierParams.AMOUNT.paramName(), Double.class, d -> d >= 0);
			return params1;

		case PERCENT_OFF:
			ParametersList params2 = new ParametersList();
			params2.addParameter(ApplierParams.PERCENT.paramName(), Double.class, d -> 0 <= d && d <= 1);
			return params2;
		default:
			return null;
		}
	}

	public static final Describer getDescriber(How how) {
		switch (how) {
		case CONST:
			return params -> new StringBuilder("减")
					.append((String) params.getParameterValue(ApplierParams.AMOUNT.paramName())).append("元").toString();

		case PERCENT_OFF:
			return params -> String.format("%d", (double) params.getParameterValue(ApplierParams.PERCENT.paramName()))
					+ "折";
		default:
			return null;
		}
	}

	public static final Discount getDiscount(How how) {
		switch (how) {
		case CONST:
			return constDiscount();
		case PERCENT_OFF:
			return percentDiscount();
		default:
			return null;
		}

	}

	private static final Discount constDiscount() {
		return (params, info) -> new OrderVoBuilder(info)
				.setDiscountPrice(
						info.getDiscountPrice() - (double) params.getParameterValue(ApplierParams.PERCENT.paramName()))
				.getOrderInfo();
	}

	private static final Discount percentDiscount() {
		return (params, info) -> new OrderVoBuilder(info).setDiscountPrice(info.getDiscountPrice()
				- (1.0 - (double) params.getParameterValue(ApplierParams.AMOUNT.paramName())) * info.getOriginalPrice())
				.getOrderInfo();
	}
}
