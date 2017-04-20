package com.jucaipen.main.payinfo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * @package :com.base.util
 * @memo :
 * @fileName:DateUtil.java @class :DateUtil
 * @write :huangjb
 * @date :2005-10-19
 */
public class DateUtil{

    /**
     * ��������ʱ���ʽ���м���ţ�������"-"��"/"��":"������Ĭ��Ϊ"-"ʱ��Ĭ��Ϊ":"
     */
    private static final String formatDateSign    = "-";

    private static final String formatDandTSign   = "/";

    private static final String formatTimeSign    = ":";

    private static final String simpleDateFormat  = "yyyy" + formatDateSign
                                                          + "MM"
                                                          + formatDateSign
                                                          + "dd";

    private static final String simpleTimeFormat  = simpleDateFormat + " HH"
                                                          + formatTimeSign
                                                          + "mm"
                                                          + formatTimeSign
                                                          + "ss";

    static SimpleDateFormat     chineseDateFormat = new SimpleDateFormat(
                                                          "yyyy-MM-dd");

    /**
     * @�������ƣ�dateTo8 @����������10λyyyy/MM/dd,yyyy-MM-dd,yyyy:MM:dd ת��Ϊ8λyyyyMMdd
     * @param date Ҫ��ʽ���������ַ���: 10λ yyyy/MM/dd��yyyy-MM-dd��yyyy:MM:dd
     * @return ���ظ�ʽ���������
     */

    public static String timeNumberTodate(String time)
    {
        int len = time.length();
        return time.substring(0, len - 4) + formatDateSign
                + time.substring(len - 4, len - 2) + formatDateSign
                + time.substring(len - 2, len);
    }

    public static String dateTo8(String date)
    {
        if (date == null)
            return "";
        if (date.trim().length() != 10)
        {
            return date;
        }
        return date.substring(0, 4) + date.substring(5, 7)
                + date.substring(8, 10);
    }

    public static String dateTo8Ext(String date)
    {
        if (null == date || date.equals(""))
            return "";
        String datestr[] = DataTypeUtil.spiltStr(date, "-");
        String mon = "";
        String day = "";
        if (datestr[1].trim().length() != 2)
        {
            mon = "0" + datestr[1];
        } else
        {
            mon = datestr[1];
        }
        if (datestr[2].trim().length() != 2)
        {
            day = "0" + datestr[2];
        } else
        {
            day = datestr[2];
        }

        return datestr[0] + mon + day;
    }

    /**
     * @�������ƣ�dateTo8 @����������8λyyyyMMdd ת��Ϊyyy/MM/dd,yyyy-MM-dd,yyyy:MM:dd
     * @param date Ҫ��ʽ���������ַ���: 8λyyyyMMdd
     * @return ���ظ�ʽ���������
     */
    public static String dateTo10(String date)
    {
        if (date == null)
            return "";
        if (date.trim().length() != 8)
            return "";
        return date.substring(0, 4) + formatDateSign + date.substring(4, 6)
                + formatDateSign + date.substring(6, 8);
    }

    public static String dateTo19(String date)
    {
        int len = date.length();
        if (len != 14)
            return date;
        return date.substring(0, 4) + formatDateSign + date.substring(4, 6)
                + formatDateSign + date.substring(6, 8) + formatDandTSign
                + date.substring(8, 10) + formatTimeSign
                + date.substring(10, 12) + formatTimeSign
                + date.substring(12, 14);
    }

    /**
     * @�������ƣ�dateTo14 @����������8λyyyyMMdd ת��Ϊyyy/MM/dd,yyyy-MM-dd,yyyy:MM:dd
     * @param date Ҫ��ʽ���������ַ���: 8λyyyyMMdd
     * @return ���ظ�ʽ���������
     */
    public static String dateTo14(String date)
    {
        if (date == null)
            return "";
        if (date.trim().length() != 19)
            return date;
        return date.substring(0, 4) + date.substring(5, 7)
                + date.substring(8, 10) + date.substring(11, 13)
                + date.substring(14, 16) + date.substring(17);
    }

