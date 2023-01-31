package nuricanozturk.dev.passwordmanager.decrypt.passwordmanagerdecrypt.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import nuricanozturk.dev.passwordmanager.decrypt.passwordmanagerdecrypt.entity.Application;
import nuricanozturk.dev.passwordmanager.decrypt.passwordmanagerdecrypt.service.DecryptionService;
@SuppressWarnings("all")
public class DecryptionController
{
    @FXML
    private TextField keyTextField;
    @FXML
    private TableView<Application> tableView;
    @FXML
    private TableColumn<Application, String> appNameTableColumn;
    @FXML
    private TableColumn<Application, String> appUserNameTableColumn;
    @FXML
    private TableColumn<Application, String> appPasswordTableColumn;
    private final DecryptionService m_service = DecryptionService.getInstance();
    private String path;
    @FXML
    private void initialize()
    {
        appNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("App_name"));
        appUserNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("App_username"));
        appPasswordTableColumn.setCellValueFactory(new PropertyValueFactory<>("App_password"));
    }

    @FXML
    private void clickFileChooser()
    {
        try
        {
            var fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(".can", "*.can"));
            var selectedFile = fileChooser.showOpenDialog(new Stage());
            var path = selectedFile.getAbsolutePath();


            if (path != null && !path.isEmpty() && !path.isBlank())
                this.path = path;

        }
        catch (Throwable ignore){}
    }

    @FXML
    private void clickDecryption()
    {
        var key = keyTextField.getText().trim().replace(" ", "");
        var filePath = path;

        m_service.decryption(key, filePath, tableView);
    }
}
