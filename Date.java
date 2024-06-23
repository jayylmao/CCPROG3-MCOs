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

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

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
