package po.hotel;


import java.util.ArrayList;
import java.util.List;


public class HotelTest {
	private int avaliableID = 10000000;
	private static final List<HotelPo> testData = new ArrayList<>();
	private static final String[] testType = new String[] { "Personal", "Personal", "Personal", "Business",
			"Business" };
	private static final int[] testStar = new int[] { 1, 2, 3, 4, 5 };
	private static final double[] testGrade = new double[] { 5, 4, 3, 4, 5 };
	private static final String[] testName = new String[] { "AAhotel", "BBhotel", "CChotel", "DDhotel", "EEhotel" };
	private static final String[] testID = new String[] { "11111111", "22222222", "33333333", "44444444", "55555555" };
	private static final String[] testScope = new String[] { "新街口", "仙林", "新街口","鼓楼", "啊" };
	private static final String[] testLocation= new String[] { "新街口", "仙林", "新街口","鼓楼", "啊" };
	private static final String[] testIntro = new String[] { "zzzzz", "buibuibui", "biubiubiu","uibuibuib", "kokookoko" };
	private static final int testPass = "12345678".hashCode();
	static {
		int i = 0;
		HotelPoBuilder b = new HotelPoBuilder().setName(testName[i]).setGrade(testGrade[i]).
				setID(testID[i]).setIntro(testIntro[i]).setPasswordHash(testPass).setScope(testScope[i])
				.setLocation(testLocation[i]);
		
	}
}
