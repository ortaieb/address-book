package com.taieb.addressbook;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by ortaieb on 08/08/2016.
 */
public class DateOperations {

    // TODO : consider migration to four digit representation of year.
    private static final String dateOfBirthPattern = "dd/MM/yy";
    private static final DateTimeFormatter dateOfBirthFormatter = DateTimeFormatter.ofPattern( dateOfBirthPattern );

    public static LocalDate parse( String date ) {
        LocalDate ld = LocalDate.parse( date , dateOfBirthFormatter);
        return ld.isAfter( LocalDate.now() ) ? ld.minusYears(100) : ld;
    }
}
