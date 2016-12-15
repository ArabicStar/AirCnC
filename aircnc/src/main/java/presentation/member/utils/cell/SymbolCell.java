package presentation.member.utils.cell;

import javafx.scene.control.TableCell;
import presentation.member.model.CreditModel;

/**
 * SymbolCell use a label(shape) to show the index of change.
 * 
 * @author paranoia
 *
 */
public class SymbolCell extends TableCell<CreditModel, Integer>{
	
	boolean isUp;
	
	public SymbolCell(){
		isUp = true;
		createSymbol(isUp);
	}
	
	/**
	 * create the symbol to show whether credit is up or down.
	 * @param isUp
	 */
	private void createSymbol(boolean isUp){
		
	}
	
	/**
     * Display button if the row is not empty
     * @param status, empty
     */
//    @Override
//    protected void updateItem(int change,boolean empty) {
//        super.updateItem(change, empty);
//        if(!empty){
//        	if(change>0)
//        		this.isUp = true;
//        	else
//        		this.isUp = false;
//        	
//        	createSymbol(isUp);
//        	setGraphic(buttons);
//        }
//    }
}
