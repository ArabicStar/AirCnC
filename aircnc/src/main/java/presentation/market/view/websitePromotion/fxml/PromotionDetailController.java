package presentation.market.view.websitePromotion.fxml;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import presentation.hotel.utils.dialog.PlainDialog;
import utils.info.promotion.PromotionInfoTemplate.Scope;
import utils.promotion.applier.ApplierParams;
import utils.promotion.applier.How;
import utils.promotion.trigger.TriggerParams;
import utils.promotion.trigger.website.WebsiteWhen;
import vo.promotion.PromotionVo;
import vo.promotion.PromotionVoBuilder;

public class PromotionDetailController {
	WebsitePromotionController controller;
	
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
		
		when.getItems().addAll("会员等级优惠","特定商圈优惠","时效性优惠");
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
		  			case "会员等级优惠":
			  			whenParaName.setText("会员等级");
			  			whenPara.setText(null);
			  			whenPara.setPromptText("大于等于该等级均享受优惠");
			  			whenPara.setDisable(false);
		  				break;
		  			case "特定商圈优惠":
		  				whenParaName.setText("商圈名称");
		  				whenPara.setText(null);
			  			whenPara.setPromptText("新街口");
			  			whenPara.setDisable(false);
		  				break;
		  			}
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

			switch (vo.getPromotion().getTrigger().name()){
			case "LEVEL":
				when.setValue("会员等级优惠");
				break;
			case "TRADE_AREA":
				when.setValue("特定商圈优惠");
				break;
			case "DURING_PERIOD":
				when.setValue("时效性优惠");
				break;
			default:
				
				break;
			}
			when.setDisable(true);
			
			switch (vo.getPromotion().getApplier().name()){
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

		updateVo();
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

	public void setController(WebsitePromotionController controller) {
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
		case "会员等级优惠":
  			try{
  				int i = Integer.parseInt(whenPara.getText());
  				if(i<=0){
  					return "请输入大于0的会员等级！";
  				}
  			}catch (Exception e){
  				return "请正确输入会员等级！";
  			}
			break;
		case "特定商圈优惠":
			if(whenPara.getText()==null||whenPara.getText()==""){
				return "请正确输入商圈名称！";
			}
		default:
			return "请选择策略名称";
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
		default:
			return "请选择优惠方式";	
		}		
		return "";
	}
	
	private void updateVo(){
		PromotionVoBuilder builder;
		if(vo==null){
			builder = new PromotionVoBuilder(Scope.Website).setPractical(false);
		}else{
			builder = new PromotionVoBuilder(vo).setPractical(false);
		}
		
		switch (when.getValue()){
  		case "时效性优惠":
  			builder.when(WebsiteWhen.DURING_PERIOD)
  			.setParam(TriggerParams.FROM, from.getValue().atStartOfDay())
  			.setParam(TriggerParams.TO, to.getValue().atStartOfDay());
  			break;
  		case "会员等级优惠":
  			builder.when(WebsiteWhen.LEVEL)
  			.setParam(TriggerParams.LEVEL_THRESHOLD, Integer.parseInt(whenPara.getText()));
  			break;
  		case "特定商圈优惠":
  			builder.when(WebsiteWhen.TRADE_AREA)
  			.setParam(TriggerParams.TARGET_TRADE_AREA, whenPara.getText());
  			break;
		}
		
		switch (how.getValue()){
  		case "直接降价":
  			builder.how(How.CONST)
  			.setParam(ApplierParams.AMOUNT, Double.parseDouble(howPara.getText()));
  			break;
  		case "原价折扣":
  			builder.how(How.PERCENT_OFF)
  			.setParam(ApplierParams.PERCENT, Double.parseDouble(howPara.getText()));
  			break;
		}
		controller.addAndUpdate(builder);
	}
	
//	public void test(){
//		PromotionVoBuilder builder = new PromotionVoBuilder(Scope.Website).
//				setName("lala").setPractical(false);
//		LocalDateTime now = LocalDateTime.now();
//		
//		builder.when(WebsiteWhen.DURING_PERIOD)
//			.setParam(TriggerParams.FROM, now.plusDays(1))
//			.setParam(TriggerParams.TO, now.plusDays(5));
		
//		builder.when(WebsiteWhen.LEVEL).setParam(TriggerParams.LEVEL_THRESHOLD, 3);
//		
//		builder.when(WebsiteWhen.TRADE_AREA).setParam(TriggerParams.TARGET_TRADE_AREA, "xinjiekou");
//		builder.how(How.CONST).setParam(ApplierParams.AMOUNT, 100);
//		WebsitePromotionVo vo = (WebsitePromotionVo) builder.getPromotionInfo();
//		
//	}

}
