package presentation.member.view.creditchange;

import java.time.Instant;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import vo.member.credit.CreditChangeVo;

public class CreditModel {
		
	private final StringProperty date;
	private final StringProperty time;
	private final ObjectProperty<Boolean> symbol;
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
	 * @param hotelName
	 * @param checkinTime
	 * @param state
	 * @param timeAndSum
	 * @param totalPrice
	 */
	public CreditModel(String s1, String s2, String s3) {
		
		this.date = new SimpleStringProperty(s2);
		this.symbol = new SimpleObjectProperty<Boolean>(true);
		this.time = new SimpleStringProperty(s1);
		this.description = new SimpleStringProperty(s3);
		this.creditChange = new SimpleObjectProperty<Integer>(233);

	}

	public CreditModel(CreditChangeVo change) {

		// process the concrete date
		this.date = new SimpleStringProperty(transformDate(change.getTimeInstant()));

		this.symbol = new SimpleObjectProperty<Boolean>(true);

		// process the concrete time
		this.time = new SimpleStringProperty(transformTime(change.getTimeInstant()));

		this.description = new SimpleStringProperty(change.getActionType().toString());
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

	public boolean getSymbol() {
		return symbol.get();
	}

	public void setSymbol(Boolean valid) {
		this.symbol.set(valid);
	}

	public ObjectProperty<Boolean> symbolProperty() {
		return symbol;
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

	public void setDescription(String firstName) {
		this.description.set(firstName);
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
