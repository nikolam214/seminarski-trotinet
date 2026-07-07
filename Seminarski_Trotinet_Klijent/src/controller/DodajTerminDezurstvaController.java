/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.TerminDezurstva;
import forme.DodajTerminDezurstvaForma;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author nikola
 */
public class DodajTerminDezurstvaController {
    
    private DodajTerminDezurstvaForma dtdf;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    public DodajTerminDezurstvaController(DodajTerminDezurstvaForma dtdf) {
        this.dtdf = dtdf;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dtdf.setVisible(true);
    }
    
    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dtdf.getjButtonAzuriraj().setVisible(false);
                dtdf.getjButtonDodaj().setVisible(true);
                break;
            case IZMENI:
                dtdf.getjButtonDodaj().setVisible(false);
                dtdf.getjButtonAzuriraj().setVisible(true);
                TerminDezurstva t = (TerminDezurstva) koordinator.Koordinator.getInstance().vratiParam("terminDezurstva");
                dtdf.getjComboBoxTipSmene().setSelectedItem(t.getTipSmene());
                dtdf.getjTextFieldVremePocetka().setText(sdf.format(t.getVremePocetka()));
                dtdf.getjTextFieldTrajanje().setText(String.valueOf(t.getTrajanje() / 60));
                break;
            default:
                throw new AssertionError();
        }
    }
    
    private void addActionListener() {
        dtdf.addBtnDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }
        });

        dtdf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }
        });
    }
    
    private TerminDezurstva validiraj() throws Exception {
        String tipSmene = (String) dtdf.getjComboBoxTipSmene().getSelectedItem();
        String vremePocetkaStr = dtdf.getjTextFieldVremePocetka().getText().trim();
        String trajanjeStr = dtdf.getjTextFieldTrajanje().getText().trim();

        if (tipSmene == null || tipSmene.isEmpty()) {
            throw new Exception("Tip smene ne sme biti prazan");
        }

        Date vremePocetka;
        try {
            vremePocetka = sdf.parse(vremePocetkaStr);
        } catch (ParseException ex) {
            throw new Exception("Vreme pocetka mora biti u formatu HH:mm");
        }

        int trajanje;
        try {
            trajanje = Integer.parseInt(trajanjeStr);
        } catch (NumberFormatException ex) {
            throw new Exception("Trajanje mora biti broj");
        }
        if (trajanje <= 0) {
            throw new Exception("Trajanje mora biti pozitivan broj");
        }

        return new TerminDezurstva(0, tipSmene, vremePocetka, trajanje * 60);
    }

    private void dodaj(ActionEvent e) {
        try {
            TerminDezurstva t = validiraj();
            komunikacija.Komunikacija.getInstance().dodajTerminDezurstva(t);
            JOptionPane.showMessageDialog(dtdf, "Termin dezurstva uspesno dodat", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
            koordinator.Koordinator.getInstance().osveziTabeluTerminaDezurstva();
            dtdf.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dtdf, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void izmeni(ActionEvent e) {
        try {
            TerminDezurstva t = validiraj();
            TerminDezurstva original = (TerminDezurstva) koordinator.Koordinator.getInstance().vratiParam("terminDezurstva");
            t.setId(original.getId());
            komunikacija.Komunikacija.getInstance().azurirajTerminDezurstva(t);
            JOptionPane.showMessageDialog(dtdf, "Termin dezurstva uspesno azuriran", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
            koordinator.Koordinator.getInstance().osveziTabeluTerminaDezurstva();
            dtdf.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dtdf, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }
}
