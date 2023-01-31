package nuricanozturk.dev.passwordmanager.mapper;

import nuricanozturk.dev.passwordmanager.Entity.Application;
import nuricanozturk.dev.passwordmanager.dto.ApplicationExportDTO;

public class ExportMapper implements IExportMapper
{
    @Override
    public ApplicationExportDTO toExportFormat(Application application)
    {
        var dto = new ApplicationExportDTO();

        dto.app_name = application.getApp_name();
        dto.app_username = application.getApp_username();
        dto.app_password = application.getApp_password();

        return dto;
    }
}
