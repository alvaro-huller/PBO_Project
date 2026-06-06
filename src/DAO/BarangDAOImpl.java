/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Connection.Connection;
import Model.Barang;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author lenovo
 */
public class BarangDAOImpl implements BarangDAO{
    // CREATE
    @Override
    public boolean tambahBarang(Barang barang) {
        String sql = "INSERT INTO barang (nama_barang, kategori, satuan, harga_per_satuan) " + "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = Connection.getConnection().prepareStatement(sql);
            ps.setString(1, barang.getNamaBarang());
            ps.setString(2, barang.getKategori());
            ps.setString(3, barang.getSatuan());
            ps.setInt   (4, barang.getHargaPerSatuan());
            ps.setInt   (5, barang.getStok());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Gagal tambah barang: " + e.getMessage());
            return false;
        }
    }

    // READ
    @Override
    public List<Barang> getAllBarang() {
        List<Barang> list = new ArrayList<>();
        String sql = "SELECT * FROM barang ORDER BY id ASC";
        try {
            Statement st = Connection.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("Gagal ambil data barang: " + e.getMessage());
        }
        return list;
    }

    // READ
    @Override
    public Barang getBarangById(int id) {
        String sql = "SELECT * FROM barang WHERE id = ?";
        try {
            PreparedStatement ps = Connection.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (SQLException e) {
            System.err.println("Gagal cari barang by id: " + e.getMessage());
        }
        return null;
    }

    // READ
    @Override
    public List<Barang> searchBarang(String keyword) {
        List<Barang> list = new ArrayList<>();
        String sql = "SELECT * FROM barang " + "WHERE nama_barang LIKE ? OR kategori LIKE ?";
        try {
            PreparedStatement ps = Connection.getConnection().prepareStatement(sql);
            String param = "%" + keyword + "%";
            ps.setString(1, param);
            ps.setString(2, param);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("Gagal search barang: " + e.getMessage());
        }
        return list;
    }

    // UPDATE
    @Override
    public boolean updateBarang(Barang barang) {
        String sql = "UPDATE barang " + "SET nama_barang = ?, kategori = ?, satuan = ?, harga_per_satuan = ? " + "WHERE id = ?";
        try {
            PreparedStatement ps = Connection.getConnection().prepareStatement(sql);
            ps.setString(1, barang.getNamaBarang());
            ps.setString(2, barang.getKategori());
            ps.setString(3, barang.getSatuan());
            ps.setInt   (4, barang.getHargaPerSatuan());
            ps.setInt   (5, barang.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Gagal update barang: " + e.getMessage());
            return false;
        }
    }

    // ============================================================
    // DELETE
    // ============================================================
    @Override
    public boolean deleteBarang(int id) {
        String sql = "DELETE FROM barang WHERE id = ?";
        try {
            PreparedStatement ps = Connection.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Gagal hapus barang: " + e.getMessage());
            return false;
        }
    }
    
    
    @Override
    public boolean kurangiStok(int idBarang, int jumlahBeli) {
        // Cek stok dulu sebelum dikurangi
        Barang barang = getBarangById(idBarang);
        if (barang == null) {
            System.err.println("Barang tidak ditemukan.");
            return false;
        }
        if (!barang.isTersedia(jumlahBeli)) {
            System.err.println("Stok tidak mencukupi. Stok tersisa: " + barang.getStok());
            return false;
        }

        String sql = "UPDATE barang SET stok = stok - ? WHERE id = ?";
        try {
            PreparedStatement ps = Connection.getConnection().prepareStatement(sql);
            ps.setInt(1, jumlahBeli);
            ps.setInt(2, idBarang);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Gagal kurangi stok: " + e.getMessage());
            return false;
        }
    }

    // ============================================================
    // HELPER — mapping ResultSet → objek Barang
    // ============================================================
    private Barang mapRow(ResultSet rs) throws SQLException {
        return new Barang(
            rs.getInt   ("id"),
            rs.getString("nama_barang"),
            rs.getString("kategori"),
            rs.getString("satuan"),
            rs.getInt   ("harga_per_satuan"),
            rs.getInt   ("stok")
        );
    }
}
