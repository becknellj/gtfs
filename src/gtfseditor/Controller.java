package gtfseditor;
//8. Search for a stop by stop_id and display the next trip_id(s) (closest to the current time)

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.*;
import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * @version 1.1
 * @authors becknellj, andrew nebel
 * @created 16-Apr-2020 12:50:58 PM
 */
public class Controller {
    File selectedTripFile = null;
    File selectedTimeFile = null;
    File selectedRouteFile = null;
    File selectedStopFile = null;

    //Main instance of the application
    static Application GTFSeditor = new Application();

    //FXML objects
    @FXML
    Label dataStructuresLabel;
    @FXML
    Label updateLabel;
    @FXML
    Label updateLabel1;
    @FXML
    CheckBox stopCheck;
    @FXML
    CheckBox stopTimeCheck;
    @FXML
    CheckBox tripCheck;
    @FXML
    CheckBox routeCheck;
    @FXML
    CheckBox stopIdSearch;
    @FXML
    Label nextTripLabel1;
    @FXML
    MenuButton updateMenuButton;
    @FXML
    RadioMenuItem timeForwards;
    @FXML
    RadioMenuItem timeBackwards;
    @FXML
    RadioMenuItem drop_off_type;
    @FXML
    RadioMenuItem pickup_type;
    @FXML
    RadioMenuItem stop_headsign;
    @FXML
    RadioMenuItem stop_id;
    @FXML
    RadioMenuItem stop_sequence;
    @FXML
    RadioMenuItem trip_id;
    @FXML
    Label searchRouteIDlabel;

    @FXML
    RadioMenuItem stop_id_item;
    @FXML
    RadioMenuItem stop_desc_item;
    @FXML
    RadioMenuItem stop_lat;
    @FXML
    RadioMenuItem stop_long;
    @FXML
    RadioMenuItem stop_name;

    @FXML
    RadioMenuItem route_id_item;
    @FXML
    RadioMenuItem agency_id_item;
    @FXML
    RadioMenuItem route_short_name;
    @FXML
    RadioMenuItem route_long_name;
    @FXML
    RadioMenuItem route_desc_item;
    @FXML
    RadioMenuItem route_type_item;
    @FXML
    RadioMenuItem route_url_item;
    @FXML
    RadioMenuItem route_color_item;
    @FXML
    RadioMenuItem route_color_text_item;

    @FXML
    RadioMenuItem trip_id_item;
    @FXML
    RadioMenuItem service_id_item;
    @FXML
    RadioMenuItem route_id_item_1;
    @FXML
    RadioMenuItem trip_headsign_item;
    @FXML
    RadioMenuItem direction_id_item;
    @FXML
    RadioMenuItem block_id_item;
    @FXML
    RadioMenuItem shape_id_item;

    @FXML
    ToggleGroup updateGroup;
    @FXML
    TextField timeTextField;
    @FXML
    TextField timeTextField1;
    @FXML
    TextField routeIDsearchBox;
    @FXML
    Button searchRouteIDButton;

    @FXML
    Label instrLabel;
    @FXML
    Label instrLabel1;
    @FXML
    Button enterButton;
    @FXML
    Label formatLabel;
    @FXML
    Label tripIdFormat;
    @FXML
    TextArea textArea1;
    @FXML
    Button tripSpeedButton;
    @FXML
    Button tripDistanceButton;
    @FXML
    Button nextTripButton;
    @FXML
    TextField nextTripText;
    @FXML
    Label label1;
    @FXML
    Label nextTripLabel;
    @FXML
    Button backButton;
    @FXML
    Button stopCountButton;
    @FXML
    Button searchStopButton;
    @FXML
    TextField searchStopBar;

    @FXML
    TextArea searchBar;
    @FXML
    Button searchButton;
    Boolean imported = false;
    @FXML
    AnchorPane secondPane;

    @FXML
    public void openStopsFile(ActionEvent event) {
        try {

            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter1 =
                    new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().addAll(extFilter1);
            selectedStopFile = fileChooser.showOpenDialog(null);
            selectedStopFile.getName();// null pointer check
            stopCheck.setSelected(true);
            throwInfoAlert("File selected.", selectedStopFile.getName() + " selected.");
        } catch (NullPointerException e) {
            throwAlert("NullPointerException", "No file was selected, please select a file.");
        } catch (IllegalArgumentException e) {
            throwAlert("IllegalArgumentException", e.getMessage());
        }
        System.out.println();
    }

