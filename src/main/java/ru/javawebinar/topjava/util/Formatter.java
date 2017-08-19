package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Formatter {
    private static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static String localDateTime(LocalDateTime value) {
        return value.format(PATTERN);
    }
}
