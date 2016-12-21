package po.hotel;

import static data.hibernate.HibernateSessionFactory.getSession;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import utils.info.hotel.Room;
import utils.info.hotel.RoomBuilder;
import utils.info.hotel.RoomTemplate.Type;

public class HotelTest {
	private static final List<HotelPo> testData = new ArrayList<>();
	private static final int[] testStar = new int[] { 1, 2, 3, 4, 5 };
	private static final double[] testGrade = new double[] { 5, 4, 3, 4, 5 };
	private static final String[] testName = new String[] { "DDhotel", "BBhotel", "CChotel", "DDhotel", "EEhotel" };
	private static final String[] testScope = new String[] { "new way", "仙林", "新街口", "鼓楼", "啊" };
	private static final String[] testLocation = new String[] { "newnewnew", "仙林", "新街口", "鼓楼", "啊" };
	private static final String[] testIntro = new String[] { "zzzzz", "buibuibui", "biubiubiu", "uibuibuib",
			"kokookoko" };
	private static final int testPass = "12345678".hashCode();
	// private static final String[] testType = new String[] { "单人间", "双人间" };
	private static final Type[] testType = new Type[] { Type.单人间, Type.双人间 };
	private static final int[] testPnum = new int[] { 10, 2 };
	private static final int[] testRnum = new int[] { 100, 20 };

	public static void main(String args[]) {
		int i = 0;
		HotelPoBuilder b = new HotelPoBuilder().setName(testName[i]).setGrade(testGrade[i]).setIntro(testIntro[i])
				.setPasswordHash(testPass).setScope(testScope[i]).setLocation(testLocation[i]).setStar(testStar[i]);

		Set<Room> rooms = new HashSet<Room>();

		for (int j = 0; j < 2; j++) {
			RoomBuilder r = new RoomBuilder(testType[j]).setPrice(300).setRoomNum(testRnum[j]);
			Room room = r.getRoomInfo();
			rooms.add(room);
		}

		b.setRooms(rooms);
		HotelPo po = b.getHotelInfo();
		po.setId(15);
		Session session = getSession();
		Transaction ts = null;
		try {
			// 开启session
			// 开启事务
			ts = session.beginTransaction();

			session.save(po);
//			HotelPo old = session.get(HotelPo.class, 15);
//			HotelPoBuilder.updatePo(po, old);
			// 提交事务
			ts.commit();

		} catch (Exception e) {
			e.printStackTrace();
			// 回滚事务
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				if (session.isOpen()) {
					// 关闭session
					session.close();
				}
			}
		}

		System.out.println(po);
	}
}
