package com.hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBC {
    public static final String USER = "postgres";
    public static final String PASSWORD = "root";
    public static final String JDBC_URL = "jdbc:postgresql://localhost:5432/lab";

    public static void Connection() {

        try(Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {

            final PreparedStatement statement = connection.prepareStatement("INSERT INTO STUDENT(IMIE, NAZWISKO, ROK_URODZENIA, ILOSC_PUNKTOW, STATUS, CLASS_ID) VALUES(?, ?, ?, ?, ?, ?)");
            statement.setString(1, "Patryk");
            statement.setString(2, "Kolotaj");
            statement.setInt(3, 2002);
            statement.setDouble(4, 2.2);
            statement.setString(5, "obecny");
            statement.setInt(6, 2);

            statement.execute();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Connection2() {

        try(Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {

            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM STUDENT");

            statement.executeQuery();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

}
