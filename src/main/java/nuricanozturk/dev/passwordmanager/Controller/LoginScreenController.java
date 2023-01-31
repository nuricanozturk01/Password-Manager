package nuricanozturk.dev.passwordmanager.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nuricanozturk.dev.passwordmanager.App;
import nuricanozturk.dev.passwordmanager.Util.*;
import nuricanozturk.dev.passwordmanager.service.LoginService;
import nuricanozturk.dev.passwordmanager.service.SignUpService;

import java.io.IOException;

public class LoginScreenController
{
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    private LoginService loginService;
    @FXML
    private void initialize()
    {
        loginService = LoginService.getInstance();
    }
    @FXML
    private void clickLogin() throws IOException
    {
        var username = usernameTextField.getText();
        var password = Hash.hashing(passwordTextField.getText());

        var user = loginService.login(username, password, anchorPane);

        if(user != null)
        {
            var loader = new FXMLLoader(App.class.getResource(SceneNames.MAIN_SCREEN.getSceneName()));
            var scene = new Scene(loader.load());

            MainScreenController controller = loader.getController();
            controller.setUser(user);

            ((Stage) anchorPane.getScene().getWindow()).setScene(scene);
        }
    }
    @FXML
    private void clickForgotPassword() throws IOException
    {
        var loader = new FXMLLoader(App.class.getResource(SceneNames.PASSWORD_CHANGE_SCREEN.getSceneName()));
        Util.initStage("Change Password", new Stage(), new Scene(loader.load()), false, null);

    }
    @FXML
    private void clickSignUp() throws IOException
    {
        var loader = new FXMLLoader(App.class.getResource(SceneNames.SIGN_UP_SCREEN.getSceneName()));
        var scene = new Scene(loader.load());
        SignUpController controller = loader.getController();
        controller.setService(SignUpService.getInstance());

        ((Stage) anchorPane.getScene().getWindow()).setScene(scene);
    }

}
