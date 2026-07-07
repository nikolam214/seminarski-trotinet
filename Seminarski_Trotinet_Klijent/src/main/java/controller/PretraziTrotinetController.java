/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Trotinet;
import forme.PretraziTrotinetForma;
import forme.model.ModelTabeleTrotinet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author nikola
 */
public class PretraziTrotinetController {
    
    private PretraziTrotinetForma ptf;

    public PretraziTrotinetController(PretraziTrotinetForma ptf) {
        this.ptf = ptf;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        ptf.setVisible(true);
    }

    public void pripremiFormu() {
        List<Trotinet> trotineti = komunikacija.Komunikacija.getInstance().ucitajTrotinete();
        ModelTabeleTrotinet mtt = new ModelTabeleTrotinet(trotineti);
        ptf.getjTable1().setModel(mtt);
    }
    
    private void addActionListener() {
        ptf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pretrazi();
            }
        });

        ptf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ptf.getjTable1().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(ptf, "Morate izabrati trotinet za azuriranje", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleTrotinet mtt = (ModelTabeleTrotinet) ptf.getjTable1().getModel();
                Trotinet t = mtt.getTrotineti().get(red);
                koordinator.Koordinator.getInstance().dodajParam("trotinet", t);
                koordinator.Koordinator.getInstance().otvoriAzurirajTrotinetFormu();
            }
        });

        ptf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ptf.getjTable1().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(ptf, "Morate izabrati trotinet za brisanje", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleTrotinet mtt = (ModelTabeleTrotinet) ptf.getjTable1().getModel();
                Trotinet t = mtt.getTrotineti().get(red);
                try {
                    komunikacija.Komunikacija.getInstance().obrisiTrotinet(t);
                    JOptionPane.showMessageDialog(ptf, "Trotinet uspesno obrisan", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                    pripremiFormu();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ptf, "Sistem ne moze da obrise trotinet", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    private void pretrazi() {
        String model = ptf.getjTextFieldModel().getText().trim();
        String cena = ptf.getjTextFieldCena().getText().trim();
        ModelTabeleTrotinet mtt = (ModelTabeleTrotinet) ptf.getjTable1().getModel();
        mtt.pretrazi(model, cena);
    }
}
