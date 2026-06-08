/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.UserDAO;
import DAO.UserDAOImpl;
import Model.Admin;
import Model.Customer;
import Model.User;
import View.MenuLogin;
import javax.swing.JOptionPane;

/**
 *
 * @author MJNJ
 */
public class MenuLoginController {
    private AdminMainMenuController ammc;
    private CustomerDashboardController cdc;
    private MenuLoginController mlc;
    private MenuLogin view;
    private UserDAO dao;

    public MenuLoginController() {
        dao = new UserDAOImpl();
        this.view = new MenuLogin(this);
    }
    
    public void setController(AdminMainMenuController ammc, CustomerDashboardController cdc) {
        this.ammc = ammc;
        this.cdc = cdc;
        this.mlc = mlc;
    }
    
    public User handleLogin(String username, String password) {

        if(username == null || password == null) {
            return null;
        }
        
        if(username.trim().isEmpty() || password.trim().isEmpty()) {
            return null;
        }   

        return dao.login(username.trim(), password.trim());
    }
    
    public void run() {
        view.setVisible(true);
    }
    
    public void loginMouseClickedHandle() {
        try {
            String username = view.getUsername();
            String password = view.getPassword();
            User user = handleLogin(username, password);
            
            if (user instanceof Admin) {
            JOptionPane.showMessageDialog(null, "Login berhasil sebagai Admin!");
            view.dispose();
            ammc.run();
                        
        }
        else if (user instanceof Customer) {
            JOptionPane.showMessageDialog(null, "Login berhasil sebagai Customer!");
            view.dispose();
            cdc.run();
        }
        else {
            JOptionPane.showMessageDialog(null, "Username atau Password salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
        }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void clearMouseClickedHandle() {
        try {
            view.setUsername("");
            view.setPassword("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
