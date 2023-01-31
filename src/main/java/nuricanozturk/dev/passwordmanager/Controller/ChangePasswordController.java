package nuricanozturk.dev.passwordmanager.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import nuricanozturk.dev.passwordmanager.exception.CodesNotMatchException;
import nuricanozturk.dev.passwordmanager.exception.InvalidEmailException;
import nuricanozturk.dev.passwordmanager.exception.PasswordsNotMatchException;
import nuricanozturk.dev.passwordmanager.service.ChangePasswordService;

public class ChangePasswordController
{
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField codeTextField;
    @FXML
    private AnchorPane changePasswordPane;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField reNewPasswordField;
    private final ChangePasswordService m_service = ChangePasswordService.getInstance();

    @FXML
    private void initialize()
    {
        changePasswordPane.setVisible(false);
    }
    @FXML
    private void clickSendMail()
    {
        var email = emailTextField.getText();

        if (m_service.isValidEmail(email))
            m_service.sendMail(email, "Password Change Notification");

        else throw new InvalidEmailException();
    }

    @FXML
    private void clickSendCode()
    {
        var mailCode = codeTextField.getText();

        if (m_service.isValidCode(mailCode))
            changePasswordPane.setVisible(true);

        else throw new CodesNotMatchException();
    }

    @FXML
    private void clickChangePassword()
    {
        var newPassword = newPasswordField.getText();
        var reNewpassword = reNewPasswordField.getText();

        if (newPassword.equals(reNewpassword))
            m_service.chanePassword(newPassword);

        else throw new PasswordsNotMatchException();
    }
}
