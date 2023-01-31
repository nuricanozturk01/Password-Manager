package nuricanozturk.dev.passwordmanager.service;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import nuricanozturk.dev.passwordmanager.DataAccessLayer.Database;
import nuricanozturk.dev.passwordmanager.Entity.User;
import nuricanozturk.dev.passwordmanager.Util.Util;

public class EmailVerificationService
{
    private static EmailVerificationService m_emailVerificationService = new EmailVerificationService();
    private String m_code;
    private User m_user;
    private EmailVerificationService() {}

    public static EmailVerificationService getInstance()
    {
       return m_emailVerificationService = m_emailVerificationService == null ?
               new EmailVerificationService() : m_emailVerificationService;
    }

    public void setUser(User user) {
        m_user = user;
    }

    public void setCode(String code)
    {
        m_code = code;
    }


    public void validateAndSave(String userCode)
    {
        if(userCode.equals(m_code))
        {
            Database.add(m_user);
            Util.alertScreen(Alert.AlertType.CONFIRMATION, "User added Successfully!", ButtonType.OK);
        }

        else Util.alertScreen(Alert.AlertType.ERROR, "Code not matched!", ButtonType.OK);
    }
}
