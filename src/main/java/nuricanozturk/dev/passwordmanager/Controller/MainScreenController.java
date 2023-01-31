package nuricanozturk.dev.passwordmanager.Controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nuricanozturk.dev.passwordmanager.DataAccessLayer.Database;
import nuricanozturk.dev.passwordmanager.Entity.Application;
import nuricanozturk.dev.passwordmanager.Entity.User;
import nuricanozturk.dev.passwordmanager.Util.AES256;
import nuricanozturk.dev.passwordmanager.Util.SceneNames;
import nuricanozturk.dev.passwordmanager.Util.Util;
import nuricanozturk.dev.passwordmanager.mapper.ExportMapper;
import nuricanozturk.dev.passwordmanager.service.MainScreenService;
import java.io.IOException;
public class MainScreenController
{
    @FXML
    private CheckMenuItem showPasswordCheckMenuItem;
    @FXML
    private CheckMenuItem hidePasswordCheckMenuItem;
    @FXML
    private TableColumn<Application, String> appNameColumn;
    @FXML
    private TableColumn<Application, String> usernameColumn;
    @FXML
    private TableColumn<Application, String> passwordColumn;
    @FXML
    private TableView<Application> tableView;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField passwordTextField;
    @FXML
    private RadioButton specialRadioButton;
    @FXML
    private RadioButton zero_nineRadioButton;
    @FXML
    private RadioButton A_ZRadioButton;
    @FXML
    private RadioButton a_zRadioButton;
    @FXML
    private RadioButton autoRadioButton;
    @FXML
    private TextField a_zTextField;
    @FXML
    private TextField zero_nineTextField;
    @FXML
    private TextField A_ZTextField;
    @FXML
    private TextField autoTextField;
    @FXML
    private TextField specialTextField;
    @FXML
    private TextField searchTextField;
    private User user;
    private MainScreenService mainService;
    private AES256 crypt;

    public static boolean IS_SHOW = false;
    public static boolean IS_HIDE = true;
    private void initialize()
    {
        var secretKey = AES256.generateSecretKey(user.getUsername());
        crypt = new AES256(secretKey);

        mainService = MainScreenService.getInstance();
        //mainService.setUser(user);
        mainService.setController(this); // Bad
        mainService.setExportMapper(new ExportMapper());

        appNameColumn.setCellValueFactory(new PropertyValueFactory<>("App_name"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("App_username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("App_password"));
        passwordColumn.setCellFactory((column) -> getHidePasswords());
        initTable();
    }
    private TableCell<Application, String> getHidePasswords()
    {
        return new TableCell<>()
        {
            @Override
            protected void updateItem(String item, boolean empty)
            {
                super.updateItem(Util.PASSWORD_MASK, empty);
                setText(empty ? null : Util.PASSWORD_MASK);
            }
        };
    }

    private TableCell<Application, String> getShowPasswords()
    {
        return new TableCell<>()
        {
            @Override
            protected void updateItem(String item, boolean empty)
            {


                super.updateItem(item, empty);
                setText(empty ? null : item);
            }
        };
    }

    public void setUser(User user)
    {
        this.user = user;
        initialize();
    }
    private void initTable()
    {
        mainService.initializeTable(tableView, user);
    }
    @FXML
    private void clickLogMenuItem() throws IOException
    {
        mainService.showLogs(user);
    }
    @FXML
    private void clickExitMenuItem() throws IOException
    {
        user = null;
        Util.changeScene(((Stage)anchorPane.getScene().getWindow()), SceneNames.LOGIN_SCREEN);
    }


    @FXML
    private void clickExport() throws IOException
    {
        mainService.export(user);
    }

    @FXML
    private void clickAddMenuItem() throws IOException
    {
        mainService.addApplication(tableView, user);
    }



    @FXML
    private void clickShowSpecPassword() throws IOException
    {
        var selectedItem = tableView.getSelectionModel().getSelectedItem();

        if (selectedItem != null)
            mainService.showSpecificPasswordInfo(selectedItem, tableView, user, tableView.getSelectionModel().getSelectedIndex());
    }


    @FXML
    private void clickUpdateMenuItem() throws IOException
    {
        var selectedItem = tableView.getSelectionModel().getSelectedItem();

        if (selectedItem != null)
            mainService.updateApplication(selectedItem, tableView, user);
    }
    @FXML
    private void clickDeleteMenuItem()
    {
        var selectedItem = tableView.getSelectionModel().getSelectedItem();

        if (selectedItem != null)
            mainService.deleteApplication(selectedItem, tableView);
    }
    @FXML
    private void clickShowPasswordCheckMenuItem() throws IOException
    {
        if (hidePasswordCheckMenuItem.isSelected())
            hidePasswordCheckMenuItem.setSelected(false);

        if (!showPasswordCheckMenuItem.isSelected())
            showPasswordCheckMenuItem.setSelected(true);

        mainService.isValidUser(user);
    }
    public void showPasswords()
    {
        passwordColumn.setCellFactory((column) -> getShowPasswords());

        tableView.refresh();
    }
    @FXML
    private void clickHidePasswordCheckMenuItem()
    {
        if (!hidePasswordCheckMenuItem.isSelected())
            hidePasswordCheckMenuItem.setSelected(true);

        if (showPasswordCheckMenuItem.isSelected())
            showPasswordCheckMenuItem.setSelected(false);

        passwordColumn.setCellFactory((column) -> getHidePasswords());
    }
    @FXML
    private void pressSearchTextField(KeyEvent keyEvent)
    {
        if (keyEvent.getCode() == KeyCode.BACK_SPACE)
            initTable();

        mainService.search(keyEvent, tableView);
    }
    @FXML
    private void clickClearBtn()
    {
        passwordTextField.setText(null);
        a_zRadioButton.setSelected(false);
        A_ZRadioButton.setSelected(false);
        specialRadioButton.setSelected(false);
        zero_nineRadioButton.setSelected(false);
        autoRadioButton.setSelected(false);
        a_zTextField.setText(null);
        A_ZTextField.setText(null);
        specialTextField.setText(null);
        zero_nineTextField.setText(null);
        autoTextField.setText(null);
        searchTextField.setText(null);
    }
    @FXML
    private void clickGenerateButton()
    {
        try
        {
            int az = 0, AZ = 0, special = 0, numbers = 0, auto = 0;

            if (a_zRadioButton.isSelected())
                az = Integer.parseInt(a_zTextField.getText());

            if (A_ZRadioButton.isSelected())
                AZ = Integer.parseInt(A_ZTextField.getText());

            if (specialRadioButton.isSelected())
                special = Integer.parseInt(specialTextField.getText());

            if (zero_nineRadioButton.isSelected())
                numbers = Integer.parseInt(zero_nineTextField.getText());

            if (autoRadioButton.isSelected())
                auto = Integer.parseInt(autoTextField.getText());

            var password = mainService.generatePassword(az, AZ, numbers, special, auto);

            passwordTextField.setText(password);

        }
        catch (NumberFormatException ex)
        {
            Util.alertScreen(Alert.AlertType.ERROR, "Please enter the number!", ButtonType.OK);
        }

    }
}
