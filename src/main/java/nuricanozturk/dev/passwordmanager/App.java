package nuricanozturk.dev.passwordmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nuricanozturk.dev.passwordmanager.Util.Util;

import java.io.IOException;

public class App extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login_screen.fxml"));
        Util.initStage("Password Manager v2.0", stage, new Scene(fxmlLoader.load()),false, null);
    }

    public static void main(String[] args) {
        launch();
    }
}