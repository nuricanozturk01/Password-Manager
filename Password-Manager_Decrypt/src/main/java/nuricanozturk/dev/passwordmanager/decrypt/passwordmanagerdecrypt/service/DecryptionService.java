package nuricanozturk.dev.passwordmanager.decrypt.passwordmanagerdecrypt.service;

import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import nuricanozturk.dev.io.FileUtil;
import nuricanozturk.dev.passwordmanager.decrypt.passwordmanagerdecrypt.Util.AES256;
import nuricanozturk.dev.passwordmanager.decrypt.passwordmanagerdecrypt.entity.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DecryptionService
{
    private static DecryptionService m_decryptionService = new DecryptionService();
    private final List<Application> list = new ArrayList<>();

    private DecryptionService() {}

    public static DecryptionService getInstance()
    {
        return m_decryptionService = m_decryptionService == null ? new DecryptionService() : m_decryptionService;
    }



    public void decryption(String key, String filePath, TableView<Application> tableView)
    {
        var dec = new AES256(key);

        FileUtil.read(filePath, ",", this::read);

        new Thread(() -> {
            list.forEach(app -> apply(app, dec));

            tableView.setItems(FXCollections.observableList(list));
        }).start();
    }

    private void apply(Application application, AES256 dec)
    {
        application.setApp_password(dec.decrypt(application.getApp_password()));
        application.setApp_name(dec.decrypt(application.getApp_name()));
        application.setApp_username(dec.decrypt(application.getApp_username()));
    }

    private void read(String[] strings)
    {
        list.add(new Application(strings[0], strings[1], strings[2]));
    }
}
