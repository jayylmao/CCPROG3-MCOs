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
		if (date < 1 || date > 31) {
			throw new IllegalArgumentException("Invalid date entered: " + date);
		} else if (month < 1 || month > 12) {
			throw new IllegalArgumentException("Invalid month entered: " + month);
		} else if (year < 1) {
			throw new IllegalArgumentException("Invalid year entered: " + year);
		} else {
			this.date = date;
			this.month = month;
			this.year = year;
		}
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

	/**
	 * Returns a date object containing the difference between the date object and another given as a parameter.
	 * @param otherDate Other date object to get difference of. (otherDate - this date object).
	 * @return Difference in dates as object.
	 */
	public Date getDateDifference(Date otherDate) {
		int dateDiff = otherDate.getDate() - getDate();
		int monthDiff = otherDate.getMonth() - getMonth();
		int yearDiff = otherDate.getYear() - getYear();

		return new Date(dateDiff, monthDiff, yearDiff);
	}
}
