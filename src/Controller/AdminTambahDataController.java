/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.BarangDAOImpl;
import Model.Barang;
import View.AdminTambahData;
import javax.swing.JOptionPane;

/**
 *
 * @author MJNJ
 */
public class AdminTambahDataController {
    private AdminMainMenuController controller;
    private AdminTambahData view;
    private BarangDAOImpl dao;
    
    public AdminTambahDataController() {
        this.dao = new BarangDAOImpl();
        this.view = new AdminTambahData(this);
    }
    
    public void setController(AdminMainMenuController controller) {
        this.controller = controller;
    }
    
    public void run() {
        view.setVisible(true);
    }
    
    public void clearMouseClickedHandle() {
        try {
            view.setNama("");
            view.setHarga("");
            view.setStok("");
            view.setKategori(0);
            view.setSatuan(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void kembaliMouseClickedHandle() {
        try {
            clearMouseClickedHandle();
            controller.run();
            view.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void tambahMouseClickedHandle() {
        try {
            String nama = view.getNama();
            String harga = view.getHarga();
            String stok = view.getStok();

            String kategori = null;
            int kategoriidx = view.getKategori();
            if (kategoriidx == 1) {
                kategori = "Material Bangunan Utama";
            } else if (kategoriidx == 2) {
                kategori = "Besi & Baja";
            } else if (kategoriidx == 3) {
                kategori = "Material Dinding & Lantai";
            } else if (kategoriidx == 4) {
                kategori = "Cat";
            }

            String satuan = null;
            int satuanidx = view.getSatuan();
            if (satuanidx == 1) {
                satuan = "Zak";
            } else if (satuanidx == 2) {
                satuan = "M3";
            } else if (satuanidx == 3) {
                satuan = "Buah";
            } else if (satuanidx == 4) {
                satuan = "Kg";
            } else if (satuanidx == 5) {
                satuan = "Batang";
            } else if (satuanidx == 6) {
                satuan = "Kaleng";
            } else if (satuanidx == 7) {
                satuan = "Lembar";
            } else if (satuanidx == 8) {
                satuan = "Meter";
            } else if (satuanidx == 9) {
                satuan = "Dus";
            }

            dao.tambahBarang(new Barang(0, nama, kategori, satuan, Integer.parseInt(harga), Integer.parseInt(stok)));

            JOptionPane.showMessageDialog(null, "Data Barang Berhasil Ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            clearMouseClickedHandle();
           } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
