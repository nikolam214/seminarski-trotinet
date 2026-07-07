/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Klijent;
import domen.Mesto;
import forme.DodajKlijentaForma;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author nikola
 */
public class DodajKlijentaController {
    
    private DodajKlijentaForma dkf;

    public DodajKlijentaController(DodajKlijentaForma dkf) {
        this.dkf=dkf;
        addActionListener();
        
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dkf.setVisible(true);
    }

    private void addActionListener() {
        dkf.addBtnSacuvajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }
        });

        dkf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }
        });
    }

    private Klijent validiraj() throws Exception {
        String ime = dkf.getjTextFieldIme().getText().trim();
        String prezime = dkf.getjTextFieldPrezime().getText().trim();
        Mesto mesto = (Mesto) dkf.getjComboBoxMesto().getSelectedItem();

        if (ime.isEmpty() || ime.length() < 2) {
            throw new Exception("Ime mora imati najmanje 2 karaktera");
        }
        if (prezime.isEmpty() || prezime.length() < 2) {
            throw new Exception("Prezime mora imati najmanje 2 karaktera");
        }
        if (mesto == null) {
            throw new Exception("Mesto mora biti izabrano");
        }

        long telefon;
        try {
            telefon = Long.parseLong(dkf.getjTextFieldBrojTelefona().getText().trim());
        } catch (NumberFormatException exc) {
            throw new Exception("Broj telefona mora biti broj");
        }
        if (telefon <= 0) {
            throw new Exception("Broj telefona mora biti pozitivan broj");
        }

        return new Klijent(0, ime, prezime, telefon, mesto);
    }

    private void dodaj(ActionEvent e) {
        try {
            Klijent k = validiraj();
            komunikacija.Komunikacija.getInstance().dodajKlijenta(k);
            JOptionPane.showMessageDialog(dkf, "Klijent uspesno dodat", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
            koordinator.Koordinator.getInstance().osveziTabeluKlijenata();
            dkf.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dkf, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void izmeni(ActionEvent e) {
        try {
            Klijent k = validiraj();
            Klijent original = (Klijent) koordinator.Koordinator.getInstance().vratiParam("klijent");
            k.setId(original.getId());
            komunikacija.Komunikacija.getInstance().azurirajKlijenta(k);
            JOptionPane.showMessageDialog(dkf, "Klijent uspesno azuriran", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
            koordinator.Koordinator.getInstance().osveziTabeluKlijenata();
            dkf.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dkf, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pripremiFormu(FormaMod mod) {
        Klijent k = null;
        switch (mod) {
            case DODAJ:
                dkf.getjButtonAzuriraj().setVisible(false);
                dkf.getjButtonDodaj().setVisible(true);
                dkf.getjButtonDodaj().setEnabled(true);
                break;
            case IZMENI:
                dkf.getjButtonAzuriraj().setVisible(true);
                dkf.getjButtonDodaj().setVisible(false);
                dkf.getjButtonAzuriraj().setEnabled(true);
                k = (Klijent) koordinator.Koordinator.getInstance().vratiParam("klijent");
                dkf.getjTextFieldIme().setText(k.getIme());
                dkf.getjTextFieldPrezime().setText(k.getPrezime());
                dkf.getjTextFieldBrojTelefona().setText(String.valueOf(k.getBrojtelefona()));
                break;
            default:
                throw new AssertionError();
        }

        List<Mesto> mesta = komunikacija.Komunikacija.getInstance().ucitajMesta();
        for (Mesto m : mesta) {
            dkf.getjComboBoxMesto().addItem(m);
        }

        if (mod == FormaMod.IZMENI && k != null) {
            for (int i = 0; i < dkf.getjComboBoxMesto().getItemCount(); i++) {
                Mesto m = dkf.getjComboBoxMesto().getItemAt(i);
                if (m.getId() == k.getMesto().getId()) {
                    dkf.getjComboBoxMesto().setSelectedIndex(i);
                    break;
                }
            }
        }
    }
}
