/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Connector;
import Model.Vaksin;
import View.FormVak;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Hp
 */
public class VaksinController implements ActionListener, MouseListener {
    private Vaksin datavak;
    private FormVak frmvak;
    
    public VaksinController(Vaksin datavak, FormVak frmvak){
        this.datavak = datavak;
        this.frmvak = frmvak;
        this.frmvak.btnSimpan.addActionListener(this);
        this.frmvak.btnEdit.addActionListener(this);
        this.frmvak.btnHapus.addActionListener(this);
        this.frmvak.tablevak.addMouseListener(this);
    }
    
    public void KosongFormVaksin(){
        frmvak.txtKodeVak.setEditable(true);
        frmvak.txtKodeVak.setText(null);
        frmvak.txtNamaVak.setText(null);
    }
    
    public void TampilDataVaksin(){
        DefaultTableModel model= new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Kode Vaksin");
        model.addColumn("Nama Vaksin");
        
        try{
            int no=1;
            String sql="SELECT * FROM jns_vak";
            java.sql.Connection conn=(Connection)Connector.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            
            while(res.next()){
                model.addRow(new Object[]{
                no++,
                res.getString(1),
                res.getString(2)});
            }
            frmvak.tablevak.setModel(model);
            
            
        }catch(SQLException e){
            System.out.println("Error "+e.getMessage());
        }
    }
    public void actionPerformed(ActionEvent ae) {
            if (ae.getSource()==frmvak.btnSimpan) {
                datavak.setKode_vaksin(frmvak.txtKodeVak.getText());
                datavak.setNama_vaksin(frmvak.txtNamaVak.getText());
                
                try {
                    if(datavak.SimpanVaksin(datavak)){
                        JOptionPane.showMessageDialog(null,
                                "Simpan Data Baru Berhasil");
                        KosongFormVaksin();
                        TampilDataVaksin();
                    }
                } catch (SQLException ex){
                        JOptionPane.showMessageDialog(null, ex);
                }
            }else if (ae.getSource()==frmvak.btnEdit) {
                datavak.setKode_vaksin(frmvak.txtKodeVak.getText());
                datavak.setNama_vaksin(frmvak.txtNamaVak.getText());
                
                try{
                    if (datavak.UpdateVaksin(datavak)) {
                        JOptionPane.showMessageDialog(null, 
                                "Update Data Baru Berhasil");
                        KosongFormVaksin();
                        TampilDataVaksin();
                    }
                } catch (SQLException ex){
                        JOptionPane.showMessageDialog(null, ex);
                }
                        
                    }else{
                datavak.setKode_vaksin(frmvak.txtKodeVak.getText());
                
                try{
                    if (datavak.HapusVaksin(datavak)) {
                        JOptionPane.showMessageDialog(null, 
                                "Hapus Data Baru Berhasil");
                        KosongFormVaksin();
                        TampilDataVaksin();
                    }
                } catch (SQLException ex){
                        JOptionPane.showMessageDialog(null, ex);
            }
                }           
    }
    @Override
    public void mouseClicked(MouseEvent me){
        if (me.getSource()==frmvak.tablevak) {
            frmvak.txtKodeVak.setEditable(false);
            
            int baris=frmvak.tablevak.rowAtPoint(me.getPoint());
            String kode_vaksin = frmvak.tablevak.getValueAt(baris, 1).toString();
            frmvak.txtKodeVak.setText(kode_vaksin);
            String nama_vaksin = frmvak.tablevak.getValueAt(baris, 2).toString();
            frmvak.txtNamaVak.setText(nama_vaksin);
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
