/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gudangmerahputih;

import Controller.AdminKelolaDataController;
import Controller.AdminMainMenuController;
import Controller.AdminTambahDataController;
import Controller.CustomerDashboardController;
import Controller.MenuLoginController;

/**
 *
 * @author ALVA
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Controller Admin
        AdminMainMenuController ammc = new AdminMainMenuController();
        AdminKelolaDataController akdc = new AdminKelolaDataController();
        AdminTambahDataController atdc = new AdminTambahDataController();
        
        // Controller Customer
        CustomerDashboardController cdc = new CustomerDashboardController();
        
        // Controller Autentikasi
        MenuLoginController mlc = new MenuLoginController();
        
        
        // Koneksi Controller
        // Admin
        ammc.setController(akdc, atdc, mlc);
        akdc.setController(ammc);
        atdc.setController(ammc);
        
        // Customer
        cdc.setController(mlc);
        
        // Autentikasi
        mlc.setController(akdc, cdc);
        
        // Running
        mlc.run();
    }
    
}
