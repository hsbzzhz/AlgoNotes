package 基础数据结构操作;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/*
 * Java有两套时间API：
 * 1.旧的Date、Calendar和TimeZone（位于java.util包里）
 * 2.新的LocalDateTime、ZonedDateTime、ZoneId等（位于java.time包里）
 *  ref. https://www.liaoxuefeng.com/wiki/1252599548343744/1303791989162017
 */
public class DateStructure {
    public static void OldWay(){
        // Date
        //获取当前时间
        Date date = new Date();
        // 打印时间戳
        System.out.println(date.getTime());
        // 打印出带时区的时间
        System.out.println(date.toString());
        // 标准版输出格式（更多格式例如MM改成MMM，参见文档）
        SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(fmt1.format(date));
        //Date 不好用的原因
        // 1. 没有时区的支持
        // 2. 缺少计算能力
    }

    public static void NewWay(){
        // 新的LocalDateTime
        // 2022-09-17
        // ref. https://www.liaoxuefeng.com/wiki/1252599548343744/1303871087444002
        // LocalDateTime 的转换：https://blog.51cto.com/u_15303040/5215798
        LocalDate d = LocalDate.now();
        // 16:53:40.753
        LocalTime t = LocalTime.now();
        // 2022-09-17T16:54:30.576 (严格按照ISO 8601格式打印)
        LocalDateTime dt = LocalDateTime.now();
        System.out.println(dt);
        // 自定义输出格式
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/M//dd HH:mm:ss");

        //
        long second = dt.toEpochSecond(ZoneOffset.of("+8"));  // 秒级时间戳
        long milliSecond = dt.toInstant(ZoneOffset.of("+8")).toEpochMilli();  // 毫秒级时间戳
    }

    public static void main(String[] args) {
        NewWay();
    }
}
