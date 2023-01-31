package nuricanozturk.dev.passwordmanager.mapper;

import nuricanozturk.dev.passwordmanager.Entity.Application;
import nuricanozturk.dev.passwordmanager.dto.ApplicationExportDTO;
import nuricanozturk.dev.passwordmanager.dto.ApplicationsExportDTO;


import java.util.List;

public interface IExportMapper
{
    ApplicationExportDTO toExportFormat(Application application);

    default ApplicationsExportDTO toApplicationExportList(List<ApplicationExportDTO> apps)
    {
        var dto = new ApplicationsExportDTO();

        dto.exportApps = apps;

        return dto;
    }
}
