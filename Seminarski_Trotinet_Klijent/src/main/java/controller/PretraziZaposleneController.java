/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Zaposleni;
import forme.FormaMod;
import forme.PretraziZaposlene;
import forme.model.ModelTabeleZaposleni;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author nikola
 */
public class PretraziZaposleneController {
    
    private PretraziZaposlene pzf;

    public PretraziZaposleneController(PretraziZaposlene pzf) {
        this.pzf = pzf;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pzf.setVisible(true);
    }

    public void pripremiFormu() {
        List<Zaposleni> zaposleni = komunikacija.Komunikacija.getInstance().ucitajZaposlene();
        ModelTabeleZaposleni mtz = new ModelTabeleZaposleni(zaposleni);
        pzf.getjTableZaposleni().setModel(mtz);
    }
    
    private void addActionListener() {
        pzf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pretrazi();
            }
        });

        pzf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pzf.getjTableZaposleni().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pzf, "Morate izabrati zaposlenog za azuriranje", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleZaposleni mtz = (ModelTabeleZaposleni) pzf.getjTableZaposleni().getModel();
                Zaposleni z = mtz.getZaposleni().get(red);
                koordinator.Koordinator.getInstance().dodajParam("zaposleni", z);
                koordinator.Koordinator.getInstance().otvoriAzurirajZaposlenogFormu();
            }
        });
        
        pzf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pzf.getjTableZaposleni().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pzf, "Morate izabrati zaposlenog za brisanje", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleZaposleni mtz = (ModelTabeleZaposleni) pzf.getjTableZaposleni().getModel();
                Zaposleni z = mtz.getZaposleni().get(red);
                try {
                    komunikacija.Komunikacija.getInstance().obrisiZaposlenog(z);
                    JOptionPane.showMessageDialog(pzf, "Zaposleni uspesno obrisan", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                    pripremiFormu();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(pzf, "Sistem ne moze da obrise zaposlenog", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    private void pretrazi() {
        String ime = pzf.getjTextFieldIme().getText().trim();
        String prezime = pzf.getjTextFieldPrezime().getText().trim();
        String korIme = pzf.getjTextFieldKorIme().getText().trim();
        ModelTabeleZaposleni mtz = (ModelTabeleZaposleni) pzf.getjTableZaposleni().getModel();
        mtz.pretrazi(ime, prezime, korIme);
    }
}