    /**
     * @�������ƣ�oracleDateTo8 @����������9λyy-mmm-dd ת��Ϊyyyy-MM-dd
     * @param date Ҫ��ʽ���������ַ���:9λyy-mmm-dd
     * @return ���ظ�ʽ���������
     * @write :wujs
     * @date :2007-08-30
     */
    public static String oracleDateTo8(String date)
    {
        if (date == null)
            return "";
        if (date.trim().length() != 9)
            return date;
        String month = "";
        String smonth = date.substring(3, 6);
        if (smonth.equals("JAN"))
            month = "01";
        else if (smonth.equals("FEB"))
            month = "02";
        else if (smonth.equals("MAR"))
            month = "03";
        else if (smonth.equals("APR"))
            month = "04";
        else if (smonth.equals("MAY"))
            month = "05";
        else if (smonth.equals("JUN"))
            month = "06";
        else if (smonth.equals("JUL"))
            month = "07";
        else if (smonth.equals("AUG"))
            month = "08";
        else if (smonth.equals("SEP"))
            month = "09";
        else if (smonth.equals("OCT"))
            month = "10";
        else if (smonth.equals("NOV"))
            month = "11";
        else if (smonth.equals("DEC"))
            month = "12";
        return "20" + date.substring(7, 9) + formatDateSign + month
                + formatDateSign + date.substring(0, 2);
    }

    /**
     * ʱ���ʽ���� <br>
     * 8λ(HH:mm:ss)��7λ(H:mm:ss)��ʱ��ת��Ϊ6λ(HHmmss)��5λ(Hmmss) <br>
     * ʱ��ķָ��ַ������������ַ���һ��Ϊð��(:)
     * 
     * @param time -Ҫ��ʽ����ʱ���ַ���:8λ(HH:mm:ss)��7λ(H:mm:ss)
     * @return String - ���ظ�ʽ�����ʱ�� <br>
     * ��time���Ȳ�Ϊ8��7������ʽ��Ϊ8λ(HH:mm:ss)��7λ(H:mm:ss)��ʽ��ʱ�䣬��ֱ�ӷ���date�� <br>
     * ��timeΪnull, �򷵻�""
     */
    public static String timeTo6(String time)
    {
        int len = time.length();
        if (len < 7 || len > 8)
            return "";
        return time.substring(0, len - 6) + time.substring(len - 5, len - 3)
                + time.substring(len - 2, len);
    }

    /**
     * ʱ���ʽ���� <br>
     * 6λ(HHmmss)��5λ(Hmmss)��ʱ��ת��Ϊ8λ(HH:mm:ss)��7λ(H:mm:ss)
     * @param date -Ҫ��ʽ����ʱ���ַ���: 6λ(HHmmss)��5λ(Hmmss)
     * @return String - ���ظ�ʽ�����ʱ�� <br>
     * ��time���Ȳ�Ϊ5��6������ʽ��Ϊ6λ(HHmmss)��5λ(Hmmss)��ʽ��ʱ�䣬��ֱ�ӷ���date�� <br>
     * ��timeΪnull, �򷵻�""
     */
    public static String timeTo8(String time)
    {
        int len = time.length();
        if (len < 5 || len > 6)
            return "";
        return time.substring(0, len - 4) + formatTimeSign
                + time.substring(len - 4, len - 2) + formatTimeSign
                + time.substring(len - 2, len);
    }

    /**
     * @�������ƣ�stringToSqlDate 
     * @������������String�͵����ڸ�ʽת��ΪSql�͵����ڸ�ʽ 
     * @param str
     * @return
     */
    public static java.sql.Date stringToSqlDate(String str)
    {
        if (stringToUtilDate(str) == null || str.length() < 1)
            return null;
        else
            return new java.sql.Date(stringToUtilDate(str).getTime());
    }

