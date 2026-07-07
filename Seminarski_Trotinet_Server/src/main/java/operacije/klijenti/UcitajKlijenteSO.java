/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.klijenti;

import domen.Klijent;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *Sistemska operacija za ucitavanje svih objekata klase Klijent iz baze podataka.
 * 
 * @author nikola
 */
public class UcitajKlijenteSO extends ApstraktnaGenerickaOperacija {
    List<Klijent> klijenti;
    
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
        klijenti = broker.getAll(new Klijent(), " JOIN mesto ON klijent.mesto = mesto.id");
    }

    public List<Klijent> getKlijenti() {
        return klijenti;
    }

 
    
}
