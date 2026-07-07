/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.TerminDezurstva;
import domen.Zaposleni;
import domen.ZtD;
import forme.ZtDForma;
import forme.model.ModelTabeleZtD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author nikola
 */
public class ZtDController {
    
    private ZtDForma zf;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public ZtDController(ZtDForma zf) {
        this.zf = zf;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        zf.setVisible(true);
    }
    
    private void pripremiFormu() {
        List<Zaposleni> zaposleni = komunikacija.Komunikacija.getInstance().ucitajZaposlene();
        for (Zaposleni z : zaposleni) {
            zf.getjComboBoxZaposleni().addItem(z);
        }

        List<TerminDezurstva> termini = komunikacija.Komunikacija.getInstance().ucitajTermineDezurstva();
        for (TerminDezurstva t : termini) {
            zf.getjComboBoxTerminDezurstva().addItem(t);
        }

        osveziTabelu();
    }

    private void osveziTabelu() {
        List<ZtD> ztdList = komunikacija.Komunikacija.getInstance().ucitajZtD();
        ModelTabeleZtD mtz = new ModelTabeleZtD(ztdList);
        zf.getjTable1().setModel(mtz);
    }
    
    private void addActionListener() {
        zf.addBtnDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj();
            }
        });

        zf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisi();
            }
        });
    }
    
    private void dodaj() {
        Zaposleni zaposleni = zf.getIzabraniZaposleni();
        TerminDezurstva termin = zf.getIzabraniTermin();
        String datumStr = zf.getjTextFieldDatum().getText().trim();
        String napomena = zf.getjTextField1().getText().trim();

        if (zaposleni == null) {
            JOptionPane.showMessageDialog(zf, "Morate izabrati zaposlenog", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (termin == null) {
            JOptionPane.showMessageDialog(zf, "Morate izabrati termin dezurstva", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }
            

        Date datum;
        try {
            datum = sdf.parse(datumStr);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(zf, "Datum mora biti u formatu dd.MM.yyyy", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (datum.before(new Date())) {
            JOptionPane.showMessageDialog(zf, "Datum ne moze biti u proslosti", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        ZtD ztd = new ZtD(zaposleni, termin, datum, napomena);
        try {
            komunikacija.Komunikacija.getInstance().dodajZtD(ztd);
            JOptionPane.showMessageDialog(zf, "ZtD uspesno dodat", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
            osveziTabelu();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(zf, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void obrisi() {
        int red = zf.getjTable1().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(zf, "Morate izabrati ZtD za brisanje", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ModelTabeleZtD mtz = (ModelTabeleZtD) zf.getjTable1().getModel();
        ZtD ztd = mtz.getZtDList().get(red);
        try {
            komunikacija.Komunikacija.getInstance().obrisiZtD(ztd);
            JOptionPane.showMessageDialog(zf, "ZtD uspesno obrisan", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
            osveziTabelu();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(zf, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }
        
}
