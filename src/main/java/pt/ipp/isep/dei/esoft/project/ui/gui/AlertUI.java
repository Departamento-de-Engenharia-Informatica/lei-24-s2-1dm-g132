package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

/**
 * Utility class for creating customized JavaFX Alert dialogs.
 */
public class AlertUI {

    /**
     * Creates a new Alert dialog with the specified alert type, title, header, and message.
     *
     * @param alertType The type of the alert (e.g., ERROR, INFORMATION, CONFIRMATION).
     * @param title     The title of the alert dialog.
     * @param header    The header text of the alert dialog.
     * @param message   The main content text of the alert dialog.
     * @return An instance of Alert configured with the specified parameters.
     */
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
