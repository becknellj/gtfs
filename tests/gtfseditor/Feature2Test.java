package gtfseditor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.naming.directory.InvalidAttributesException;
import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;

import static gtfseditor.StopTime.timeShift;
import static org.junit.jupiter.api.Assertions.*;

class Feature2Test {

    Application GTFS;


    @BeforeEach
    void setUp() {
        GTFS = new Application();

    }

    @AfterEach
    void tearDown() {
    }

    //Andrew Nebel
    //This test makes sure that if the data structures
    //are empty that the user is made aware.
    @Test
    void emptyDataStructures(){
        assertThrows(InvalidAttributesException.class,
                () -> {
                    GTFS.displayAllTripDistance();
                });
    }

    //Andrew Nebel
    //This test makes sure that the helper method
    //distance, cannot return a negative double
    @Test
    void negativeDistance() {
        assertTrue(GTFS.distance(4, -3, 3, -4) >=0);
    }

    //Andrew Nebel
    //This test makes sure that the zeroFlag
    //is set high if zero is returned so that
    //trip can be marked "0*"
    @Test
    void zeroDistance() {
        GTFS.zeroFlag = false;
        GTFS.distance(1,1,1,1);
        assertTrue(GTFS.zeroFlag);
    }


}