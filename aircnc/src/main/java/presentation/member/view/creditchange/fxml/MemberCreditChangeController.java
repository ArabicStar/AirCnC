package presentation.member.view.creditchange.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import interactor.impl.member.MemberInfoCourier;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import presentation.member.ClientCenterController;
import presentation.member.manager.CreditChangeManager;
import presentation.member.manager.impl.CreditChangeManagerImpl;
import presentation.member.model.CreditModel;
import presentation.member.utils.cell.CreditChangeCell;

/**
 * the controller of 
 * @author paranoia
 *
 */
public class MemberCreditChangeController implements Initializable{
	
	private ClientCenterController controller;
	
	private CreditChangeManager manager;
	
	@FXML
	private TableView<CreditModel> creditTable;
	
	@FXML
	private TableColumn<CreditModel, String> date;
	
	@FXML
	private TableColumn<CreditModel,String> time;
	
	@FXML
	private TableColumn<CreditModel,String> description;
	
	@FXML
	private TableColumn<CreditModel,Integer> change;
	
	private int changeNum;
	
	public void setCenterController(ClientCenterController controller){
		this.controller=controller;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources){
		Platform.runLater(new Runnable() {
			  @Override public void run() {
				  MemberInfoCourier.getInstance().getMemberCreditChange();
				  creditTable.setItems(manager.getCreditList());
				  creditTable.setFocusTraversable(false);
			  }
			});
		
		manager = CreditChangeManagerImpl.getInstance();
		
		init();
	}
	
	public void init(){
		date.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
		time.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
		description.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
		//change.setCellValueFactory(cellData -> cellData.getValue().creditChangeProperty());
		
		change.setSortable(false);
		
		change.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<CreditModel, Integer>, 
                ObservableValue<Integer>>() {

            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<CreditModel, Integer> p) {
                changeNum=p.getValue().getCreditChange();
            	return new SimpleObjectProperty<Integer>(p.getValue().getCreditChange());
            }
        });
		

		change.setCellFactory(
                new Callback<TableColumn<CreditModel, Integer>, TableCell<CreditModel, Integer>>() {

            public TableCell<CreditModel, Integer> call(TableColumn<CreditModel, Integer> p) {
                return new CreditChangeCell(changeNum);
            }       
        });
	}
}
