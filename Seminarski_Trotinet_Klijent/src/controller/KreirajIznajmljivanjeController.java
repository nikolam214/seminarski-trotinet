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
import forme.KreirajIznajmljivanjeForma;
import forme.model.ModelTabeleStavkeIznajmljivanja;
import forme.model.ModelTabeleTrotinet;
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
public class KreirajIznajmljivanjeController {
    
    private KreirajIznajmljivanjeForma kif;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    public KreirajIznajmljivanjeController(KreirajIznajmljivanjeForma kif) {
        this.kif = kif;
        
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        kif.setVisible(true);
    }
    
    private void pripremiFormu() {
        
        List<Klijent> klijenti = komunikacija.Komunikacija.getInstance().ucitajKlijente();
        for (Klijent k : klijenti) {
            kif.getjComboBoxKlijenti().addItem(k);
        }

        
        List<Zaposleni> zaposleni = komunikacija.Komunikacija.getInstance().ucitajZaposlene();
        for (Zaposleni z : zaposleni) {
            kif.getjComboBoxZaposleni().addItem(z);
        }

        List<Trotinet> trotineti = komunikacija.Komunikacija.getInstance().ucitajTrotinete();
        ModelTabeleTrotinet mtt = new ModelTabeleTrotinet(trotineti);
        kif.getjTableTrotinet().setModel(mtt);

       
        ModelTabeleStavkeIznajmljivanja mts = new ModelTabeleStavkeIznajmljivanja();
        kif.getjTableStavke().setModel(mts);
        kif.getjLabel6().setText("0.0");

        
    }
    
    private void addActionListener() {
        kif.addBtnDodajTrotActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajStavku();
            }
        });

        kif.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiStavku();
            }
        });

        kif.addBtnSacuvajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvaj();
            }
        });
        
        
    }
    
    private void dodajStavku() {
        int red = kif.getjTableTrotinet().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(kif, "Morate izabrati trotinet", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String vremeUzString = kif.getjTextFieldVremeUzimanja().getText().trim();
        String vremeVrString = kif.getjTextFieldVremeVracanja().getText().trim();

        Date vremeUzimanja;
        Date vremeVracanja;
        try {
            vremeUzimanja = sdf.parse(vremeUzString);
            vremeVracanja = sdf.parse(vremeVrString);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(kif, "Vreme mora biti u formatu dd.MM.yyyy HH:mm", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (vremeVracanja.before(vremeUzimanja)) {
            JOptionPane.showMessageDialog(kif, "Vreme vracanja mora biti posle vremena uzimanja", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ModelTabeleTrotinet mtt = (ModelTabeleTrotinet) kif.getjTableTrotinet().getModel();
        Trotinet trotinet = mtt.getTrotineti().get(red);

        long trajanjeMilisekundi = vremeVracanja.getTime() - vremeUzimanja.getTime();
        double trajanjeMinuta = trajanjeMilisekundi / 60000.0;
        double iznos = trotinet.getCenaPoMinutu() * trajanjeMinuta;

        StavkaIznajmljivanja stavka = new StavkaIznajmljivanja(
            0, null, vremeUzimanja, vremeVracanja,
            trotinet.getCenaPoMinutu(), iznos, trotinet
        );

        ModelTabeleStavkeIznajmljivanja mts = (ModelTabeleStavkeIznajmljivanja) kif.getjTableStavke().getModel();
        mts.dodajStavku(stavka);

        kif.getjLabel6().setText(String.format("%.2f", mts.izracunajUkupnuCenu()));
    }
    
    private void obrisiStavku() {
        int red = kif.getjTableStavke().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(kif, "Morate izabrati stavku za brisanje", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ModelTabeleStavkeIznajmljivanja mts = (ModelTabeleStavkeIznajmljivanja) kif.getjTableStavke().getModel();
        mts.obrisiStavku(red);
        kif.getjLabel6().setText(String.format("%.2f", mts.izracunajUkupnuCenu()));
    }

    private void sacuvaj() {
        ModelTabeleStavkeIznajmljivanja mts = (ModelTabeleStavkeIznajmljivanja) kif.getjTableStavke().getModel();

        if (mts.getStavke().isEmpty()) {
            JOptionPane.showMessageDialog(kif, "Morate dodati najmanje jednu stavku", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Klijent klijent = kif.getIzabraniKlijent();
        Zaposleni zaposleni = kif.getIzabraniZaposleni();
        double ukupnaCena = mts.izracunajUkupnuCenu();

        Iznajmljivanje iznajmljivanje = new Iznajmljivanje(0, ukupnaCena, zaposleni, klijent);
        iznajmljivanje.setStavkeIznajmljivanja(mts.getStavke());

        try {
            komunikacija.Komunikacija.getInstance().kreirajIznajmljivanje(iznajmljivanje);
            JOptionPane.showMessageDialog(kif, "Iznajmljivanje uspesno kreirano", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
            koordinator.Koordinator.getInstance().osveziTabeluIznajmljivanja();
            kif.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(kif, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    

}
