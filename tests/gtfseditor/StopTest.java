package gtfseditor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;

import static gtfseditor.StopTime.timeShift;
import static org.junit.jupiter.api.Assertions.*;

class StopTest {

    Stop stop;
    stop_id a;
    stop_id b;
    stop_id c;


    @BeforeEach
    void setUp() {
        stop = new Stop();
        a = "";
        b = null;
        c = "Milwaukee St."
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getStop_desc() {
        assertNull(a);
        assertNoNull();

    }

    @Test
    void getStop_id() {
        assertNull();
        assertNoNull();
    }

}