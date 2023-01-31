package nuricanozturk.dev.passwordmanager.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import nuricanozturk.dev.passwordmanager.Util.Util;

public class CodesNotMatchException extends RuntimeException
{
    public CodesNotMatchException()
    {
        Util.alertScreen(Alert.AlertType.ERROR, "Email Codes Not Match!", ButtonType.OK);
    }
}
