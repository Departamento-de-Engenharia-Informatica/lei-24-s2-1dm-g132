package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/mainMenu.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            stage.setTitle("Green Spaces App");
            stage.setScene(scene);

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Alert alerta = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, "Green Spaces App",
                            "Action confirmation.", "Do you wish to close the app?");
                    if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                        event.consume();
                    }
                }
            });
            stage.show();
        }catch (IOException ex) {
            AlertUI.createAlert(Alert.AlertType.ERROR, "Green Spaces App",
                    "Problems starting the application.", ex.getMessage()).show();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
