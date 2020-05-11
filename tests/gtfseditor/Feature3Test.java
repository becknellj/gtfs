package gtfseditor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.naming.directory.InvalidAttributesException;
import java.io.*;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;

import static gtfseditor.StopTime.timeShift;
import static org.junit.jupiter.api.Assertions.*;

class Feature3Test {

    Application GTFS;



    @BeforeEach
    void setUp() {
        GTFS = new Application();
    }

    @AfterEach
    void tearDown() {
    }

    //Andrew Nebel
    //This test makes sure that the zeroFlag
    //is set high if the time is 0 so that
    //trip can be marked "0*"
    @Test
    void zeroTime() throws ParseException{
        GTFS.zeroFlag = false;
        GTFS.getTimeDifferenceHours("00:00:01","00:00:01");
        assertTrue(GTFS.zeroFlag);
    }

    //Andrew Nebel
    //This test makes sure that the helper method
    //time, cannot return a negative double
    @Test
    void negativeTime() throws ParseException {
        assertTrue(GTFS.getTimeDifferenceHours("00:00:02","00:00:01")>= 0);
    }

    //Andrew Nebel
    //This test makes sure that if the data structures
    //are empty that the user is made aware.
    @Test
    void emptyDataStructuresSpeed(){
        assertThrows(InvalidAttributesException.class,
                () -> {
                    GTFS.displayAllTripSpeed();
                });
    }
}