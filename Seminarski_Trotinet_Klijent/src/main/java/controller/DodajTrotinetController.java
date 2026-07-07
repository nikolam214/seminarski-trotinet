/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Trotinet;
import forme.DodajTrotinetForma;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author nikola
 */
public class DodajTrotinetController {
    
    private DodajTrotinetForma dtf;

    public DodajTrotinetController(DodajTrotinetForma dtf) {
        this.dtf = dtf;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dtf.setVisible(true);
    }
    
    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dtf.getjButtonAzuriraj().setVisible(false);
                dtf.getjButtonDodaj().setVisible(true);
                break;
            case IZMENI:
                dtf.getjButtonDodaj().setVisible(false);
                dtf.getjButtonAzuriraj().setVisible(true);
                Trotinet t = (Trotinet) koordinator.Koordinator.getInstance().vratiParam("trotinet");
                dtf.getjTextFieldModel().setText(t.getModelTrotineta());
                dtf.getjTextFieldCena().setText(String.valueOf(t.getCenaPoMinutu()));
                break;
            default:
                throw new AssertionError();
        }
    }

    
    private void addActionListener() {
        dtf.addBtnDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }
        });

        dtf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }
        });
    }
    
    private Trotinet validiraj() throws Exception {
        String model = dtf.getjTextFieldModel().getText().trim();
        String cenaStr = dtf.getjTextFieldCena().getText().trim();

        if (model.isEmpty() || model.length() < 2) {
            throw new Exception("Model mora imati najmanje 2 karaktera");
        }

        double cena;
        try {
            cena = Double.parseDouble(cenaStr);
        } catch (NumberFormatException ex) {
            throw new Exception("Cena mora biti broj");
        }
        if (cena <= 0) {
            throw new Exception("Cena mora biti pozitivan broj");
        }

        return new Trotinet(0, model, cena);
    }
    
    private void dodaj(ActionEvent e) {
        try {
            Trotinet t = validiraj();
            komunikacija.Komunikacija.getInstance().dodajTrotinet(t);
            JOptionPane.showMessageDialog(dtf, "Trotinet uspesno dodat", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
            koordinator.Koordinator.getInstance().osveziTabeluTrotineta();
            dtf.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dtf, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void izmeni(ActionEvent e) {
        try {
            Trotinet t = validiraj();
            Trotinet original = (Trotinet) koordinator.Koordinator.getInstance().vratiParam("trotinet");
            t.setId(original.getId());
            komunikacija.Komunikacija.getInstance().azurirajTrotinet(t);
            JOptionPane.showMessageDialog(dtf, "Trotinet uspesno azuriran", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
            koordinator.Koordinator.getInstance().osveziTabeluTrotineta();
            dtf.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dtf, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }
}
