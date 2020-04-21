package gtfseditor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.Hashtable;


public class Controller {
    File selectedTripFile = null;
    File selectedTimeFile = null;
    File selectedRouteFile = null;
    File selectedStopFile = null;

    Hashtable<String, String> tripInfo = new Hashtable<>();//k is stringId, v is strings with info
    Hashtable<String, String> stopInfo = new Hashtable<>();//k is stringId, v is strings with info
    Hashtable<String, String> stopTimeInfo = new Hashtable<>();//k is stringId, v is strings with info
    Hashtable<String, String> routeInfo = new Hashtable<>();//k is stringId, v is strings with info

    @FXML
    public void openStopsFile(ActionEvent event){
        try {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter1 =
                    new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().addAll(extFilter1);
            selectedStopFile = fileChooser.showOpenDialog(null);
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

          /*  FileInputStream inTrip = new FileInputStream(selectedTripFile); //input stream
            FileOutputStream outTrip = new FileOutputStream(new File("importTrip.txt"));

            int c;
            while((c = inTrip.read()) != -1){
                outTrip.write(c);
            }

            inTrip.close();
            outTrip.close();*/

        } catch (NullPointerException e){
            throwAlert("NullPointerException", "No file was selected, please select a file.");
        } catch (IllegalArgumentException e){
            throwAlert("IllegalArgumentException", e.getMessage());
        }
        System.out.println();
    }

    @FXML
    public void importFiles(ActionEvent event){
        FileInputStream tripIn = null;
        FileOutputStream tripOut = null;
        try{

           parseFiles(selectedTripFile,selectedRouteFile,selectedStopFile,selectedTimeFile);

        } catch (NullPointerException e){
            throwAlert("FileNotFoundException", "Following file not found: ");
                    e.printStackTrace();
        } /*catch (IOException e) {
            throwAlert("IOException", "Error writing to stream");
        }*/

    }
    //helper method that takes in four selected files and parses the information with four hash tables
    private void parseFiles(File tripFile,File routeFile,File stopFile,File timeFile){
        BufferedReader tripIn = null;
        BufferedReader stopIn = null;
        BufferedReader stopTimeIn = null;
        BufferedReader routeIn = null;

        try{
            tripIn = new BufferedReader(new FileReader(tripFile));
            stopIn = new BufferedReader(new FileReader(stopFile));
            stopTimeIn = new BufferedReader(new FileReader(timeFile));
            routeIn = new BufferedReader(new FileReader(routeFile));


            fileToTable(tripIn, tripInfo);
            fileToTable(stopIn, stopInfo);
            fileToTable(stopTimeIn, stopTimeInfo);
            fileToTable(routeIn, routeInfo);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void fileToTable(BufferedReader bufferIn, Hashtable<String, String> hashtable){
        String[] elements = null;

        try {
            String c = bufferIn.readLine();

           // while (c != null) {
                //take each line and put into hash

                //System.out.println(elements[1]);

                //
            //}

            //now array is full of one line of data

            for(int i = 0; c != null; i++) { //for each line in doc
                elements = c.split(",");

                for (int j = 0; j <elements.length; j++) { //for each element in one line
                    String key = Integer.toString(i);
                    String value = elements[j];

                    hashtable.put(key, value);
                }
                c = bufferIn.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
       // return elements;
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

}
