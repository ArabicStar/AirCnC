package utils.promotion;

public abstract class HotelBusinessTrigger extends HotelTrigger {
	private String targetEnterprise;

	public HotelBusinessTrigger(String targetEnterprise) {
		super(When.BUSINESS);
		this.targetEnterprise = targetEnterprise;
	}

	@Override
	public String when() {
		return new StringBuilder(targetEnterprise).append("的企业会员").toString();
	}

}
