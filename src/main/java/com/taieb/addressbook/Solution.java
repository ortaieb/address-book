package com.taieb.addressbook;

import com.gs.collections.impl.factory.Lists;
import java.io.IOException;
import java.util.List;

/**
 * Created by ortaieb on 08/08/2016.
 */
public class Solution {

    public static void main(String[] args) throws IOException {

        final String addressbookFile = "./src/main/resources/AddressBook";
        final AddressBook addressBook = AddressBookProvider.fromFile( addressbookFile );

        System.out.println("== How many males are in the address book? ");
        System.out.println( String.format("There are %d Males in the address book" , addressBook.maleCounter() ) );

        System.out.println("== Who is the oldest person in the address book? ");
        List<Person> oldest = addressBook.oldestPerson().orElse(Lists.fixedSize.empty());
        if ( oldest.size() == 0 )
            System.out.println( "The are no old people in the address book" );
        else if( oldest.size() == 1 )
            System.out.println( String.format("Found %s is the oldest person", oldest.get(0).name() ) );
        else
            System.out.println( String.format("Found the following people as the oldest" , Lists.immutable.ofAll(oldest).makeString(", ") ) );

        System.out.println("==  How many days older is Bill than Paul?");
        System.out.println( String.format("There are %d days between birthdays of Bill and Paul." , addressBook.birthdayGapInDays("Bill McKnight","Paul Robinson").orElseThrow( IllegalArgumentException::new ) ) );
    }

}
