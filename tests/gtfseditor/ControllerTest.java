package gtfseditor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    Controller c1;
    Application GTFS;
    File f1;
    File f2;

    @BeforeEach
    void setUp() {
        GTFS = new Application();
        c1 = new Controller();
        f1 = new File("text.txt");
        f2 = new File("../../trips.txt");
    }

    @AfterEach
    void tearDown() {
    }



    //Andrew Nebel
    //This test makes sure that if there is no file to parse
    //that it is thrown up to the importFiles method so that
    //an alert is thrown to the user.
    @Test
    void parseFilesNullPointer(){
        assertThrows(NullPointerException.class,
                () ->{c1.parseFiles(null,null,null,null);});
    }

    //Andrew Nebel
    //This test makes sure that if there are files that do not exist to parse
    //that it is thrown up to the importFiles method so that
    //an alert is thrown to the user.
    @Test
    void parseFilesFileNotFound(){
        assertThrows(FileNotFoundException.class,
                () ->{c1.parseFiles(f1, f1, f1, f1);});
    }

    //Andrew Nebel
    //This test makes sure trips are successfully put into the application
    //hashtable and that their info is the same as excepted based on the
    //.txt file they are in
    @Test
    void filesToTripsEqualTrip(){
        try {
            BufferedReader b1 = new BufferedReader(new FileReader(f2));
            c1.fileToTrips(b1, "trips.txt");
            assertEquals("64",Controller.GTFSeditor.trips.get("21736564_2535").getRoute_id());
        } catch (FileNotFoundException e){
            e.getMessage();
        }
    }
}