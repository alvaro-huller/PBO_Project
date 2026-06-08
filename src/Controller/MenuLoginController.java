/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.MenuLogin;
import javax.swing.JOptionPane;

/**
 *
 * @author MJNJ
 */
public class MenuLoginController {
    private AdminKelolaDataController akdc;
    private CustomerDashboardController cdc;
    private MenuLogin view;

    public MenuLoginController() {
        this.view = new MenuLogin(this);
    }
    
    public void setController(AdminKelolaDataController akdc, CustomerDashboardController cdc) {
        this.akdc = akdc;
        this.cdc = cdc;
    }
    
    public void run() {
        view.setVisible(true);
    }
    
    public void loginMouseClickedHandle() {
        try {
            
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
