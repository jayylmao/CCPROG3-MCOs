/**
 * The Date class defines a date, month, year, and the time.
 * It is to be used to store the date of a reservation.
 */
public class Date {
	private int date;
	private int month;
	private int year;

	private int hour;
	private int minute;

	public Date(int date, int month, int year) {
		this.date = date;
		this.month = month;
		this.year = year;
	}

	public Date(int date, int month, int year, int hour, int minute) {
		this(date, month, year);
		this.hour = hour;
		this.minute = minute;
	}

	public int getDate() {
		return date;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public String getTime() {
		return String.format("%02d", hour) + ":" + String.format("%02d", minute);
	}

	public void setDate(int date) {
		this.date = date;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
