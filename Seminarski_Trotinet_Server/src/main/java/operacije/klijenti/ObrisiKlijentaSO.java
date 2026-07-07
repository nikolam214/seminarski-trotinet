/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.klijenti;

import domen.Klijent;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * 
 * Sistemska operacija za brisanje postojeceg klijenta u bazi podataka.
 *
 * @author nikola
 */
public class ObrisiKlijentaSO extends ApstraktnaGenerickaOperacija {

    /**
     * Proverava preduslove za izvrsavanje operacije.
     *
     * @param objekat Objekat nad kojim se operacija izvrsava
     * @throws Exception ako objekat nije odgovarajuce klase ili ne ispunjava uslove operacije
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Klijent)){
            throw new Exception("Sistem nije mogao da obrise klijenta");
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
        broker.delete((Klijent)objekat);
    }
    
}
