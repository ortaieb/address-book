package com.taieb.addressbook;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ortaieb on 06/08/2016.
 */
public final class Person implements Comparable<Person> {

    private static final String validInputRegex = "^([a-zA-Z0-9\\-\\s\\.]*),\\s*(Male|Female),\\s*(\\d{1,2}\\/\\d\\d\\/\\d\\d)$";
    private static final Pattern pattern = Pattern.compile( validInputRegex );

    public static Optional<Person> parse( String line ) {
        final Matcher matcher = pattern.matcher( line );
        if( matcher.matches() ) {
            return Optional.of( new Person( matcher.group(1) , Gender.valueOf(matcher.group(2) ), DateOperations.parse( matcher.group(3 ) ) ) );
        } else {
            return Optional.empty();
        }
    }

    private static boolean valideInput( String line ) {
        return line != null && line.matches( validInputRegex );
    }

    private final String name;
    private final Gender gender;
    private final LocalDate dateOfBirth;

    public Person(String name, Gender gender, LocalDate dateOfBirth) {
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public String name() { return this.name; }
    public Gender gender() { return this.gender; }
    public LocalDate dateOfBirth() { return this.dateOfBirth; }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Person person = (Person)o;

        if (name != null ? !name.equals(person.name) : person.name != null)
            return false;
        if (gender != person.gender)
            return false;
        return dateOfBirth != null ? dateOfBirth.equals(person.dateOfBirth) : person.dateOfBirth == null;

    }

    @Override public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        return result;
    }

    @Override public int compareTo(Person person) {
        int nameBased = name.compareTo(person.name());
        if( nameBased != 0 )
            return nameBased;

        int compareBirthday = dateOfBirth().compareTo( person.dateOfBirth() );
        if( compareBirthday != 0 )
            return compareBirthday;

        else
            return gender().compareTo(person.gender() );
    }
}
