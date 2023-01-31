package nuricanozturk.dev.passwordmanager.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import nuricanozturk.dev.passwordmanager.Util.Util;

public class InvalidArgumentInFieldException extends RuntimeException
{
    public InvalidArgumentInFieldException(String message)
    {
        super(message);
        Util.alertScreen(Alert.AlertType.ERROR, message, ButtonType.OK);
    }
}
