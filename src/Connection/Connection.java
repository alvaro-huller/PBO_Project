/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lenovo
 */
public class Connection {
    private static final String URL = "jdbc:mysql://localhost:3306/klinik_db";

    private static final String USER = "root";
    private static final String PASS = "";
    private static java.sql.Connection connection = null;

    private Connection() {}

    public static java.sql.Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("Koneksi database berhasil!");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC tidak ditemukan: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Gagal koneksi database: " + e.getMessage());
        }
        return connection;
    }
    
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Koneksi database ditutup.");
            }
        } catch (SQLException e) {
            System.err.println("Error menutup koneksi: " + e.getMessage());
        }
    }
}