    /**
     * @�������ƣ�stringToUtilDate 
     * @������������String�͵����ڸ�ʽת��ΪUtil�͵����ڸ�ʽ 
     * @param str
     * @return
     */
    public static java.util.Date stringToUtilDate(String str)
    {
        if (null != str && str.length() > 0)
        {
            try
            {
                // ������ʱ���ʽ����ת����
                if (str.length() <= simpleDateFormat.length())
                { // ֻ�������ڡ�
                    return (new SimpleDateFormat(simpleDateFormat)).parse(str);
                } else
                { // ���ں�ʱ�䶼��
                    return (new SimpleDateFormat(simpleTimeFormat)).parse(str);
                }
            } catch (ParseException ex)
            {
                ex.printStackTrace();
                return null;
            }
        } else
            return null;
    }

    /**
     * @�������ƣ�utilDateToSql
     * @������������Util�͵����ڸ�ʽת��ΪSql�͵����ڸ�ʽ
     * @param date
     * @return
     */
    public static java.sql.Date utilDateToSql(java.util.Date date)
    {
        return new java.sql.Date(date.getTime());
    }

    /**
     * @�������ƣ�sqlDateToUtil
     * @������������Sql�͵����ڸ�ʽת��ΪUtil�͵����ڸ�ʽ
     * @param date
     * @return
     */
    public static java.util.Date sqlDateToUtil(java.sql.Date date)
    {
        return new java.util.Date(date.getTime());
    }

    /**
     * @�������ƣ�toDateTimeString
     * @������������Sql�͵Ĵ�ʱ�����ڸ�ʽת��ΪString�͵����ڸ�ʽ
     * @param d
     * @return
     */
    public static String toDateTimeString(java.sql.Date d)
    {
        if (d == null)
        {
            return null;
        } else
        {
            return (new SimpleDateFormat(simpleTimeFormat)).format(d);
        }
    }

    /**
     * @�������ƣ�toDateTimeString
     * @������������Util�͵Ĵ�ʱ�����ڸ�ʽת��ΪString�͵����ڸ�ʽ
     * @param d
     * @return
     */
    public static String toDateTimeString(java.util.Date d)
    {
        if (d == null)
        {
            return null;
        } else
        {
            return (new SimpleDateFormat(simpleTimeFormat)).format(d);
        }
    }

    /**
     * @�������ƣ�toDateString
     * @������������Sql�͵�ֻ�����ڸ�ʽת��ΪString�͵����ڸ�ʽ
     * @param d
     * @return
     */
    public static String toDateString(java.sql.Date d)
    {
        if (d == null)
        {
            return null;
        } else
        {
            return (new SimpleDateFormat(simpleDateFormat)).format(d);
        }
    }

    /**
     * @�������ƣ�toDateString
     * @������������Sql�͵�ֻ�����ڸ�ʽת��ΪString�͵����ڸ�ʽ
     * @param d
     * @return
     */
    public static String toDateString(java.util.Date d)
    {
        if (d == null)
        {
            return null;
        } else
        {
            return (new SimpleDateFormat(simpleDateFormat)).format(d);
        }
    }

    /**
     * @�������ƣ�getCurrentDate 
     * @������������ȡ��ǰ���ں�ʱ�� 
     * @return
     */
    public static java.sql.Date getCurrentDateTime()
    {
        Calendar newcalendar = null;
        newcalendar = Calendar.getInstance();

        String year = String.valueOf(newcalendar.get(Calendar.YEAR));
        String month = String.valueOf(newcalendar.get(Calendar.MONTH) + 1);
        String day = String.valueOf(newcalendar.get(Calendar.DAY_OF_MONTH));
        String hour = String.valueOf(newcalendar.get(Calendar.HOUR_OF_DAY));
        String min = String.valueOf(newcalendar.get(Calendar.MINUTE));
        String sec = String.valueOf(newcalendar.get(Calendar.MINUTE));

        return stringToSqlDate(year + formatDateSign + month + formatDateSign
                + day + " " + hour + formatTimeSign + min + formatTimeSign
                + sec);
    }

