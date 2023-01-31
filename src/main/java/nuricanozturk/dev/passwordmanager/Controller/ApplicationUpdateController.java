package nuricanozturk.dev.passwordmanager.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import nuricanozturk.dev.passwordmanager.Entity.Application;
import nuricanozturk.dev.passwordmanager.Entity.User;
import nuricanozturk.dev.passwordmanager.Util.AES256;
import nuricanozturk.dev.passwordmanager.service.AppCRUDService;

public class ApplicationUpdateController
{
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    private final AppCRUDService service = AppCRUDService.getInstance();
    private Application existingApplication;
    private TableView<Application> apps;

    private void init()
    {
        nameTextField.setText(existingApplication.getApp_name());
        usernameTextField.setText(existingApplication.getApp_username());
        passwordField.setText(existingApplication.getApp_password());
    }

    public void setTable(TableView<Application> apps) {
        this.apps = apps;
    }

    public void setExistingApplication(Application existingApplication)
    {
        this.existingApplication = existingApplication;
        init();
    }


    public AppCRUDService getService() {
        return service;
    }

    @FXML
    private void clickUpdateButton()
    {
        var name = nameTextField.getText();
        var username = usernameTextField.getText();
        var password = passwordField.getText();

        service.updateApplication(existingApplication, name, username, password, apps);
    }

}
