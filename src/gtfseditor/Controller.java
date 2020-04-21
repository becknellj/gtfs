package gtfseditor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

    Hashtable<String, LinkedList<String>> tripHashtable = new Hashtable<>();
    Hashtable<String, LinkedList<String>> stopHashtable = new Hashtable<>();
    Hashtable<String, LinkedList<String>> stopTimeHashtable = new Hashtable<>();
    Hashtable<String, LinkedList<String>> routeHashtable = new Hashtable<>();

    @FXML
    TextArea textArea;

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

            selectedTripFile.getName();
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

            //passes selected files from filechooser into helper method
            parseFiles(selectedTripFile, selectedRouteFile,selectedStopFile, selectedTimeFile);

        } catch (NullPointerException e){
            throwAlert("FileNotFoundException", "Following file not found: ");
                    e.printStackTrace();
        }

    }
    //helper method that takes in four selected files and parses the information with four hash tables

    /**
     * Helper method tht takes in four selected files and parses the information into four hash tables
     * @param tripFile file with trip information
     * @param routeFile file with route information
     * @param stopFile file with stop information
     * @param timeFile file with stop time information
     */
    private void parseFiles(File tripFile,File routeFile,File stopFile,File timeFile){

        try{
            BufferedReader tripBufferedReader = new BufferedReader(new FileReader(tripFile));
            BufferedReader stopBufferedReader = new BufferedReader(new FileReader(stopFile));
            BufferedReader stopTimeBufferedReader = new BufferedReader(new FileReader(timeFile));
            BufferedReader routeBufferedReader = new BufferedReader(new FileReader(routeFile));


            //passes the buffered reader and Hashtable into helper method to transfer data from file to table
            fileToTable(tripBufferedReader, tripHashtable, tripFile.getName());
            fileToTable(stopBufferedReader, stopHashtable, stopFile.getName());
            fileToTable(stopTimeBufferedReader, stopTimeHashtable, timeFile.getName());
            fileToTable(routeBufferedReader, routeHashtable, routeFile.getName());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Helper method buffered reader and hashtable into helper method to transfer data from file to table
     * @param bufferIn Buffered reader that is attatched to the gtfs csv files
     * @param hashtable List with keys indicating the row of the file and value is a list data from one line
     */
    private Hashtable<String, LinkedList<String>> fileToTable(BufferedReader bufferIn, Hashtable<String, LinkedList<String>> hashtable, String fileName){
        String[] elements = null;

        try {
            String c = bufferIn.readLine();
            System.out.println("\n\nNew Hashtable: "+ fileName);

            for(int i = 0; c != null; i++) { //for each line in doc
                elements = c.split(","); //fills array with data from one line

                String key = Integer.toString(i); //essentially the "row"
                LinkedList<String> oneLine = new LinkedList<>();        //contains one line of data

                for (int j = 0; j <elements.length; j++) {  //for each element in one line
                    oneLine.add(elements[j]);               //filling up list that will be put in the table
                }

                hashtable.put(key, oneLine);                //adding info at key indicating the row
                c = bufferIn.readLine();
                //textArea.appendText(hashtable.toString());

            }
            System.out.println(hashtable.toString()+"\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return hashtable;
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