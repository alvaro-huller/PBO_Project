/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;
import Model.Barang;
import java.util.List;
/**
 *
 * @author lenovo
 */
public interface BarangDAO {
    boolean tambahBarang(Barang barang);
    List<Barang> getAllBarang();
    Barang getBarangById(int id);
    List<Barang> searchBarang(String keyword);
    boolean updateBarang(Barang barang);
    boolean deleteBarang(int id);
    boolean kurangiStok(int idBarang, int jumlahBeli);
}