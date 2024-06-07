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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignTeamController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.mapper.dto.GSTaskDTO;
import pt.ipp.isep.dei.esoft.project.mapper.dto.TeamDTO;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

public class AssignTeamUI implements Initializable {

    private final AssignTeamController ctrl;

    @FXML
    private ChoiceBox<String> team;
    @FXML
    private ChoiceBox<String> agendaEntry;
    @FXML
    private Label teamInfo;
    @FXML
    private Label agendaEntryInfo;

    private int selectedEntryIndex = -1;

    private int selectedTeamIndex = -1;

    public AssignTeamUI()
    {
        ctrl = new AssignTeamController();
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
            String entryAgenda = agendaEntry.getValue();
            String selectedTeam = team.getValue();

            ctrl.getSelectedTask(selectedEntryIndex);

            boolean result = ctrl.assignTeam(selectedTeamIndex);
            if (!result)
            {
                AlertUI.createAlert(Alert.AlertType.ERROR, "Assign Team to Agenda Entry UI",
                        "Problems updating task.", "Task wasn't stored correctly.").show();
            }
            else
            {
                AlertUI.createAlert(Alert.AlertType.INFORMATION, "Assign Team to Agenda Entry UI",
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
        }catch (UnsupportedOperationException ex)
        {
            AlertUI.createAlert(Alert.AlertType.ERROR, "Assign Team to Agenda Entry UI",
                    "Operation not supported.", ex.getMessage()).show();
        } catch (RuntimeException ex)
        {
            AlertUI.createAlert(Alert.AlertType.ERROR, "Assign Team to Agenda Entry UI",
                    "Choice box empty.", "Please select values in all choice boxes.").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<GSTaskDTO> gsTaskDTOList = ctrl.getAgendaEntries();

        for(GSTaskDTO gsTaskDTO : gsTaskDTOList)
        {
            agendaEntry.getItems().add(gsTaskDTO.getTitle());
        }

        List<TeamDTO> teamDTOList = ctrl.getTeams();

        String teamtitle = "Composed of:";

        for(TeamDTO teamDTO : teamDTOList)
        {
            for(Collaborator collaborator : teamDTO.getCollaborators())
            {
                teamtitle = String.format("%s %s,", teamtitle, collaborator.getName());
            }
            team.getItems().add(teamtitle);
        }

        agendaEntry.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selectedEntryIndex = agendaEntry.getSelectionModel().getSelectedIndex();
                if (selectedEntryIndex >= 0 && selectedEntryIndex < gsTaskDTOList.size()) {
                    GSTaskDTO selectedEntry = gsTaskDTOList.get(selectedEntryIndex);
                    String text = String.format(
                            "Selected Entry additional information:\n" +
                                    "Description: %s\n" +
                                    "Degree of Urgency: %s\n" +
                                    "Type: %d working hours\n" +
                                    "Green Space: %s\n" +
                                    "Starting Date: %d/%d/%d",
                            selectedEntry.getDescription(),
                            selectedEntry.getDegreeOfUrgency(),
                            selectedEntry.getExpectedDuration(),
                            selectedEntry.getGreenSpace().getName(),
                            selectedEntry.getStartingDate().get(Calendar.YEAR),
                            selectedEntry.getStartingDate().get(Calendar.MONTH) + 1,
                            selectedEntry.getStartingDate().get(Calendar.DAY_OF_MONTH)
                    );
                    agendaEntryInfo.setText(text);
                } else {
                    agendaEntryInfo.setText("No entry selected");
                }
            }
        });

        team.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selectedTeamIndex = team.getSelectionModel().getSelectedIndex();
                if (selectedTeamIndex >= 0 && selectedTeamIndex < teamDTOList.size()) {
                    TeamDTO selectedTeam = teamDTOList.get(selectedTeamIndex);

                    String printTeam = "Composed of:";
                    for(Collaborator collaborator : selectedTeam.getCollaborators())
                    {
                        printTeam = String.format("%s\n%s: %s", printTeam,  collaborator.getName(), collaborator.getSkills());
                    }
                    teamInfo.setText("Selected Team additional information:\n" + printTeam);
                } else {
                    teamInfo.setText("No team selected");
                }
            }
        });
    }
}
