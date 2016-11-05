package presentation.view;

import java.util.List;

import javax.swing.text.View;

import vo.OrderVo;

public interface ProcessOrderViewControllerService {
	
	public int getHotelId();
	
	public void setView(ProcessOrderView view);
	
	public List<OrderVo> getAllOrders(int hotelId);
	
	public List<OrderVo> getUnfinishedOrders(int hotelId);
	
	public List<OrderVo> getFinishedOrders(int hotelId);
	
	public List<OrderVo> getAbnormalOrders(int hotelId);
	
	public boolean processUnfinishedOrder(int orderId);
	
	public boolean processAbnormalOrder(int orderId,String delayTime);
	
	public void updateListModel(String comboboxValue);
	
	public void processOrderButtonClicked();
	
	public void delayOrderButtonClicked();
}
