package gtfseditor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    Application GTFSeditor = new Application();

    //FXML objects
    @FXML
    CheckBox stopCheck;
    @FXML
    CheckBox stopTimeCheck;
    @FXML
    CheckBox tripCheck;
    @FXML
    CheckBox routeCheck;
    @FXML
    MenuButton updateMenuButton;
    @FXML
    RadioMenuItem arrivalTimeForwards;
    @FXML
    RadioMenuItem departureTimeForward;
    @FXML
    RadioMenuItem arrivalTimeBackwards;
    @FXML
    RadioMenuItem departureTimeBackwards;
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
    ToggleGroup timeUpdateGroup;
    @FXML
    TextField timeTextField;
    @FXML
    TextField timeTextField1;
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
        } catch (NullPointerException e) {
            throwAlert("NullPointerException", "There were troubles importing, " +
                    "make sure four files were selected.");
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
    private void parseFiles(File tripFile, File routeFile, File stopFile, File timeFile) throws NullPointerException {

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


    private void fileToStops(BufferedReader bufferIn, String fileName) {
        String[] elements = null;
        try {
            String c = bufferIn.readLine();
            System.out.println("\n\nImporting: " + fileName);

            for (int i = 0; c != null; i++) { //for each line in doc
                if (i != 0) { //skip first line, no info to import
                    elements = c.split(","); //fills array with data from one line
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

    public void fileToTrips (BufferedReader bufferIn, String fileName){
        String[] elements = null;
        String[] validParamters = {"route_id","service_id","trip_id","trip_headsign","trip_short_name","direction_id","block_id","shape_id",
                "wheelchair_accessible","bikes_allowed"};
        try {

            String c = bufferIn.readLine();
            elements = c.split(",");
            for(String element : elements) {
                boolean validParameter = false;
                for(String valid : validParamters) {
                    if(element.equals(valid)) {
                        validParameter = true;
                    }
                }
                if(!validParameter) {
                    throw new IllegalArgumentException("Invalid parameter for stops.txt");
                }
            }
            System.out.println("\n\nImporting: "+ fileName);

            for(int i = 0; c != null; i++) { //for each line in doc
                if(i != 0) { //skip first line, no info to import
                    elements = c.split(","); //fills array with data from one line
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

    String[] timeElements = null;
    //ArrayList timeKeys = new ArrayList();

    private void fileToStopTimes(BufferedReader bufferIn, String fileName) {
        String[] validParamters = {"trip_id","arrival_time","departure_time","stop_id","stop_sequence","stop_headsign","pickup_type",
                "drop_off_type", "shape_dist_travel","timepoint"};
        try {
            String c = bufferIn.readLine();
            timeElements = c.split(",");
            for(String element : timeElements) {
                boolean validParameter = false;
                for(String valid : validParamters) {
                    if(element.equals(valid)) {
                        validParameter = true;
                    }
                }
                if(!validParameter) {
                    throw new IllegalArgumentException("Invalid parameter for stops.txt");
                }
            }
            System.out.println("\n\nImporting: " + fileName);

            for (int i = 0; c != null; i++) { //for each line in doc
                if (i != 0) { //skip first line, no info to import
                    timeElements = c.split(","); //fills array with data from one line
                    //create new stopTime
                    //timeKeys.add(timeElements[0]);
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

    private void fileToRoutes(BufferedReader bufferIn, String fileName) {
        String[] elements = null;
        String[] validParamters = {"route_id","agency_id","route_short_name","route_long_name","route_desc","route_type",
                "route_url","route_color", "route_text_color","route_sort_order"};
        try {
            String c = bufferIn.readLine();
            System.out.println("\n\nImporting: " + fileName);

            for (int i = 0; c != null; i++) { //for each line in doc
                if (i != 0) { //skip first line, no info to import
                    elements = c.split(","); //fills array with data from one line
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
    public void display() {
        throwInfoAlert("Data Structure Info",
                "There are:\n" + GTFSeditor.stops.size() + " stops\n" +
                        "There are:\n" + GTFSeditor.stopTimes.size() + " routes with stopTimes\n" +
                        "There are:\n" + GTFSeditor.trips.size() + " trips\n" +
                        "There are:\n" + GTFSeditor.routes.size() + " routes\n");
    }

    @FXML
    public void tripSpeeds(){
        try{
            //reset area
            textArea1.setText("");
            //set to list of speeds
            textArea1.setText(GTFSeditor.displayAllTripSpeed());
        } catch (ParseException e){
            throwAlert("Parse exception", "Error parsing stop time");
        } catch (NullPointerException e){
            throwAlert("Null Pointer Exception",
                    "Make sure files are imported into the application before" +
                            " requesting average trip speeds");
        }
    }

    //this method takes the selected item that they want to update and makes data entry visible
    @FXML
    public void updateStopTimes() {
        //make field invisible until one of the items is selected
        //System.out.println("hehe");
        timeTextField.clear();
        String toField = "Nothing selected";
        String format = "";

        //making gui elements available to user
        timeTextField.visibleProperty().setValue(true);
        timeTextField1.visibleProperty().setValue(true);
        instrLabel.visibleProperty().setValue(true);
        instrLabel1.visibleProperty().setValue(true);
        enterButton.visibleProperty().setValue(true);
        formatLabel.visibleProperty().setValue(true);
        tripIdFormat.visibleProperty().setValue(true);
        textArea1.visibleProperty().setValue(false);
        tripSpeedButton.visibleProperty().setValue(false);


        tripIdFormat.setText("Format: '12345678_9ABC'");

        if (arrivalTimeForwards.isSelected()) {
            toField = "Enter amount to shift forward arrival time:";
            format = "HH:MM:SS";
        }
        if (arrivalTimeBackwards.isSelected()) {
            toField = "Enter amount to shift back arrival time:";
            format = "HH:MM:SS";
        }
        if (departureTimeForward.isSelected()) {
            toField = "Enter amount to shift forward departure time:";
            format = "'HH:MM:SS'";
        }
        if (departureTimeBackwards.isSelected()) {
            toField = "Enter amount to shift back departure time:";
            format = "'HH:MM:SS'";
        }
        if (drop_off_type.isSelected()) {
            toField = "Enter new drop off type:\n";
        }
        if (pickup_type.isSelected()) {
            toField = "Enter new pickup type:\n";

        }
        if (stop_headsign.isSelected()) {
            toField = "Enter new stop headsign:\n";
        }
        if (stop_id.isSelected()) {
            toField = "Enter new stop id:\n";
            format = "####";

        }
        if (stop_sequence.isSelected()) {
            toField = "Enter new stop sequence:\n";
        }
        if (trip_id.isSelected()) {
            toField = "Enter new trip id:\n";
            format = "'12345678_9ABC'";
        }

        instrLabel.setText(toField);
        enterButton.setDisable(false);
        formatLabel.setText("Format: " + format);
        System.out.println(toField);
    }

    /**
     * This method is called when hitting enter button to update stop time  attributes
     * it updates the attribute of all objects with the desired trip_id
     */
    @FXML
    public void enterUpdate() {
        try {
            String updateInfo = timeTextField.getText();
            LinkedList<StopTime> times_with_common_trip = GTFSeditor.stopTimes.get(timeTextField1.getText()); //now has list of stop times with a specific stop id
            int timeListSize = times_with_common_trip.size();

            //TODO
            if (arrivalTimeForwards.isSelected()) {
                for (int i = 0; i < timeListSize; i++) {
                    StopTime.timeShift(updateInfo, times_with_common_trip, "arrival", "f", i);
                }
            } else if (arrivalTimeBackwards.isSelected()) {
                for (int i = 0; i < timeListSize; i++) {
                    StopTime.timeShift(updateInfo, times_with_common_trip, "arrival", "b", i);
                }
            } else if (departureTimeForward.isSelected()) {
                for (int i = 0; i < timeListSize; i++) {
                    StopTime.timeShift(updateInfo, times_with_common_trip, "departure", "f", i);
                }
            } else if (departureTimeBackwards.isSelected()) {
                for (int i = 0; i < timeListSize; i++) {
                    StopTime.timeShift(updateInfo, times_with_common_trip, "departure", "b", i);
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
            throwInfoAlert("Update Status", "All stop times in group were updated successfully");

            timeTextField.visibleProperty().setValue(false);
            timeTextField1.visibleProperty().setValue(false);
            instrLabel.visibleProperty().setValue(false);
            instrLabel1.visibleProperty().setValue(false);
            enterButton.visibleProperty().setValue(false);
            formatLabel.visibleProperty().setValue(false);
            tripIdFormat.visibleProperty().setValue(false);

            textArea1.visibleProperty().setValue(true);
            tripSpeedButton.visibleProperty().setValue(true);

        } catch (NumberFormatException E) {
            throwAlert("NumberFormatExeption", "Enter valid update data");
        } catch (NullPointerException E) {
            throwAlert("NullPointerException", "Enter valid update data");
        } catch (DateTimeParseException e) {
            throwAlert("DateTimeParseException", "Enter a valid time");
        } catch (Exception E) {
            throwAlert("Formatting Error", "Enter valid update data");
        }
    }

    /**
     * Presents an alert containing exception information
     * @param exceptionType Type of exception thrown
     * @param message Message of why exception occurred
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

}

