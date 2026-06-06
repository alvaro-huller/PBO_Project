/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author lenovo
 */
public class Admin extends User{
    private String adminLevel; // "SUPERADMIN" atau "STAFF"
    public Admin(String username, String password, String adminlevel) {
        super(username, password);
        this.adminLevel = adminlevel;
    }

    // Override method getRole() dari User (Polymorphism)
    @Override
    public String getRole() {
        return "Admin";
    }
//    @Override public javax.swing.JFrame getDashboardFrame() { 
//        return new view.AdminFrame(this); 
//    }
    @Override public boolean canEditDatabase() { 
        return true; 
    }
    public boolean canManageUsers() {
        return "SUPERADMIN".equals(adminLevel);
    }

    public String getAdminLevel(){ 
        return adminLevel; 
    }
    public void setAdminLevel(String adminLevel){ 
        this.adminLevel = adminLevel; 
    }

    @Override
    public String toString() {
        return super.toString() + " | Level: " + adminLevel;
    }
}
