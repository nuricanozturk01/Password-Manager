package nuricanozturk.dev.passwordmanager.decrypt.passwordmanagerdecrypt.entity;

public class Application
{
    private String app_name;
    private String app_username;

    private String app_password;

    public Application(String app_name, String app_username, String app_password)
    {
        this.app_name = app_name;
        this.app_username = app_username;
        this.app_password = app_password;
    }

    public String getApp_name()
    {
        return app_name;
    }

    public void setApp_name(String app_name)
    {
        this.app_name = app_name;
    }

    public String getApp_username()
    {
        return app_username;
    }

    public void setApp_username(String app_username)
    {
        this.app_username = app_username;
    }

    public String getApp_password()
    {
        return app_password;
    }

    public void setApp_password(String app_password)
    {
        this.app_password = app_password;
    }

    @Override
    public String toString()
    {
        return app_name + "," + app_username + "," + app_password;
    }
}
