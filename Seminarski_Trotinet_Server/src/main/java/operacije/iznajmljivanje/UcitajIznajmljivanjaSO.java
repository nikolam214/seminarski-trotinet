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
 * Sistemska operacija za ucitavanje svih objekata klase Iznajmljivanje iz baze podataka.
 *
 * @author nikola
 */
public class UcitajIznajmljivanjaSO extends ApstraktnaGenerickaOperacija {

    List<Iznajmljivanje> iznajmljivanja;

    

    /**
     * Proverava preduslove za izvrsavanje operacije.
     *
     * @param objekat Objekat nad kojim se operacija izvrsava
     * @throws Exception ako objekat nije odgovarajuce klase ili ne ispunjava uslove operacije
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {

    }
    
    /**
     * Izvrsava konkretnu operaciju nad prosledjenim objektom.
     *
     * @param objekat Objekat nad kojim se operacija izvrsava
     * @param kljuc Dodatni kriterijum operacije
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
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
