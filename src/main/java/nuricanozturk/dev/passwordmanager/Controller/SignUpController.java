package nuricanozturk.dev.passwordmanager.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nuricanozturk.dev.passwordmanager.Util.SceneNames;
import nuricanozturk.dev.passwordmanager.Util.Util;
import nuricanozturk.dev.passwordmanager.service.SignUpService;

import java.io.IOException;

public class SignUpController
{
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    private SignUpService service;

    public void setService(SignUpService service)
    {
        this.service = service;
    }
    @FXML
    private void clickSignUp() throws IOException {
        var name = nameTextField.getText();
        var surname = surnameTextField.getText();
        var email = emailTextField.getText();
        var username = usernameTextField.getText();
        var password = passwordTextField.getText();

        service.signUp(name, surname, email, username, password);

        clear();
    }

    private void clear()
    {
        nameTextField.setText(null);
        surnameTextField.setText(null);
        emailTextField.setText(null);
        usernameTextField.setText(null);
        passwordTextField.setText(null);
    }
    @FXML
    private void clickBack() throws IOException
    {
        Util.changeScene(((Stage)anchorPane.getScene().getWindow()), SceneNames.LOGIN_SCREEN);
    }
}
