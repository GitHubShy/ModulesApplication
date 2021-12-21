package com.modules.study.algorithm;

/**
 * 给你一个字符串 date ，按 YYYY-MM-DD 格式表示一个 现行公元纪年法 日期。请你计算并返回该日期是当年的第几天。
 *
 * 通常情况下，我们认为 1 月 1 日是每年的第 1 天，1 月 2 日是每年的第 2 天，依此类推。每个月的天数与现行公元纪年法（格里高利历）一致。
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/day-of-the-year
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DayInYear {

    public static final String date = "1980-02-01";

    public static void main(String[] args) {
        int length = date.length();
        String year = date.substring(0,4);
        String month = date.substring(5,7);
        String day = date.substring(8,10);

        int yearNumber = Integer.valueOf(year);
        int monthNumer = Integer.valueOf(month);
        int dayNumber = Integer.valueOf(day);

        //System.out.print(monthNumer);


        int[] days = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};

        if (yearNumber % 100 == 0) {//世纪年
            if (yearNumber % 400 == 0) {
                days = new int[]{31,29,31,30,31,30,31,31,30,31,30,31};
            }
        } else {//普通年
            if (yearNumber % 4 == 0) {
                days = new int[]{31,29,31,30,31,30,31,31,30,31,30,31};
            }
        }

        int dayIndex = 0;
        for (int i = 0 ; i < monthNumer - 1 ; i++) {
            dayIndex = dayIndex + days[i];
        }
        dayIndex = dayIndex + dayNumber;

        System.out.print(dayIndex);

    }

}
