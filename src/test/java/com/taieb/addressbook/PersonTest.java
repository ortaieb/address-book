package com.taieb.addressbook;

import java.time.LocalDate;
import java.util.Optional;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ortaieb on 08/08/2016.
 */
public class PersonTest {

    @Test
    public void canParseLineToPerson() {

        assertThat( Person.parse( "John Smith, Male, 23/10/95" ).orElseThrow( RuntimeException::new ) , is( new Person( "John Smith" , Gender.Male , LocalDate.of( 1995 , 10 , 23 ) ) ) );

    }

    @Test
    public void invalidFormatReturnEmptyOptional() {
        assertThat( Person.parse( "Adam Smith, Other, 23/10/00" ), is( Optional.empty() ) );
        assertThat( Person.parse( "Adam ,Smith, Male, 23/10/00" ), is( Optional.empty() ) );
    }
}
