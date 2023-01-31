package nuricanozturk.dev.passwordmanager.service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nuricanozturk.dev.PasswordGenerator.PasswordGenerator;
import nuricanozturk.dev.passwordmanager.App;
import nuricanozturk.dev.passwordmanager.Controller.EmailVerificationScreenController;
import nuricanozturk.dev.passwordmanager.DataAccessLayer.Database;
import nuricanozturk.dev.passwordmanager.Entity.User;
import nuricanozturk.dev.passwordmanager.Util.*;
import nuricanozturk.dev.passwordmanager.exception.InvalidArgumentInFieldException;
import nuricanozturk.dev.passwordmanager.exception.InvalidEmailException;
import nuricanozturk.dev.passwordmanager.exception.UserAlreadyExistsException;

import java.io.IOException;

public class SignUpService
{
    private static SignUpService m_signUpService;

    private SignUpService(){}

    public static SignUpService getInstance()
    {
        return m_signUpService = m_signUpService == null ? new SignUpService() : m_signUpService;
    }
    private String generateCode()
    {
        return new PasswordGenerator.Builder().setNumbers(4).build().generate();
    }

    public void signUp(String name, String surname, String email, String username, String password) throws IOException
    {
        if (!FieldValidator.isValidText(name, surname, email, username, password))
            throw new InvalidArgumentInFieldException("You should fill the blanks without space characters(start and end)");

        if (!FieldValidator.isValidEmail(email))
            throw new InvalidEmailException();

        var uname = Database.getUserByUsername(username);

        if (uname != null)
            throw new UserAlreadyExistsException();

        var tmpUser = new User(name, surname, username, Hash.hashing(password), email);
        var code = generateCode();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                MailService.sendMessage(tmpUser.getEmail(), "Email Verification", code);
            }
        }).start();

        var loader = new FXMLLoader(App.class.getResource(SceneNames.EMAIL_VERIFICATION_SCREEN.getSceneName()));
        Util.initStage("Email Verification", new Stage(), new Scene(loader.load()), false, null);

        EmailVerificationScreenController controller = loader.getController();
        controller.setCode(code);
        controller.setUser(tmpUser);

    }
}
