package nuricanozturk.dev.passwordmanager.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import nuricanozturk.dev.passwordmanager.Util.Util;

public class UserAlreadyExistsException extends RuntimeException
{
    public UserAlreadyExistsException()
    {
        Util.alertScreen(Alert.AlertType.ERROR, "Invalid Username or Password", ButtonType.OK);
    }
}
