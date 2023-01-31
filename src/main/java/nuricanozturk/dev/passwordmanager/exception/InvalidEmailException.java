package nuricanozturk.dev.passwordmanager.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import nuricanozturk.dev.passwordmanager.Util.Util;

public class InvalidEmailException extends RuntimeException
{

    public InvalidEmailException()
    {
        Util.alertScreen(Alert.AlertType.ERROR, "Email does not exists!", ButtonType.OK);
    }
}
