package nuricanozturk.dev.passwordmanager.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import nuricanozturk.dev.passwordmanager.Util.Util;

public class PasswordsNotMatchException extends RuntimeException
{
    public PasswordsNotMatchException() {
        Util.alertScreen(Alert.AlertType.ERROR, "Passwords Not Matched!", ButtonType.OK);
    }
}
