package presentation.hotel.view.hotelPromotion.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import vo.promotion.PromotionVo;

public class PromotionDetailController implements Initializable{
	
	@FXML
	private ComboBox<String> when;
	
	@FXML
	private ComboBox<String> how;
	
	@FXML
	private TextField howPara;
	
	@FXML
	private TextField whenPara;
	
	@FXML
	private Label whenParaName;
	
	@FXML
	private Button operate;
	
	@FXML
	private GridPane timeBox;
	
	private PromotionVo vo = null;
	
	private DatePicker from;
	
	private DatePicker to;
	
	private HotelPromotionMainController controller;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
	}
	
	private void initPromotion(){
		from = new DatePicker();
		to = new DatePicker();
		timeBox.add(from, 1, 0);
		timeBox.add(to, 1, 1);
		
		whenParaName.setText("策略参数");
		whenPara.setDisable(true);
		howPara.setDisable(true);
		from.setDisable(true);
		to.setDisable(true);
		
		when.getItems().addAll("生日优惠","合作企业优惠","多间房优惠","时效性优惠");
		when.valueProperty().addListener((observable, oldValue, newValue) -> {
		  	if(oldValue!=newValue){
		  		switch (newValue){
		  		case "时效性优惠":
		  			from.setDisable(false);
		  			to.setDisable(false);
		  			whenParaName.setText("时效名称");
		  			whenPara.setPromptText("双十一优惠");
		  			whenPara.setDisable(false);
		  			break;
		  		default:
		  			from.setDisable(true);
		  			to.setDisable(true);
		  			switch (newValue){
		  			case "多间房优惠":
			  			whenParaName.setText("房间数量");
//			  			whenPara.setText(null);
			  			whenPara.setPromptText("4");
			  			whenPara.setDisable(false);
		  				break;
		  			case "合作企业优惠":
		  				whenParaName.setText("企业名称");
			  			whenPara.setPromptText("南京大学");
			  			whenPara.setDisable(false);
		  				break;
		  			case "生日优惠":
		  				whenParaName.setText("优惠名称");
			  			whenPara.setText("生日优惠");;
			  			whenPara.setDisable(true);
		  				break;
		  			}
		  			break;
		  		}
			}
		});
		
		how.getItems().addAll("原价折扣","直接降价");
		how.valueProperty().addListener((observable, oldValue, newValue) -> {
		  	if(oldValue!=newValue){
		  		switch (newValue){
		  		case "原价折扣":
		  			howPara.setDisable(false);
		  			howPara.setPromptText("0.8");
		  			break;
		  		default:
		  			howPara.setDisable(false);
		  			howPara.setPromptText("100");		  			
		  		}
			}
		});
		
		
		if(vo==null){
			operate.setText("添加");
			return;
		}else{
			operate.setText("保存");
			switch (vo.getPromotion().getTrigger().when()){
			case "BIRTHDAY":
				when.setValue("生日优惠");
				break;
			case "ENTERPRISE":
				when.setValue("合作企业优惠");
				break;
			case "DURING_PERIOD":
				when.setValue("时效性优惠");
				break;
			case "MULTI_ROOMS":
				when.setValue("多间房优惠");
				break;
			default:
				
				break;
			}
			
			switch (vo.getPromotion().getApplier().how()){
			case "CONST":
				how.setValue("直接降价");
				break;
			case "PERCENT_OFF":
				how.setValue("原价折扣");
				break;
			default:
				break;
			}
		}
	}
	
	@FXML
	public void handleAddPromotion(){
		switch (operate.getText()){
		case "保存":
			
			break;
		default://添加
			
			break;
		}
	}
	
	@FXML
	public void close(){
		controller.removeDetailPane();
	}
	
	public void setPromotionVo(PromotionVo promotion){
		this.vo = promotion;
		initPromotion();
	}

	public void setController(HotelPromotionMainController controller) {
		this.controller = controller;
		
	}
	
}
