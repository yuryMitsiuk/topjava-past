package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static boolean isBetween(LocalTime lt, LocalTime start, LocalTime end) {
        return lt.compareTo(start) >= 0 && lt.compareTo(end) <= 0;
    }

    public static boolean isBetweenDate(LocalDate ld, LocalDate start, LocalDate end) {
        return ld.compareTo(start) >= 0 && ld.compareTo(end) <= 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}
