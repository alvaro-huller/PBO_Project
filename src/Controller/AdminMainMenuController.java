/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.AdminMainMenu;
import javax.security.auth.login.LoginContext;
import javax.swing.JOptionPane;

/**
 *
 * @author MJNJ
 */
public class AdminMainMenuController {
    private AdminKelolaDataController akdc;
    private AdminTambahDataController atdc;
    private MenuLoginController mlc;
    private AdminMainMenu view;
    
    public void run() {
        view.setVisible(true);
    }
    
    public AdminMainMenuController(){
        this.view = new AdminMainMenu(this);
    }
    
    public void setController(AdminKelolaDataController akdc, AdminTambahDataController atdc, MenuLoginController mlc) {
        this.akdc = akdc;
        this.atdc = atdc;
    }
    
    public void kelolaMouseClickedHandle() {
        try{
            akdc.run();
            view.dispose();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void tambahMouseClickedHandle() {
        try {
            atdc.run();
            view.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void logoutMouseClickedHandle() {
        try {
            mlc.run();
            view.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
