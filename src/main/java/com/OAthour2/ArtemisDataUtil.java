package com.OAthour2;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Transy on 2016/10/12.
 */
public class ArtemisDataUtil {
    private static Logger log = LoggerFactory.getLogger(ArtemisDataUtil.class);
    private static SimpleDateFormat inputFormater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private static SimpleDateFormat outputFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat outputShortFormater = new SimpleDateFormat("yyyy-MM-dd");

    public static String format(String dateStr){
        try {
            Date date = inputFormater.parse(dateStr);
            date = DateUtils.addHours(date,8);
            return outputFormater.format(date);
        }catch (Exception e){
            log.error("格式化日期错误,日期:{}",dateStr);
        }
        return null;
    }
    public static String shortFormat(String dateStr){
        try {
            Date date = inputFormater.parse(dateStr);
            date = DateUtils.addHours(date,8);
            return outputShortFormater.format(date);
        }catch (Exception e){
            log.error("格式化日期错误,日期:{}",dateStr);
        }
        return null;
    }
}
