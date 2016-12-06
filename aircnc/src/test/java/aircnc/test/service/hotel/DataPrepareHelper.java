package aircnc.test.service.hotel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import data.dao.hotel.HotelDao;
import javafx.util.converter.LocalDateStringConverter;
import po.hotel.HotelPo;
import po.hotel.HotelPoBuilder;
import po.hotel.RoomPo;
import po.hotel.RoomPoBuilder;

public class DataPrepareHelper {
	/* test data */
	private static final List<HotelPo> testData = new ArrayList<>();
	private static final int[] testStar = new int[] { 1, 2, 3, 4, 5 };
	private static final double[] testGrade = new double[] { 5, 4, 3, 4, 5 };
	private static final String[] testName = new String[] { "AAhotel", "BBhotel", "CChotel", "DDhotel", "EEhotel" };
	private static final String[] testScope = new String[] { "new way", "仙林", "新街口","鼓楼", "啊" };
	private static final String[] testLocation= new String[] { "newnewnew", "仙林", "新街口","鼓楼", "啊" };
	private static final String[] testIntro = new String[] { "zzzzz", "buibuibui", "biubiubiu","uibuibuib", "kokookoko" };
	private static final int testPass = "12345678".hashCode();
	private static final String[] testType = new String[]{"couple","double"};
	private static final int[] testPnum = new int[] {10,2};
	private static final int[] testRnum = new int[] {100,20};
	static {
		int i = 0;
		HotelPoBuilder b = new HotelPoBuilder().setName(testName[i]).setGrade(testGrade[i]).
				setIntro(testIntro[i]).setPasswordHash(testPass).setScope(testScope[i])
				.setLocation(testLocation[i]).setStar(testStar[i]);
//		b.setID(1);
		
		Set<RoomPo> rooms = new HashSet<RoomPo>();
 		for(int j = 0;j<2;j++){
 			RoomPoBuilder r = new RoomPoBuilder(testType[j]).setPeopleNum(testPnum[j]).setRoomNum(testRnum[j]);
 			rooms.add(r.getRoomInfo());
 		}
 		
 		b.setRooms(rooms);
 		
 		testData.add(b.getHotelInfo());
	}
	/* test data */

	public static final void prepareTestStatistic(HotelDao dao) {
		testData.forEach(dao::addHotel);
	}

	public static final void dumpTestStatistic(HotelDao dao) {
		testData.forEach(d -> dao.deleteHotel(d.getId()));
	}


	public static final String testName(int i) {
		return testName[i];
	}

	public static final HotelPo testData(int i) {
		return testData.get(i);
	}
}
