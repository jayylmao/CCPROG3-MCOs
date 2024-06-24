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

	public Date(int day, int month, int year) {
		if (day < 1 || day > 31) {
			throw new IllegalArgumentException("Invalid date entered: " + day);
		} else if (month < 1 || month > 12) {
			throw new IllegalArgumentException("Invalid month entered: " + month);
		} else if (year < 1) {
			throw new IllegalArgumentException("Invalid year entered: " + year);
		} else {
			this.day = day;
			this.month = month;
			this.year = year;
		}
	}

	public Date(int day, int month, int year, int hour, int minute) {
		this(day, month, year);
		this.hour = hour;
		this.minute = minute;
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

	public void setDay(int day) {
		this.day = day;
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
		int dateDiff = otherDate.getDay() - getDay();
		int monthDiff = otherDate.getMonth() - getMonth();
		int yearDiff = otherDate.getYear() - getYear();

		return new Date(dateDiff, monthDiff, yearDiff);
	}

	/**
	 * Checks if this Date object's date is before a given Date object's date.
	 * @param date Other Date object to compare.
	 * @return true if this Date object is before the given Date object parameter. False otherwise.
	 */
	public boolean isBefore(Date date) {
		if(date.getYear() < this.year) {
			return false;
		}
		else if(date.getYear() == this.year && date.getMonth() < this.month) {
			return false;
		}
		else if(date.getYear() == this.year && date.getMonth() == this.month && date.getDay() < this.day) {
			return false;
		}
		else if(date.getYear() == this.year && date.getMonth() == this.month && date.getDay() == this.day && date.getHour() < this.hour) {
			return false;
		}
		else if(date.getYear() == this.year && date.getMonth() == this.month && date.getDay() == this.day && date.getHour() == this.hour && date.getMinute() <= this.minute) {
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
		else if(date.getYear() == this.year && date.getMonth() == this.month && date.getDay() == this.day && date.getHour() == this.hour && date.getMinute() >= this.minute) {
			return false;
		}
		
		return true;
	}
}
