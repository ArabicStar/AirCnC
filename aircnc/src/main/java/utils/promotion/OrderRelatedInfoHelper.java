package utils.promotion;

import java.io.Serializable;
import java.time.LocalDate;

public interface OrderRelatedInfoHelper extends Serializable{
	public int getMemberLevel(String memberId);

	public String getMemberEnterprise(String memberId);

	public LocalDate getMemberBirthday(String memberId);

	public String getHotelScope(final int hotelId);
}
