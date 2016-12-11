package presentation.member.accessor;

import vo.member.MemberVoBuilder;

public interface RegisterAccessor {
	public MemberVoBuilder getNewAccountInfo();

	public int getPasswordHash();
	
	public void setUsername(String name);

	public void setPassword(String password);
}
