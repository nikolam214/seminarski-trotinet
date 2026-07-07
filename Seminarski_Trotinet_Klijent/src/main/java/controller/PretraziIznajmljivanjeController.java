/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Iznajmljivanje;
import domen.Klijent;
import domen.Zaposleni;
import forme.PretraziIznamljivanjeForma;
import forme.model.ModelTabeleIznajmljivanje;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author nikola
 */
public class PretraziIznajmljivanjeController {
    
    private PretraziIznamljivanjeForma pif;

    public PretraziIznajmljivanjeController(PretraziIznamljivanjeForma pif) {
        this.pif = pif;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pif.setVisible(true);
    }

    public void pripremiFormu() {
        List<Iznajmljivanje> iznajmljivanja = komunikacija.Komunikacija.getInstance().ucitajIznajmljivanja();
        ModelTabeleIznajmljivanje mti = new ModelTabeleIznajmljivanje(iznajmljivanja);
        pif.getjTable1().setModel(mti);

        pif.getjComboBox1().addItem(null);
        List<Klijent> klijenti = komunikacija.Komunikacija.getInstance().ucitajKlijente();
        for (Klijent k : klijenti) {
            pif.getjComboBox1().addItem(k);
        }

        pif.getjComboBox2().addItem(null);
        List<Zaposleni> zaposleni = komunikacija.Komunikacija.getInstance().ucitajZaposlene();
        for (Zaposleni z : zaposleni) {
            pif.getjComboBox2().addItem(z);
        }
    }

    private void addActionListener() {
        pif.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pretrazi();
            }
        });

        pif.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pif.getjTable1().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pif, "Morate izabrati iznajmljivanje za izmenu", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleIznajmljivanje mti = (ModelTabeleIznajmljivanje) pif.getjTable1().getModel();
                Iznajmljivanje i = mti.getIznajmljivanja().get(red);
                koordinator.Koordinator.getInstance().dodajParam("iznajmljivanje", i);
                koordinator.Koordinator.getInstance().otvoriIzmeniIznajmljivanjeFormu();
            }
        });
    }
    
    private void pretrazi() {
        Klijent klijent = (Klijent) pif.getjComboBox1().getSelectedItem();
        Zaposleni zaposleni = (Zaposleni) pif.getjComboBox2().getSelectedItem();
        ModelTabeleIznajmljivanje mti = (ModelTabeleIznajmljivanje) pif.getjTable1().getModel();
        mti.pretrazi(klijent, zaposleni);
    }
    
}
