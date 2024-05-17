module at.ac.fhcampuswien.fhmdb {
    requires javafx.controls;
    requires javafx.fxml;
    requires ormlite.jdbc;
    requires com.h2database;
    requires java.sql;

    requires com.jfoenix;
    requires org.junit.jupiter.api;
    requires com.google.gson;
    requires okhttp3;

    opens at.ac.fhcampuswien.fhmdb.models to com.google.gson;
    opens at.ac.fhcampuswien.fhmdb to javafx.fxml;
    opens at.ac.fhcampuswien.fhmdb.database to ormlite.jdbc;
    exports at.ac.fhcampuswien.fhmdb;
    exports at.ac.fhcampuswien.fhmdb.models;
    exports at.ac.fhcampuswien.fhmdb.utils;
    exports at.ac.fhcampuswien.fhmdb.exceptions;
    opens at.ac.fhcampuswien.fhmdb.utils to javafx.fxml;

}