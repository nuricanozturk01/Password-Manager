package nuricanozturk.dev.passwordmanager.service;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import nuricanozturk.dev.passwordmanager.DataAccessLayer.Database;
import nuricanozturk.dev.passwordmanager.Entity.Log;
import nuricanozturk.dev.passwordmanager.Entity.User;
import nuricanozturk.dev.passwordmanager.Util.Util;
import nuricanozturk.dev.passwordmanager.exception.UserAlreadyExistsException;

import java.io.IOException;
import java.time.LocalDateTime;

public class LoginService
{
    private static LoginService m_loginService = new LoginService();

    private LoginService() {}

    public static LoginService getInstance()
    {
       return m_loginService = m_loginService == null ? new LoginService() : m_loginService;
    }

    public User login(String username, String password, AnchorPane anchorPane) throws IOException
    {

        var user = Database.getUserByUsername(username);

        if (user == null)
            throw new UserAlreadyExistsException();

        if (user.getUsername().equals(username) && user.getPassword().equals(password))
            Database.add(new Log(LocalDateTime.now(), true, user));

        else
        {
            Util.alertScreen(Alert.AlertType.ERROR, "Invalid username or password!", ButtonType.OK);
            Database.add(new Log(LocalDateTime.now(), false, user));
            return null;
        }

        return user;
    }
}
