package nuricanozturk.dev.passwordmanager.decrypt.passwordmanagerdecrypt.Util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;
import javafx.stage.Stage;


public final class Util
{
    public static void initStage(String title, Stage stage, Scene scene, boolean resizable, String icon)
    {
        stage.setTitle(title);
        //stage.getIcons().add(new Image(icon));
        stage.setScene(scene);
        stage.setResizable(resizable);
        stage.show();
    }
    public static void alertScreen(Alert.AlertType type, String msg, ButtonType btn)
    {
        var alert = new Alert(type, msg, btn);
        //((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add();
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
    }

}
