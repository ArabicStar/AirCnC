package presentation.manage.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import vo.member.MemberVo;

/**
 * the model of member
 * aiming to wrap the MemberVo into the presentation manager.
 * @author paranoia
 *
 */
public class MemberManageModel {
	
	private final StringProperty username;
	private final StringProperty id;
	
	/**
	 * Default constructor.
	 */
	public MemberManageModel() {
		this(null);
	}
	
	public MemberManageModel(MemberVo vo){
		this.id = new SimpleStringProperty(vo.getId());
		this.username = new SimpleStringProperty(vo.getName());		
	}
	
	public String getID() {
        return id.get();
    }

    public void setId(String newId) {
        this.id.set(newId);
    }

    public StringProperty idProperty() {
        return id;
    }
	
	public String getUername() {
        return username.get();
    }

    public void setMarketName(String newName) {
        this.username.set(newName);
    }

    public StringProperty UsernameProperty() {
        return username;
    }
}
