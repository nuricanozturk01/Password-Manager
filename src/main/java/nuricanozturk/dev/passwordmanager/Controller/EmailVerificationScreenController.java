package nuricanozturk.dev.passwordmanager.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import nuricanozturk.dev.passwordmanager.Entity.User;
import nuricanozturk.dev.passwordmanager.service.EmailVerificationService;

public class EmailVerificationScreenController
{
    @FXML
    private TextField codeTextField;

    private final EmailVerificationService m_service = EmailVerificationService.getInstance();


    public void setCode(String code)
    {
        m_service.setCode(code);
    }

    public void setUser(User user) {
        m_service.setUser(user);
    }

    @FXML
    private void clickSendButton()
    {
        var userCode = codeTextField.getText();
        m_service.validateAndSave(userCode);

    }


}
