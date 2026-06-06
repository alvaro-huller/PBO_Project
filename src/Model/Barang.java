/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author lenovo
 */
public class Barang {
    private int id;
    private String namaBarang;
    private String kategori;
    private String satuan;
    private int hargaPerSatuan;
    private int stok;

    // Constructor load dari DB (ada id)
    public Barang(int id, String namaBarang, String kategori, String satuan, int hargaPerSatuan, int stok) {
        this.id             = id;
        this.namaBarang     = namaBarang;
        this.kategori       = kategori;
        this.satuan         = satuan;
        this.hargaPerSatuan = hargaPerSatuan;
        this.stok           = stok;
    }

    // Constructor input baru (belum ada id, diisi DB nanti)
    public Barang(String namaBarang, String kategori, String satuan, int hargaPerSatuan, int stok) {
        this.id             = -1;
        this.namaBarang     = namaBarang;
        this.kategori       = kategori;
        this.satuan         = satuan;
        this.hargaPerSatuan = hargaPerSatuan;
        this.stok           = stok;
    }

    public int getId(){ 
        return id; 
    }
    public void setId(int id){ 
        this.id = id; 
    }

    public String getNamaBarang(){ 
        return namaBarang; 
    }
    public void setNamaBarang(String namaBarang){ 
        this.namaBarang = namaBarang; 
    }

    public String getKategori(){ 
        return kategori;
    }
    public void setKategori(String kategori){ 
        this.kategori = kategori; 
    }

    public String getSatuan(){ 
        return satuan; 
    }
    public void setSatuan(String satuan){ 
        this.satuan = satuan; 
    }

    public int getHargaPerSatuan(){ 
        return hargaPerSatuan; 
    }
    public void setHargaPerSatuan(int hargaPerSatuan){ 
        this.hargaPerSatuan = hargaPerSatuan; 
    }
    public int getStok(){ 
        return stok; 
    }
    public void setStok(int stok){ 
        this.stok = stok; 
    }

    //cek apakah stok cukup untuk dibeli
    public boolean isTersedia(int jumlahBeli) {
        return stok >= jumlahBeli;
    }
    @Override
    public String toString() {
        return "[" + id + "] " + namaBarang + " | " + kategori + " | " + satuan + " | Rp" + hargaPerSatuan + " | Stok" + stok;
    }
}
