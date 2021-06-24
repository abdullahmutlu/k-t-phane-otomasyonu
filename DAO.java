/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ogrenciklup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author asus
 */
public class DAO {

    Connection conn;

    public DAO() throws SQLException {
        conn = baglanti.baglantiOlustur();
    }

    public boolean girisKontrol(String kullaniciAdi, String parola) throws SQLException {
        boolean sonuc = false;
        String sorgu = "SELECT OKUL_NO,PAROLA FROM ADMIN WHERE OKUL_NO=" + "'" + kullaniciAdi + "'" + "AND PAROLA=" + "'" + parola + "'";
        Statement stmt = (Statement) conn.createStatement();
        ResultSet rs = stmt.executeQuery(sorgu);
        if (rs.next()) {
            sonuc = true;
        } else {
            sonuc = false;
        }
        return sonuc;
    }

    public DefaultTableModel anaSayfaTablosunuDoldur(DefaultTableModel uye) throws SQLException {
        ResultSet resultset;
        String sorgu = "SELECT * FROM uyeler";
        Statement stmt = conn.createStatement();
        resultset = stmt.executeQuery(sorgu);
        while (resultset.next()) {
            int okul_no = resultset.getInt("okul_no");
            String adi = resultset.getString("adi");
            String soyadi = resultset.getString("soyadi");
            String bolumu = resultset.getString("bolumu");
            uye.addRow(new Object[]{okul_no, adi, soyadi, bolumu});
        }
        return uye;
    }

    public void uyeEkle(int okulno, String adi, String soyadi, String bolum) throws SQLException {
        String sorgu = "INSERT INTO `uyeler`(`OKUL_NO`, `ADI`, `SOYADI`, `BOLUMU`) VALUES (?,?,?,?)";
        PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sorgu);
        pstmt.setInt(1, okulno);
        pstmt.setString(2, adi);
        pstmt.setString(3, soyadi);
        pstmt.setString(4, bolum);
        pstmt.execute();
    }

    public void uyeSil(int okulNo) throws SQLException {
        String sorgu = "DELETE FROM UYELER WHERE OKUL_NO = " + okulNo;
        Statement stmt = (Statement) conn.createStatement();
        stmt.executeUpdate(sorgu);
    }

    // isim güncelleme
    public void isimGuncelle(int okulNo, String gelenVeri) throws SQLException {
        String sorgu = "UPDATE UYELER SET ADI=" + "'" + gelenVeri + "'" + "WHERE OKUL_NO=" + okulNo;
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sorgu);
    }

    // soyisim guncelle
    public void soyisimGuncelle(int okulNo, String gelenVeri) throws SQLException {
        String sorgu = "UPDATE UYELER SET SOYADI=" + "'" + gelenVeri + "'" + "WHERE OKUL_NO=" + okulNo;
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sorgu);
    }

    // bolum guncelle
    public void bolumGuncelle(int okulNo, String gelenVeri) throws SQLException {
        String sorgu = "UPDATE UYELER SET BOLUMU=" + "'" + gelenVeri + "'" + "WHERE OKUL_NO=" + okulNo;
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sorgu);
    }

    public void adminEkle(int okulno, String adi, String soyadi, String bolum, String parola) throws SQLException {
        String sorgu = "INSERT INTO `admin`(`OKUL_NO`, `ADI`, `SOYADI`, `BOLUMU`, `PAROLA`) VALUES (?,?,?,?,?)";
        PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sorgu);
        pstmt.setInt(1, okulno);
        pstmt.setString(2, adi);
        pstmt.setString(3, soyadi);
        pstmt.setString(4, bolum);
        pstmt.setString(5, parola);
        pstmt.execute();
    }
}
