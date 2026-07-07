/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.termindezurstva;

import domen.TerminDezurstva;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * Sistemska operacija za ucitavanje svih objekata klase TerminDezurstva iz baze podataka.
 * @author nikola
 */
public class UcitajTermineDezurstvaSO extends ApstraktnaGenerickaOperacija {
    
    List<TerminDezurstva> termini;

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
        try {
            termini = (List<TerminDezurstva>) (List<?>) broker.getAll(new TerminDezurstva(), null);
        } catch (Exception e) {
            System.out.println("GRESKA: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public List<TerminDezurstva> getTermini() {
        return termini;
    }
}
