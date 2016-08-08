package com.taieb.addressbook;

import java.time.LocalDate;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ortaieb on 07/08/2016.
 */
public class GenderCounterTest {

    @Test
    public void genderCounterInitWithZeros() {
        GenderCounter genderCounter = GenderCounter.create();
        assertThat( genderCounter.genderCount( Gender.Male ) , is( 0 ) );
        assertThat( genderCounter.genderCount( Gender.Female ) , is( 0 ) );
    }

    @Test
    public void withMaleReturnCounterObjectWithMaleInc() {

        GenderCounter genderCounter = GenderCounter.create();

        Person maleTestPerson = new Person( "Some One" , Gender.Male , LocalDate.of( 1930 , 1 , 1 ) );
        genderCounter = genderCounter.with( maleTestPerson );

        assertThat( genderCounter.genderCount( Gender.Male ) , is( 1 ) );
        assertThat( genderCounter.genderCount( Gender.Female ) , is( 0 ) );

        Person maleAnotherTestPerson = new Person( "Some One Else" , Gender.Male , LocalDate.of( 1934 , 1 , 1 ) );
        genderCounter = genderCounter.with( maleAnotherTestPerson );

        assertThat( genderCounter.genderCount( Gender.Male ) , is( 2 ) );
        assertThat( genderCounter.genderCount( Gender.Female ) , is( 0 ) );

    }

    @Test
    public void withFemaleReturnCounterObjectWithFemaleInc() {

        GenderCounter genderCounter = GenderCounter
            .create()
            .with( new Person( "Some One" , Gender.Female , LocalDate.of( 1960 , 1 , 1 ) ) );

        assertThat( genderCounter.genderCount( Gender.Male ) , is( 0 ) );
        assertThat( genderCounter.genderCount( Gender.Female ) , is( 1 ) );

    }
}
