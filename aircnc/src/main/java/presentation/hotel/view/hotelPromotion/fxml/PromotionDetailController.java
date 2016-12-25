package presentation.hotel.view.hotelPromotion.fxml;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import presentation.hotel.utils.dialog.PlainDialog;
import utils.info.promotion.PromotionInfoTemplate.Scope;
import utils.promotion.applier.ApplierParams;
import utils.promotion.applier.How;
import utils.promotion.trigger.TriggerParams;
import utils.promotion.trigger.hotel.HotelWhen;
import vo.promotion.HotelPromotionVo;
import vo.promotion.PromotionVo;
import vo.promotion.PromotionVoBuilder;

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
		  			whenPara.setText(null);
		  			whenPara.setPromptText("双十一优惠");
		  			whenPara.setDisable(false);
		  			break;
		  		default:
		  			from.setDisable(true);
		  			to.setDisable(true);
		  			switch (newValue){
		  			case "多间房优惠":
			  			whenParaName.setText("房间数量");
			  			whenPara.setText(null);
			  			whenPara.setPromptText("4");
			  			whenPara.setDisable(false);
		  				break;
		  			case "合作企业优惠":
		  				whenParaName.setText("企业名称");
		  				whenPara.setText(null);
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
		  			howPara.setText(null);
		  			howPara.setPromptText("0.8");
		  			break;
		  		default:
		  			howPara.setDisable(false);
		  			howPara.setText(null);
		  			howPara.setPromptText("100");		  			
		  		}
			}
		});
		
		
		if(vo==null){
			operate.setText("添加");
			return;
		}else{
			operate.setText("保存");
			//TODO
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
		String warning = check();
		if(warning!=""){
			PlainDialog alert = new PlainDialog(AlertType.WARNING,"保存失败",warning);
         	alert.showDialog();
         	return;
		}
//		switch (operate.getText()){
//		case "保存":
//			
//			break;
//		default://添加
//			
//			break;
//		}
		updateVo();
		PlainDialog alert = new PlainDialog(AlertType.INFORMATION,"保存成功","已保存填写的促销策略信息");
     	alert.showDialog();
     	controller.removeDetailPane();
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
	
	private String check(){
		if(when.getValue()==null||how.getValue()==null){
			return "请输入完整策略信息!";
		}
		switch (when.getValue()){
		case "时效性优惠":
  			if(from.getValue().isBefore(LocalDate.now())){
  				return "时效性优惠开始时间不能早于当天！";
  			}
  			
  			if(to.getValue().isBefore(from.getValue().plusDays(1))){
  				return "时效性优惠结束时间不能早于开始时间！";
  			}
  			break;
		case "多间房优惠":
  			try{
  				int i = Integer.parseInt(whenPara.getText());
  				if(i<=0){
  					return "请输入大于0的房间数量！";
  				}
  			}catch (Exception e){
  				return "请正确输入房间数量！";
  			}
			break;
		case "合作企业优惠":
			if(whenPara.getText()==null||whenPara.getText()==""){
				return "请正确输入合作企业名称！";
			}
		}
		
		switch (how.getValue()){
		case "原价折扣":
			try{
  				double i = Double.parseDouble(howPara.getText());
  				if(i>=1||i<=0){
  					return "请输入小于1大于0的折扣百分数！";
  				}
  			}catch (Exception e){
  				return "请正确输入立减价格！";
  			}
  			break;
		case "直接降价":
  			try{
  				double i = Double.parseDouble(howPara.getText());
  				if(i<=0){
  					return "请输入大于0的立减金额！";
  				}
  			}catch (Exception e){
  				return "请正确输入立减价格！";
  			}
			break;
		}
		return "";
	}
	
	private void updateVo(){
		PromotionVoBuilder builder;
		if(vo==null){
			builder = new PromotionVoBuilder(Scope.Hotel);
		}else{
			builder = new PromotionVoBuilder(vo).setPractical(false);
		}
		
		switch (when.getValue()){
  		case "时效性优惠":
  			builder.when(HotelWhen.DURING_PERIOD)
  			.setParam(TriggerParams.FROM, from.getValue().atStartOfDay())
  			.setParam(TriggerParams.FROM, from.getValue().atStartOfDay());
  			break;
  		case "多间房优惠":
  			builder.when(HotelWhen.MULTI_ROOMS)
  			.setParam(TriggerParams.ROOM_NUM_THRESHOLD, Integer.parseInt(whenPara.getText()));
  			break;
  		case "合作企业优惠":
  			builder.when(HotelWhen.ENTERPRISE)
  			.setParam(TriggerParams.ENTERPRISE, whenPara.getText());
  			break;
		case "生日优惠":
			builder.when(HotelWhen.BIRTHDAY);
			break;
		}
		
		switch (how.getValue()){
  		case "原价折扣":
  			builder.how(How.CONST)
  			.setParam(ApplierParams.AMOUNT, Double.parseDouble(howPara.getText()));
  			break;
  		case "直接降价":
  			builder.how(How.PERCENT_OFF)
  			.setParam(ApplierParams.PERCENT, Double.parseDouble(howPara.getText()));
  			break;
		}
		controller.addAndUpdate(builder);
	}
	
}
