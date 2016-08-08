package com.taieb.addressbook;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by ortaieb on 08/08/2016.
 */
public class AddressBookProvider {

    public static AddressBookProvider create() {
        return new AddressBookProvider();
    }

    private AddressBookProvider() {}

    public AddressBook fromFile( String path ) throws IOException {

        if( path == null || !Files.exists(Paths.get(path)) )
            throw new IllegalArgumentException( String.format("Could not find file in path [%s]",path) );

        final List<Optional<Person>> parsed = readLines( path )
            .orElseThrow( IllegalStateException::new )
            .map( line -> Person.parse(line) )
            .collect(Collectors.toList());

        return toAddressBook( parsed );
    }

    private Optional<Stream<String>> readLines( String path ) throws IOException {

        final Path parsedPath = getPath(path);

        return Files.exists( parsedPath ) ?
            Optional.of ( Files.lines( parsedPath , StandardCharsets.UTF_8 ) ) :
            Optional.empty();

    }


    private Path getPath( String path ) {
        return path.indexOf("classpath") == -1 ?
            fromFilepath( path ) :
            fromClasspth( path );
    }

    private Path fromFilepath( String path ) {
        return Paths.get( path );
    }

    private Path fromClasspth( String path ) {
        try {
            return Paths.get( AddressBookProvider.class.getClassLoader().getResource( path ).toURI() );
        }
        catch (URISyntaxException e) {
            throw new IllegalArgumentException("Could not read file " , e );
        }
    }

    private boolean hasInvalidEntries( List<Optional<Person>> input ) {
        return input.stream().anyMatch( p -> p.equals(Optional.empty() ) );
    }

    private AddressBook toAddressBook( List<Optional<Person>> parsed ) {
        AddressBook ab = AddressBook.create();

        for( Optional<Person> op : parsed ) {
            if( op.isPresent() )
                ab = ab.with( op.get() );
        }

        return ab;
    }
}
