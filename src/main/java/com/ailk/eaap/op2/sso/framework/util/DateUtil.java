package com.ailk.eaap.op2.sso.framework.util;

import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class DateUtil {
	public static final int SECOND = 1000;

	public static final int MINUTE = SECOND * 60;

	public static final int HOUR = MINUTE * 60;

	public static final int DAY = HOUR * 24;

	public static final int WEEK = DAY * 7;

	public static final int YEAR = DAY * 365; 

   private static final String DEF_OUT_FORMAT = "yyyy-MM-dd";

   
	public static long millionSecondsOfDay = 86400000;

	
	public static Date getDate(Date date, int i) {
		// long seconds = date.getTime() + i * 86400000;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, i);

		return calendar.getTime();

	}

	
	public static Date getDateByHour(Date date, int hour) {
		// long seconds = date.getTime() + i * 86400000;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hour);

		return calendar.getTime();

	}

	public static int getDay(Date date1, Date date2) {
		return (int) ((date2.getTime() - date1.getTime()) / millionSecondsOfDay);
	}

	
	public static int getWeekOfDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return (calendar.get(Calendar.DAY_OF_WEEK) - 1) == 0 ? 7 : calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	
	public static int getDaysOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		int monthDays = calendar.getActualMaximum(Calendar.DATE);
		return monthDays;
	}

	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	public static int getYearOfDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	public static int getMonthOfDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	public static java.sql.Date getSqlDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	
	public static Date getDate(Date date) {
		String str = dateToString(date);
		return getDate(str);

	}

	public static List getDates(Date date1, Date date2) {
		int day = getDay(date1, date2);

		List dates = new ArrayList();

		for (int i = 0; i <= day; i++) {
			Date date = getDate(date1, i);
			dates.add(toStringByFormat(date, "yyyy-MM-dd"));
		}
		return dates;
	}

	public static List getFormatDates(Date date1, Date date2, String format) {
		int day = getDay(date1, date2);

		List dates = new ArrayList();

		for (int i = 0; i <= day; i++) {
			Date date = getDate(date1, i);
			dates.add(toStringByFormat(date, format));
		}
		return dates;
	}

	
	public static String getUniueTimeString() {
		return String.valueOf(Calendar.getInstance().getTimeInMillis());
	}

	public static String dateToString(Date date) {
		if (date == null) {
			return "";
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String tmpStr = "";
		if (date != null) {
			tmpStr = sdf.format(date);
		}

		return tmpStr;
	}

	public static String datetimeToString(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String tmpStr = "";
		if (date != null) {
			tmpStr = sdf.format(date);
		}

		return tmpStr;
	}

	public static String datetimesToString(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String tmpStr = "";
		if (date != null) {
			tmpStr = sdf.format(date);
		}

		return tmpStr;
	}

	public static String toStringByFormat(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String tmpStr = "";
		if (date != null) {
			tmpStr = sdf.format(date);
		}

		return tmpStr;
	}

	public static Date toDateByFormat(String str, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date stringToDate(String str) {
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date stringToDateTime(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			return null;
		}
	}

	public static Date stringToDatetime(String str) {
		if (str == null) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			return null;
		}
	}

	public static Date getDate(String str) {
		if (str == null || str.equals("")) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			return null;
		}

	}

	public static String getTimeString(int duration) {
		int hours = duration / DateUtil.HOUR;
		int remain = duration - (hours * DateUtil.HOUR);
		int minutes = remain / DateUtil.MINUTE;
		StringBuffer time = new StringBuffer(64);
		if (hours != 0) {
			if (hours == 1) {
				time.append("1 hour and ");
			} else {
				time.append(hours).append(" hours and ");
			}
		}
		if (minutes == 1) {
			time.append("1 minute");
		} else {
			// what if minutes == 0 ???
			time.append(minutes).append(" minute(s)");
		}
		return time.toString();
	}

	
	public static boolean beforeEqual(Date f, Date t) {
		f = getDate(f);
		t = getDate(t);
		if (f.before(t) || f.equals(t)) {
			return true;
		} else {
			return false;
		}
	}

	
	public static boolean afterEqual(Date f, Date t) {
		f = getDate(f);
		t = getDate(t);
		if (f.after(t) || f.equals(t)) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean between(Date d, Date s, Date e) {
		if (d.before(e) && d.after(s)) {
			return true;
		} else {
			return false;
		}
	}

     
   public static String getCurrentDateStr()
   {
      String curDateStr = "";

      Calendar cal = Calendar.getInstance();
      int year  = cal.get(Calendar.YEAR);
      int month = cal.get(Calendar.MONTH) + 1;
      int day   = cal.get(Calendar.DAY_OF_MONTH);

      curDateStr  = String.valueOf(year) + "-";
      curDateStr += ( (month < 10) ? "0" + String.valueOf(month) : String.valueOf(month)) + "-";
      curDateStr += ( (day < 10) ? "0" + String.valueOf(day) : String.valueOf(day));

      return curDateStr;
   }


   public static String getCurrentMonthStr()
   {
      String curDateStr = "";

      Calendar cal = Calendar.getInstance();
      int year  = cal.get(Calendar.YEAR);
      int month = cal.get(Calendar.MONTH) + 1;
      int day   = cal.get(Calendar.DAY_OF_MONTH);

      curDateStr  = String.valueOf(year) + "-";
      curDateStr += ( (month < 10) ? "0" + String.valueOf(month) : String.valueOf(month));

      return curDateStr;
   }
    	public static String format(Date date, String outFormat) {
		return format(date, outFormat, Locale.ENGLISH);
	}
    	public static String format(Date date, String outFormat, Locale locale) {
		if (outFormat == null || "".compareTo(outFormat) == 0) {
			outFormat = DEF_OUT_FORMAT;
		}
		SimpleDateFormat outDateFormat = null;
		if (locale == null) {
			outDateFormat = new SimpleDateFormat(outFormat, Locale.ENGLISH);
		} else {
			outDateFormat = new SimpleDateFormat(outFormat, locale);
		}
		String outDate = outDateFormat.format(date);
		return outDate;
	}

    public static Date getBeforetime(Date date, int interval) {
        GregorianCalendar gca = new GregorianCalendar();
        gca.setTime(date);
        gca.add(Calendar.MONTH, (-1) * interval);
        return gca.getTime();
    }

    public static String getBeforetime(Date date) {
        return format(getBeforetime(date, 1), DEF_OUT_FORMAT);
    }

  public static  Date getFirstDateOfMonth(String  dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     Date date= null;
     try {
         date = sdf.parse(dateString);
     } catch (ParseException e) {
         e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
     }
        Calendar ca = Calendar.getInstance();
        ca.setTime(date) ;
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMinimum(Calendar.DAY_OF_MONTH));
        return ca.getTime();
    }

  public static  Date getEndDateOfMonth(String  dateString) {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     Date date= null;
     try {
         date = sdf.parse(dateString);
     } catch (ParseException e) {
         e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
     }
        Calendar ca = Calendar.getInstance();
        ca.setTime(date) ;
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        return ca.getTime();
    }

  public static  Date getFirstDateOfPreMonth(String  dateString) {
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     Date date= null;
     try {
         date = sdf.parse(dateString);
     } catch (ParseException e) {
         e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
     }
         Calendar ca = Calendar.getInstance();
        GregorianCalendar gca = new GregorianCalendar();
		gca.setTime(date);
		gca.add(Calendar.MONTH, (-1) * 1);
		ca.setTime(gca.getTime());
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMinimum(Calendar.DAY_OF_MONTH));
        return ca.getTime();
    }

  public static  Date getEndDateOfPreMonth(String  dateString) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     Date date= null;
     try {
         date = sdf.parse(dateString);
     } catch (ParseException e) {
         e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
     }
     Calendar ca = Calendar.getInstance();
        GregorianCalendar gca = new GregorianCalendar();
		gca.setTime(date);
		gca.add(Calendar.MONTH, (-1) * 1);
		ca.setTime(gca.getTime());
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        return ca.getTime();
    }

    public static void main(String[] args) {
		int year = 2007;
		int month = 4;
		int days = 21;
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);

	}
}
