module nuricanozturk.dev.passwordmanager
{
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.naming;
    requires java.sql;
    requires org.hibernate.commons.annotations;
    requires nuricanozturk.passwordgenerator;
    requires javax.mail.api;
    requires io.lib;


    requires static lombok;



    opens nuricanozturk.dev.passwordmanager to javafx.fxml;
    exports nuricanozturk.dev.passwordmanager;

    exports nuricanozturk.dev.passwordmanager.Entity;
    opens nuricanozturk.dev.passwordmanager.Entity to org.hibernate.orm.core;

    exports nuricanozturk.dev.passwordmanager.mapper;
    opens nuricanozturk.dev.passwordmanager.mapper;

    exports nuricanozturk.dev.passwordmanager.dto;
    opens nuricanozturk.dev.passwordmanager.dto;

    exports nuricanozturk.dev.passwordmanager.Controller;
    opens nuricanozturk.dev.passwordmanager.Controller to javafx.fxml;

    exports nuricanozturk.dev.passwordmanager.service;
    opens nuricanozturk.dev.passwordmanager.service to javafx.fxml;
}