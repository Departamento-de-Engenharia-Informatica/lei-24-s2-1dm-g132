package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pt.ipp.isep.dei.esoft.project.application.controller.AddEntryToDoController;
import pt.ipp.isep.dei.esoft.project.mapper.dto.GSTaskDTO;
import pt.ipp.isep.dei.esoft.project.mapper.dto.GreenSpaceDTO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller class for the Add Entry to To-Do List user interface.
 */
public class AddEntryToDoUI implements Initializable {

    /**
     * The controller responsible for managing the addition of tasks to the To-Do list.
     */
    private final AddEntryToDoController ctrl;

    /**
     * Label to display information about the selected green space.
     */
    @FXML
    private Label greenSpaceInfo;

    /**
     * Text area for entering the description of the task.
     */
    @FXML
    private TextArea description;

    /**
     * Text field for entering the title of the task.
     */
    @FXML
    private TextField title;

    /**
     * Choice box for selecting the degree of urgency of the task.
     */
    @FXML
    private ChoiceBox<String> degreeOfUrgency;

    /**
     * Choice box for selecting the green space associated with the task.
     */
    @FXML
    private ChoiceBox<String> greenSpace;

    /**
     * Text field for entering the expected duration of the task.
     */
    @FXML
    private TextField duration;

    /**
     * Index of the selected green space in the choice box.
     */
    private int selectedGreenSpaceIndex = -1;

    /**
     * Constructor for the AddEntryToDoUI class.
     * Initializes the controller.
     */
    public AddEntryToDoUI ()
    {
        ctrl = new AddEntryToDoController();
    }

    /**
     * Handles the action event for canceling the task entry and returning to the GSM menu.
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
     * Handles the action event for submitting the task entry.
     *
     * @param actionEvent The action event that triggered this method.
     */
    @FXML
    public void submit(ActionEvent actionEvent) {
        try {
            String taskTitle = title.getText();
            String taskDescription = description.getText();
            String taskDegreeOfUrgency = degreeOfUrgency.getValue();
            int taskExpectedDuration = Integer.parseInt(duration.getText());
            String gsTitle = greenSpace.getValue();
            ctrl.getSelectedGreenSpace(selectedGreenSpaceIndex);

            GSTaskDTO gsTaskDTO = new GSTaskDTO(taskTitle, taskDescription, taskDegreeOfUrgency, taskExpectedDuration);

            boolean result = ctrl.addEntry(gsTaskDTO);
            if (!result)
            {
                AlertUI.createAlert(Alert.AlertType.ERROR, "Add Entry to To-Do List UI",
                        "Problems saving task.", "Task wasn't stored correctly.").show();
            }
            else
            {
                AlertUI.createAlert(Alert.AlertType.INFORMATION, "Add Entry to To-Do List UI",
                        "Operation success.", "Task registered successfully.").showAndWait();

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

        }catch (IllegalArgumentException ex)
        {
            AlertUI.createAlert(Alert.AlertType.ERROR, "Add Entry to To-Do List UI",
                    "Invalid input value.", ex.getMessage()).show();
        } catch (RuntimeException ex)
        {
            AlertUI.createAlert(Alert.AlertType.ERROR, "Add Entry to To-Do List UI",
                    "Choice box empty.", "Please select values in all choice boxes.").show();
        }
    }

    /**
     * Initializes the choice boxes and populates the green space choice box with available options.
     *
     * @param url            The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources specific to this controller.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        degreeOfUrgency.getItems().add("Low");
        degreeOfUrgency.getItems().add("Medium");
        degreeOfUrgency.getItems().add("High");

        List<GreenSpaceDTO> greenSpacesList = ctrl.getGreenSpaces();

        for(GreenSpaceDTO greenSpaceDTO : greenSpacesList)
        {
            greenSpace.getItems().add(greenSpaceDTO.getName());
        }

        greenSpace.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selectedGreenSpaceIndex = greenSpace.getSelectionModel().getSelectedIndex();
                if (selectedGreenSpaceIndex >= 0 && selectedGreenSpaceIndex < greenSpacesList.size()) {
                    GreenSpaceDTO selectedGreenSpace = greenSpacesList.get(selectedGreenSpaceIndex);
                    greenSpaceInfo.setText("Selected Green Space additional information:\n" +
                            "Address: " + selectedGreenSpace.getAddress() + "\n" +
                            "Area: " + selectedGreenSpace.getArea() + " hectares\n" +
                            "Type: " + selectedGreenSpace.getType());
                } else {
                    greenSpaceInfo.setText("No green space selected");
                }
            }
        });
    }
}
