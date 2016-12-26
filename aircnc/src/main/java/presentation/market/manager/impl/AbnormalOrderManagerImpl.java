package presentation.market.manager.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import presentation.hotel.model.OrderModel;
import presentation.market.manager.AbnormalOrderManager;
import presentation.market.manager.MarketPromotionManager;
import presentation.market.model.WebsitePromotionModel;
import vo.order.OrderVo;
import vo.promotion.PromotionVo;

public class AbnormalOrderManagerImpl implements AbnormalOrderManager{

	private static AbnormalOrderManagerImpl instance;
	
	private List<OrderVo> orders;
	
//	private ObservableList<WebsitePromotionModel> promotionData;

	
	public static final AbnormalOrderManager launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new AbnormalOrderManagerImpl();
	}
	
	public static final AbnormalOrderManager getInstance(){
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	@Override
	public void setAbnormalOrders(List<OrderVo> orders) {
		this.orders = orders;
		
	}

	@Override
	public ObservableList<OrderModel> getOrderList() {
		// TODO Auto-generated method stub
		return null;
	}

}
