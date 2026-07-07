/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Iznajmljivanje;
import domen.Klijent;
import domen.StavkaIznajmljivanja;
import domen.Trotinet;
import domen.Zaposleni;
import forme.IzmeniIznajmljivanjeForma;
import forme.model.ModelTabeleStavkeIznajmljivanja;
import forme.model.ModelTabeleTrotinet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 *
 * @author nikola
 */
public class IzmeniIznajmljivanjeController {
    
    private IzmeniIznajmljivanjeForma iif;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    private Iznajmljivanje iznajmljivanje;

    public IzmeniIznajmljivanjeController(IzmeniIznajmljivanjeForma iif) {
        this.iif = iif;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        iif.setVisible(true);
    }

    private void pripremiFormu() {
        iznajmljivanje = (Iznajmljivanje) koordinator.Koordinator.getInstance().vratiParam("iznajmljivanje");

        
        List<Klijent> klijenti = komunikacija.Komunikacija.getInstance().ucitajKlijente();
        for (Klijent k : klijenti) {
            iif.getjComboBoxKlijent().addItem(k);
            if (k.getId() == iznajmljivanje.getKlijent().getId()) {
                iif.getjComboBoxKlijent().setSelectedItem(k);
            }
        }

        List<Zaposleni> zaposleni = komunikacija.Komunikacija.getInstance().ucitajZaposlene();
        for (Zaposleni z : zaposleni) {
            iif.getjComboBoxZaposleni().addItem(z);
            if (z.getId() == iznajmljivanje.getZaposleni().getId()) {
                iif.getjComboBoxZaposleni().setSelectedItem(z);
            }
        }
        
        List<Trotinet> trotineti = komunikacija.Komunikacija.getInstance().ucitajTrotinete();
        for (Trotinet t : trotineti) {
            iif.getjComboBoxTrotinet().addItem(t);
        }

        
        ModelTabeleStavkeIznajmljivanja mts = new ModelTabeleStavkeIznajmljivanja();
        for (StavkaIznajmljivanja s : iznajmljivanje.getStavkeIznajmljivanja()) {
            mts.dodajStavku(s);
        }
        iif.getjTable1().setModel(mts);
        iif.getjLabelUkupnaCena().setText(String.format("%.2f", iznajmljivanje.getUkupnaCena()));
    }
    
    private void addActionListener() {
        iif.addTableSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int red = iif.getjTable1().getSelectedRow();
                    if (red != -1) {
                        ModelTabeleStavkeIznajmljivanja mts = (ModelTabeleStavkeIznajmljivanja) iif.getjTable1().getModel();
                        StavkaIznajmljivanja stavka = mts.getStavke().get(red);
                        
                        for (int i = 0; i < iif.getjComboBoxTrotinet().getItemCount(); i++) {
                            if (iif.getjComboBoxTrotinet().getItemAt(i).getId() == stavka.getTrotinet().getId()) {
                                iif.getjComboBoxTrotinet().setSelectedIndex(i);
                                break;
                            }
                        }
                        iif.getjTextFieldVremeUzimanja().setText(sdf.format(stavka.getVremeuzimanja()));
                        iif.getjTextFieldVremeVracanja().setText(sdf.format(stavka.getVremeVracanja()));
                    }
                }
            }
        });
        
        iif.addBtnIzmeniStavkuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeniStavku();
            }
        });

        iif.addBtnSacuvajIzmeneActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvaj();
            }
        });
    }

    private void izmeniStavku() {
        int red = iif.getjTable1().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(iif, "Morate izabrati stavku za izmenu", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String vremeUzString = iif.getjTextFieldVremeUzimanja().getText().trim();
        String vremeVrString = iif.getjTextFieldVremeVracanja().getText().trim();

        Date vremeUzimanja;
        Date vremeVracanja;
        try {
            vremeUzimanja = sdf.parse(vremeUzString);
            vremeVracanja = sdf.parse(vremeVrString);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(iif, "Vreme mora biti u formatu dd.MM.yyyy HH:mm", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (vremeVracanja.before(vremeUzimanja)) {
            JOptionPane.showMessageDialog(iif, "Vreme vracanja mora biti posle vremena uzimanja", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Trotinet trotinet = iif.getIzabraniTrotinet();
        long trajanjeMilisekundi = vremeVracanja.getTime() - vremeUzimanja.getTime();
        double trajanjeMinuta = trajanjeMilisekundi / 60000.0;
        double iznos = trotinet.getCenaPoMinutu() * trajanjeMinuta;
        
        ModelTabeleStavkeIznajmljivanja mts = (ModelTabeleStavkeIznajmljivanja) iif.getjTable1().getModel();
        StavkaIznajmljivanja stavka = mts.getStavke().get(red);
        stavka.setTrotinet(trotinet);
        stavka.setVremeuzimanja(vremeUzimanja);
        stavka.setVremeVracanja(vremeVracanja);
        stavka.setCenaPoMinutu(trotinet.getCenaPoMinutu());
        stavka.setIznos(iznos);

        mts.fireTableDataChanged();
        iif.getjLabelUkupnaCena().setText(String.format("%.2f", mts.izracunajUkupnuCenu()));
    }

    private void sacuvaj() {
        ModelTabeleStavkeIznajmljivanja mts = (ModelTabeleStavkeIznajmljivanja) iif.getjTable1().getModel();

        iznajmljivanje.setKlijent(iif.getIzabraniKlijent());
        iznajmljivanje.setZaposleni(iif.getIzabraniZaposleni());
        iznajmljivanje.setUkupnaCena(mts.izracunajUkupnuCenu());
        iznajmljivanje.setStavkeIznajmljivanja(mts.getStavke());

        try { komunikacija.Komunikacija.getInstance().izmeniIznajmljivanje(iznajmljivanje);
            JOptionPane.showMessageDialog(iif, "Iznajmljivanje uspesno izmenjeno", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
            koordinator.Koordinator.getInstance().osveziTabeluIznajmljivanja();
            iif.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(iif, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }
}
