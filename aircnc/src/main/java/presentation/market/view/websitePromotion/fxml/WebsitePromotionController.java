package presentation.market.view.websitePromotion.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import interactor.market.MarketPromotionInteractor;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.Pane;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import presentation.market.MarketCenterController;
import presentation.market.accessor.MarketPromotionAccessor;
import presentation.market.accessor.impl.MarketPromotionAccessorImpl;
import presentation.market.manage.MarketPromotionManager;
import presentation.market.manage.impl.MarketPromotionManagerImpl;
import presentation.market.model.WebsitePromotionModel;
import presentation.market.utils.cell.ButtonName;
import presentation.market.utils.cell.PromotionButtonCell;
import presentation.market.view.websitePromotion.PromotionDetailPane;
import vo.promotion.WebsitePromotionVo;
import vo.promotion.PromotionVo;
import vo.promotion.PromotionVoBuilder;
import vo.promotion.WebsitePromotionVo;

public class WebsitePromotionController implements Initializable{
	MarketCenterController controller;

	@FXML
	private Button addPromotion;
	
	@FXML
	private TableView<WebsitePromotionModel> promotionTable;
	
	@FXML
	private TableColumn<WebsitePromotionModel, String> description;
	
	@FXML
	private TableColumn<WebsitePromotionModel,PromotionVo> operation;
	
	private MarketPromotionManager manager;
	
	private MarketPromotionAccessor accessor;
	
	private MarketPromotionInteractor interactor;
	
	private ObservableList<WebsitePromotionModel> models;
	
	private WebsitePromotionController promotionController = this;
	
	private Pane rootLayout;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		manager = MarketPromotionManagerImpl.getInstance();

		accessor = MarketPromotionAccessorImpl.getInstance();
		
//		interactor = MarketPromotionCourier.getInstance();
		interactor.getMarketActivePromotion();
		
		Platform.runLater(new Runnable() {
			  @Override public void run() {
				  initPromotion();
			  }
		});
		
	}
	
	private void initPromotion(){
		models = manager.getPromotionList();
		promotionTable.setItems(models);
		
		description.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
		
		operation.setSortable(false);
		
		operation.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<WebsitePromotionModel, PromotionVo>, 
                ObservableValue<PromotionVo>>() {

            public ObservableValue<PromotionVo> call(TableColumn.CellDataFeatures<WebsitePromotionModel, PromotionVo> p) {
            	return new SimpleObjectProperty<PromotionVo>(p.getValue().getOperation());
            }
        });
	

		operation.setCellFactory(
                new Callback<TableColumn<WebsitePromotionModel, PromotionVo>, TableCell<WebsitePromotionModel, PromotionVo>>() {
            public TableCell<WebsitePromotionModel, PromotionVo> call(TableColumn<WebsitePromotionModel, PromotionVo> p) {
                return new PromotionButtonCell(promotionController);
            }       
        });

	}
	
	public void setCenterController(MarketCenterController controller){
		this.controller=controller;
	}
	
	@FXML
	public void handleAddPromotion(){
		addDetailPane(null);
	}
	
	public void addAndUpdate(PromotionVoBuilder builder){
		WebsitePromotionVo vo = (WebsitePromotionVo) builder.getPromotionInfo();
		accessor.setPromotion(vo);
		if(vo.getId()==0)
			interactor.addNew();
		else
			interactor.update();
		refresh();
	}
	
	public void deletePromotion(WebsitePromotionVo vo){
		accessor.setPromotion(vo);
		interactor.delete();
		refresh();
	}
	
	public void setRootLayout(Pane pane){
		this.rootLayout = pane;
	}
	
	public void addDetailPane(PromotionVo vo){
		PromotionDetailPane detail = new PromotionDetailPane(vo);
		rootLayout.getChildren().add(detail.getPane());
		detail.getPane().setLayoutX(300);
		detail.getPane().setLayoutY(150);
		detail.getController().setController(this);
	}
	
	public void removeDetailPane(){
		rootLayout.getChildren().remove(rootLayout.getChildren().size()-1);
		refresh();
	}
	
	public void refresh(){
		interactor.getMarketActivePromotion();
		models = manager.getPromotionList();
		promotionTable.setItems(models);
	}
	
	

}
