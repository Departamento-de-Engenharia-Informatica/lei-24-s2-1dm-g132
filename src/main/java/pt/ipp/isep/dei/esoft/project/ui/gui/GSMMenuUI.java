package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
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
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;

import java.io.IOException;

/**
 * Controller class for the Green Spaces Manager (GSM) menu user interface.
 */
public class GSMMenuUI {

    /**
     * The authentication controller for managing user authentication and session.
     */
    private final AuthenticationController ctrl;

    /**
     * Constructor for GSMMenuUI class.
     */
    public GSMMenuUI()
    {
        ctrl = new AuthenticationController();
    }

    /**
     * Logs out the user from the system and returns to the main menu.
     *
     * @param actionEvent The action event that triggered this method.
     */
    @FXML
    public void logout(ActionEvent actionEvent) {
        try{
            ctrl.doLogout();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/mainMenu.fxml"));
            Parent mainMenuUI = loader.load();

            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Stage mainMenuUIStage = new Stage();
            mainMenuUIStage.setTitle("Green Spaces App");
            Scene scene = new Scene(mainMenuUI);
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
                    "Problems opening Main Menu UI.", ex.getMessage()).show();
        }
    }

    /**
     * Opens the Register Green Space user interface.
     *
     * @param actionEvent The action event that triggered this method.
     */
    @FXML
    public void openRegisterGreenSpace(ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/registerGreenSpace.fxml"));
            Parent registerGreenSpaceUI = loader.load();

            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Stage registerGreenSpaceUIStage = new Stage();
            registerGreenSpaceUIStage.setTitle("Register Green Space UI");
            Scene scene = new Scene(registerGreenSpaceUI);
            registerGreenSpaceUIStage.setScene(scene);
            currentStage.close();
            registerGreenSpaceUIStage.show();

            registerGreenSpaceUIStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Alert alerta = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, "Register Green Space UI",
                            "Action confirmation.", "Do you wish to close the app?");
                    if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                        event.consume();
                    }
                }
            });
            registerGreenSpaceUIStage.show();
        }catch (IOException ex) {
            AlertUI.createAlert(Alert.AlertType.ERROR, "Register Green Space UI",
                    "Problems opening Register Green Space UI.", ex.getMessage()).show();
        }
    }

    /**
     * Opens the Add Entry to To-Do List user interface.
     *
     * @param actionEvent The action event that triggered this method.
     */
    @FXML
    public void openAddEntryToDo(ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/addEntryToDo.fxml"));
            Parent addEntryToDoUI = loader.load();

            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Stage addEntryToDoUIStage = new Stage();
            addEntryToDoUIStage.setTitle("Add Entry to To-Do List UI");
            Scene scene = new Scene(addEntryToDoUI);
            addEntryToDoUIStage.setScene(scene);
            currentStage.close();
            addEntryToDoUIStage.show();

            addEntryToDoUIStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Alert alerta = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, "Add Entry to To-Do List UI",
                            "Action confirmation.", "Do you wish to close the app?");
                    if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                        event.consume();
                    }
                }
            });
            addEntryToDoUIStage.show();
        }catch (IOException ex) {
            AlertUI.createAlert(Alert.AlertType.ERROR, "Add Entry to To-Do List UI",
                    "Problems opening Add Entry to To-Do List UI.", ex.getMessage()).show();
        }
    }

    /**
     * Opens the Add Entry to Agenda user interface.
     *
     * @param actionEvent The action event that triggered this method.
     */
    @FXML
    public void openAddEntryAgenda(ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/addEntryAgenda.fxml"));
            Parent addEntryAgendaUI = loader.load();

            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Stage addEntryAgendaUIStage = new Stage();
            addEntryAgendaUIStage.setTitle("Add Entry to Agenda UI");
            Scene scene = new Scene(addEntryAgendaUI);
            addEntryAgendaUIStage.setScene(scene);
            currentStage.close();
            addEntryAgendaUIStage.show();

            addEntryAgendaUIStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Alert alerta = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, "Add Entry to Agenda UI",
                            "Action confirmation.", "Do you wish to close the app?");
                    if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                        event.consume();
                    }
                }
            });
            addEntryAgendaUIStage.show();
        }catch (IOException ex) {
            AlertUI.createAlert(Alert.AlertType.ERROR, "Add Entry to Agenda UI",
                    "Problems opening Add Entry to Agenda UI.", ex.getMessage()).show();
        }
    }

    /**
     * Opens the Assign Team to Agenda Entry user interface.
     *
     * @param actionEvent The action event that triggered this method.
     */
    @FXML
    public void openAssignTeam(ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/assignTeam.fxml"));
            Parent assignTeamUI = loader.load();

            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Stage assignTeamUIStage = new Stage();
            assignTeamUIStage.setTitle("Assign Team to Agenda Entry UI");
            Scene scene = new Scene(assignTeamUI);
            assignTeamUIStage.setScene(scene);
            currentStage.close();
            assignTeamUIStage.show();

            assignTeamUIStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Alert alerta = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, "Assign Team to Agenda Entry UI",
                            "Action confirmation.", "Do you wish to close the app?");
                    if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                        event.consume();
                    }
                }
            });
            assignTeamUIStage.show();
        }catch (IOException ex) {
            AlertUI.createAlert(Alert.AlertType.ERROR, "Assign Team to Agenda Entry UI",
                    "Problems opening Assign Team to Agenda Entry UI.", ex.getMessage()).show();
        }
    }
}
