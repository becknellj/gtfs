package gtfseditor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.*;
import java.sql.SQLOutput;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 * @authors becknellj, andrew nebel
 * @version 1.1
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
    public void openStopsFile(ActionEvent event){
        try {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter1 =
                    new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().addAll(extFilter1);
            selectedStopFile = fileChooser.showOpenDialog(null);
            selectedStopFile.getName();// null pointer check
            stopCheck.setSelected(true);
            throwInfoAlert("File selected.", selectedStopFile.getName() + " selected.");
        } catch (NullPointerException e){
            throwAlert("NullPointerException", "No file was selected, please select a file.");
        } catch (IllegalArgumentException e){
            throwAlert("IllegalArgumentException", e.getMessage());
        }
        System.out.println();
    }

    @FXML
    public void openRoutesFile(ActionEvent event){
        try {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter1 =
                    new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().addAll(extFilter1);
            selectedRouteFile = fileChooser.showOpenDialog(null);
            selectedRouteFile.getName();// null pointer check
            routeCheck.setSelected(true);
            throwInfoAlert("File selected.", selectedRouteFile.getName() + " selected.");
        } catch (NullPointerException e){
            throwAlert("NullPointerException", "No file was selected, please select a file.");
        } catch (IllegalArgumentException e){
            throwAlert("IllegalArgumentException", e.getMessage());
        }
        System.out.println();
    }

    @FXML
    public void openStopTimesFile(ActionEvent event){
        try {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter1 =
                    new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().addAll(extFilter1);
            selectedTimeFile = fileChooser.showOpenDialog(null);
            selectedTimeFile.getName();// null pointer check
            stopTimeCheck.setSelected(true);
            throwInfoAlert("File selected.", selectedTimeFile.getName() + " selected.");

        } catch (NullPointerException e){
            throwAlert("NullPointerException", "No file was selected, please select a file.");
        } catch (IllegalArgumentException e){
            throwAlert("IllegalArgumentException", e.getMessage());
        }
        System.out.println();
    }

    @FXML
    public void openTripsFile(ActionEvent event){
        try {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter1 =
                    new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().addAll(extFilter1);
            selectedTripFile = fileChooser.showOpenDialog(null);
            selectedTripFile.getName();// null pointer check
            tripCheck.setSelected(true);
            throwInfoAlert("File selected.", selectedTripFile.getName() + " selected.");
        } catch (NullPointerException e){
            throwAlert("NullPointerException", "No file was selected, please select a file.");
        } catch (IllegalArgumentException e){
            throwAlert("IllegalArgumentException", e.getMessage());
        }

    }

    @FXML
    public void importFiles(ActionEvent event){
        FileInputStream tripIn = null;
        FileOutputStream tripOut = null;
        try{

            //passes selected files from filechooser into helper method
            parseFiles(selectedTripFile, selectedRouteFile,selectedStopFile, selectedTimeFile);
            //confirmation files imported
            throwInfoAlert("Import Status", "All files were imported successfully");
        } catch (NullPointerException e){
            throwAlert("NullPointerException", "There were troubles importing, " +
                    "make sure four files were selected.");
        }
        reset();
    }
    //helper method that takes in four selected files and parses the information with four hash tables

    @FXML
   public void reset(){
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
     * @param tripFile file with trip information
     * @param routeFile file with route information
     * @param stopFile file with stop information
     * @param timeFile file with stop time information
     */
    private void parseFiles(File tripFile,File routeFile,File stopFile,File timeFile) throws NullPointerException{

        try{
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
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void fileToStops (BufferedReader bufferIn, String fileName){
        String[] elements = null;
        try {
            String c = bufferIn.readLine();
            System.out.println("\n\nImporting: "+ fileName);

            for(int i = 0; c != null; i++) { //for each line in doc
                if(i != 0) { //skip first line, no info to import
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

    private void fileToTrips (BufferedReader bufferIn, String fileName){
        String[] elements = null;
        try {
            String c = bufferIn.readLine();
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

    private void fileToStopTimes (BufferedReader bufferIn, String fileName){
        String[] elements = null;
        try {
            String c = bufferIn.readLine();
            System.out.println("\n\nImporting: "+ fileName);

            for(int i = 0; c != null; i++) { //for each line in doc
                if(i != 0) { //skip first line, no info to import
                    elements = c.split(","); //fills array with data from one line
                    //create new stopTime
                    StopTime newStopTime = new StopTime(elements[0], elements[1], elements[2], elements[3], elements[4],
                            elements[5], elements[6], elements[7]);
                    //add stopTime to stopTime table in application via its trip id where
                    //it is added to a list of all stopTimes on that trip
                    //check if there is a linked list
                    if(GTFSeditor.stopTimes.get(elements[0]) == null){ //add new one if there isnt
                        GTFSeditor.stopTimes.put(elements[0], new LinkedList<StopTime>());
                    }
                    //add stopTime
                    GTFSeditor.stopTimes.get(elements[0]).add(newStopTime);
                }
                //read next line
                c = bufferIn.readLine();
            }
            //remind user to check formatting
        } catch (IOException e) {
            throwAlert("Formatting Error", "Make sure " + selectedTimeFile.getName() + " file is formatted correctly");
        }
    }

    private void fileToRoutes (BufferedReader bufferIn, String fileName){
        String[] elements = null;
        try {
            String c = bufferIn.readLine();
            System.out.println("\n\nImporting: "+ fileName);

            for(int i = 0; c != null; i++) { //for each line in doc
                if(i != 0) { //skip first line, no info to import
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
    public void display(){
        throwInfoAlert("Data Structure Info",
                "There are:\n" + GTFSeditor.stops.size() + " stops\n" +
                "There are:\n" + GTFSeditor.stopTimes.size() + " routes with stopTimes\n" +
                "There are:\n" + GTFSeditor.trips.size() + " trips\n" +
                "There are:\n" + GTFSeditor.routes.size() + " routes\n");
    }


    /**
     * Presents an alert containing exception information
     * @param exceptionType Type of exception thrown
     * @param message Message of why exception occurred
     */
    private void throwAlert(String exceptionType, String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(exceptionType);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Presents an alert containing information
     * @param headerText Message for header
     * @param message Message for body
     */
    private void throwInfoAlert(String headerText, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(headerText);
        alert.setContentText(message);
        alert.showAndWait();
    }

}