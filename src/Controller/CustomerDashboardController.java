/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.BarangDAOImpl;
import Model.Barang;
import View.CustomerDashboard;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ALVA
 */
public class CustomerDashboardController {
    private CustomerDashboardController controller;
    private CustomerDashboard view;
    private BarangDAOImpl dao;
    
    public CustomerDashboardController() {
        this.controller = controller;
        this.dao = new BarangDAOImpl();
        view = new CustomerDashboard(this);
    }
    
        public void run() {
        loadData();
        view.setVisible(true);
    }
    
    public void loadData() {
        try {
            List<Barang> list = dao.getAllBarang();

            DefaultTableModel model = view.getTableModel();
            model.setRowCount(0);

            for (Barang barang : list) {
                model.addRow(new Object[]{
                    barang.getId(),
                    barang.getNamaBarang(),
                    barang.getKategori(),
                    barang.getSatuan(),
                    barang.getHargaPerSatuan(),
                    barang.getStok()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void tabelMouseClickedHandle() {
        try {
            JTable tabel = view.getTable();
            int baris = tabel.getSelectedRow();

            // ambil data dari tabel sesuai kolom
            String id = tabel.getValueAt(baris, 0).toString();
            String nama = tabel.getValueAt(baris, 1).toString();
            String kategori = tabel.getValueAt(baris, 2).toString();
            String satuan = tabel.getValueAt(baris, 3).toString();
            String harga = tabel.getValueAt(baris, 4).toString();
            String stok = tabel.getValueAt(baris, 5).toString();

            // set data ke field inputan
            view.setNama(nama);
            view.setKategori(kategori);
            view.setSatuan(satuan);
            view.setHarga(harga);
            view.setStok(stok);    
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void clearMouseClickedHandle() {
        try {
            view.setNama("");
            view.setHarga("");
            view.setStok("");
            view.setKategori("");
            view.setSatuan("");
            view.setTextCari("");
            view.setJumlahBeli("");
            view.setHargaTotal("");
            loadData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
        public void tombolCariMouseClickedHandle() {
        try {
            String cari = view.getTextCari();

            List<Barang> list = dao.searchBarang(cari);

            DefaultTableModel model = view.getTableModel();
            model.setRowCount(0);

            for (Barang barang : list) {
                model.addRow(new Object[]{
                    barang.getId(),
                    barang.getNamaBarang(),
                    barang.getKategori(),
                    barang.getSatuan(),
                    barang.getHargaPerSatuan(),
                    barang.getStok()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
        
        public void jumlahBeliHandle() {
            try {
                String nama = view.getNama();
                
                if(nama == null){
                    JOptionPane.showMessageDialog(null, "IdSelect Kosong!", "Warning", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    int stok = Integer.parseInt(view.getStok());
                    int hargaSatuan = Integer.parseInt(view.getHarga());
                    int jumlahBeli = Integer.parseInt(view.getJumlahBeli());
                    
                    int totalHarga = jumlahBeli * hargaSatuan;
                    String totalHargaStr = String.valueOf(totalHarga); 
                    view.setHargaTotal(totalHargaStr);
                    
                }
            } catch (Exception e) {
                view.setHargaTotal("");
            }
        }

}
