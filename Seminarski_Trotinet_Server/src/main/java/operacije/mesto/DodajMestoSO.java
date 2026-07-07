/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.mesto;

import domen.Mesto;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *Sistemska operacija za dodavanje novog mesta u bazi podataka.
 * @author nikola
 */
public class DodajMestoSO extends ApstraktnaGenerickaOperacija {

    /**
     * Proverava preduslove za izvrsavanje operacije.
     *
     * @param objekat Objekat nad kojim se operacija izvrsava
     * @throws Exception ako objekat nije odgovarajuce klase ili ne ispunjava uslove operacije
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Mesto)) {
            throw new Exception("Sistem nije mogao da doda mesto");
        }
        
        Mesto m = (Mesto) objekat;
        
        if (m.getNaziv() == null || m.getNaziv().isEmpty() || m.getNaziv().length() < 2) {
            throw new Exception("Naziv mora imati najmanje 2 karaktera");
        }
        if (m.getPostanskiBroj() <= 0) {
            throw new Exception("Postanski broj mora biti pozitivan broj");
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
        broker.add((Mesto) objekat);
    }
}
