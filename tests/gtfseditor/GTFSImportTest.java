

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
class GTFSImportTest {

    Controller c1;
    Application GTFS;
    File f1;
    File f2;
    File f3;
    File f4;
    File f5;
    File f6;
    File f7;
    File testStop;
    File testRoute;
    File testTrip;
    LinkedList<StopTime> testTimes;
    StopTime a;
    StopTime b;
    StopTime c;


    @BeforeEach
    void setUp() {
        GTFS = new Application();
        c1 = new Controller();
        f1 = new File("text.txt");
        f2 = new File("../../trips.txt");
        f3 = new File("C:\\Users\\becknellj\\Documents\\GTFS_MCTS\\stop_times.txt");
        f4 = new File(System.getProperty("user.home") + "/se2030/GTFS_MCTS/routes.txt");
        f5 = new File(System.getProperty("user.home") + "/se2030/GTFS_MCTS/stop_times.txt");
        f6 = new File(System.getProperty("user.home") + "/se2030/GTFS_MCTS/stops.txt");
        f7 = new File(System.getProperty("user.home") + "/se2030/GTFS_MCTS/trips.txt");
        testTimes = new LinkedList<>();
        a = new StopTime("21736564_2535", "08:52:00", "08:52:00", "4664", "2", "", "0", "0");
        b = new StopTime("21736564_2535", "08:52:00", "08:52:00", "4664", "2", "", "0", "0");
        c = new StopTime("21736564_2535", "08:52:00", "08:52:00", "4664", "2", "", "0", "0");

    }

    @AfterEach
    void tearDown() {
    }
    //Andrew Nebel
//This test makes sure that if there is no file to parse
//that it is thrown up to the importFiles method so that
//an alert is thrown to the user.
    @Test
    void parseFilesNullPointer() {
        assertThrows(NullPointerException.class,
                () -> {
                    c1.parseFiles(null, null, null, null);
                });
    }

    //Andrew Nebel
//This test makes sure that if there are files that do not exist to parse
//that it is thrown up to the importFiles method so that
//an alert is thrown to the user.
    @Test
    void parseFilesFileNotFound() {
        assertThrows(FileNotFoundException.class,
                () -> {
                    c1.parseFiles(f1, f1, f1, f1);
                });
    }
    // Daniel Griffith
    // This test checks input validation on import files
    // Should throw IllegalArgumentException if a GTFS file is imported as the wrong type of GTFS file
    // i.e. stops.txt being imported as routes.txt
    @Test
    void fileImportInputValidationTest1() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    c1.parseFiles(f7,f4,f6,f4);
                });

    }

    // Daniel Griffith
    // This test checks input validation on import files
    // Should throw IllegalArgumentException if a GTFS file is imported as the wrong type of GTFS file
    // i.e. stops.txt being imported as routes.txt
    @Test
    void fileImportInputValidationTest2() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    c1.parseFiles(f7,f5,f6,f5);
                });
    }


    // Daniel Griffith
    // This test checks input validation on import files
    // Should throw IllegalArgumentException if a GTFS file is imported as the wrong type of GTFS file
    // i.e. stops.txt being imported as routes.txt
    @Test
    void fileImportInputValidationTest3() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    c1.parseFiles(f7,f4,f4,f5);
                });

    }

    // Daniel Griffith
    // This test checks input validation on import files
    // Should throw IllegalArgumentException if a GTFS file is imported as the wrong type of GTFS file
    // i.e. stops.txt being imported as routes.txt
    @Test
    void fileImportInputValidationTest4() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    c1.parseFiles(f6,f4,f6,f5);
                });
    }
    // Daniel Griffith
    // This test checks input validation on import files
    // Should throw IllegalArgumentException if a GTFS file is imported as the wrong type of GTFS file
    // i.e. stops.txt being imported as routes.txt
    @Test
    void fileImportInputValidationTest5() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    c1.parseFiles(f4,f4,f4,f4);
                });
    }
    //Andrew Nebel
    //This test makes sure trips are successfully put into the application
    //hashtable and that their info is the same as excepted based on the
    //.txt file they are in
    @Test
    void filesToTripsEqualTrip() {
        try {
            BufferedReader b1 = new BufferedReader(new FileReader(f2));
            c1.fileToTrips(b1, "trips.txt");
            assertEquals("64", Controller.GTFSeditor.trips.get("21736564_2535").getRoute_id());
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
    }
}