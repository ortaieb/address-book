package com.taieb.addressbook;

import java.io.IOException;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by ortaieb on 08/08/2016.
 */
public class AddressBookProviderTest {

    @Test
    public void parseGivenFileToAddressBook() {

        try {

            AddressBook ab = AddressBookProvider.create().fromFile( "./src/test/resources/ab.test" );
            assertThat( ab.maleCounter() , is( 1 ) );

        }
        catch (IOException e) {
            fail();
        }
    }

    @Test( expected = IllegalArgumentException.class )
    public void nullShouldThrowException() throws IOException {

        AddressBookProvider.create().fromFile( null );
    }

    @Test( expected = IllegalArgumentException.class )
    public void missingFileShouldThrowException() throws IOException {

        AddressBookProvider.create().fromFile( "./src/test/resources/missing.file" );
    }
}
