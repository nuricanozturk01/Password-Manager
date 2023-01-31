package nuricanozturk.dev.passwordmanager.service;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import nuricanozturk.dev.PasswordGenerator.PasswordGenerator;
import nuricanozturk.dev.passwordmanager.DataAccessLayer.Database;
import nuricanozturk.dev.passwordmanager.Entity.User;
import nuricanozturk.dev.passwordmanager.Util.Hash;
import nuricanozturk.dev.passwordmanager.Util.MailService;
import nuricanozturk.dev.passwordmanager.Util.Util;

public class ChangePasswordService
{
    private static ChangePasswordService m_changePasswordService = new ChangePasswordService();
    private String m_code;
    private String m_email;
    private ChangePasswordService() {}

    public static ChangePasswordService getInstance()
    {
        return m_changePasswordService = m_changePasswordService == null ? new ChangePasswordService() : m_changePasswordService;
    }
    public boolean isValidEmail(String email)
    {
        if (email == null)
            return false;

        m_email = email;

        return Database.searchEmail(email).equals(email);
    }
    public boolean isValidCode(String mailCode)
    {
        return m_code.equals(mailCode);
    }


    public void sendMail(String eMail, String msg)
    {
       new Thread(new Runnable() {
           @Override
           public void run() {
               var code = new PasswordGenerator.Builder().setNumbers(4).build().generate();

               m_code = code;

               MailService.sendMessage(eMail, msg, code);

               Util.alertScreen(Alert.AlertType.INFORMATION, "Please enter the code in email!", ButtonType.OK);
           }
       }).start();
    }


    public void chanePassword(String newPassword)
    {
        var user = (User) Database.getUserByEmail(m_email);

        if (user != null)
        {
            user.setPassword(Hash.hashing(newPassword));
            Database.update(user);
            Util.alertScreen(Alert.AlertType.CONFIRMATION, "Password changed Successfully!", ButtonType.OK);
        }
    }
}
