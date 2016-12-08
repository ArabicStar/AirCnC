package presentation.member.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import vo.member.MemberVo;

/**
 * the model of member
 * aiming to wrap the MemberVo into the presentation manager.
 * @author paranoia
 *
 */
public class MemberInfoModel {
	
	private final StringProperty id;
	private final StringProperty username;
	private final StringProperty tele;
	private final StringProperty mobi;
	private final StringProperty email;
	private final IntegerProperty credit;
	private final StringProperty type;
	private final StringProperty typeContent;
	
	/**
	 * Default constructor.
	 */
	public MemberInfoModel() {
		this(null);
	}
	
	public MemberInfoModel(MemberVo vo){
		this.id = new SimpleStringProperty(vo.getId());
		this.username = new SimpleStringProperty(vo.getName());
		this.tele = new SimpleStringProperty(vo.getContact().getFixedPhone());
		this.mobi = new SimpleStringProperty(vo.getContact().getMobilePhone());
		this.email = new SimpleStringProperty(vo.getContact().getEmail());
		this.credit = new SimpleIntegerProperty(vo.getCredit());
		switch(vo.getType()){
		case "personal" : 
			this.type = new SimpleStringProperty("生日");
			this.typeContent = new SimpleStringProperty(String.valueOf(vo.getBirthday()));
			break;
		case "business" : 
			this.type = new SimpleStringProperty("企业名称");
			this.typeContent = new SimpleStringProperty(String.valueOf(vo.getEnterprise()));
			break;
		default: this.type = new SimpleStringProperty("");
			this.typeContent = new SimpleStringProperty("");
		}
		
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
	
	public String getUsername() {
        return username.get();
    }

    public void setUsername(String newName) {
        this.username.set(newName);
    }

    public StringProperty usernameProperty() {
        return username;
    }
    
    public String getTele() {
        return tele.get();
    }

    public void setTele(String newTele) {
        this.tele.set(newTele);
    }

    public StringProperty teleProperty() {
        return tele;
    }
    
    public String getMobi() {
        return mobi.get();
    }

    public void setMobi(String newMobi) {
        this.mobi.set(newMobi);
    }

    public StringProperty mobiProperty() {
        return mobi;
    }
    
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String newEmail) {
        this.email.set(newEmail);
    }

    public StringProperty emailProperty() {
        return email;
    }
    
    public int getCredit() {
        return credit.get();
    }

    public void setHotelName(int newCredit) {
        this.credit.set(newCredit);
    }

    public IntegerProperty creditProperty() {
        return credit;
    }
    
    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public StringProperty typeProperty() {
        return type;
    }
    
    public String getTypeContent() {
        return typeContent.get();
    }

    public void setTypeContent(String content) {
        this.typeContent.set(content);
    }

    public StringProperty typeContentProperty() {
        return typeContent;
    }
}
