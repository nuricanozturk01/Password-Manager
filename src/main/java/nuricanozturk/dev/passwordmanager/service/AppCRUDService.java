package nuricanozturk.dev.passwordmanager.service;

import javafx.scene.control.TableView;
import nuricanozturk.dev.passwordmanager.DataAccessLayer.Database;
import nuricanozturk.dev.passwordmanager.Entity.Application;
import nuricanozturk.dev.passwordmanager.Entity.User;
import nuricanozturk.dev.passwordmanager.Util.AES256;

public class AppCRUDService
{
    private static AppCRUDService m_appCrudService = new AppCRUDService();
    private User user;

    public void setUser(User user)
    {
        this.user = user;
    }
    private AppCRUDService(){}

    public static AppCRUDService getInstance()
    {
        return m_appCrudService = m_appCrudService == null ? new AppCRUDService() : m_appCrudService;
    }

    public Application addApplication(String name, String username, String password)
    {
        var secretKey = AES256.generateSecretKey(user.getUsername());
        var app = new Application(user, name, username, password);
        var encrypt = new AES256(secretKey);

        app.setApp_password(encrypt.encrypt(app.getApp_password()));

        Database.add(app);

        return app;
    }

    public void updateApplication(Application existingApplication, String name, String username, String password, TableView<Application> table)
    {
        var key = AES256.generateSecretKey(user.getUsername());
        var enc = new AES256(key);

        existingApplication.setApp_name(name);
        existingApplication.setApp_username(username);
        existingApplication.setApp_password(enc.encrypt(password));

        Database.update(existingApplication);


        existingApplication.setApp_password(password);
        table.refresh();
        existingApplication.setApp_password(password);
    }

    public void deleteApplication(Application selectedItem)
    {
        Database.remove(selectedItem);
    }
}
