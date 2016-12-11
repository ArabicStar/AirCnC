package presentation.member.accessor;

import java.time.LocalDate;

public interface RegisterPersonAccessor extends RegisterAccessor {
	public void setBirthday(LocalDate date);

}
