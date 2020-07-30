package com.chelkatrao;

public class Utils {

    public static int getHour(String time) {
        return Integer.parseInt(time.substring(0, 2));
    }

    public static int getMinute(String time) {
        return Integer.parseInt(time.substring(3, 5));
    }

    public static int getSecond(String time) {
        return 0;
    }
}
