/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.klijenti;

import domen.Klijent;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *Sistemska operacija za dodavanje novog klijenta u bazi podataka.
 * 
 * @author nikola
 */
public class DodajKlijentaSO extends ApstraktnaGenerickaOperacija {
    

    
    /**
     * Proverava preduslove za izvrsavanje operacije.
     *
     * @param objekat Objekat nad kojim se operacija izvrsava
     * @throws Exception ako objekat nije odgovarajuce klase ili ne ispunjava uslove operacije
     */
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Klijent)) {
            throw new Exception("Sistem nije mogao da doda klijenta");
        }

        Klijent k = (Klijent) objekat;

        if (k.getIme() == null || k.getIme().isEmpty() || k.getIme().length() < 2) {
            throw new Exception("Ime mora imati najmanje 2 karaktera");
        }

        if (k.getPrezime() == null || k.getPrezime().isEmpty() || k.getPrezime().length() < 2) {
            throw new Exception("Prezime mora imati najmanje 2 karaktera");
        }

        if (k.getBrojtelefona() <= 0) {
            throw new Exception("Broj telefona mora biti pozitivan broj");
        }

        if (k.getMesto() == null) {
            throw new Exception("Mesto mora biti izabrano");
        }
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
        broker.add((Klijent) objekat);
    }


}
