package com.taieb.addressbook;

import com.gs.collections.api.map.ImmutableMap;
import com.gs.collections.api.multimap.sortedset.ImmutableSortedSetMultimap;
import com.gs.collections.impl.factory.Lists;
import com.gs.collections.impl.factory.Maps;
import com.gs.collections.impl.factory.Multimaps;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Created by ortaieb on 07/08/2016.
 */
public class AddressBook {

    private final ImmutableMap<String,Person> byName;
    private final ImmutableSortedSetMultimap<LocalDate,Person> byDate;
    private final GenderCounter genderCounter;

    public static AddressBook create() {
        return new AddressBook(
            Maps.immutable.empty() ,
            Multimaps.immutable.sortedSet.with( Person::compareTo ) ,
            GenderCounter.create() );
    }

    private AddressBook( ImmutableMap<String,Person> byName , ImmutableSortedSetMultimap<LocalDate,Person> byDate , GenderCounter genderCounter ) {
        this.byName = byName;
        this.byDate = byDate;
        this.genderCounter = genderCounter;
    }

    public AddressBook with( Person person ) {
        return new AddressBook(
            byName.newWithKeyValue( person.name() , person ) ,
            byDate.newWith( person.dateOfBirth() , person) ,
            genderCounter.with( person ) );
    }

    public int maleCounter() {
        return genderCounter.genderCount( Gender.Male );
    }

    public int femaleCounter() {
        return genderCounter.genderCount( Gender.Female );
    }

    public List<Person> oldestPerson() {

        final Optional<LocalDate> date = oldestDate();

        return date.isPresent() ?
            byDate.get( date.get() ).toList() :
            Lists.fixedSize.empty();

    }

    public Optional<Long> birthdayGapInDays( String name1 , String name2 ) {

        if( byName.containsKey( name1 ) && byName.containsKey( name2 ) ) {
            return Optional.of( countGap( byName.get( name1 ).dateOfBirth() , byName.get( name2 ).dateOfBirth() ) );
        } else
            return Optional.empty();
    }

    private Optional<LocalDate> oldestDate() {
        return byDate.keysView().isEmpty() ?
            Optional.empty() :
            Optional.of( byDate.keysView().min() );
    }

    private Long countGap(LocalDate localDate1, LocalDate localDate2) {
        return DAYS.between( localDate1 , localDate2 );
    }
}