    /**
     * @�������ƣ�getCurrentDate 
     * @������������ȡ��ǰ����(ֻ������) 
     * @return
     */
    public static java.sql.Date getCurrentDate()
    {
        Calendar newcalendar = null;
        newcalendar = Calendar.getInstance();

        String year = String.valueOf(newcalendar.get(Calendar.YEAR));
        String month = String.valueOf(newcalendar.get(Calendar.MONTH) + 1);
        String day = String.valueOf(newcalendar.get(Calendar.DAY_OF_MONTH));

        return stringToSqlDate(year + formatDateSign + month + formatDateSign
                + day);
    }

    /**
     * @�������ƣ�getCurrentTime 
     * @������������ȡ��ǰʱ��(ֻ��ʱ��) 
     * @return
     */
    public static String getCurrentTime()
    {
        SimpleDateFormat dataFormat = new SimpleDateFormat("HHmmss");
        Date date = new Date();
        String timeString = dataFormat.format(date);
        return timeTo8(timeString);
    }

    /**
     * @�������ƣ�getCurrentTime 
     * @������������ȡ��ǰʱ��(ֻ��ʱ��) 
     * @return
     */
    public static String getCurrentDateTimeStr()
    {
        SimpleDateFormat dataFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String timeString = dataFormat.format(date);
        return timeString;
    }

    public static String getCurrentDateTimeStr1()
    {
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String timeString = dataFormat.format(date);
        return timeString;
    }
    public static String getCurrentDateTimeStr2()
    {
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date date = new Date();
        String timeString = dataFormat.format(date);
        return timeString;
    }

    /**
     * ��������������yyyyMMddHHmmss��ʽ����ʱ���ַ���
     * @param date
     * @return
     */
    public static String getCurrentDateTimeStr1(Date date)
    {
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeString = dataFormat.format(date);
        return timeString;
    }

    public static String getCurrentDateStr()
    {
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String timeString = dataFormat.format(date);
        return timeString;
    }

    public static String getCurrentDateStr1()
    {
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String timeString = dataFormat.format(date);
        return timeString;
    }

    public static String getWeekStr()
    {
        String s = "";
        int week = getWeek();
        switch (week)
        {
            case 1:
                s = "����һ";
                break;
            case 2:
                s = "���ڶ�";
                break;
            case 3:
                s = "������";
                break;
            case 4:
                s = "������";
                break;
            case 5:
                s = "������";
                break;
            case 6:
                s = "������";
                break;
            case 7:
                s = "������";
                break;
            default:
                break;
        }
        return s;
    }

    /**
     * ��ȡ��ǰ�����ڼ��� <br>
     * 0Ϊ�����졢1Ϊ����һ���Դ����ơ�
     * @return String - ���ص�ǰ���ڼ�
     */
    public static int getWeek()
    {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int posOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        posOfWeek--; // Calendar��ʽת������ͨ��ʽ 0�����죬 1 ����һ...
        return posOfWeek;
    }

    /**
     * 
     * @�������ƣ�addYear
     * @����������
     * @param beginTime
     * @return
     */
    public static java.sql.Date addYear(java.sql.Date beginTime, int i)
    {
        Calendar date = Calendar.getInstance();
        date.setTime(beginTime);
        date.add(Calendar.YEAR, i);
        return utilDateToSql(date.getTime());
    }

    /**
     * 
     * @�������ƣ�addMonth
     * @����������
     * @param beginTime
     * @return
     */
    public static java.sql.Date addMonth(java.sql.Date beginTime, int i)
    {
        Calendar date = Calendar.getInstance();
        date.setTime(beginTime);
        date.add(Calendar.MONTH, i);
        return utilDateToSql(date.getTime());
    }

