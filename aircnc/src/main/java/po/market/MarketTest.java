package po.market;

import static data.hibernate.HibernateSessionFactory.getSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MarketTest {
	  
	 public static void main(String[]args){  
		 
	        Session session = getSession();
	        
	        Transaction ts = null; 
	        try{  
	            //开启session  
	            //开启事务  
	            ts=session.beginTransaction();  
	            
	            int pwdHash = "12345678".hashCode();
	              
	            MarketPo po = new MarketPo().setId("00123456").setName("market1").setPasswordHash(pwdHash); 
	            MarketPo po2 = new MarketPo().setId("00111111").setName("market2").setPasswordHash("6666".hashCode());
	            //保存User对象  
	            session.save(po2);
	            session.delete(po2);
	              
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
