package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.mapper.dto.GreenSpaceDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the Register Green Space user interface.
 */
public class RegisterGreenSpaceUI implements Initializable {

    /**
     * The controller responsible for managing the registration of green spaces.
     */
    private final RegisterGreenSpaceController ctrl;

    /**
     * Text field for entering the area of the green space.
     */
    @FXML
    private TextField area;

    /**
     * Text field for entering the address of the green space.
     */
    @FXML
    private TextField address;

    /**
     * Text field for entering the name of the green space.
     */
    @FXML
    private TextField name;

    /**
     * Choice box for selecting the type of the green space.
     */
    @FXML
    private ChoiceBox<String> type;

    /**
     * Constructor for RegisterGreenSpaceUI class.
     * Initializes the controller.
     */
    public RegisterGreenSpaceUI()
    {
        ctrl = new RegisterGreenSpaceController();
    }

    /**
     * Handles the action event for canceling the registration and returning to the GSM menu.
     *
     * @param actionEvent The action event that triggered this method.
     */
    @FXML
    public void cancel(ActionEvent actionEvent) {
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

    /**
     * Handles the action event for submitting the green space registration.
     *
     * @param actionEvent The action event that triggered this method.
     */
    @FXML
    public void submit(ActionEvent actionEvent) {
        try {
            String gsName = name.getText();
            String gsAddress = address.getText();
            int gsArea = Integer.parseInt(area.getText());
            String gsType = type.getValue();

            GreenSpaceDTO greenSpaceDTO = new GreenSpaceDTO(gsName, gsAddress, gsArea, gsType);
            boolean result = ctrl.registerGreenSpace(greenSpaceDTO);

            if (!result)
            {
                AlertUI.createAlert(Alert.AlertType.ERROR, "Register Green Space UI",
                        "Problems saving green space.", "Green space wasn't stored correctly.").show();
            }
            else
            {
                AlertUI.createAlert(Alert.AlertType.INFORMATION, "Register Green Space UI",
                        "Operation success.", "Green space registered successfully.").showAndWait();

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
        } catch (IllegalArgumentException ex)
        {
            AlertUI.createAlert(Alert.AlertType.ERROR, "Register Green Space UI",
                    "Invalid input value.", ex.getMessage()).show();
        } catch (RuntimeException ex)
        {
            AlertUI.createAlert(Alert.AlertType.ERROR, "Register Green Space UI",
                    "Choice box empty.", "Please select values in all choice boxes.").show();
        }
    }

    /**
     * Initializes the choice box with predefined options for green space types.
     *
     * @param url            The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources specific to this controller.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        type.getItems().add("Garden");
        type.getItems().add("Medium Sized Park");
        type.getItems().add("Large Sized Park");
    }
}
