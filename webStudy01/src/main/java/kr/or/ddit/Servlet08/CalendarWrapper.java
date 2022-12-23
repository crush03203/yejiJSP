package kr.or.ddit.servlet08;

import java.util.Calendar;
import java.util.Locale;

import static java.util.Calendar.*;

import java.text.DateFormatSymbols;

public class CalendarWrapper {
//어댑터에는 어댑티가 먼저 있어야한다
	private Calendar adaptee;
	private Locale locale;
	
	private int offset;
	private int dayofWeekFirst;
	private int lastDate;
	private String[] weekDays;
	private int beforeYear;
	private int beforeMonth;
	private int currentYear;
	private int currentMonth;
	private int nextYear;
	private int nextMonth;
	private String[] months;

	public CalendarWrapper(Calendar adaptee, Locale locale) {
		super();
		this.adaptee = adaptee;
		this.locale = locale;

		DateFormatSymbols dfs = DateFormatSymbols.getInstance(locale);
		weekDays = dfs.getWeekdays();
		months = dfs.getMonths();

		adaptee.set(DAY_OF_MONTH, 1);
		dayofWeekFirst = adaptee.get(DAY_OF_WEEK);
		offset = dayofWeekFirst - SUNDAY;
		lastDate = adaptee.getActualMaximum(DAY_OF_MONTH);
		
		
		currentYear = adaptee.get(YEAR);
		currentMonth = adaptee.get(MONTH);
		
		
		
		
		adaptee.add(MONTH, -1);
		beforeYear = adaptee.get(YEAR);
		beforeMonth = adaptee.get(MONTH);
		
		adaptee.add(MONTH,2);
		nextYear = adaptee.get(YEAR);
		nextMonth = adaptee.get(MONTH);
		
		adaptee.add(MONTH,-1);
	}
	
	public String[] getWeekDays() {
		return weekDays;
	}

	public void setWeekDays(String[] weekDays) {
		this.weekDays = weekDays;
	}

	public Calendar getAdaptee() {
		return adaptee;
	}

	public void setAdaptee(Calendar adaptee) {
		this.adaptee = adaptee;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getDayofWeekFirst() {
		return dayofWeekFirst;
	}

	public void setDayofWeekFirst(int dayofWeekFirst) {
		this.dayofWeekFirst = dayofWeekFirst;
	}

	public int getLastDate() {
		return lastDate;
	}

	public void setLastDate(int lastDate) {
		this.lastDate = lastDate;
	}
	
	public int getBeforeYear() {
		return beforeYear;
	}

	public void setBeforeYear(int beforeYear) {
		this.beforeYear = beforeYear;
	}

	public int getBeforeMonth() {
		return beforeMonth;
	}

	public void setBeforeMonth(int beforeMonth) {
		this.beforeMonth = beforeMonth;
	}

	public int getNextYear() {
		return nextYear;
	}

	public void setNextYear(int nextYear) {
		this.nextYear = nextYear;
	}

	public int getNextMonth() {
		return nextMonth;
	}

	public void setNextMonth(int nextMonth) {
		this.nextMonth = nextMonth;
	}
	
	public String[] getMonths() {
		return months;
	}

	public void setMonths(String[] months) {
		this.months = months;
	}
	

	public int getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(int currentYear) {
		this.currentYear = currentYear;
	}

	public int getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(int currentMonth) {
		this.currentMonth = currentMonth;
	}
	
	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	@Override
	public String toString() {
		return String.format(locale,"%1$tY,%1$tB", adaptee);

	}
}
