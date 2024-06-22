package com.pup.global.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateFormatUtils {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * String을 LocalDate로 변환
     */
    public static LocalDate getLocalDateFromString(String date) {
        return LocalDate.parse(date, dateFormatter);
    }

    /**
     * String을 LocalDateTime으로 변환
     */
    public static LocalDateTime getFirstLocalDateTimeFromString(String dateTime) {
        LocalDate localDate = getLocalDateFromString(dateTime);
        LocalTime localTime = LocalTime.of(0, 0, 0);

        return localDate.atTime(localTime);
    }

    /**
     * String을 LocalDateTime으로 변환
     */
    public static LocalDateTime getLastLocalDateTimeFromString(String dateTime) {
        LocalDate localDate = getLocalDateFromString(dateTime);
        LocalTime localTime = LocalTime.of(23, 59, 59);

        return localDate.atTime(localTime);
    }
}
