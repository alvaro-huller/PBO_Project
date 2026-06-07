/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.BarangDAOImpl;
import Model.Barang;
import View.AdminKelolaData;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MJNJ
 */
public class AdminKelolaDataController {
    private AdminMainMenuController controller;
    private AdminKelolaData view;
    private BarangDAOImpl dao;
    
    public AdminKelolaDataController() {
        this.controller = controller;
        this.dao = new BarangDAOImpl();
        view = new AdminKelolaData(this);
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
    
    public void kembaliMouseClickedHandle() {
        try {
            clearMouseClickedHandle();
            controller.run();
            view.dispose();
        } catch ( Exception e) {
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
            view.setId(id);
            view.setNama(nama);
            view.setHarga(harga);
            view.setStok(stok);

            if (kategori.equals("Material bangunan utama")) {
                view.setKategori(1);
            } else if (kategori.equals("Besi & baja")) {
                view.setKategori(2);
            } else if (kategori.equals("Material dinding & lantai")) {
                view.setKategori(3);
            } else if (kategori.equals("Cat")) {
                view.setKategori(4);
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void clearMouseClickedHandle() {
        try {
            view.setId("");
            view.setNama("");
            view.setHarga("");
            view.setStok("");
            view.setKategori(0);
            view.setSatuan(0);
            view.setTextCari("");
            loadData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void updateMouseClicked() {
        try {
            String id = view.getId() ;

            if (id == null) {
                JOptionPane.showMessageDialog(null, "IdSelect Kosong!", "Warning", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String nama = view.getNama();
                String harga = view.getHarga();
                String stok = view .getStok();
                
                String kategori = null;
                int kategoriidx = view.getKategori();
                if(kategoriidx == 1) {
                    kategori = "Material bangunan utama";
                } else if(kategoriidx == 2) {
                    kategori = "Besi & baja";
                } else if(kategoriidx == 3) {
                    kategori = "Material dinding & lantai";
                } else if(kategoriidx == 4) {
                    kategori = "Cat";
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
                
                dao.updateBarang(new Barang(Integer.parseInt(id), nama, kategori, satuan, Integer.parseInt(harga), Integer.parseInt(stok)));

                JOptionPane.showMessageDialog(null, "Data Film Berhasil Diupdate!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                clearMouseClickedHandle();
                loadData();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void deleteMouseClickedHandle() {
        try {
            String id = view.getId();

            if (id == null) {
                JOptionPane.showMessageDialog(null, "IdSelect Kosong!", "Warning", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int pilihan = JOptionPane.showConfirmDialog(null,
                        "Apakah Anda yakin ingin menghapus data ini?",
                        "Konfirmasi Hapus",
                        JOptionPane.YES_NO_OPTION);

                if (pilihan == JOptionPane.YES_OPTION) {
                    dao.deleteBarang(Integer.parseInt(id));
                    JOptionPane.showMessageDialog(null, "Data Film Berhasil Dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Data Film Batal Dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                }
                clearMouseClickedHandle();
                loadData();
            }

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
}
