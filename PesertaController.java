/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Connector;
import Model.Peserta;
import View.FormInput;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hp
 */
public class PesertaController implements ActionListener, MouseListener {
    private Peserta data;
    private FormInput frm;
    
    public PesertaController(Peserta data, FormInput frm){
        this.data = data;
        this.frm = frm;
        this.frm.btnSimpan.addActionListener(this);
        this.frm.btnEdit.addActionListener(this);
        this.frm.btnHapus.addActionListener(this);
        this.frm.tablePeserta.addMouseListener(this);
    }
    
    public void KosongFormPeserta(){
        frm.txtNIK.setEditable(true);
        frm.txtNIK.setText(null);
        frm.txtNama.setText(null);
        frm.txtTglLhr.setDate(null);
        frm.cbxJK.getSelectedItem();
        frm.txtNoHp.setText(null);
        frm.cbxStatus.getSelectedItem();
        frm.txtTglVak.setDate(null);
        frm.cbxNamaVak.getSelectedItem();
    }
    
    public void TampilDataPeserta(){
        DefaultTableModel model= new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("NIK");
        model.addColumn("Nama");
        model.addColumn("Tgl Lahir");
        model.addColumn("Gender");
        model.addColumn("No HP");
        model.addColumn("Status");
        model.addColumn("Tgl Vaksin");
        model.addColumn("Nama Vaksin");
        
        try{
            int no=1;
            String sql="SELECT * FROM peserta";
            java.sql.Connection conn=(Connection)Connector.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            
            while(res.next()){
                model.addRow(new Object[]{
                no++,
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getString(4),
                res.getString(5),
                res.getString(6),
                res.getString(7),
                res.getString(8)});
            }
            frm.tablePeserta.setModel(model);
            
            
        }catch(SQLException e){
            System.out.println("Error "+e.getMessage());
        }
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
            if (ae.getSource()==frm.btnSimpan) {
                data.setNik(frm.txtNIK.getText());
                data.setNama(frm.txtNama.getText());
                data.setTgl_lhr(frm.txtTglLhr.getDate());
                data.setJk((String) frm.cbxJK.getSelectedItem());
                data.setNo_hp(frm.txtNoHp.getText());
                data.setStatus((String) frm.cbxStatus.getSelectedItem());
                data.setTgl_vak(frm.txtTglVak.getDate());
                data.setNama_vak((String) frm.cbxNamaVak.getSelectedItem());
                
                try {
                    if(data.SimpanPeserta(data)){
                        JOptionPane.showMessageDialog(null,
                                "Simpan Data Baru Berhasil");
                        KosongFormPeserta();
                        TampilDataPeserta();
                    }
                } catch (SQLException ex){
                        JOptionPane.showMessageDialog(null, ex);
                }
            }else if (ae.getSource()==frm.btnEdit) {
                data.setNik(frm.txtNIK.getText());
                data.setNama(frm.txtNama.getText());
                data.setTgl_lhr(frm.txtTglLhr.getDate());
                data.setJk((String) frm.cbxJK.getSelectedItem());
                data.setNo_hp(frm.txtNoHp.getText());
                data.setStatus((String) frm.cbxStatus.getSelectedItem());
                data.setTgl_vak(frm.txtTglVak.getDate());
                data.setNama_vak((String) frm.cbxNamaVak.getSelectedItem());
                
                try{
                    if (data.UpdatePeserta(data)) {
                        JOptionPane.showMessageDialog(null, 
                                "Update Data Baru Berhasil");
                        KosongFormPeserta();
                        TampilDataPeserta();
                    }
                } catch (SQLException ex){
                        JOptionPane.showMessageDialog(null, ex);
                }
                        
                    }else{
                data.setNik(frm.txtNIK.getText());
                
                try{
                    if (data.HapusPeserta(data)) {
                        JOptionPane.showMessageDialog(null, 
                                "Hapus Data Baru Berhasil");
                        KosongFormPeserta();
                        TampilDataPeserta();
                    }
                } catch (SQLException ex){
                        JOptionPane.showMessageDialog(null, ex);
            }
                }           
    }
    @Override
    public void mouseClicked(MouseEvent me){
        if (me.getSource()==frm.tablePeserta) {
            frm.txtNIK.setEditable(false);
            int baris=frm.tablePeserta.rowAtPoint(me.getPoint());
            String nik =frm.tablePeserta.getValueAt(baris, 1).toString();
            frm.txtNIK.setText(nik);
            String nama=frm.tablePeserta.getValueAt(baris, 2).toString();
            frm.txtNama.setText(nama);
            String tgl_lhr=(String)frm.tablePeserta.getModel().getValueAt(baris, 7);
            try{
            SimpleDateFormat tgls = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date tanggals=tgls.parse(tgl_lhr);
            frm.txtTglLhr.setDate(tanggals);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            String jk =frm.tablePeserta.getValueAt(baris, 4).toString();
            frm.cbxJK.setSelectedItem(jk);
            String no_hp=frm.tablePeserta.getValueAt(baris, 5).toString();
            frm.txtNoHp.setText(no_hp);
            String status=frm.tablePeserta.getValueAt(baris, 6).toString();
            frm.cbxStatus.setSelectedItem(status);
            String tgl_vak=(String)frm.tablePeserta.getModel().getValueAt(baris, 7);
            try{
            SimpleDateFormat tgls = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date tanggals=tgls.parse(tgl_vak);
            frm.txtTglVak.setDate(tanggals);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            String nama_vak =frm.tablePeserta.getValueAt(baris, 8).toString();
            frm.cbxNamaVak.setSelectedItem(nama_vak);
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
