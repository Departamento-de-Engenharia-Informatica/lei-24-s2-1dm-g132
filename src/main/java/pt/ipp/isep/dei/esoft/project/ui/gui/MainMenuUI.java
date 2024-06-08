package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javafx.event.ActionEvent;
import java.io.IOException;

/**
 * Controller class for the Main Menu UI.
 */
public class MainMenuUI {

    /**
     * Opens the Developer Team UI when the corresponding button is clicked.
     *
     * @param actionEvent The action event that triggered this method.
     */
    @FXML
    public void openDevTeamUI(ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/devTeam.fxml"));
            Parent devTeamUI = loader.load();

            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Stage devTeamUIStage = new Stage();
            devTeamUIStage.setTitle("Dev Team UI");
            Scene scene = new Scene(devTeamUI);
            devTeamUIStage.setScene(scene);
            currentStage.close();
            devTeamUIStage.show();

            devTeamUIStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Alert alerta = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, "Dev Team UI",
                            "Action confirmation.", "Do you wish to close the app?");
                    if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                        event.consume();
                    }
                }
            });
            devTeamUIStage.show();
        }catch (IOException ex) {
            AlertUI.createAlert(Alert.AlertType.ERROR, "Dev Team UI",
                    "Problems opening Dev Team UI.", ex.getMessage()).show();
        }
    }

    /**
     * Opens the Login UI when the corresponding button is clicked.
     *
     * @param actionEvent The action event that triggered this method.
     */
    @FXML
    public void openLoginUI(ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/login.fxml"));
            Parent loginUI = loader.load();

            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Stage loginUIStage = new Stage();
            loginUIStage.setTitle("Login UI");
            Scene scene = new Scene(loginUI);
            loginUIStage.setScene(scene);
            currentStage.close();
            loginUIStage.show();

            loginUIStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Alert alerta = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, "Login UI",
                            "Action confirmation.", "Do you wish to close the app?");
                    if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                        event.consume();
                    }
                }
            });
            loginUIStage.show();
        }catch (IOException ex) {
            AlertUI.createAlert(Alert.AlertType.ERROR, "Login UI",
                    "Problems opening Login UI.", ex.getMessage()).show();
        }
    }
}
