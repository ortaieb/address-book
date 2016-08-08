package com.taieb.addressbook;

/**
 * Created by ortaieb on 07/08/2016.
 */
public final class GenderCounter {

    private final int maleCnt;
    private final int femaleCnt;

    public static GenderCounter create() {
        return new GenderCounter( 0 , 0 );
    }

    private GenderCounter( int maleCnt , int femaleCnt ) {
        this.maleCnt = maleCnt;
        this.femaleCnt = femaleCnt;
    }

    public GenderCounter with( Person person ) {
        return new GenderCounter(
            this.maleCnt + addForGender( person , Gender.Male ) ,
            this.femaleCnt + addForGender( person , Gender.Female ) );
    }

    private int addForGender( Person person , Gender desiredGender ) {
        return person.gender() == desiredGender ? 1 : 0 ;
    }

    public int genderCount( Gender gender ) {
        switch ( gender ) {
            case Male:
                return maleCnt;
            case Female:
                return femaleCnt;
            default:
                throw new IllegalArgumentException( "Supported Genders [Male,Female]" );
        }
    }
}
