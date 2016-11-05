package vo;

import java.util.Vector;

import po.OrderPo;
import po.UserPo;

/**
 * orderNo		订单编号
 * orderInfo 	订单详情
 * userInfo		用户信息（用户名+信用值信息）
 * entryTime	用户入住时间
 * lastTime		订单最晚执行时间
 * orderStatus	订单状态
 * @author sunfan314
 */
public class OrderVo extends Vector<String>{
	
	public OrderVo(OrderPo orderPo,UserPo userPo){
		String userInfo = userPo.getUsername()+"("+userPo.getCredit()+")";
		this.add(String.valueOf(orderPo.getId()));
		this.add(orderPo.getOrderInfo());
		this.add(userInfo);
		String entryTime = orderPo.getEntryTime();
		this.add((entryTime.equals(""))?"尚未入住":entryTime);
		this.add(orderPo.getLastTime());
		switch (orderPo.getStatus()) {
		case 0://订单未执行
			this.add("订单未执行");
			break;
		case 1://订单已执行
			this.add("订单已执行");
			break;
		case 2://订单异常
			this.add("订单异常");
			break;
		default:
			this.add("异常订单状态");
			break;
		}
		this.add(String.valueOf(orderPo.getPrice()));
	}
	
	
	public OrderVo(int orderNo,String orderInfo, String userInfo, String entryTime,
			String lastTime, String orderStatus,int price) {
		this.add(String.valueOf(orderNo));
		this.add(orderInfo);
		this.add(userInfo);
		this.add(entryTime);
		this.add(lastTime);
		this.add(orderStatus);
		this.add(String.valueOf(price));
	}
	
	public int getOrderNo(){
		return Integer.valueOf(this.get(0));
	}
	
	public String getOrderInfo(){
		return this.get(1);
	}
	
	public String getUserInfo(){
		return this.get(2);
	}
	
	public String getEntryTime(){
		return this.get(3);
	}
	
	public String getLastTime(){
		return this.get(4);
	}
	
	public String getOrderStatus(){
		return this.get(5);
	}

	public int getPrice(){
		return Integer.valueOf(this.get(6));
	}
	
	
}
