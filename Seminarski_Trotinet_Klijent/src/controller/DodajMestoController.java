/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Mesto;
import forme.DodajMestoForma;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author nikola
 */
public class DodajMestoController {
    
    private DodajMestoForma dmf;

    public DodajMestoController(DodajMestoForma dmf) {
        this.dmf = dmf;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dmf.setVisible(true);
    }
    
    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dmf.getjButtonAzuriraj().setVisible(false);
                dmf.getjButtonDodaj().setVisible(true);
                break;
            case IZMENI:
                dmf.getjButtonDodaj().setVisible(false);
                dmf.getjButtonAzuriraj().setVisible(true);
                Mesto m = (Mesto) koordinator.Koordinator.getInstance().vratiParam("mesto");
                dmf.getjTextFieldNaziv().setText(m.getNaziv());
                dmf.getjTextFieldPostanskiBroj().setText(String.valueOf(m.getPostanskiBroj()));
                break;
            default:
                throw new AssertionError();
        }
    }
    
    private void addActionListener() {
        dmf.addBtnDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }
        });

        dmf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }
        });
    }
    
    private Mesto validiraj() throws Exception {
        String naziv = dmf.getjTextFieldNaziv().getText().trim();
        String postanskiBrojStr = dmf.getjTextFieldPostanskiBroj().getText().trim();

        if (naziv.isEmpty() || naziv.length() < 2) {
            throw new Exception("Naziv mora imati najmanje 2 karaktera");
        }

        int postanskiBroj;
        try {
            postanskiBroj = Integer.parseInt(postanskiBrojStr);
        } catch (NumberFormatException ex) {
            throw new Exception("Postanski broj mora biti broj");
        }
        if (postanskiBroj <= 0) {
            throw new Exception("Postanski broj mora biti pozitivan broj");
        }

        return new Mesto(0, naziv, postanskiBroj);
    }

    private void dodaj(ActionEvent e) {
        try {
            Mesto m = validiraj();
            komunikacija.Komunikacija.getInstance().dodajMesto(m);
            JOptionPane.showMessageDialog(dmf, "Mesto uspesno dodato", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
            koordinator.Koordinator.getInstance().osveziTabeluMesta();
            dmf.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dmf, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void izmeni(ActionEvent e) {
        try {
            Mesto m = validiraj();
            Mesto original = (Mesto) koordinator.Koordinator.getInstance().vratiParam("mesto");
            m.setId(original.getId());
            komunikacija.Komunikacija.getInstance().azurirajMesto(m);
            JOptionPane.showMessageDialog(dmf, "Mesto uspesno azurirano", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
            koordinator.Koordinator.getInstance().osveziTabeluMesta();
            dmf.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dmf, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
