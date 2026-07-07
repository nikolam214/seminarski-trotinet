/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.iznajmljivanje;

import domen.Iznajmljivanje;
import domen.StavkaIznajmljivanja;
import java.util.ArrayList;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikola
 */
public class UcitajIznajmljivanjaSO extends ApstraktnaGenerickaOperacija {

    List<Iznajmljivanje> iznajmljivanja;

    

    @Override
    protected void preduslovi(Object objekat) throws Exception {

    }
    
    @Override
    protected void izvsiOperaciju(Object objekat, String kljuc) throws Exception {
        System.out.println("Ucitavam iznajmljivanja...");
        iznajmljivanja = (List<Iznajmljivanje>) (List<?>) broker.getAll(new Iznajmljivanje(),
            " JOIN zaposleni ON iznajmljivanje.zaposleni = zaposleni.id" +
            " JOIN klijent ON iznajmljivanje.klijent = klijent.id");
        System.out.println("Ucitano " + iznajmljivanja.size() + " iznajmljivanja");

        List<Iznajmljivanje> kopija = new ArrayList<>(iznajmljivanja);
        for (Iznajmljivanje i : kopija) {
            System.out.println("Ucitavam stavke za: " + i.getId());
            try {
                List<StavkaIznajmljivanja> stavke = (List<StavkaIznajmljivanja>) (List<?>) broker.getAll(
                    new StavkaIznajmljivanja(),
                    " JOIN trotinet ON stavkaIznajmljivanja.trotinet = trotinet.id" +
                    " WHERE stavkaIznajmljivanja.iznajmljivanje = " + i.getId()
                );
                System.out.println("Ucitano stavki: " + stavke.size());
                i.setStavkeIznajmljivanja(stavke);
            } catch (Exception e) {
                System.out.println("GRESKA pri ucitavanju stavki: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    public List<Iznajmljivanje> getIznajmljivanja() {
        return iznajmljivanja;
    }
    
}
