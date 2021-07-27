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
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
/**
 *
 * @author Hp
 */
public class Arsip extends Connector {
    private String nik ;
    private String nama ;
    private String kode_vak ;
    private Date tgl_vak ;
    

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public String getKode_vak() {
        return kode_vak;
    }

    public void setKode_vak(String kode_vak) {
        this.kode_vak = kode_vak;
    }
    
    public Date getTgl_vak() {
        return tgl_vak;
    }

    public void setTgl_vak(Date tgl_vak) {
        this.tgl_vak = tgl_vak;
    }
    
    
    
    public boolean SimpanArsip(Arsip datar) throws SQLException {
        PreparedStatement pstm=null ;
        Connection conn=(Connection)Connector.configDB();
        String sq="alter table arsipdata auto_increment=0";
            java.sql.PreparedStatement pst=conn.prepareStatement(sq);
            pst.execute();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        String SQL="INSERT INTO arsipdata (nik, nama, kode_vaksin, tgl_vak) VALUES(?,?,?,?)";
        
        try{
            pstm=conn.prepareStatement(SQL);
            pstm.setString(1, datar.getNik());
            pstm.setString(2, datar.getNama());
            pstm.setString(3, datar.getKode_vak());
            pstm.setString(4, df.format(getTgl_vak()));
            pstm.execute();
            return true;
        }
        catch(HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean UpdateArsip(Arsip datar) throws SQLException {
        PreparedStatement pstm=null ;
        Connection conn=(Connection)Connector.configDB();
        String sq="alter table arsipdata auto_increment=0";
            java.sql.PreparedStatement pst=conn.prepareStatement(sq);
            pst.execute();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        String SQL="UPDATE peserta SET nik=? , kode_vaksin=?, nama=?, tgl_vak=?, kode_vaksin=? WHERE nik=?";
        
        try{
            pstm=conn.prepareStatement(SQL);
            pstm.setString(1, datar.getNik());
            pstm.setString(2, datar.getNama());
            pstm.setString(3, datar.getKode_vak());
            pstm.setString(4, df.format(getTgl_vak()));
            pstm.setString(1, datar.getNik());
            pstm.execute();
            return true;
        }
        catch(HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean HapusArsip(Arsip datar) throws SQLException {
        PreparedStatement pstm=null ;
        Connection conn=(Connection)Connector.configDB();
        
        String SQL="DELETE FROM arsipdata where nik=?";
        
        try{
            pstm=conn.prepareStatement(SQL);
            pstm.setString(1, datar.getNik());
            pstm.execute();
            return true;
        }
        catch(HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }
}
