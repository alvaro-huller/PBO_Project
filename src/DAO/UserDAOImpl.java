/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Admin;
import Model.Customer;
import Model.User;
/**
 *
 * @author lenovo
 */
public class UserDAOImpl implements UserDAO{
    @Override
    public User login(String username, String password) {
        // Superadmin
        if (username.equals("admin") && password.equals("admin123")) {
            return new Admin(username, password, "SUPERADMIN");
        }
        // Customer 1
        if (username.equals("alva") && password.equals("alva321")) {
            return new Customer(username, password, "Alvaro Huller", "081234567890", "Jl. Malioboro No.1, Yogyakarta");
        }
        // Customer 2 (Akun kamu, Nicholaus!)
        if (username.equals("vincent") && password.equals("vincent123")) {
            return new Customer(username, password, "Vincentius", "082345678901", "Jl. Kaliurang No.5, Sleman");
        }
    
    // Login gagal
    return null;
}
}
