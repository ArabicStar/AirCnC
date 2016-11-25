package po.market;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class MarketTest {
	  
	 public static void main(String[]args){  
	        //读取hibernate.cfg.xml文件  
	        Configuration cfg = new Configuration().configure();  
	        //建立SessionFactory  
	        SessionFactory factory =cfg.buildSessionFactory();  
	          
	        //取得session  
	        Session session = null;  
	          
	        try{  
	            //开启session  
	            session = factory.openSession();  
	            //开启事务  
	            session.beginTransaction();  
	              
	            MarketPo user = new MarketPo();
	            user.setId("12345678");
	            user.setName("我是小管理～");  
	            user.setPasswordHash(123456);  
 
	            //保存User对象  
	            session.save(user);  
	              
	            //提交事务  
	            session.getTransaction().commit();  
	              
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
