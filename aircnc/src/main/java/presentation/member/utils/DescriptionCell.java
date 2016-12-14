package presentation.member.utils;

import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import presentation.member.model.CreditModel;
import vo.member.credit.CreditChangeVo;

public class DescriptionCell extends TableCell<CreditModel, CreditChangeVo>{
	
	String description;
	HBox total;
	List<Label> descriptions;
	
	public DescriptionCell(){
		
	}
	
}
