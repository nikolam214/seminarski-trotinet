/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.TerminDezurstva;
import forme.PretraziTermineDezurstvaForma;
import forme.model.ModelTabeleTerminDezurstva;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author nikola
 */
public class PretraziTerminDezurstvaController {
    
    
    private PretraziTermineDezurstvaForma ptdf;

    public PretraziTerminDezurstvaController(PretraziTermineDezurstvaForma ptdf) {
        this.ptdf = ptdf;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        ptdf.setVisible(true);
    }

    public void pripremiFormu() {
        List<TerminDezurstva> termini = komunikacija.Komunikacija.getInstance().ucitajTermineDezurstva();
        ModelTabeleTerminDezurstva mtd = new ModelTabeleTerminDezurstva(termini);
        ptdf.getjTable1().setModel(mtd);
    }

    private void addActionListener() {
        ptdf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pretrazi();
            }
        });
        ptdf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ptdf.getjTable1().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(ptdf, "Morate izabrati termin za azuriranje", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleTerminDezurstva mtd = (ModelTabeleTerminDezurstva) ptdf.getjTable1().getModel();
                TerminDezurstva t = mtd.getTermini().get(red);
                koordinator.Koordinator.getInstance().dodajParam("terminDezurstva", t);
                koordinator.Koordinator.getInstance().otvoriAzurirajTerminDezurstvaFormu();
            }
        });

        ptdf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ptdf.getjTable1().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(ptdf, "Morate izabrati termin za brisanje", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleTerminDezurstva mtd = (ModelTabeleTerminDezurstva) ptdf.getjTable1().getModel();
                TerminDezurstva t = mtd.getTermini().get(red);
                try {
                    komunikacija.Komunikacija.getInstance().obrisiTerminDezurstva(t);
                    JOptionPane.showMessageDialog(ptdf, "Termin dezurstva uspesno obrisan", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                    pripremiFormu();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ptdf, "Sistem ne moze da obrise termin dezurstva", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void pretrazi() {
        String tipSmene = (String) ptdf.getjComboBoxTipSmene().getSelectedItem();
        String vremePocetka = ptdf.getjTextFieldVremePocetka().getText().trim();
        String trajanje = ptdf.getjTextFieldTrajanje().getText().trim();
        ModelTabeleTerminDezurstva mtd = (ModelTabeleTerminDezurstva) ptdf.getjTable1().getModel();
        mtd.pretrazi(tipSmene, vremePocetka, trajanje);
    }
}
