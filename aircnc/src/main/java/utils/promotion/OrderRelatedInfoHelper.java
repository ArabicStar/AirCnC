package utils.promotion;

import java.time.LocalDate;

public interface OrderRelatedInfoHelper {
	public int getMemberLevel(String memberId);

	public String getMemberEnterprise(String memberId);

	public LocalDate getMemberBirthday(String memberId);

	public String getHotelScope(final int hotelId);
}
