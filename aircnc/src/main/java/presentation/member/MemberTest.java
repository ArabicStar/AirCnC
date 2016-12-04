package presentation.member;

import java.time.LocalDate;

import presentation.member.manager.UserInfoManager;
import presentation.member.manager.impl.MemberInfoManagerImpl;
import vo.member.ContactVoBuilder;
import vo.member.MemberVo;
import vo.member.MemberVoBuilder;

/**
 * 这是一个迟早要删掉的类，我就不用英文勒，这里就模拟的是member的逻辑层
 * @author paranoia
 *
 */
public class MemberTest {
	
	public static UserInfoManager getUserData(){
		UserInfoManager memberInfoManager = new MemberInfoManagerImpl();
		MemberVoBuilder builder =  new MemberVoBuilder("PERSONAL").setId("00002222").setName("hhh").setBirthday(LocalDate.parse("1998-04-17"))
				.setContactInfo(new ContactVoBuilder().setEmail("12345@qq.com").setFixedPhone("0511-12344444")
						.setMobilePhone("13822222222").getContactInfo()).setCredit(0);
		MemberVo vo = builder.getMemberInfo();
		memberInfoManager.setUser(vo);
		return memberInfoManager;
	}
	
}
