package nuricanozturk.dev.passwordmanager.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import nuricanozturk.dev.passwordmanager.Util.Util;

public class AuthenticationException extends RuntimeException
{
    public AuthenticationException()
    {
        Util.alertScreen(Alert.AlertType.ERROR, "Password is wrong!...", ButtonType.OK);
    }
}
