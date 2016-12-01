package po.order;

import static data.hibernate.HibernateSessionFactory.getSession;

import java.time.LocalDateTime;

import org.hibernate.Session;
import org.hibernate.Transaction;

import utils.info.order.OrderStatus;

public class OrderTest {
	public static void main(String[]args){  
		 
        Session session = getSession();
        
        Transaction ts = null; 
        try{  
            //开启session  
            //开启事务  
            ts=session.beginTransaction();  
            
            LocalDateTime entryTime = LocalDateTime.now();
            
            OrderPo po2 = new OrderPoBuilder().setEntryTime(entryTime).setHasChildren(false)
            		.setHotelId(1000).setHotelName("乐天玛特").setLastTime(entryTime)
            		.setOrderId("2016110110000020").setPeopleNumber(3).setPrice(200)
            		.setReviewed(true).setRoomNumber(1).setRoomType("标准间")
            		.setStatus(OrderStatus.EXECUTED).setStayDays(2).setUserId(20808121)
            		.getOrderInfo();
            
            
            //保存User对象  
            session.save(po2);
//            session.delete(po2);
              
            //提交事务  
            ts.commit();  
              
        }catch(Exception e){  
            e.printStackTrace();  
            //回滚事务  
            session.getTransaction().rollback();  
        }finally{  
            if(session != null){  
                if(session.isOpen()){  
                    //关闭session  
                    session.close();  
                }  
            }  
        }  
    }
}
