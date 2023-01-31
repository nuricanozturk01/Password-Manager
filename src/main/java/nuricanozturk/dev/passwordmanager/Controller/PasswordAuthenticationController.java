package nuricanozturk.dev.passwordmanager.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nuricanozturk.dev.passwordmanager.Entity.User;
import nuricanozturk.dev.passwordmanager.Util.Hash;
import nuricanozturk.dev.passwordmanager.exception.AuthenticationException;

public class PasswordAuthenticationController
{
    @FXML private PasswordField passwordField;
    @FXML private AnchorPane anchorPane;
    private User user;
    private MainScreenController controller;

    public void setUser(User user)
    {
        this.user = user;
    }
    public void setMainScreenController(MainScreenController controller)
    {
        this.controller = controller;
    }

    @FXML
    private void clickOkButton()
    {
        var password = passwordField.getText();

        if(Hash.hashing(password).equals(user.getPassword()))
        {
            controller.showPasswords();

            ((Stage)anchorPane.getScene().getWindow()).close();
        }
        else
        {
            passwordField.setText(null);
            throw new AuthenticationException();
        }
    }


}
