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

public class RegisterGreenSpaceUI implements Initializable {

    private final RegisterGreenSpaceController ctrl;

    @FXML
    private TextField area;
    @FXML
    private TextField address;
    @FXML
    private TextField name;
    @FXML
    private ChoiceBox<String> type;

    public RegisterGreenSpaceUI()
    {
        ctrl = new RegisterGreenSpaceController();
    }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        type.getItems().add("Garden");
        type.getItems().add("Medium Sized Park");
        type.getItems().add("Large Sized Park");
    }
}
