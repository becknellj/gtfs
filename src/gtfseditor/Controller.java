package gtfseditor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;


public class Controller {

    @FXML
    public void openStopsFile(ActionEvent event){
        try {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter1 =
                    new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().addAll(extFilter1);
            File selectedFile = fileChooser.showOpenDialog(null);
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
            File selectedFile = fileChooser.showOpenDialog(null);
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
            File selectedFile = fileChooser.showOpenDialog(null);
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
            File selectedFile = fileChooser.showOpenDialog(null);
        } catch (NullPointerException e){
            throwAlert("NullPointerException", "No file was selected, please select a file.");
        } catch (IllegalArgumentException e){
            throwAlert("IllegalArgumentException", e.getMessage());
        }
        System.out.println();
    }

    @FXML
    public void importFiles(ActionEvent event){

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
