/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Hp
 */
public class Vaksin extends Connector {
    private String kode_vaksin ;
    private String nama_vaksin ;

    public String getKode_vaksin() {
        return kode_vaksin;
    }

    public void setKode_vaksin(String kode_vaksin) {
        this.kode_vaksin = kode_vaksin;
    }

    public String getNama_vaksin() {
        return nama_vaksin;
    }

    public void setNama_vaksin(String nama_vaksin) {
        this.nama_vaksin = nama_vaksin;
    }
    
    public boolean SimpanVaksin(Vaksin datavak) throws SQLException {
        PreparedStatement pstm=null ;
        Connection conn=(Connection)Connector.configDB();
        
        String SQL="INSERT INTO jns_vak (kode_vaksin, nama_vaksin) VALUES(?,?)";
        
        try{
            pstm=conn.prepareStatement(SQL);
            pstm.setString(1, datavak.getKode_vaksin());
            pstm.setString(2, datavak.getNama_vaksin());
            pstm.execute();
            return true;
        }
        catch(HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean UpdateVaksin(Vaksin datavak) throws SQLException {
        PreparedStatement pstm=null ;
        Connection conn=(Connection)Connector.configDB();
        
        String SQL="UPDATE jns_vak SET kode_vaksin=?, nama_vaksin=? WHERE kode_vaksin=?";
        
        try{
            pstm=conn.prepareStatement(SQL);
            pstm.setString(1, datavak.getKode_vaksin());
            pstm.setString(2, datavak.getNama_vaksin());
            pstm.setString(3, datavak.getKode_vaksin());
            pstm.execute();
            return true;
        }
        catch(HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean HapusVaksin(Vaksin datavak) throws SQLException {
        PreparedStatement pstm=null ;
        Connection conn=(Connection)Connector.configDB();
        
        String SQL="DELETE FROM jns_vak where kode_vaksin=?";
        
        try{
            pstm=conn.prepareStatement(SQL);
            pstm.setString(1, datavak.getKode_vaksin());
            pstm.execute();
            return true;
        }
        catch(HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }  
}
