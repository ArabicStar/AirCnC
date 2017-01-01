package presentation.market.view.levelStrategy.fxml;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import interactor.impl.market.MarketServiceCourier;
import interactor.market.MarketServiceInteractor;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import presentation.market.accessor.LevelAccessor;
import presentation.market.accessor.impl.LevelAccessorImpl;
import presentation.market.manager.LevelManager;
import presentation.market.manager.impl.LevelManagerImpl;

public class LevelStrategyController implements Initializable {
	@FXML
	private TextField lv1;

	@FXML
	private TextField lv2;
	
	@FXML
	private TextField lv3;

	@FXML
	private TextField lv4;
	
	@FXML
	private TextField lv5;

	@FXML
	private TextField lv6;
	
	@FXML
	private TextField lv7;

	@FXML
	private TextField lv8;
	
	@FXML
	private TextField lv9;

	@FXML
	private TextField lv10;
	
	private List<TextField> levels;
	
	@FXML
	private Button modify;
	
	private List<Integer> credits;
	
	private LevelManager manager;
	
	private LevelAccessor accessor;
	
	private MarketServiceInteractor interactor;
	
	static int size = 10;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		manager = LevelManagerImpl.getInstance();
		accessor = LevelAccessorImpl.getInstance();
		interactor = MarketServiceCourier.getInstance();
		interactor.getLevelStrategy();
		credits = manager.getLevelCredit();
		Platform.runLater(new Runnable() {
			  @Override public void run() {
				  initCredit();
			  }
		});
		
	}
	
	@FXML
	public void handleModify() {
		if(modify.getText().equals("修改策略")){
			for(int i = 0;i<size;i++){
				TextField t = levels.get(i);
				t.setDisable(false);
				t.setStyle("-fx-border-width:1px;");
			}
		}else{
			
		}
	}
	
	private void initCredit(){
		levels = new ArrayList<TextField>();
		levels.add(lv1);
		levels.add(lv2);
		levels.add(lv3);
		levels.add(lv4);
		levels.add(lv5);
		levels.add(lv6);
		levels.add(lv7);
		levels.add(lv8);
		levels.add(lv9);
		levels.add(lv10);
		for(int i = 0;i<size;i++){
			TextField t = levels.get(i);
			t.setText(String.valueOf(credits.get(i)));
			t.setDisable(true);		
		}
	}

}
