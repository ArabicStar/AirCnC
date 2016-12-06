package po.hotel;


import static data.hibernate.HibernateSessionFactory.getSession;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;


public class HotelTest {
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
	private static final int[] testRnum = new int[] {100,20 };
 	public static void main(String args[]){
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
 		
 		HotelPo po = b.getHotelInfo();
 		
 		boolean flag = false;
		Session session = getSession();

		Transaction ts = null;
		try {
			// normal
			ts = session.beginTransaction();

			if (!(flag = session.contains(po))) {
				session.save(po);
				HotelPo po2 = (HotelPo) session.createCriteria(HotelPo.class).add(Restrictions.eq("name",po.getName())).list().get(0);;
				System.out.println(po.getName()==po2.getName());
				session.delete(po);
			}

			ts.commit();

		} catch (HibernateException he) {
			// exception
			he.printStackTrace();

			if (ts != null)
				ts.rollback();

		} finally {
			// close
			session.close();
		}
 		
		
		
	}
}
