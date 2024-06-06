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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class LoginUI {
    @FXML
    private TextField id;
    @FXML
    private PasswordField pwd;

    private final AuthenticationController ctrl;

    public LoginUI()
    {
        ctrl = new AuthenticationController();
    }

    @FXML
    public void doLogin(ActionEvent actionEvent) {
        boolean success = login();

        if (success) {
            List<UserRoleDTO> roles = this.ctrl.getUserRoles();
            if ((roles == null) || (roles.isEmpty())) {
                AlertUI.createAlert(Alert.AlertType.WARNING, "Login UI",
                        "Login failed.", "No role assigned to user..").show();
            } else {
                UserRoleDTO role = selectsRole(roles);
                if (!Objects.isNull(role)) {
                    this.redirectToRoleUI(role, actionEvent);
                } else {
                    AlertUI.createAlert(Alert.AlertType.WARNING, "Login UI",
                            "Login failed.", "No role selected.").show();
                }
            }
        }
    }

    @FXML
    public void goBack(ActionEvent actionEvent) {
        try{
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
                    "Problems starting the application.", ex.getMessage()).show();
        }
    }

    private boolean login() {
        String email = id.getText();
        String password = pwd.getText();

        boolean success = ctrl.doLogin(email, password);

        if (!success) {
            AlertUI.createAlert(Alert.AlertType.WARNING, "Login UI",
                    "Login failed.", "User isn't registered in the system.").show();
        }

        return success;
    }

    private UserRoleDTO selectsRole(List<UserRoleDTO> roles) {
        if (roles.size() == 1)
            return roles.get(0);
        else
            return (UserRoleDTO) Utils.showAndSelectOne(roles, "Select the role you want to adopt in this session:");
    }

    private void redirectToRoleUI(UserRoleDTO role, ActionEvent actionEvent) {
        if (role.getId().compareTo("GREEN_SPACES_MANAGER") == 0) {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/gsmMenu.fxml"));
                Parent gsmMenuUI = loader.load();

                Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Stage gsmMenuUIStage = new Stage();
                gsmMenuUIStage.setTitle("Green Spaces Manager UI");
                Scene scene = new Scene(gsmMenuUI);
                gsmMenuUIStage.setScene(scene);
                currentStage.close();
                gsmMenuUIStage.show();

                gsmMenuUIStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Alert alerta = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, "Green Spaces Manager UI",
                                "Action confirmation.", "Do you wish to close the app?");
                        if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                            event.consume();
                        }
                    }
                });
                gsmMenuUIStage.show();
            }catch (IOException ex) {
                AlertUI.createAlert(Alert.AlertType.ERROR, "Green Spaces Manager UI",
                        "Problems starting Green Spaces Manager UI.", ex.getMessage()).show();
            }
        }
        else {
            AlertUI.createAlert(Alert.AlertType.WARNING, "Login UI",
                    "Couldn't get UI", "Role without UI available.").show();
        }
    }
}
