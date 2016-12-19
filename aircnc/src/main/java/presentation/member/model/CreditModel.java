package presentation.member.model;

import java.time.Instant;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import vo.member.credit.CreditChangeVo;

/**
 * the model of credit change
 * aiming to wrap the CreditChangeVo into the presentation manager.
 * @author paranoia
 *
 */
public class CreditModel {
		
	private final StringProperty date;
	private final StringProperty time;
	private final StringProperty description;
	private final ObjectProperty<Integer> creditChange;

	/**
	 * Default constructor.
	 */
	public CreditModel() {
		this(null);
	}

	/**
	 * Constructor with some initial data.
	 * 
	 * @param date
	 * @param symbol
	 * @param time
	 * @param description
	 * @param creditChange
	 */
	public CreditModel(CreditChangeVo change) {

		// process the concrete date
		this.date = new SimpleStringProperty(transformDate(change.getTimeInstant()));

		// process the concrete time
		this.time = new SimpleStringProperty(transformTime(change.getTimeInstant()));

		//this.description = new SimpleStringProperty(change.getFormatString(),change.getOrderId());
		String des;
		switch(change.getActionType()){
		case CHARGE:
			des = String.format(change.getFormatString(), (float)change.getMoney()
					,change.getAfterCredit()-change.getBeforeCredit());
			break;
		case ORDER_EXECUTION:
			des = String.format(change.getFormatString(),change.getOrderId(),change.getOrderId()
					,change.getAfterCredit()-change.getBeforeCredit());
			break;
		case ORDER_CANCEL:
			des = String.format(change.getFormatString(),change.getOrderId(),change.getOrderId()
					,change.getBeforeCredit()-change.getAfterCredit());
			break;
		case ORDER_OVERDUE:
			des = String.format(change.getFormatString(),change.getOrderId(),change.getOrderId()
					,change.getBeforeCredit()-change.getAfterCredit());
			break;
		case ORDER_APPEAL:
			des = String.format(change.getFormatString(),change.getOrderId(),change.getOrderId()
					,change.getAfterCredit()-change.getBeforeCredit());
			break;
		case ORDER_DELAY:
			des = String.format(change.getFormatString(),change.getOrderId(),change.getOrderId()
					,change.getAfterCredit()-change.getBeforeCredit());
			break;
		default:
			des = String.format(change.getFormatString());
			break;
		}
		this.description = new SimpleStringProperty(des);
		
		//这里要完善
		this.creditChange = new SimpleObjectProperty<Integer>(233);

	}

	/**
	 * transform the local date time (yyyy-mm-ddTHH:mm:ss.sssZ) to the new
	 * format (HH:mm)
	 * 
	 * @param date
	 * @return new date format(String)
	 */
	private static String transformTime(Instant time) {

		String result = time.toString().substring(0, 10);

		return result;
	}

	/**
	 * transform the local date time (yyyy-mm-ddTHH:mm:ss.sssZ) to the new
	 * format (yyyy-mm-dd)
	 * 
	 * @param date
	 * @return new date format(String)
	 */
	private static String transformDate(Instant time) {

		String result = time.toString().substring(11, 16);

		return result;
	}

	public String getDate() {
		return date.get();
	}

	public void setDate(String firstName) {
		this.date.set(firstName);
	}

	public StringProperty dateProperty() {
		return date;
	}

	public String getTime() {
		return time.get();
	}

	public void setTime(String firstName) {
		this.time.set(firstName);
	}

	public StringProperty timeProperty() {
		return time;
	}
	
	public String getDescription() {
		return description.get();
	}

	public void setDescription(String newChange) {
		this.description.set(newChange);
	}

	public StringProperty descriptionProperty() {
		return description;
	}
	
	public int getCreditChange() {
		return creditChange.get();
	}

	public void setCreditChange(int creditChange) {
		this.creditChange.set(creditChange);
	}

	public ObjectProperty<Integer> creditChangeProperty() {
		return creditChange;
	}
}
