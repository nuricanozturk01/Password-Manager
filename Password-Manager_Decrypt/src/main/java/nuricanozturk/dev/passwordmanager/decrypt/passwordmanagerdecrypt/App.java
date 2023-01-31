package nuricanozturk.dev.passwordmanager.decrypt.passwordmanagerdecrypt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nuricanozturk.dev.passwordmanager.decrypt.passwordmanagerdecrypt.Util.SceneNames;
import nuricanozturk.dev.passwordmanager.decrypt.passwordmanagerdecrypt.Util.Util;

import java.io.IOException;

public class App extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        var fxmlLoader = new FXMLLoader(App.class.getResource(SceneNames.MAIN_SCREEN.getSceneName()));

        Util.initStage("Decryption", stage, new Scene(fxmlLoader.load()), false, null);
    }

    public static void main(String[] args)
    {
        launch();
    }
}