    @FXML
    public void openRoutesFile(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter1 =
                    new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().addAll(extFilter1);
            selectedRouteFile = fileChooser.showOpenDialog(null);
            selectedRouteFile.getName();// null pointer check
            routeCheck.setSelected(true);
            throwInfoAlert("File selected.", selectedRouteFile.getName() + " selected.");
        } catch (NullPointerException e) {
            throwAlert("NullPointerException", "No file was selected, please select a file.");
        } catch (IllegalArgumentException e) {
            throwAlert("IllegalArgumentException", e.getMessage());
        }
        System.out.println();
    }

    @FXML
    public void openStopTimesFile(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter1 =
                    new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().addAll(extFilter1);
            selectedTimeFile = fileChooser.showOpenDialog(null);
            selectedTimeFile.getName();// null pointer check
            stopTimeCheck.setSelected(true);
            throwInfoAlert("File selected.", selectedTimeFile.getName() + " selected.");

        } catch (NullPointerException e) {
            throwAlert("NullPointerException", "No file was selected, please select a file.");
        } catch (IllegalArgumentException e) {
            throwAlert("IllegalArgumentException", e.getMessage());
        }
        System.out.println();
    }

    @FXML
    public void openTripsFile(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter1 =
                    new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().addAll(extFilter1);
            selectedTripFile = fileChooser.showOpenDialog(null);
            selectedTripFile.getName();// null pointer check
            tripCheck.setSelected(true);
            throwInfoAlert("File selected.", selectedTripFile.getName() + " selected.");
        } catch (NullPointerException e) {
            throwAlert("NullPointerException", "No file was selected, please select a file.");
        } catch (IllegalArgumentException e) {
            throwAlert("IllegalArgumentException", e.getMessage());
        }

    }

    @FXML
    public void importFiles(ActionEvent event) {
        FileInputStream tripIn = null;
        FileOutputStream tripOut = null;
        try {

            //passes selected files from filechooser into helper method
            parseFiles(selectedTripFile, selectedRouteFile, selectedStopFile, selectedTimeFile);
            //confirmation files imported
            throwInfoAlert("Import Status", "All files were imported successfully");
            imported = true;
        } catch (NullPointerException e) {
            throwAlert("NullPointerException", "There were troubles importing, " +
                    "make sure four files were selected.");
        } catch (IllegalArgumentException e) {
            throwAlert("Illegal Argument Exception", e.getMessage());
        }
        reset();
    }
    //helper method that takes in four selected files and parses the information with four hash tables

    @FXML
    public void reset() {
        //reset all variables
        stopCheck.setSelected(false);
        stopTimeCheck.setSelected(false);
        routeCheck.setSelected(false);
        tripCheck.setSelected(false);

        selectedTripFile = null;
        selectedRouteFile = null;
        selectedStopFile = null;
        selectedTimeFile = null;
    }

    /**
     * Helper method tht takes in four selected files and parses the information into four hash tables
     *
     * @param tripFile  file with trip information
     * @param routeFile file with route information
     * @param stopFile  file with stop information
     * @param timeFile  file with stop time information
     */
    void parseFiles(File tripFile, File routeFile, File stopFile, File timeFile) throws NullPointerException, IllegalArgumentException {

        try {
            BufferedReader tripBufferedReader = new BufferedReader(new FileReader(tripFile));
            BufferedReader stopBufferedReader = new BufferedReader(new FileReader(stopFile));
            BufferedReader stopTimeBufferedReader = new BufferedReader(new FileReader(timeFile));
            BufferedReader routeBufferedReader = new BufferedReader(new FileReader(routeFile));


            //passes the buffered reader and Hashtable into helper methods to transfer data from
            //file to objects in the application class
            fileToTrips(tripBufferedReader, tripFile.getName());
            fileToStops(stopBufferedReader, stopFile.getName());
            fileToStopTimes(stopTimeBufferedReader, timeFile.getName());
            fileToRoutes(routeBufferedReader, routeFile.getName());
        } catch (FileNotFoundException e) {
            throwAlert("FileNotFoundException", "There was an issue finding a file");
        }

    }


    private void fileToStops(BufferedReader bufferIn, String fileName) throws IllegalArgumentException {
        String[] elements = null;
        String[] validParamters = {"stop_id", "stop_code", "stop_name", "stop_desc", "stop_lat", "stop_lon", "zone_id",
                "stop_url", "location_type", "parent_station", "stop_timezone", "wheelchair_boarding", "level_id", "platform_code"};
        int numParameters = 0;
        try {
            String c = bufferIn.readLine();
            elements = c.split(",");
            numParameters = elements.length;
            for (String element : elements) {
                boolean validParameter = false;
                for (String valid : validParamters) {
                    if (element.equals(valid)) {
                        validParameter = true;
                    }
                }
                if (!validParameter) {
                    throw new IllegalArgumentException("Invalid parameter for stops.txt");
                }
            }
            System.out.println("\n\nImporting: " + fileName);

            for (int i = 0; c != null; i++) { //for each line in doc
                if (i != 0) { //skip first line, no info to import
                    elements = c.split(","); //fills array with data from one line
                    if(elements.length != numParameters) {

                     //   throw new IllegalArgumentException("Invalid parameter for stops.txt");
                    }
                    //create new stop
                    Stop newStop = new Stop(elements[0], elements[1], elements[2],
                            Double.parseDouble(elements[3]), Double.parseDouble(elements[4]));
                    //add stop to stop table in application via its stop id
                    GTFSeditor.stops.put(elements[0], newStop);
                }
                //read next line
                c = bufferIn.readLine();
            }
            //remind user to check formatting
        } catch (IOException e) {
            throwAlert("Formatting Error", "Make sure " + selectedStopFile.getName() + " file is formatted correctly");
        }
    }

    public void fileToTrips(BufferedReader bufferIn, String fileName) throws IllegalArgumentException {
        String[] elements = null;
        String[] validParamters = {"route_id", "service_id", "trip_id", "trip_headsign", "trip_short_name", "direction_id", "block_id", "shape_id",
                "wheelchair_accessible", "bikes_allowed"};
        int numParameters = 0;
        try {

            String c = bufferIn.readLine();
            elements = c.split(",");
            numParameters = elements.length;
            for (String element : elements) {
                boolean validParameter = false;
                for (String valid : validParamters) {
                    if (element.equals(valid)) {
                        validParameter = true;
                    }
                }
                if (!validParameter) {
                    throw new IllegalArgumentException("Invalid parameter for stops.txt");
                }
            }
            System.out.println("\n\nImporting: " + fileName);

            for (int i = 0; c != null; i++) { //for each line in doc
                if (i != 0) { //skip first line, no info to import
                    elements = c.split(","); //fills array with data from one line
                    if(elements.length != numParameters) {
                        throw new IllegalArgumentException("Invalid parameter for stops.txt");
                    }
                    //create new trip
                    Trip newTrip = new Trip(elements[0], elements[1], elements[2], elements[3],
                            elements[4], elements[5], elements[6]);
                    //add trip to trip table in application via its trip id
                    GTFSeditor.trips.put(elements[2], newTrip);
                }
                //read next line
                c = bufferIn.readLine();
            }
            //remind user to check formatting
        } catch (IOException e) {
            throwAlert("Formatting Error", "Make sure " + selectedTripFile.getName() + " file is formatted correctly");
        }
    }

    ArrayList timeKeys = new ArrayList();

    public void fileToStopTimes(BufferedReader bufferIn, String fileName) throws IllegalArgumentException {
        String[] validParamters = {"trip_id", "arrival_time", "departure_time", "stop_id", "stop_sequence", "stop_headsign", "pickup_type",
                "drop_off_type", "shape_dist_travel", "timepoint"};
        String[] timeElements;
        int numParameters = 0;

        try {
            String c = bufferIn.readLine();
            timeElements = c.split(",");
            numParameters = timeElements.length;
            for (String element : timeElements) {
                boolean validParameter = false;
                for (String valid : validParamters) {
                    if (element.equals(valid)) {
                        validParameter = true;
                    }
                }
                if (!validParameter) {
                    throw new IllegalArgumentException("Invalid parameter for stop_times.txt");
                }
            }
            System.out.println("\n\nImporting: " + fileName);

            for (int i = 0; c != null; i++) { //for each line in doc
                if (i != 0) { //skip first line, no info to import
                    timeElements = c.split(","); //fills array with data from one line
                    if(timeElements.length != numParameters) {
                        throw new IllegalArgumentException("Invalid parameter for stop_times.txt");
                    }
                    //create new stopTime
                    timeKeys.add(timeElements[0]);
                    StopTime newStopTime = new StopTime(timeElements[0], timeElements[1], timeElements[2], timeElements[3], timeElements[4],
                            timeElements[5], timeElements[6], timeElements[7]);
                    //add stopTime to stopTime table in application via its trip id where
                    //it is added to a list of all stopTimes on that trip
                    //check if there is a linked list
                    if (GTFSeditor.stopTimes.get(timeElements[0]) == null) { //add new one if there isnt
                        GTFSeditor.stopTimes.put(timeElements[0], new LinkedList<StopTime>());
                    }
                    //add stopTime
                    GTFSeditor.stopTimes.get(timeElements[0]).add(newStopTime);

                }
                //read next line
                c = bufferIn.readLine();
            }
            //remind user to check formatting
        } catch (IOException e) {
            throwAlert("Formatting Error", "Make sure " + selectedTimeFile.getName() + " file is formatted correctly");
        }
    }

    private void fileToRoutes(BufferedReader bufferIn, String fileName) throws IllegalArgumentException {
        String[] elements = null;
        String[] validParamters = {"route_id", "agency_id", "route_short_name", "route_long_name", "route_desc", "route_type",
                "route_url", "route_color", "route_text_color", "route_sort_order"};
        int numParameters = 0;
        try {
            String c = bufferIn.readLine();
            elements = c.split(",");
            numParameters = elements.length;
            for (String element : elements) {
                boolean validParameter = false;
                for (String valid : validParamters) {
                    if (element.equals(valid)) {
                        validParameter = true;
                    }
                }
                if (!validParameter) {

                  //  throw new IllegalArgumentException("Invalid parameter for routes.txt");
                }
            }
            System.out.println("\n\nImporting: " + fileName);

            for (int i = 0; c != null; i++) { //for each line in doc
                if (i != 0) { //skip first line, no info to import
                    elements = c.split(","); //fills array with data from one line
                    if(elements.length != numParameters) {
                  //      throw new IllegalArgumentException("Invalid parameter for routes.txt");
                    }
                    //create new route
                    Route newRoute = new Route(elements[0], elements[1], elements[2], elements[3], elements[4], elements[5], elements[6], elements[7]);
                    //add Route to Route table in application via its route id
                    GTFSeditor.routes.put(elements[0], newRoute);
                }
                //read next line
                c = bufferIn.readLine();
            }
            //remind user to check formatting
        } catch (IOException e) {
            throwAlert("Formatting Error", "Make sure " + selectedRouteFile.getName() + " file is formatted correctly");
        }
    }

    @FXML
    public void displayDataStructure() {
        throwInfoAlert("Data Structure Info",
                "There are:\n" + GTFSeditor.stops.size() + " stops\n" +
                        "There are:\n" + GTFSeditor.stopTimes.size() + " stopTimes\n" +
                        "There are:\n" + GTFSeditor.trips.size() + " trips\n" +
                        "There are:\n" + GTFSeditor.routes.size() + " routes\n");
    }

    @FXML
    public void tripSpeeds() {
        try {
            if (imported == true) {
                //reset area
                textArea1.setText("");
                //set to list of speeds
                textArea1.setText(GTFSeditor.displayAllTripSpeed());
            } else {
                throwAlert("NullPointerException", "No GTFS files have been imported");
            }

        } catch (ParseException e) {
            throwAlert("Parse exception", "Error parsing stop time");
        } catch (NullPointerException e) {
            throwAlert("Null Pointer Exception",
                    "Make sure files are imported into the application before" +
                            " requesting average trip speeds");
        }
    }


    @FXML
    public void tripDistances() {
        try {
            if (imported == true) {
                //reset area
                textArea1.setText("");
                //set to list of speeds
                textArea1.setText(GTFSeditor.displayAllTripDistance());
            } else {
                throwAlert("NullPointerException", "No GTFS files have been imported");
            }
        } catch (ParseException e) {
            throwAlert("Parse exception", "Error parsing stop time");
        } catch (NullPointerException e) {
            throwAlert("Null Pointer Exception",
                    "Make sure files are imported into the application before" +
                            " requesting trip distances");
        }
    }

    @FXML
    public void stopTripCount() {
        try {
            //check for imported files
                //if imported go ahead
            if (imported == true) {
                //reset area
                textArea1.setText("");
                //set to list of speeds
                textArea1.setText(GTFSeditor.displayStopTripCount());
            } else {
                //else tell user no files imported
                throwAlert("NullPointerException", "No GTFS files have been imported");
            }
        }  catch (NullPointerException e) {
            throwAlert("Null Pointer Exception",
                    "Make sure files are imported into the application before" +
                            " requesting stops's trip counts");
        }
    }

    /**
     * This method is called when hitting enter button to update stop time  attributes
     * it updates the attribute of all objects with the desired trip_id
     */
    @FXML
    public void enterUpdate() {
        try {
            if (imported == true) { //identifier is id of thing you want to change
                String updateInfo = timeTextField.getText();
                String identifier = timeTextField1.getText();
                String current = null;

                LinkedList<StopTime> times_with_common_trip = GTFSeditor.stopTimes.get(identifier); //now has list of stop times with a specific stop id
                int timeListSize = times_with_common_trip.size();

                Iterator<String> stopItr = GTFSeditor.stops.keySet().iterator();
                Iterator<String> routeItr = GTFSeditor.routes.keySet().iterator();
                Iterator<String> tripItr = GTFSeditor.trips.keySet().iterator();


                //TODO
                if (timeForwards.isSelected()) {
                    for (int i = 0; i < timeListSize; i++) {
                        StopTime.timeShift(updateInfo, times_with_common_trip, "f", i);
                    }
                } else if (timeBackwards.isSelected()) {
                    for (int i = 0; i < timeListSize; i++) {
                        StopTime.timeShift(updateInfo, times_with_common_trip, "b", i);
                    }
                } else if (drop_off_type.isSelected()) {
                    for (int i = 0; i < timeListSize; i++) {
                        times_with_common_trip.get(i).setDrop_off_type(updateInfo);
                    }
                } else if (pickup_type.isSelected()) {
                    for (int i = 0; i < times_with_common_trip.size(); i++) {
                        times_with_common_trip.get(i).setPickup_type(updateInfo);
                    }
                } else if (stop_headsign.isSelected()) {
                    for (int i = 0; i < timeListSize; i++) {
                        times_with_common_trip.get(i).setStop_headsign(updateInfo);
                    }
                } else if (stop_id.isSelected()) {
                    for (int i = 0; i < timeListSize; i++) {
                        times_with_common_trip.get(i).setStop_id(updateInfo);
                    }
                } else if (stop_sequence.isSelected()) {
                    for (int i = 0; i < timeListSize; i++) {
                        times_with_common_trip.get(i).setStop_sequence(updateInfo);
                    }
                } else if (trip_id.isSelected()) {
                    for (int i = 0; i < timeListSize; i++) {
                        times_with_common_trip.get(i).setTrip_id(updateInfo);
                    }
                }
                /*------------------------------------------------------------*/
                //Attributes for stop
                else if (stop_sequence.isSelected()) {
                    while (stopItr.hasNext()) {
                        current = stopItr.next();
                        if (current.equalsIgnoreCase(identifier)) {
                            GTFSeditor.stops.get(identifier).setStop_id(updateInfo);
                        }
                    }
                } else if (trip_id.isSelected()) {
                    while (stopItr.hasNext()) {
                        current = stopItr.next();
                        if (current.equalsIgnoreCase(identifier)) {
                            GTFSeditor.stops.get(identifier).setTripNumber(Integer.parseInt(updateInfo));
                        }
                    }
                } else if (stop_id_item.isSelected()) {
                    while (stopItr.hasNext()) {
                        current = stopItr.next();
                        if (current.equalsIgnoreCase(identifier)) {
                            GTFSeditor.stops.get(identifier).setStop_id(updateInfo);
                        }
                    }
                } else if (stop_desc_item.isSelected()) {
                    while (stopItr.hasNext()) {
                        current = stopItr.next();
                        if (current.equalsIgnoreCase(identifier)) {
                            GTFSeditor.stops.get(identifier).setStop_desc(updateInfo);
                        }
                    }
                } else if (stop_lat.isSelected()) {
                    while (stopItr.hasNext()) {
                        current = stopItr.next();
                        if (current.equalsIgnoreCase(identifier)) {
                            GTFSeditor.stops.get(identifier).setStop_lat(Double.parseDouble(updateInfo));
                        }
                    }
                } else if (stop_long.isSelected()) {
                    while (stopItr.hasNext()) {
                        current = stopItr.next();
                        if (current.equalsIgnoreCase(identifier)) {
                            GTFSeditor.stops.get(identifier).setStop_long(Double.parseDouble(updateInfo));
                        }
                    }
                } else if (stop_name.isSelected()) {
                    while (stopItr.hasNext()) {
                        current = stopItr.next();
                        if (current.equalsIgnoreCase(identifier)) {
                            GTFSeditor.stops.get(identifier).setStop_name(updateInfo);
                        }
                    }
                }
                /*-------------------------------------------*/
                //Attributed for routes
                else if (route_id_item.isSelected()) {
                    while (routeItr.hasNext()) {
                        current = routeItr.next();
                        if (current.equalsIgnoreCase(identifier)) {
                            GTFSeditor.routes.get(identifier).setRoute_id(updateInfo);
                        }
                    }
                } else if (agency_id_item.isSelected()) {
                    while (routeItr.hasNext()) {
                        current = routeItr.next();
                        if (current.equalsIgnoreCase(identifier)) {
                            GTFSeditor.routes.get(identifier).setAgency_id(updateInfo);
                        }
                    }
                } else if (route_short_name.isSelected()) {
                    while (routeItr.hasNext()) {
                        current = routeItr.next();
                        if (current.equalsIgnoreCase(identifier)) {
                            GTFSeditor.routes.get(identifier).setRoute_short_name(updateInfo);
                        }
                    }
                } else if (route_long_name.isSelected()) {
                    while (routeItr.hasNext()) {
                        current = routeItr.next();
                        if (current.equalsIgnoreCase(identifier)) {
                            GTFSeditor.routes.get(identifier).setRoute_long_name(updateInfo);
                        }
                    }
                } else if (route_desc_item.isSelected()) {
                    while (routeItr.hasNext()) {
                        current = routeItr.next();
                        if (current.equalsIgnoreCase(identifier)) {
                            GTFSeditor.routes.get(identifier).setRoute_desc(updateInfo);
                        }
                    }
                } else if (route_type_item.isSelected()) {
                    while (routeItr.hasNext()) {
                        current = routeItr.next();
                        if (current.equalsIgnoreCase(identifier)) {
                            GTFSeditor.routes.get(identifier).setRoute_type(updateInfo);
                        }
                    }
                } else if (route_url_item.isSelected()) {
                    while (routeItr.hasNext()) {
                        current = routeItr.next();
                        if (current.equalsIgnoreCase(identifier)) {
                            GTFSeditor.routes.get(identifier).setRoute_url(updateInfo);
                        }
                    }
                } else if (route_color_item.isSelected()) {
                    while (routeItr.hasNext()) {
                        current = routeItr.next();
                        if (current.equalsIgnoreCase(identifier)) {
                            GTFSeditor.routes.get(identifier).setRoute_color(updateInfo);
                        }
                    }
                } else if (route_url_item.isSelected()) {
                    while (routeItr.hasNext()) {
                        current = routeItr.next();
                        if (current.equalsIgnoreCase(identifier)) {
                            GTFSeditor.routes.get(identifier).setRoute_url(updateInfo);
                        }
                    }
                }
                /*------------------------------------------------*/
                //Attributes for trips
                else if (trip_id_item.isSelected()) {
                    while (tripItr.hasNext()) {
                        current = tripItr.next();
                        if (current.equalsIgnoreCase(identifier)) {
                            GTFSeditor.trips.get(identifier).setTrip_id(updateInfo);
                        }
                    }
                } else if (service_id_item.isSelected()) {
                    while (tripItr.hasNext()) {
                        current = tripItr.next();
                        if (current.equalsIgnoreCase(identifier)) {
                            GTFSeditor.trips.get(identifier).setService_id(updateInfo);
                        }
                    }
                } else if (trip_headsign_item.isSelected()) {
                    while (tripItr.hasNext()) {
                        current = tripItr.next();
                        if (current.equalsIgnoreCase(identifier)) {
                            GTFSeditor.trips.get(identifier).setTrip_headsign(updateInfo);
                        }
                    }
                } else if (route_id_item_1.isSelected()) {
                    while (tripItr.hasNext()) {
                        current = tripItr.next();
                        if (current.equalsIgnoreCase(identifier)) {
                            GTFSeditor.trips.get(identifier).setRoute_id(updateInfo);
                        }
                    }
                } else if (direction_id_item.isSelected()) {
                    while (tripItr.hasNext()) {
                        current = tripItr.next();
                        if (current.equalsIgnoreCase(identifier)) {
                            GTFSeditor.trips.get(identifier).setDirection_id(updateInfo);
                        }
                    }
                } else if (block_id_item.isSelected()) {
                    while (tripItr.hasNext()) {
                        current = tripItr.next();
                        if (current.equalsIgnoreCase(identifier)) {
                            GTFSeditor.trips.get(identifier).setBlock_id(updateInfo);
                        }
                    }
                } else if (shape_id_item.isSelected()) {
                    while (tripItr.hasNext()) {
                        current = tripItr.next();
                        if (current.equalsIgnoreCase(identifier)) {
                            GTFSeditor.trips.get(identifier).setBlock_id(updateInfo);
                        }
                    }
                }
                throwInfoAlert("Update Status", "All stop times in group were updated successfully");

                showUpdateGUI(false);
            } else {
                throwAlert("NullPointerException", "No GTFS files have been imported");
            }

        } catch (NumberFormatException E) {
            throwAlert("NumberFormatExeption", "Enter valid update data");
            E.printStackTrace();
        } catch (NullPointerException E) {
            throwAlert("NullPointerException", "Enter valid tripId");
        } catch (DateTimeParseException e) {
            throwAlert("DateTimeParseException", "Enter a valid time");
        } catch (Exception E) {
            throwAlert("Formatting Error", "Enter valid update data");
            E.printStackTrace();

        }
    }

    @FXML
    public void backToStart() {
        showUpdateGUI(false);
    }

    @FXML
    public void displayNextTrips() {
        String stop_id;
        Hashtable<Integer, String> closestTimes;
        String finalIds = "";

        try {
            if (imported == true) {
                stop_id = nextTripText.getText();
                closestTimes = GTFSeditor.searchStopNextTrip(stop_id, timeKeys);
                textArea1.clear();

                textArea1.appendText("Nearest departure times for stop_id  " + stop_id + ":\n\n");

                for (int i = 0; i < closestTimes.size(); i++) {
                    //System.out.println(closestTimes.get(i));
                    finalIds = closestTimes.get(i); //now has array with id and time
                    textArea1.appendText(finalIds);
                }
            } else {
                throwAlert("NullPointerException", "No GTFS files have been imported");
            }

        } catch (NullPointerException E) {
            throwAlert("NullPointerException", "No stop id was entered");
            E.printStackTrace();
        } catch (NumberFormatException e) {
            throwAlert("NumberFormatException", "Incorrect format for stop id, please enter valid ID");
        } catch (Exception e) {
            throwAlert("Input Error", "Try again.");
        }

    }

    @FXML
    public void searchRouteID() {
        String route_id;
        Hashtable<Integer, String> closestTimes;
        String finalIds = "";

        try {
            if (imported == true) {
                route_id = routeIDsearchBox.getText();
                closestTimes = GTFSeditor.searchRouteNextTrip(route_id, timeKeys);
                textArea1.clear();

                textArea1.appendText("Nearest departure times for route_id  " + route_id + ":\n\n");

                for (int i = 0; i < closestTimes.size(); i++) {
                    //System.out.println(closestTimes.get(i));
                    finalIds = closestTimes.get(i); //now has array with id and time
                    textArea1.appendText(finalIds);
                }
            } else {
                throwAlert("NullPointerException", "No GTFS files have been imported");
            }

        } catch (NullPointerException E) {
            throwAlert("NullPointerException", "No route id was entered");
            E.printStackTrace();
        } catch (NumberFormatException e) {
            throwAlert("NumberFormatException", "Incorrect format for route id, please enter valid ID");
        } catch (Exception e) {
            throwAlert("Input Error", "Try again.");
        }

    }


    void showUpdateGUI(boolean b) {
        timeTextField.visibleProperty().setValue(b);
        timeTextField1.visibleProperty().setValue(b);
        instrLabel.visibleProperty().setValue(b);
        instrLabel1.visibleProperty().setValue(b);
        enterButton.visibleProperty().setValue(b);
        backButton.visibleProperty().setValue(b);
        formatLabel.visibleProperty().setValue(b);
        tripIdFormat.visibleProperty().setValue(b);
        updateLabel.visibleProperty().setValue(b);
        updateLabel1.visibleProperty().setValue(b);

        textArea1.visibleProperty().setValue(!b);
        tripSpeedButton.visibleProperty().setValue(!b);
        tripDistanceButton.visibleProperty().setValue(!b);
        nextTripButton.visibleProperty().setValue(!b);
        nextTripText.visibleProperty().setValue(!b);
        nextTripLabel.visibleProperty().setValue(!b);
        dataStructuresLabel.visibleProperty().setValue(!b);
        stopCountButton.visibleProperty().setValue(!b);
        searchRouteIDlabel.visibleProperty().setValue(!b);
        routeIDsearchBox.visibleProperty().setValue(!b);
        searchRouteIDButton.visibleProperty().setValue(!b);
        nextTripLabel1.visibleProperty().setValue(!b);
        searchStopBar.visibleProperty().setValue(!b);
        searchStopButton.visibleProperty().setValue(!b);


    }

    @FXML
    public void searchRoute() {
        List results;
        results = GTFSeditor.searchRoute(searchStopBar.getText());
        //clear old text
        textArea1.setText("");
        textArea1.setText(results.toString());
    }


    /**
     * Presents an alert containing exception information
     *
     * @param exceptionType Type of exception thrown
     * @param message       Message of why exception occurred
     */
    private void throwAlert(String exceptionType, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(exceptionType);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Presents an alert containing information
     *
     * @param headerText Message for header
     * @param message    Message for body
     */
    protected static void throwInfoAlert(String headerText, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(headerText);
        alert.setContentText(message);
        alert.showAndWait();
    }

    //this method takes the selected item that they want to update and makes data entry visible
    @FXML
    public void updateAttributeGUI() {
        //make field invisible until one of the items is selected
        //System.out.println("hehe");
        timeTextField.clear();
        String toTopField = "";
        String toBotField = "";
        String topFormat = "n/a";
        String botFormat = "";
        String instr = "";

        //making gui elements available to user
        showUpdateGUI(true);

        /*------------------------------------------------------------*/
        //Attributes for stop time
        if (timeForwards.isSelected()) {
            toTopField = "Enter amount to shift forward arrival and departure times:";
            toBotField = "Enter trip_id of group:";
            topFormat = "'HH:MM:SS'";
            botFormat = "'12345678_9ABC'";
            instr = "Stop Times/" + timeForwards.getText();
        }
        if (timeBackwards.isSelected()) {
            toTopField = "Enter amount to shift back arrival and departure times:";
            toBotField = "Enter trip_id of group:";
            topFormat = "HH:MM:SS";
            botFormat = "'12345678_9ABC'";
            instr = "Stop Times/" + timeBackwards.getText();
        }
        if (drop_off_type.isSelected()) {
            toTopField = "Enter new drop off type:\n";
            toBotField = "Enter trip_id of group:";
            botFormat = "'12345678_9ABC'";
            instr = "Stop Times/" + drop_off_type.getText();

        }
        if (pickup_type.isSelected()) {
            toTopField = "Enter new pickup type:\n";
            toBotField = "Enter trip_id of group:";
            botFormat = "'12345678_9ABC'";
            instr = "Stop Times/" + pickup_type.getText();

        }
        if (stop_headsign.isSelected()) {
            toTopField = "Enter new stop headsign:\n";
            toBotField = "Enter trip_id of group:";
            botFormat = "'12345678_9ABC'";
            instr = "Stop Times/" + stop_headsign.getText();

        }

        /*------------------------------------------------------------*/
        //Attributes for stop
        if (stop_id.isSelected()) {
            toTopField = "Enter new stop id:\n";
            toBotField = "Enter trip_id of group:";
            topFormat = "'####'";
            botFormat = "'12345678_9ABC'";
            instr = "Stop/" + stop_id.getText();

        }
        if (stop_sequence.isSelected()) {
            toTopField = "Enter new stop sequence:\n";
            topFormat = "integer value";
            botFormat = "'12345678_9ABC'";
            instr = "Stop/" + stop_sequence.getText();

        }
        if (trip_id.isSelected()) {
            toTopField = "Enter new trip id:\n";
            toBotField = "Enter trip_id of group:";
            topFormat = "'12345678_9ABC'";
            botFormat = "'12345678_9ABC'";
            instr = "Stop/" + trip_id.getText();

        }
        if (stop_id_item.isSelected()) {
            toTopField = "Enter new stop_id:\n";
            toBotField = "Enter stop_id to update:";
            topFormat = "'####'";
            botFormat = "'####'";
            instr = "Stop/" + stop_id_item.getText();

        }
        if (stop_desc_item.isSelected()) {
            toTopField = "Enter new stop description:\n";
            toBotField = "Enter stop_id to update:";
            botFormat = "'####'";
            instr = "Stop/" + stop_desc_item.getText();

        }
        if (stop_lat.isSelected()) {
            toTopField = "Enter new stop latitude:\n";
            toBotField = "Enter stop_id to change:";
            topFormat = "'(+/-) ##.#######'";
            botFormat = "'####'";
            instr = "Stop/" + stop_lat.getText();
        }
        if (stop_long.isSelected()) {
            toTopField = "Enter new stop longitude:\n";
            toBotField = "Enter stop_id to change:";
            topFormat = "'(+/-) ##.#######'";
            botFormat = "'####'";
            instr = "Stop/" + stop_long.getText();

        }
        if (stop_name.isSelected()) {
            toTopField = "Enter new stop latitude:\n";
            toBotField = "Enter stop_id to change:";
            topFormat = "String";
            botFormat = "'####'";
            instr = "Stop/" + stop_name.getText();

        }
        /*-------------------------------------------*/
        //Attributed for routes
        if (route_id_item.isSelected()) {
            toTopField = "Enter new route_id:\n";
            toBotField = "Enter route_id to change:";
            topFormat = "String (ex. '12', 'GGR', '30X')";
            instr = "Route/" + route_id_item.getText();

        }
        if (agency_id_item.isSelected()) {
            toTopField = "Enter new agency_id:\n";
            toBotField = "Enter route_id to update:";
            topFormat = "String (ex. 'MCTS')";
            botFormat = "String (ex. '12', 'GGR', '30X')";
            instr = "Route/" + agency_id_item.getText();

        }
        if (route_short_name.isSelected()) {
            toTopField = "Enter new short name:\n";
            toBotField = "Enter route_id to update:";
            topFormat = "Similar to route_id";
            botFormat = "String (ex. '12', 'GGR', '30X')";
            instr = "Route/" + route_short_name.getText();

        }
        if (route_long_name.isSelected()) {
            toTopField = "Enter new long name:\n";
            toBotField = "Enter route_id to update:";
            topFormat = "String (location)";
            botFormat = "String (ex. '12', 'GGR', '30X')";
            instr = "Route/" + route_long_name.getText();

        }
        if (route_desc_item.isSelected()) {
            toTopField = "Enter new route description:\n";
            toBotField = "Enter route_id to update:";
            topFormat = "String (describe route)";
            botFormat = "String (ex. '12', 'GGR', '30X')";
            instr = "Route/" + route_desc_item.getText();

        }
        if (route_type_item.isSelected()) {
            toTopField = "Enter new route type:\n";
            toBotField = "Enter route_id to update:";
            topFormat = "'#'";
            botFormat = "String (ex. '12', 'GGR', '30X')";
            instr = "Route/" + route_type_item.getText();

        }
        if (route_url_item.isSelected()) {
            toTopField = "Enter new route url:\n";
            toBotField = "Enter route_id to update:";
            topFormat = "n/a";
            botFormat = "String (ex. '12', 'GGR', '30X')";
            instr = "Route/" + route_url_item.getText();

        }
        if (route_color_item.isSelected()) {
            toTopField = "Enter new route color:\n";
            toBotField = "Enter route_id to update:";
            topFormat = "'######' (rgb hex code)";
            botFormat = "String (ex. '12', 'GGR', '30X')";
            instr = "Route/" + route_color_item.getText();

        }
        
        /*------------------------------------------------*/
        //Attributes for trips
        if (trip_id_item.isSelected()) {
            toTopField = "Enter new trip_id:\n";
            toBotField = "Enter trip_id to change:";
            topFormat = "'12345678_9ABC'";
            botFormat = "'12345678_9ABC'";
            instr = "Trip/" + trip_id_item.getText();

        }
        if (service_id_item.isSelected()) {
            toTopField = "Enter new service_id:\n";
            toBotField = "Enter trip_id to update:";
            topFormat = "'DD-MONTH-DAY'(ex. '17-SEP_SUN')";
            botFormat = "'12345678_9ABC'";
            instr = "Trip/" + service_id_item.getText();

        }
        if (trip_headsign_item.isSelected()) {
            toTopField = "Enter new trip headsign:\n";
            toBotField = "Enter trip_id to update:";
            topFormat = "String (ex. 'DOWNTOWN' or '60TH-VLIET')";
            botFormat = "'12345678_9ABC'";
            instr = "Trip/" + trip_headsign_item.getText();

        }
        if (route_id_item_1.isSelected()) {
            toTopField = "Enter new trip route_id:\n";
            toBotField = "Enter trip_id to update:";
            topFormat = "String (ex. '12', 'GGR', '30X')";
            botFormat = "'12345678_9ABC'";
            instr = "Trip/" + route_id_item_1.getText();

        }
        if (direction_id_item.isSelected()) {
            toTopField = "Enter new trip direction_id:\n";
            toBotField = "Enter trip_id to update:";
            topFormat = "'0' or '1'";
            botFormat = "'12345678_9ABC'";
            instr = "Trip/" + direction_id_item.getText();

        }
        if (block_id_item.isSelected()) {
            toTopField = "Enter new trip block_id:\n";
            toBotField = "Enter trip_id to update:";
            topFormat = "'#####'";
            botFormat = "'12345678_9ABC'";
            instr = "Trip/" + block_id_item.getText();

        }
        if (shape_id_item.isSelected()) {
            toTopField = "Enter new trip block_id:\n";
            toBotField = "Enter trip_id to update:";
            topFormat = "'DD-MONTH_##_#_##'   (ex '17-SEP_64_0_23')";
            botFormat = "'12345678_9ABC'";
            instr = "Trip/" + shape_id_item.getText();

        }

        /*------------------------------------------------*/
        instrLabel.setText(toTopField);
        instrLabel1.setText(toBotField);
        updateLabel1.setText(instr);
        enterButton.setDisable(false);
        formatLabel.setText("Format: " + topFormat);
        tripIdFormat.setText("Format: " + botFormat);
    }

}

