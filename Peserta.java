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
public class Peserta extends Connector {
    private String nik ;
    private String nama ;
    private Date tgl_lhr ;
    private String jk ;
    private String no_hp ;
    private String status ;
    private Date tgl_vak ;
    private String nama_vak ;

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

    public Date getTgl_lhr() {
        return tgl_lhr;
    }

    public void setTgl_lhr(Date tgl_lhr) {
        this.tgl_lhr = tgl_lhr;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTgl_vak() {
        return tgl_vak;
    }

    public void setTgl_vak(Date tgl_vak) {
        this.tgl_vak = tgl_vak;
    }

    public String getNama_vak() {
        return nama_vak;
    }

    public void setNama_vak(String nama_vak) {
        this.nama_vak = nama_vak;
    }
    
    
    public boolean SimpanPeserta(Peserta data) throws SQLException {
        PreparedStatement pstm=null ;
        Connection conn=(Connection)Connector.configDB();
        String sq="alter table peserta auto_increment=0";
            java.sql.PreparedStatement pst=conn.prepareStatement(sq);
            pst.execute();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        String SQL="INSERT INTO peserta (nik, nama, tgl_lahir, jenis_kelamin, no_hp, status, tgl_vak, nama_vaksin) VALUES(?,?,?,?,?,?,?,?)";
        
        try{
            pstm=conn.prepareStatement(SQL);
            pstm.setString(1, data.getNik());
            pstm.setString(2, data.getNama());
            pstm.setString(3, df.format(getTgl_lhr()));
            pstm.setString(4, data.getJk());
            pstm.setString(5, data.getNo_hp());
            pstm.setString(6, data.getStatus());
            pstm.setString(7, df.format(getTgl_vak()));
            pstm.setString(8, data.getNama_vak());
            pstm.execute();
            return true;
        }
        catch(HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean UpdatePeserta(Peserta data) throws SQLException {
        PreparedStatement pstm=null ;
        Connection conn=(Connection)Connector.configDB();
        String sq="alter table peserta auto_increment=0";
            java.sql.PreparedStatement pst=conn.prepareStatement(sq);
            pst.execute();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        String SQL="UPDATE peserta SET nik=?, nama=?, tgl_lahir=?, jenis_kelamin=?, no_hp=?, status=?, tgl_vak=?, nama_vaksin=? WHERE nik=?";
        
        try{
            pstm=conn.prepareStatement(SQL);
            pstm.setString(1, data.getNik());
            pstm.setString(2, data.getNama());
            pstm.setString(3, df.format(getTgl_lhr()));
            pstm.setString(4, data.getJk());
            pstm.setString(5, data.getNo_hp());
            pstm.setString(6, data.getStatus());
            pstm.setString(7, df.format(getTgl_vak()));
            pstm.setString(8, data.getNama_vak());
            pstm.setString(9, data.getNik());
            pstm.execute();
            return true;
        }
        catch(HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean HapusPeserta(Peserta data) throws SQLException {
        PreparedStatement pstm=null ;
        Connection conn=(Connection)Connector.configDB();
        
        String SQL="DELETE FROM peserta where nik=?";
        
        try{
            pstm=conn.prepareStatement(SQL);
            pstm.setString(1, data.getNik());
            pstm.execute();
            return true;
        }
        catch(HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }  
}
