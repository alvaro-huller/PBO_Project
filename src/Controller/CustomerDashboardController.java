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
    private MenuLoginController mlc;
    private CustomerDashboard view;
    private BarangDAOImpl dao;
    
    public CustomerDashboardController() {
        this.dao = new BarangDAOImpl();
        view = new CustomerDashboard(this);
    }
    
    public void setController(MenuLoginController controller) {
        this.mlc =  mlc;
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
            view.setIdSelected(id);
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
        
        public void BeliBarangHandle(){
            try {
                // 1. Ambil input dari form (Sesuaikan nama txtIdBarang dan txtJumlahBeli 
                // dengan nama variabel komponen GUI yang ada di form kamu)
                int idBarang = Integer.parseInt(view.getIdSelected());
                int jumlahBeli = Integer.parseInt(view.getJumlahBeli());
        
                // 3. Panggil method kurangiStok dan tampung hasil boolean-nya
                boolean berhasil = dao.kurangiStok(idBarang, jumlahBeli);
        
                // 4. Tampilkan notifikasi JOptionPane berdasarkan hasil
                if (berhasil) {
                    JOptionPane.showMessageDialog(null, "Pembelian barang berhasil! Stok telah diperbarui.", 
                "Sukses", JOptionPane.INFORMATION_MESSAGE);
            loadData();
            
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal membeli barang. Pastikan ID barang benar dan stok mencukupi.", 
                "Gagal", JOptionPane.ERROR_MESSAGE);
                }
        
            } catch (NumberFormatException e) {
                // Catch error kalau user input huruf di field yang harusnya angka
                JOptionPane.showMessageDialog(null, "Input tidak valid! ID dan Jumlah Beli harus berupa angka.", 
            "Error Input", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan sistem: " + e.getMessage(), 
            "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
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
