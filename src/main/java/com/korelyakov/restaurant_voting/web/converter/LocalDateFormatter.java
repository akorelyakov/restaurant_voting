package com.korelyakov.restaurant_voting.web.converter;

import org.springframework.format.Formatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.korelyakov.restaurant_voting.util.DateTimeUtil.parseLocalDate;

public class LocalDateFormatter implements Formatter<LocalDate> {

    @Override
    public LocalDate parse(String text, Locale locale) {
        return parseLocalDate(text);
    }

    @Override
    public String print(LocalDate lt, Locale locale) {
        return lt.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}

