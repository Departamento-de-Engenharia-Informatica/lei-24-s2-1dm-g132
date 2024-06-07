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
import pt.ipp.isep.dei.esoft.project.application.controller.AddEntryAgendaController;
import pt.ipp.isep.dei.esoft.project.mapper.dto.GSTaskDTO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddEntryAgendaUI implements Initializable {

    private final AddEntryAgendaController ctrl;

    @FXML
    private ChoiceBox<String> toDoListEntry;
    @FXML
    private Label toDoListEntryInfo;
    @FXML
    private TextField startingDate;

    private int selectedEntryIndex = -1;

    public AddEntryAgendaUI()
    {
        ctrl = new AddEntryAgendaController();
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
            String entryStartingDate = startingDate.getText();
            String entryToDo = toDoListEntry.getValue();

            ctrl.getSelectedTask(selectedEntryIndex);

            boolean result = ctrl.addEntry(entryStartingDate);
            if (!result)
            {
                AlertUI.createAlert(Alert.AlertType.ERROR, "Add Entry to Agenda UI",
                        "Problems updating task.", "Task wasn't stored correctly.").show();
            }
            else
            {
                AlertUI.createAlert(Alert.AlertType.INFORMATION, "Add Entry to Agenda UI",
                        "Operation success.", "Task updated successfully.").showAndWait();

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
            AlertUI.createAlert(Alert.AlertType.ERROR, "Add Entry to Agenda UI",
                    "Invalid input value.", ex.getMessage()).show();
        } catch (RuntimeException ex)
        {
            AlertUI.createAlert(Alert.AlertType.ERROR, "Add Entry to Agenda UI",
                    "Choice box empty.", "Please select values in all choice boxes.").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<GSTaskDTO> gsTaskDTOList = ctrl.getTodoListEntries();

        for(GSTaskDTO gsTaskDTO : gsTaskDTOList)
        {
            toDoListEntry.getItems().add(gsTaskDTO.getTitle());
        }

        toDoListEntry.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selectedEntryIndex = toDoListEntry.getSelectionModel().getSelectedIndex();
                if (selectedEntryIndex >= 0 && selectedEntryIndex < gsTaskDTOList.size()) {
                    GSTaskDTO selectedEntry = gsTaskDTOList.get(selectedEntryIndex);
                    toDoListEntryInfo.setText("Selected Entry additional information:\n" +
                            "Description: " + selectedEntry.getDescription() + "\n" +
                            "Degree of Urgency: " + selectedEntry.getDegreeOfUrgency() + "\n" +
                            "Type: " + selectedEntry.getExpectedDuration() + " working hours\n" +
                            "Green Space: " + selectedEntry.getGreenSpace().getName());
                } else {
                    toDoListEntryInfo.setText("No green space selected");
                }
            }
        });

    }
}
