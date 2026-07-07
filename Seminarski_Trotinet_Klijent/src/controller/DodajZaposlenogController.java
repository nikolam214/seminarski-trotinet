/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Zaposleni;
import forme.DodajZaposlenog;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author nikola
 */
public class DodajZaposlenogController {
    
    private DodajZaposlenog dzf;

    public DodajZaposlenogController(DodajZaposlenog dzf) {
        this.dzf = dzf;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dzf.setVisible(true);
    }
    
    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dzf.getjButtonAzuriraj().setVisible(false);
                dzf.getjButtonDodaj().setVisible(true);
                break;
            case IZMENI:
                dzf.getjButtonDodaj().setVisible(false);
                dzf.getjButtonAzuriraj().setVisible(true);
                Zaposleni z = (Zaposleni) koordinator.Koordinator.getInstance().vratiParam("zaposleni");
                dzf.getjTextFieldIme().setText(z.getIme());
                dzf.getjTextFieldPrezime().setText(z.getPrezime());
                dzf.getjTextFieldKorIme().setText(z.getKorisnickoIme());
                break;
            default:
                throw new AssertionError();
        }
    }

    private void addActionListener() {
        dzf.addBtnDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }
        });

        dzf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }
        });
    }
    
    private Zaposleni validiraj() throws Exception {
        String ime = dzf.getjTextFieldIme().getText().trim();
        String prezime = dzf.getjTextFieldPrezime().getText().trim();
        String korIme = dzf.getjTextFieldKorIme().getText().trim();
        String sifra = String.valueOf(dzf.getjPasswordField1().getPassword()).trim();

        if (ime.isEmpty() || ime.length() < 2) {
            throw new Exception("Ime mora imati najmanje 2 karaktera");
        }
        if (prezime.isEmpty() || prezime.length() < 2) {
            throw new Exception("Prezime mora imati najmanje 2 karaktera");
        }
        if (korIme.isEmpty()) {
            throw new Exception("Korisnicko ime ne sme biti prazno");
        }
        if (sifra.isEmpty() || sifra.length() < 4) {
            throw new Exception("Sifra mora imati najmanje 4 karaktera");
        }

        return new Zaposleni(0, korIme, sifra, ime, prezime);
    }

    private void dodaj(ActionEvent e) {
        try {
            Zaposleni z = validiraj();
            komunikacija.Komunikacija.getInstance().dodajZaposlenog(z);
            JOptionPane.showMessageDialog(dzf, "Zaposleni uspesno dodat", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
            koordinator.Koordinator.getInstance().osveziTabeluZaposlenih();
            dzf.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dzf, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void izmeni(ActionEvent e) {
        try {
            Zaposleni z = validiraj();
            Zaposleni original = (Zaposleni) koordinator.Koordinator.getInstance().vratiParam("zaposleni");
            z.setId(original.getId());
            komunikacija.Komunikacija.getInstance().azurirajZaposlenog(z);
            JOptionPane.showMessageDialog(dzf, "Zaposleni uspesno azuriran", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
            koordinator.Koordinator.getInstance().osveziTabeluZaposlenih();
            dzf.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dzf, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }
}
