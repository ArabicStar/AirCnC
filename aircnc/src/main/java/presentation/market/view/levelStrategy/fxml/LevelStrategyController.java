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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import presentation.hotel.utils.dialog.PlainDialog;
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

	@FXML
	private Button cancel;

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
		accessor.setOldStrategy(manager.getOldStrategy());
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				cancel.setVisible(false);
				initCredit();
			}
		});

	}

	@FXML
	public void handleModify() {
		if (modify.getText().equals("修改策略")) {
			cancel.setVisible(true);
			for (int i = 0; i < size; i++) {
				TextField t = levels.get(i);
				t.setDisable(false);
				t.setStyle("-fx-border-width:1px;");
			}
			modify.setText("保存策略");
		} else {
			String warning = check();
			if (warning == "") {
				for(int i = 0;i<size;i++){
					TextField t = levels.get(i);
					credits.set(i, Integer.parseInt(t.getText()));
				}
				accessor.setLevelCredit(credits);
				interactor.updateLevelStrategy();
				modify.setText("修改策略");
				cancel.setVisible(false);
				refresh();
			} else {
				PlainDialog alert = new PlainDialog(AlertType.WARNING, "保存失败", warning);
				alert.showDialog();
				return;
			}
		}
	}
	
	private void refresh(){
		interactor.getLevelStrategy();
		credits = manager.getLevelCredit();
		accessor.setOldStrategy(manager.getOldStrategy());
		for (int i = 0; i < size; i++) {
			TextField t = levels.get(i);
			t.setStyle("-fx-border-width:0px;");
			t.setText(String.valueOf(credits.get(i)));
			t.setDisable(true);
		}
	}

	@FXML
	public void handleCancel() {
		credits = manager.getLevelCredit();
		for (int i = 0; i < size; i++) {
			TextField t = levels.get(i);
			t.setStyle("-fx-border-width:0px;");
			t.setText(String.valueOf(credits.get(i)));
			t.setDisable(true);
		}
		cancel.setVisible(false);
		modify.setText("修改策略");
	}

	private void initCredit() {
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
		for (int i = 0; i < size; i++) {
			TextField t = levels.get(i);
			t.setText(String.valueOf(credits.get(i)));
			t.setDisable(true);
		}
	}

	private String check() {
		try {
			for (int i = 0; i < size; i++) {
				TextField t = levels.get(i);
				int j = Integer.parseInt(t.getText());
				if (j <= 0)
					return "请输入大于0的整数";
			}
		} catch (Exception e) {
			return "请正确输入信用值";
		}

		return "";
	}

}
