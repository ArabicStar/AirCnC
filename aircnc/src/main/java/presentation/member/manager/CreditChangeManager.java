package presentation.member.manager;

import java.util.List;

import javafx.collections.ObservableList;
import presentation.member.model.CreditModel;
import vo.member.credit.CreditChangeVo;
import vo.order.OrderVo;

public interface CreditChangeManager {
	
	public boolean setCreditChanges(List<CreditChangeVo> list);
	
	public ObservableList<CreditModel> getCreditList();
	
	public boolean setCauseOrder(OrderVo vo);
	
	public String getCauseHotelName();
	
	public String getCauseTime();
}
