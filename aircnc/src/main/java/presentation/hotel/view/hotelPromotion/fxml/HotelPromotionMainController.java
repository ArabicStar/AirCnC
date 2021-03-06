package presentation.hotel.view.hotelPromotion.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import interactor.hotel.HotelPromotionInteractor;
import interactor.impl.hotel.HotelPromotionCourier;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import presentation.hotel.HotelCenterController;
import presentation.hotel.accessor.HotelPromotionAccessor;
import presentation.hotel.accessor.impl.HotelPromotionAccessorImpl;
import presentation.hotel.manager.HotelPromotionManager;
import presentation.hotel.manager.impl.HotelPromotionManagerImpl;
import presentation.hotel.model.HotelPromotionModel;
import presentation.hotel.utils.cell.PromotionButtonCell;
import presentation.hotel.view.hotelPromotion.PromotionDetailPane;
import vo.promotion.HotelPromotionVo;
import vo.promotion.PromotionVo;
import vo.promotion.PromotionVoBuilder;

public class HotelPromotionMainController implements Initializable{
	private HotelCenterController controller;
	
	@FXML
	private Button addPromotion;
	
	@FXML
	private TableView<HotelPromotionModel> promotionTable;
	
	@FXML
	private TableColumn<HotelPromotionModel, String> description;
	
	@FXML
	private TableColumn<HotelPromotionModel,PromotionVo> operation;
	
	private HotelPromotionManager manager;
	
	private HotelPromotionAccessor accessor;
	
	private HotelPromotionInteractor interactor;
	
	private ObservableList<HotelPromotionModel> models;
	
	private HotelPromotionMainController promotionController = this;
	
	private Pane rootLayout;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		manager = HotelPromotionManagerImpl.getInstance();

		accessor = HotelPromotionAccessorImpl.getInstance();
		
		interactor = HotelPromotionCourier.getInstance();
		interactor.getHotelActivePromotion();
		
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
                new Callback<TableColumn.CellDataFeatures<HotelPromotionModel, PromotionVo>, 
                ObservableValue<PromotionVo>>() {

            public ObservableValue<PromotionVo> call(TableColumn.CellDataFeatures<HotelPromotionModel, PromotionVo> p) {
            	return new SimpleObjectProperty<PromotionVo>(p.getValue().getOperation());
            }
        });
	

		operation.setCellFactory(
                new Callback<TableColumn<HotelPromotionModel, PromotionVo>, TableCell<HotelPromotionModel, PromotionVo>>() {
            public TableCell<HotelPromotionModel, PromotionVo> call(TableColumn<HotelPromotionModel, PromotionVo> p) {
                return new PromotionButtonCell(promotionController);
            }       
        });

	}
	
	public void setCenterController(HotelCenterController controller){
		this.controller=controller;
	}
	
	@FXML
	public void handleAddPromotion(){
		addDetailPane(null);
	}
	
	public void addAndUpdate(PromotionVoBuilder builder){
		HotelPromotionVo vo = (HotelPromotionVo) builder.setHotelId(manager.getHotelId()).getPromotionInfo();
		accessor.setPromotion(vo);
		if(vo.getId()==0)
			interactor.addNew();
		else
			interactor.update();
		refresh();
	}
	
	public void deletePromotion(HotelPromotionVo vo){
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
		interactor.getHotelActivePromotion();
		models = manager.getPromotionList();
		promotionTable.setItems(models);
	}

}
