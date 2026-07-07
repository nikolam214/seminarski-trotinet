/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.trotinet;

import domen.Trotinet;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *Sistemska operacija za ucitavanje svih objekata klase Trotinet iz baze podataka.
 * 
 * @author nikola
 */
public class UcitajTrotineteSO extends ApstraktnaGenerickaOperacija {
    
    List<Trotinet> trotineti;

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
        trotineti = (List<Trotinet>) (List<?>) broker.getAll(new Trotinet(), null);
    }

    public List<Trotinet> getTrotineti() {
        return trotineti;
    }
}
