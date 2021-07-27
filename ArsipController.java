/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Arsip;
import Model.Connector;
import View.FormArsip;
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
public class ArsipController implements ActionListener, MouseListener {
    private Arsip datar;
    private FormArsip frmar;
    
    public ArsipController( Arsip datar, FormArsip frmar){
        this.datar = datar;
        this.frmar = frmar;
        this.frmar.btnSimpan.addActionListener(this);
        this.frmar.btnEdit.addActionListener(this);
        this.frmar.btnHapus.addActionListener(this);
        this.frmar.tableArsip.addMouseListener(this);
    }
    
    public void KosongFormArsip(){
        frmar.txtNIK.setEditable(true);
        frmar.txtNIK.setText(null);
        frmar.txtNama.setText(null);
        frmar.cbxKodeVak.getSelectedItem();
        frmar.txtTglVak.setDate(null);
        
    }
    
    public void TampilDataArsip(){
        DefaultTableModel model= new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("NIK");
        model.addColumn("Nama");
        model.addColumn("Kode Vaksin");
        model.addColumn("Tgl Vaksin");
        
        
        try{
            int no=1;
            String sql="SELECT * FROM arsipdata";
            java.sql.Connection conn=(Connection)Connector.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            
            while(res.next()){
                model.addRow(new Object[]{
                no++,
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getString(4)});
            }
            frmar.tableArsip.setModel(model);
            
            
        }catch(SQLException e){
            System.out.println("Error "+e.getMessage());
        }
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
            if (ae.getSource()==frmar.btnSimpan) {
                datar.setNik(frmar.txtNIK.getText());
                datar.setNama(frmar.txtNama.getText());
                datar.setTgl_vak(frmar.txtTglVak.getDate());
                datar.setKode_vak((String) frmar.cbxKodeVak.getSelectedItem());
                
                try {
                    if(datar.SimpanArsip(datar)){
                        JOptionPane.showMessageDialog(null,
                                "Simpan Data Baru Berhasil");
                        KosongFormArsip();
                        TampilDataArsip();
                    }
                } catch (SQLException ex){
                        JOptionPane.showMessageDialog(null, ex);
                }
            }else if (ae.getSource()==frmar.btnEdit) {
                datar.setNik(frmar.txtNIK.getText());
                datar.setNama(frmar.txtNama.getText());
                datar.setTgl_vak(frmar.txtTglVak.getDate());
                datar.setKode_vak((String) frmar.cbxKodeVak.getSelectedItem());
                
                try{
                    if (datar.UpdateArsip(datar)) {
                        JOptionPane.showMessageDialog(null, 
                                "Update Data Baru Berhasil");
                        KosongFormArsip();
                        TampilDataArsip();
                    }
                } catch (SQLException ex){
                        JOptionPane.showMessageDialog(null, ex);
                }
                        
                    }else{
                datar.setNik(frmar.txtNIK.getText());
                
                try{
                    if (datar.HapusArsip(datar)) {
                        JOptionPane.showMessageDialog(null, 
                                "Hapus Data Baru Berhasil");
                        KosongFormArsip();
                        TampilDataArsip();
                    }
                } catch (SQLException ex){
                        JOptionPane.showMessageDialog(null, ex);
            }
                }           
    }
    @Override
    public void mouseClicked(MouseEvent me){
        if (me.getSource()==frmar.tableArsip) {
            frmar.txtNIK.setEditable(false);
            
            int baris=frmar.tableArsip.rowAtPoint(me.getPoint());
            String nik =frmar.tableArsip.getValueAt(baris, 1).toString();
            frmar.txtNIK.setText(nik);
            String nama=frmar.tableArsip.getValueAt(baris, 2).toString();
            frmar.txtNama.setText(nama);
            String kode_vak =frmar.tableArsip.getValueAt(baris, 3).toString();
            frmar.cbxKodeVak.setSelectedItem(kode_vak);
            String tgl_vak=(String)frmar.tableArsip.getModel().getValueAt(baris, 4);
            try{
            SimpleDateFormat tgls = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date tanggals=tgls.parse(tgl_vak);
            frmar.txtTglVak.setDate(tanggals);
            }catch(Exception ex){
                ex.printStackTrace();
            }
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
