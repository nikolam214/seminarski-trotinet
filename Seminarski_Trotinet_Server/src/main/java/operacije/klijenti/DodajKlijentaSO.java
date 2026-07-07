/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.klijenti;

import domen.Klijent;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikola
 */
public class DodajKlijentaSO extends ApstraktnaGenerickaOperacija {
    

    
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

    @Override
    protected void izvsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.add((Klijent) objekat);
    }

}
