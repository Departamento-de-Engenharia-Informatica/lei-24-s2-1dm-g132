package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class AlertUI {
    public static Alert createAlert(Alert.AlertType alertType, String title, String header, String message) {
        Alert alerta = new Alert(alertType);

        alerta.setTitle(title);
        alerta.setHeaderText(header);
        alerta.setContentText(message);

        if (alertType == Alert.AlertType.CONFIRMATION) {
            ((Button) alerta.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
            ((Button) alerta.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
        }

        return alerta;
    }
}
