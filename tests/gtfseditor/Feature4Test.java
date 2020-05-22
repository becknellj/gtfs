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

class Feature4Test {

    Application GTFS;

    @BeforeEach
    void setUp() {
        GTFS = new Application();
        Controller controller1 = new Controller();

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
                    GTFS.displayStopTripCount();
                });
    }

    //Andrew Nebel
    //This test makes sure nothing is returned
    //if data structures empty
    @Test
    void zeroCheck(){
        assertEquals("", GTFS.displayStopTripCount());
    }

}