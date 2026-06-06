/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author lenovo
 */
public class Customer extends User{
    private String namaLengkap;
    private String noHp;
    private String alamat;

    public Customer(String username, String password, String namaLengkap, String noHp, String alamat) {
        super(username, password);
        this.namaLengkap = namaLengkap;
        this.noHp        = noHp;
        this.alamat      = alamat;
    }

    // Override method getRole() dari User (Polymorphism)
    @Override
    public String getRole() {
        return "Customer";
    }
    
    @Override
    public boolean canEditDatabase(){ 
        return false; 
    }

    public String getNamaLengkap(){ 
        return namaLengkap; 
    }
    public void setNamaLengkap(String namaLengkap){ 
        this.namaLengkap = namaLengkap; 
    }

    public String getNoHp(){ 
        return noHp; 
    }
    public void setNoHp(String noHp){ 
        this.noHp = noHp; 
    }

    public String getAlamat(){ 
        return alamat; 
    }
    public void setAlamat(String alamat){ 
        this.alamat = alamat; 
    }

    @Override
    public String toString() {
        return super.toString() + " | Nama: " + namaLengkap + " | HP: " + noHp;
    }
}
