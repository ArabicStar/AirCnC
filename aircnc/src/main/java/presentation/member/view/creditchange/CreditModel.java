package presentation.member.view.creditchange;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import vo.member.credit.CreditChangeVo;

public class CreditModel {
	
	 	private final StringProperty discription;

	    /**
	     * Default constructor.
	     */
//	    public CreditModel() {
//	        this();
//	    }

	    /**
	     * Constructor with some initial data.
	     * 
	     * @param discription
	     */
	    public CreditModel(CreditChangeVo change) {
	        this.discription=null;
	        
	    }
	    
	    public CreditModel(String s1) {
	        this.discription = new SimpleStringProperty(s1);	        
	    }
	    
	    /**
	     * transform the params of credit change to a string
	     * @param CreditChangeVo
	     * @return new date format(String)
	     */	    
	    private static String transformDiscription(CreditChangeVo change){
	    	String result = "";	    	
			return result;	
	    }

	    public String getDiscription() {
	        return discription.get();
	    }

	    public void setDiscription(String newChange) {
	        this.discription.set(newChange);
	    }

	    public StringProperty hotelNameProperty() {
	        return discription;
	    }
	
}
