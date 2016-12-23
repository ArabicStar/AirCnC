package presentation.member.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

public class DateTimePicker extends DatePicker {
	private ObjectProperty<LocalTime> timeValue = new SimpleObjectProperty<>();
	/* private ObjectProperty<ZonedDateTime> dateTimeValue; */
	private ObjectProperty<LocalDateTime> dateTimeValue;

	public DateTimePicker() {
		super();
		setValue(LocalDate.now());
		setTimeValue(LocalTime.now());
		dateTimeValue = dateTimeValueProperty();

		setConverter(new StringConverter<LocalDate>() {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

			@Override
			public String toString(LocalDate object) {

				return dateTimeValue.get().format(formatter);
			}

			@Override
			public LocalDate fromString(String string) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

				// Date d=LocalDateConvert.getDate(string, "yyyy-MM-dd HH:mm");
				// timeValue.set(LocalDateConvert.UDateToLocalTime(d));
				Date date = null;
				try {
					date = sdf.parse(string);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Instant instant = date.toInstant();
				ZoneId zone = ZoneId.systemDefault();
				LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
				LocalTime localTime = localDateTime.toLocalTime();
				localTime = localTime.minusMinutes(localTime.getMinute());
				timeValue.set(localTime);
//				LocalDate ld = LocalDate.parse(string, formatter);
				return LocalDate.parse(string, formatter);
			}
		});
	}

	/*
	 * @Override protected Skin<?> createDefaultSkin () { return new
	 * DateTimePickerSkin(this); }
	 */

	public LocalTime getTimeValue() {
		return timeValue.get();
	}

	void setTimeValue(LocalTime timeValue) {
		this.timeValue.set(timeValue.minusMinutes(timeValue.getMinute()));
	}

	public ObjectProperty<LocalTime> timeValueProperty() {
		return timeValue;
	}

	public LocalDateTime getDateTimeValue() {
		return dateTimeValueProperty().get();
	}

	public void setDateTimeValue(LocalDateTime dateTimeValue) {
		super.setValue(dateTimeValue.toLocalDate());
		dateTimeValueProperty().set(dateTimeValue);
	}

	public ObjectProperty<LocalDateTime> dateTimeValueProperty() {
		if (dateTimeValue == null) {
			dateTimeValue = new SimpleObjectProperty<LocalDateTime>(LocalDateTime.of(this.getValue(), timeValue.get()));
			/*
			 * dateTimeValue = new
			 * SimpleObjectProperty<>(ZonedDateTime.of(LocalDateTime.of(this.
			 * getValue(), timeValue.get()), ZoneId.systemDefault()));
			 */
			timeValue.addListener(t -> {
				dateTimeValue.set(LocalDateTime.of(this.getValue(), timeValue.get()));
				/*
				 * dateTimeValue.set(ZonedDateTime.of(LocalDateTime.of(this.
				 * getValue(), timeValue.get()), ZoneId.systemDefault()));
				 */
			});

			valueProperty().addListener(t -> {

				dateTimeValue.set(LocalDateTime.of(this.getValue(), timeValue.get()));
				/*
				 * dateTimeValue.set(ZonedDateTime.of(LocalDateTime.of(this.
				 * getValue(), timeValue.get()), ZoneId.systemDefault()));
				 */
			});
		}
		return dateTimeValue;
	}

}
