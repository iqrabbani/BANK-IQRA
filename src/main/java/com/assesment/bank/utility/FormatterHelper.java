package com.assesment.bank.utility;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FormatterHelper{
    static Locale indoFormat = new Locale("id","ID");
    static DateTimeFormatter formatterParse = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy", indoFormat);
    static DateTimeFormatter formatterTime1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", indoFormat);
    static DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd MMMM yyyy", indoFormat);

    public static String formatDate(LocalDate date){
        return date.format(formatter2);
    }

    public static String formatDateTime(LocalDateTime date){
        return date.format(formatterTime1);
    }

    public static String formatDateSlash(LocalDate date){
        return date.format(formatter1);
    }

    public static String currencyFormat(BigDecimal price){
        Locale locale = new Locale("id", "ID");
        return NumberFormat.getCurrencyInstance(locale).format(price);
    }


    public static int calculateAge(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(date, currentDate);

        return period.getYears();
    }
}
