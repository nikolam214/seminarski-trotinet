/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Mesto;
import forme.PretraziMestoForma;
import forme.model.ModelTabeleMesto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author nikola
 */
public class PretraziMestoController {
    
    private PretraziMestoForma pmf;

    public PretraziMestoController(PretraziMestoForma pmf) {
        this.pmf = pmf;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pmf.setVisible(true);
    }

    public void pripremiFormu() {
        List<Mesto> mesta = komunikacija.Komunikacija.getInstance().ucitajMesta();
        ModelTabeleMesto mtm = new ModelTabeleMesto(mesta);
        pmf.getjTable1().setModel(mtm);
    }

    private void addActionListener() {
        pmf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pretrazi();
            }
        });

        pmf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pmf.getjTable1().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pmf, "Morate izabrati mesto za azuriranje", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleMesto mtm = (ModelTabeleMesto) pmf.getjTable1().getModel();
                Mesto m = mtm.getMesta().get(red);
                koordinator.Koordinator.getInstance().dodajParam("mesto", m);
                koordinator.Koordinator.getInstance().otvoriAzurirajMestoFormu();
            }
        });
        
        pmf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pmf.getjTable1().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pmf, "Morate izabrati mesto za brisanje", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleMesto mtm = (ModelTabeleMesto) pmf.getjTable1().getModel();
                Mesto m = mtm.getMesta().get(red);
                try {
                    komunikacija.Komunikacija.getInstance().obrisiMesto(m);
                    JOptionPane.showMessageDialog(pmf, "Mesto uspesno obrisano", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                    pripremiFormu();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(pmf, "Sistem ne moze da obrise mesto", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    private void pretrazi() {
        String naziv = pmf.getjTextFieldNaziv().getText().trim();
        String postanskiBroj = pmf.getjTextFieldPostanskiBroj().getText().trim();
        ModelTabeleMesto mtm = (ModelTabeleMesto) pmf.getjTable1().getModel();
        mtm.pretrazi(naziv, postanskiBroj);
    }
    
}
