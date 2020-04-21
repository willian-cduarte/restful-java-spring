package com.will.udemy.microservices.infrastructure.formatter;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DatePatternFormatting {

    private SimpleDateFormat timestampFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    }
    private SimpleDateFormat stringDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    public String toStringTimestamp(Date date) {
        return timestampFormat().format(date);
    }

    public Date stringTimestampToDate(String timestamp) throws ParseException {
        return timestampFormat().parse(timestamp);
    }

    public String birthDateToString(Date date) {
        return stringDateFormat().format(date);
    }

    public Date toDate(String timestamp) throws ParseException {
        return stringDateFormat().parse(timestamp);
    }

}