    /**
     * 
     * @�������ƣ�addMonth
     * @����������
     * @param beginTime
     * @return
     */
    public static java.util.Date addMonth(java.util.Date beginTime, int i)
    {
        Calendar date = Calendar.getInstance();
        date.setTime(beginTime);
        date.add(Calendar.MONTH, i);
        return date.getTime();
    }

    /**
     * 
     * @�������ƣ�addDay
     * @����������
     * @param beginTime
     * @return
     */
    public static java.sql.Date addDay(java.sql.Date beginTime, int i)
    {
        Calendar date = Calendar.getInstance();
        date.setTime(beginTime);
        date.add(Calendar.DAY_OF_MONTH, i);
        return utilDateToSql(date.getTime());
    }

    /**
     * 
     * @�������ƣ�compareYear
     * @�����������Ƚ���
     * @param beginTime
     * @param endTime
     * @return
     */
    public static int compareYear(java.sql.Date beginTime, java.sql.Date endTime)
    {
        Calendar begin = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        begin.setTime(beginTime);
        end.setTime(endTime);
        int compareYear = end.get(Calendar.YEAR) - begin.get(Calendar.YEAR);
        return compareYear;
    }

    /**
     * 
     * @�������ƣ�compareMonth
     * @�����������Ƚ���
     * @param beginTime
     * @param endTime
     * @return
     */
    public static int compareMonth(java.sql.Date beginTime,
            java.sql.Date endTime)
    {
        int compareYear = compareYear(beginTime, endTime);
        Calendar begin = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        begin.setTime(beginTime);
        end.setTime(endTime);
        int compareMonth = compareYear * 12
                + (end.get(Calendar.MONTH) - begin.get(Calendar.MONTH));
        return compareMonth;
    }

    /**
     * 
     * @�������ƣ�compareDay
     * @�����������Ƚ���
     * @param beginTime
     * @param endTime
     * @return
     */
    public static int compareDay(java.sql.Date beginTime, java.sql.Date endTime)
    {
        long compare = (endTime.getTime() - beginTime.getTime())
                / (1000 * 3600 * 24);
        String compareStr = String.valueOf(compare);
        return Integer.parseInt(compareStr);
    }

    /**
     * 
     * @�������ƣ�compareHour
     * @�����������Ƚ�Сʱ
     * @param date
     * @param date2
     * @return
     */
    public static int compareHour(Date date, Date date2)
    {
        long compare = (date2.getTime() - date.getTime()) / (1000 * 3600);
        String compareStr = String.valueOf(compare);
        return Integer.parseInt(compareStr);
    }

    /**
     * 
     * @�������ƣ�compareHour
     * @�����������Ƚ�Сʱ
     * @param beginTime
     * @param endTime
     * @return
     */
    public static int compareHourByTimestamp(String beginTime, String endTime)
    {
        Long beginTimestamp = Long.parseLong(beginTime);
        Long endTimestamp = Long.parseLong(endTime);
        return compareHour(new java.util.Date(beginTimestamp),
                new java.util.Date(endTimestamp));
    }

