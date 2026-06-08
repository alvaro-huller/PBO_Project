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
                kategori = "Material bangunan utama";
            } else if (kategoriidx == 2) {
                kategori = "Besi & baja";
            } else if (kategoriidx == 3) {
                kategori = "Material dinding & lantai";
            } else if (kategoriidx == 4) {
                kategori = "Cat DInding";
            } else {
                kategori = "";
            }

            String satuan = null;
            int satuanidx = view.getSatuan();
            if (satuanidx == 1) {
                satuan = "";
            } else if (satuanidx == 2) {
                satuan = "";
            } else if (satuanidx == 3) {
                satuan = "";
            } else if (satuanidx == 4) {
                satuan = "";
            } else {
                satuan = "";
            }

            dao.tambahBarang(new Barang(0, nama, kategori, satuan, Integer.parseInt(harga), Integer.parseInt(stok)));

            JOptionPane.showMessageDialog(null, "Data Film Berhasil Diupdate!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            clearMouseClickedHandle();
           } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
