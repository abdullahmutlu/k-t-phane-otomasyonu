/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ogrenciklup;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author asus
 */
public class baglanti {
    public static Connection baglantiOlustur() throws SQLException{
        Connection conn;
        conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3308/ogrenciklup", "root", "");
        return conn;
    }
    
}
