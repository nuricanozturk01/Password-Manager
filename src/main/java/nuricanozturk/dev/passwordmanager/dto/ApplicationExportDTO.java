package nuricanozturk.dev.passwordmanager.dto;

public class ApplicationExportDTO
{
    public String app_name;
    public String app_username;

    public String app_password;

    public ApplicationExportDTO(){}

    @Override
    public String toString()
    {
        return app_name + "," + app_username + "," + app_password;
    }
}
