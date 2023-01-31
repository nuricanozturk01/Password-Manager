package nuricanozturk.dev.passwordmanager.service;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import nuricanozturk.dev.PasswordGenerator.PasswordGenerator;
import nuricanozturk.dev.io.FileUtil;
import nuricanozturk.dev.passwordmanager.App;
import nuricanozturk.dev.passwordmanager.Controller.ApplicationAddController;
import nuricanozturk.dev.passwordmanager.Controller.ApplicationUpdateController;
import nuricanozturk.dev.passwordmanager.Controller.MainScreenController;
import nuricanozturk.dev.passwordmanager.Controller.PasswordAuthenticationController;
import nuricanozturk.dev.passwordmanager.DataAccessLayer.Database;
import nuricanozturk.dev.passwordmanager.Entity.Application;
import nuricanozturk.dev.passwordmanager.Entity.User;
import nuricanozturk.dev.passwordmanager.Util.AES256;
import nuricanozturk.dev.passwordmanager.Util.MailService;
import nuricanozturk.dev.passwordmanager.Util.SceneNames;
import nuricanozturk.dev.passwordmanager.Util.Util;
import nuricanozturk.dev.passwordmanager.mapper.IExportMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class MainScreenService
{
    private static MainScreenService m_mainScreenService;
    private MainScreenController controller;
    private IExportMapper m_exportMapper;

    private MainScreenService() {}

    public static MainScreenService getInstance()
    {
        return m_mainScreenService = m_mainScreenService == null ? new MainScreenService() : m_mainScreenService;
    }

    public void setController(MainScreenController controller) {
        this.controller = controller;
    }
    public void setExportMapper(IExportMapper mapper) {
        m_exportMapper = mapper;
    }


    public void showLogs(User user) throws IOException
    {
        var logService = LogService.getInstance();

        logService.showLogs(user);
    }

    public void deleteApplication(Application selectedItem, TableView<Application> table)
    {
        AppCRUDService.getInstance().deleteApplication(selectedItem);
        table.getItems().remove(selectedItem);
    }

    public void updateApplication(Application selectedItem, TableView<Application> apps, User user) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(SceneNames.UPDATE_SCREEN.getSceneName()));
        Util.initStage("Update Application", new Stage(), new Scene(loader.load()), false, null);
        ApplicationUpdateController controller = loader.getController();
        controller.setExistingApplication(selectedItem);
        AppCRUDService.getInstance().setUser(user);
        controller.setTable(apps);
    }

    public void addApplication(TableView<Application> table, User user) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(SceneNames.ADD_SCREEN.getSceneName()));
        Util.initStage("Add Application", new Stage(), new Scene(loader.load()), false, null);
        ApplicationAddController controller = loader.getController();
        controller.setTable(table);

        AppCRUDService.getInstance().setUser(user);
    }

    public void isValidUser(User user) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(SceneNames.PASSWORD_AUTHENTICATION_SCREEN.getSceneName()));
        Util.initStage("Password Authentication", new Stage(), new Scene(loader.load()), false, null);
        PasswordAuthenticationController controller = loader.getController();
        controller.setUser(user);
        controller.setMainScreenController(this.controller);
    }

    public void search(KeyEvent keyEvent, TableView<Application> tableView)
    {
        var list = tableView.getItems().stream().toList();

        var searchedList = list.stream()
                .filter(key -> key.getApp_name().toLowerCase().contains(keyEvent.getText().toLowerCase()))
                .toList();

        tableView.setItems(FXCollections.observableArrayList(searchedList));
    }

    private PasswordGenerator getAutoPasswordGenerator(int auto)
    {
        return new PasswordGenerator
                .Builder()
                .setExceptionState((msg) -> Util.alertScreen(Alert.AlertType.ERROR, msg, ButtonType.OK))
                .isRandom(true, auto).build();
    }

    private PasswordGenerator getCustomizedPasswordGenerator(int lowerCase, int upperCase, int numbers, int special)
    {
       return new PasswordGenerator
                .Builder()
                .setExceptionState((msg) -> Util.alertScreen(Alert.AlertType.ERROR, msg, ButtonType.OK))
                .setSpecialCharacter(special)
                .setUpperCase(upperCase)
                .setLowerCase(lowerCase)
                .setNumbers(numbers)
                .build();
    }
    public String generatePassword(int lowerCase, int upperCase, int numbers, int special, int auto)
    {
        var pg = auto != 0 ? getAutoPasswordGenerator(auto) : getCustomizedPasswordGenerator(lowerCase, upperCase, numbers, special);

        return pg.generate();
    }

    public void initializeTable(TableView<Application> tableView, User user)
    {
        new Thread(() ->
        {
            var secretKey = AES256.generateSecretKey(user.getUsername());

            var decrypt = new AES256(secretKey);

            var apps = Database.getApplications(user.getUser_id());

            if (apps != null)
                apps.forEach(app -> app.setApp_password(decrypt.decrypt(app.getApp_password())));

            tableView.setItems(FXCollections.observableArrayList(apps));
        }
        ).start();
    }

    public void export(User user)
    {

        var file = Util.fileChoose(user.getUsername(), "can", ".can");

        var apps = Database.getApplications(user.getUser_id());
        var secretKey = getCustomizedPasswordGenerator(13,0,7,0).generate();
        var encrypt = new AES256(secretKey);

        if (apps == null)
        {
            Util.alertScreen(Alert.AlertType.ERROR, "Your List is Empty!", ButtonType.OK);
            return;
        }

        var thread = new Thread(() -> execute(user, apps, encrypt, file, secretKey));
        thread.start();

        Util.alertScreen(Alert.AlertType.INFORMATION, "Your Secret Key on email!!", ButtonType.OK);
    }

    private void execute(User user, List<Application> apps, AES256 encrypt, File file, String secretKey)
    {
        var dec = new AES256(AES256.generateSecretKey(user.getUsername()));
        apps.forEach(a -> a.setApp_password(dec.decrypt(a.getApp_password())));

        apps.forEach(app -> encryption(app, encrypt));


        var exportFormatApps = m_exportMapper.toApplicationExportList(apps.stream().map(m_exportMapper::toExportFormat).toList());

        FileUtil.write(exportFormatApps.exportApps, file.getAbsolutePath(), true);

        new Thread(() -> MailService.sendMessage(user.getEmail(), "Secret Key!", "Your Secret key: " + secretKey)).start();
    }

    private void encryption(Application application, AES256 encrypt)
    {
        application.setApp_name(encrypt.encrypt(application.getApp_name()));
        application.setApp_username(encrypt.encrypt(application.getApp_username()));
        application.setApp_password(encrypt.encrypt(application.getApp_password()));
    }

    public void showSpecificPasswordInfo(Application app, TableView<Application> tableView, User user, int focusedIndex)
    {
        throw new UnsupportedOperationException("NOT IMPLEMENTED YET");
    }
}
