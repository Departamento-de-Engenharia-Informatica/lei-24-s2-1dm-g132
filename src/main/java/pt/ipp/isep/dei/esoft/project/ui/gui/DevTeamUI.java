package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 * Controller class for the Developer Team UI.
 */
public class DevTeamUI {

    /**
     * Handles the action when the "Go Back" button is clicked.
     *
     * @param actionEvent The action event that triggered this method.
     */
    @FXML
    public void goBack(ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/mainMenu.fxml"));
            Parent devTeamUI = loader.load();

            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Stage mainMenuUIStage = new Stage();
            mainMenuUIStage.setTitle("Green Spaces App");
            Scene scene = new Scene(devTeamUI);
            mainMenuUIStage.setScene(scene);
            currentStage.close();
            mainMenuUIStage.show();

            mainMenuUIStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Alert alerta = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, "Green Spaces App",
                            "Action confirmation.", "Do you wish to close the app?");
                    if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                        event.consume();
                    }
                }
            });
            mainMenuUIStage.show();
        }catch (IOException ex) {
            AlertUI.createAlert(Alert.AlertType.ERROR, "Green Spaces App",
                    "Problems starting the application.", ex.getMessage()).show();
        }
    }
}
