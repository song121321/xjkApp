package song.song121321.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import song.song121321.config.MyConfig;

public class StringUtil {
    /**
     * 将网页文本中的图片相对路径转化为绝对路径
     *
     * @param rUrl 20px;\"><img src=\"/UEditor/
     * @return 20px;\"><img src=\"http://wlw.jtslkj.cn:8021/UEditor/
     */
    public static String imgUrlFromRelativeFromAbsolute(String rUrl) {
        if (rUrl.contains("img src=\"")) {
            rUrl = rUrl.replaceAll("img src=\"", "img src=\"" + MyConfig.picServer);
        }
        return rUrl;
    }

    public static boolean isEmpty(String str) {
        return !notEmpty(str);
    }

    public static boolean notEmpty(String str) {
        return str != null && !str.trim().equals("");
    }

    public static String getCurrentMonthStr() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(new java.util.Date());
    }
    public static String getPreMonthStr(String monthStr) {
        Calendar calendar = Calendar.getInstance();
        String year = monthStr.substring(0, 4);
        String month = monthStr.substring(5, 7);
        calendar.set(Integer.parseInt(year),Integer.parseInt(month)-1,1);
        calendar.add(Calendar.MONTH,-1);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(calendar.getTime());
    }
    public static String getNextMonthStr(String monthStr) {
        Calendar calendar = Calendar.getInstance();
        String year = monthStr.substring(0, 4);
        String month = monthStr.substring(5, 7);
        calendar.set(Integer.parseInt(year),Integer.parseInt(month)-1,1);
        calendar.add(Calendar.MONTH,1);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(calendar.getTime());
    }

    public static String getCurrentYearStr() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return sdf.format(new java.util.Date());
    }

    public static String getCurrentDateStr() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new java.util.Date());
    }

    public static String getCurrentDateTimeStr() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new java.util.Date());
    }
}
