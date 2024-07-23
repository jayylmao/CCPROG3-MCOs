/**
 * The Date class defines a date, month, year, and the time.
 * It is to be used to store the date of a reservation.
 */
public class Date {
	private int day;
	private int month;
	private int year;

	/** Hour which is within the range of 0-23. */
	private int hour;
	/** Minute which is within the range of 0-59. */
	private int minute;

	/**
	 * This constructor creates a Date instance with just a given day, setting the month and year to June 2024.
	 * @param day Given day.
	 */
	public Date(int day) throws IllegalDateException {
		if (day < 1 || day > 31) {
			throw new IllegalArgumentException("Invalid date entered: " + day);
		} else {
			this.day = day;
			this.month = 6;
			this.year = 2024;
			this.hour = 15;
			this.minute = 0;
		}
	}

	/**
	 * Gets the Date object's day number.
	 * @return Day of the month.
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Gets the Date object's month number.
	 * @return Month of the year.
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * Gets the Date object's year number.
	 * @return Year number.
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Gets the Date object's hour number.
	 * @return Hour of the day from 0-23.
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * Gets the Date object's minute number.
	 * @return Minute of the hour from 0-59.
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * Gets the time of the Date object expressed as a String.
	 * @return Formatted String regarding the time.
	 */
	public String getTime() {
		return String.format("%02d", hour) + ":" + String.format("%02d", minute);
	}

	/**
	 * getFormattedDate() returns a string containing the date, formatted in YYYY-MM-DD HH:MM
	 * @return Formatted String regarding the full date.
	 */
	public String getFormattedDate() {
		return getYear() + "-" + getMonth() + "-" + getDay() + " " + String.format("%02d", getHour()) + ":" + String.format("%02d", getMinute());
	}

	/**
	 * Sets the hour variable in the Date object.
	 * @param hour Hour number that the hour variable will be set to.
	 */
	public void setHour(int hour) {
		if (hour < 0 || hour > 23) {
			throw new IllegalArgumentException("Invalid hour entered: " + hour);
		} else {
			this.hour = hour;
		}
	}

	/**
	 * Sets the minute variable in the Date object.
	 * @param minute Minute number that the minute variable will be set to.
	 */
	public void setMinute(int minute) {
		if (minute < 0 || minute > 59) {
			throw new IllegalArgumentException("Invalid minute entered: " + minute);
		} else {
			this.minute = minute;
		}
	}

	/**
	 * getDayDifference() returns the difference between the date object and another given as a parameter in days.
	 * @param otherDate Other date object to get difference of. (otherDate - this date object).
	 * @return Difference in days between two dates.
	 */
	public int getDayDifference(Date otherDate) {
		return otherDate.getDay() - getDay();
	}

	/**
	 * Checks if this Date object's date is before a given Date object's date.
	 * @param date Other Date object to compare.
	 * @return true if this Date object is before the given Date object parameter. False otherwise.
	 */
	public boolean isBefore(Date date) {
		if (date.getYear() < this.year) {
			return false;
		}
		else if (date.getYear() == this.year && date.getMonth() < this.month) {
			return false;
		}
		else if (date.getYear() == this.year && date.getMonth() == this.month && date.getDay() < this.day) {
			return false;
		}
		else if (date.getYear() == this.year && date.getMonth() == this.month && date.getDay() == this.day && date.getHour() < this.hour) {
			return false;
		}
		else if (date.getYear() == this.year && date.getMonth() == this.month && date.getDay() == this.day && date.getHour() == this.hour && date.getMinute() < this.minute) {
			return false;
		}

		return true;
	}

	/**
	 * Checks if this Date object's date is after a given Date object's date.
	 * @param date Other Date object to compare.
	 * @return true if this Date object is after the given Date object parameter. False otherwise.
	 */
	public boolean isAfter(Date date) {
		if(date.getYear() > this.year) {
			return false;
		}
		else if(date.getYear() == this.year && date.getMonth() > this.month) {
			return false;
		}
		else if(date.getYear() == this.year && date.getMonth() == this.month && date.getDay() > this.day) {
			return false;
		}
		else if(date.getYear() == this.year && date.getMonth() == this.month && date.getDay() == this.day && date.getHour() > this.hour) {
			return false;
		}
		else if(date.getYear() == this.year && date.getMonth() == this.month && date.getDay() == this.day && date.getHour() == this.hour && date.getMinute() > this.minute) {
			return false;
		}

		return true;
	}

	/**
	 * isBetween() checks if this Date is in between two other Date instances.
	 * @param dateBefore The Date before this Date.
	 * @param dateAfter The Date after this Date.
	 * @return True if the Date object is before the 2nd date parameter and after the 1st date parameter.
	 */
	public boolean isBetween(Date dateAfter, Date dateBefore) {
		return this.isBefore(dateBefore) && this.isAfter(dateAfter);
	}
}
