package com.taieb.addressbook;

import com.gs.collections.impl.factory.Lists;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.Test;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ortaieb on 07/08/2016.
 */
public class AddressBookTest {

    @Test
    public void emptyAddressBookHasZeroMale() {
        assertThat( AddressBook.create().maleCounter() , is( 0 ) );
    }

    @Test
    public void addressBookCanProvideNumberOfMaleEntries() {

        AddressBook addressBook = AddressBook.create();

        addressBook = addressBook.with( new Person( "Jack Smith" , Gender.Male , LocalDate.of( 2001 , 2 , 3 ) ) );
        assertThat( addressBook.maleCounter() , is( 1 ) );
        assertThat( addressBook.femaleCounter() , is( 0 ) );

        addressBook = addressBook.with( new Person( "John James" , Gender.Male , LocalDate.of( 1960 , 5 , 12 ) ) );
        assertThat( addressBook.maleCounter() , is( 2 ) );
        assertThat( addressBook.femaleCounter() , is( 0 ) );

    }

    @Test
    public void addressBookReturnEmptyOldestForEmpty() {
        assertThat( AddressBook.create().oldestPerson() , is( Optional.empty() ) );
    }

    @Test
    public void addressBookReturnListWithSingleEntryListForOldestPerson() {
        AddressBook ab = AddressBook
            .create()
            .with( new Person( "Jim" , Gender.Male , LocalDate.of( 1960 , 6 , 10 ) ) )
            .with( new Person( "Jerry" , Gender.Male , LocalDate.of( 1956 , 6 , 10 ) ) );
        assertThat( ab.oldestPerson().orElseThrow( RuntimeException::new) , is(Lists.mutable.of( new Person( "Jerry" , Gender.Male , LocalDate.of( 1956 , 6 , 10 ) ) ) ) );
    }

    @Test
    public void addressBookReturnListWithListForOldestShareTheSameDay() {

        Person jim = new Person( "Jim" , Gender.Male , LocalDate.of( 1943 , 6 , 10 ) );
        Person jerry = new Person( "Jerry" , Gender.Male , LocalDate.of( 1956 , 7 , 4 ) );
        Person edna = new Person( "Edna" , Gender.Female , LocalDate.of( 1943 , 6 , 10 ) );
        AddressBook ab = AddressBook
            .create()
            .with( jim )
            .with( jerry )
            .with( edna );
        assertThat( ab.oldestPerson().orElseThrow( RuntimeException::new) , containsInAnyOrder( jim , edna ) );
    }

    @Test
    public void addressBookReturnOneDayGapForTwoEntriesBornInTheSameDay() {

        AddressBook addressBook = AddressBook.create()
            .with( new Person( "Jack Smith" , Gender.Male , LocalDate.of( 2001 , 2 , 3 ) ) )
            .with( new Person( "Jim Smith" , Gender.Male , LocalDate.of( 2001 , 2 , 3 ) ) );

        assertThat( addressBook.birthdayGapInDays( "Jack Smith" , "Jim Smith" ).orElseThrow( RuntimeException::new ) , is( 0l ) );

    }

    @Test
    public void addressBookReturnOneForTwoEntriesWithSingleDayGap() {

        AddressBook addressBook = AddressBook.create()
            .with( new Person( "Jack Smith" , Gender.Male , LocalDate.of( 2001 , 2 , 3 ) ) )
            .with( new Person( "Jim Smith" , Gender.Male , LocalDate.of( 2001 , 2 , 4 ) ) );

        assertThat( addressBook.birthdayGapInDays( "Jack Smith" , "Jim Smith" ).orElseThrow( RuntimeException::new ) , is( 1l ) );

    }

    @Test
    public void addressBookReturn365ForGapOfOneYear() {

        AddressBook addressBook = AddressBook.create()
            .with( new Person( "Jack Smith" , Gender.Male , LocalDate.of( 2002 , 2 , 3 ) ) )
            .with( new Person( "Jim Smith" , Gender.Male , LocalDate.of( 2001 , 2 , 3 ) ) );

        assertThat( addressBook.birthdayGapInDays( "Jack Smith" , "Jim Smith" ).orElseThrow( RuntimeException::new ) , is( -365l ) );

    }

    @Test
    public void addressBookReturnEmptyOptionIfOneOfTheEntriesMissing() {

        AddressBook addressBook = AddressBook.create()
            .with( new Person( "Jack Smith" , Gender.Male , LocalDate.of( 2002 , 2 , 3 ) ) )
            .with( new Person( "Jim Smith" , Gender.Male , LocalDate.of( 2001 , 2 , 3 ) ) );

        assertThat( addressBook.birthdayGapInDays( "Jack Tuttle" , "Jim Smith" ) , is( Optional.empty() ) );
        assertThat( addressBook.birthdayGapInDays( "Jack Smith" , "Jim Beam" ) , is( Optional.empty() ) );

    }
}
