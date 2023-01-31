module nuricanozturk.dev.passwordmanager.decrypt.passwordmanagerdecrypt {
    requires javafx.controls;
    requires javafx.fxml;
    requires io.lib;

    opens nuricanozturk.dev.passwordmanager.decrypt.passwordmanagerdecrypt.controller to javafx.fxml;
    exports nuricanozturk.dev.passwordmanager.decrypt.passwordmanagerdecrypt.controller;

    opens nuricanozturk.dev.passwordmanager.decrypt.passwordmanagerdecrypt to javafx.fxml;
    exports nuricanozturk.dev.passwordmanager.decrypt.passwordmanagerdecrypt;

    opens nuricanozturk.dev.passwordmanager.decrypt.passwordmanagerdecrypt.entity;
    exports nuricanozturk.dev.passwordmanager.decrypt.passwordmanagerdecrypt.entity;
}