    /**
     * ��ȡһ���µ����һ�졣 <br>
     * ����20030111,��һ�·ݵ����һ����20030131
     * @param date -ָ������(yyyyMMdd)
     * @return String - ���ؼ���������
     */
    public static String getLastDayOfMonth(String date)
    {
        if (date.length() != 8)
            return "";
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(4, 6));
        int day = 0;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
                || month == 10 || month == 12)
        {
            day = 31;
        } else if (month == 2)
        {
            if (((year % 4) == 0) && ((year % 100) != 0))
            {
                day = 29;
            } else
            {
                day = 28;
            }
        } else
        {
            day = 30;
        }

        String newDate = "" + year;
        if (month < 10)
        {
            newDate += "0" + month + day;
        } else
        {
            newDate += "" + month + day;
        }
        return newDate;
    }

    /**
     * ��ȡһ�����ڵ����� <br>
     * 
     * @param date -ָ������(yyyy-MM-dd)
     * @return String - ������õ�����
     */
    public static String getWeek(String sDate)
    {
        final String dayNames[] = { "������", "����һ", "���ڶ�", "������", "������", "������",
                "������" };

        SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance();
        Date date = new Date();

        try
        {
            date = sdfInput.parse(sDate);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }

        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayNames[dayOfWeek - 1];
    }


  /*  // date����ת��ΪGMT��ʽ
    public static String StrToGMTDateString(String str)
    {
        if (null == str || str.equals("") || str.equals("NULL"))
        {
            return "";
        }
        Date date = stringToUtilDate(str);
        String[] array = FuncUtils.spiltStr(date.toGMTString(), " ");
        if (array[0].length() < 2)
        {
            array[0] = "0" + array[0];
        }
        array[2] = array[2].substring(2);
        return array[2] + "-" + array[1] + "-" + array[0];
    }
*/
    /**
     * ���ڴ���
     * @param dateStr
     * @return
     */
  /*  public static String dateProcess(String dateStr)
    {
        if (FuncUtils.isNull(dateStr))
            return "";
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        try
        {
            return format.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .parse(dateStr));
        } catch (ParseException e)
        {
        }
        return null;
    }*/

    /**
     * ���ڴ���
     * @param dateStr
     * @return
     */
   /* public static String dateConv(String dateStr, String from_pattern,
            String to_pattern)
    {
        if (FuncUtils.isNull(dateStr))
            return "";
        SimpleDateFormat format = new SimpleDateFormat(to_pattern);
        try
        {
            return format.format(new SimpleDateFormat(from_pattern)
                    .parse(dateStr));
        } catch (ParseException e)
        {
        }
        return null;
    }*/

    public static boolean compareAWithB(Date a, Date b)
    {
        return a.before(b);
    }

    /**
     * �Ƚ�String���͵�ʱ���С
     * @param a
     * @param b
     * @return true ��ʾa<b
     */
    public static boolean compareString(String a, String b)
    {
        java.util.Date x = stringToUtilDate(a);
        java.util.Date y = stringToUtilDate(b);
        return compareAWithB(x, y);
    }

    /**
     * �Ƚϵ�ǰʱ���Ƿ񳬳�ָ��ʱ��̶��ĺ�����
     * @param a String������yyyy-MM-dd HH:mm:ss
     * @param time �ȶԵ�����
     * @return
     */
    public static boolean compareWithNow(String a, String time)
    {
        java.util.Date x = stringToUtilDate(a);
        int interval = Integer.parseInt(time);
        java.util.Date y = new Date(System.currentTimeMillis() - interval
                * 1000);
        return compareAWithB(x, y);
    }

    /**
     * @�������ƣ�getCurrentYearMonthStr 
     * @������������ȡ��ǰ����(ֻ������) ��ʽ yyMM
     * @return
     */
    public static String getCurrentYearMonthStr()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMM");
        Date date = new Date();
        String dateString = dateFormat.format(date);
        return dateString;
    }

    /**
     * ��ǰʱ��ӷ��� ��ʽyyyyMMddHHmmss
     * @param minute
     * @return
     */
   /* public static String addMinute(String minute)
    {
        if(FuncUtils.isNull(minute)){
            return null;
        }

        try
        {
            int minuteInt=Integer.valueOf(minute);
            java.util.Date newDate = new java.util.Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(newDate);
            cal.add(Calendar.MINUTE, minuteInt);
            Date nextDate = cal.getTime();
            String next_dateStr = new SimpleDateFormat("yyyyMMddHHmmss")
                    .format(nextDate);
            return next_dateStr;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }*/
}