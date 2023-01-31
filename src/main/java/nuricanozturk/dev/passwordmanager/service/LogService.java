package nuricanozturk.dev.passwordmanager.service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nuricanozturk.dev.passwordmanager.App;
import nuricanozturk.dev.passwordmanager.Controller.LogScreenController;
import nuricanozturk.dev.passwordmanager.DataAccessLayer.Database;
import nuricanozturk.dev.passwordmanager.Entity.User;
import nuricanozturk.dev.passwordmanager.Util.SceneNames;
import nuricanozturk.dev.passwordmanager.Util.Util;

import java.io.IOException;

public class LogService
{
    private static LogService m_logService = new LogService();

    private LogService(){}

    public static LogService getInstance()
    {
        return m_logService = m_logService == null ? new LogService() : m_logService;
    }

    public void showLogs(User user) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(SceneNames.LOG_SCREEN.getSceneName()));
        Util.initStage("Access Logs", new Stage(), new Scene(fxmlLoader.load()),false, null);
        LogScreenController controller = fxmlLoader.getController();

        var logs = Database.getLogs(user.getUser_id());

        controller.setLogs(logs);
    }
}
