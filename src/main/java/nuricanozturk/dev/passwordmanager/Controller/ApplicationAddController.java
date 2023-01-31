package nuricanozturk.dev.passwordmanager.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import nuricanozturk.dev.passwordmanager.Entity.Application;
import nuricanozturk.dev.passwordmanager.service.AppCRUDService;

public class ApplicationAddController
{
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    private AppCRUDService service = AppCRUDService.getInstance();
    private TableView<Application> table;

    public AppCRUDService getService() {
        return service;
    }
    public void setTable(TableView<Application> table)
    {
        this.table = table;
    }
    @FXML
    private void clickSaveButton()
    {
        var name = nameTextField.getText();
        var username = usernameTextField.getText();
        var password = passwordField.getText();

        var app = service.addApplication(name, username, password);

        table.getItems().add(app);
    }
}
