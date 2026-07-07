/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.mesto;

import domen.Mesto;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *Sistemska operacija za ucitavanje svih objekata klase mesta iz baze podataka.
 * 
 * @author nikola
 */
public class UcitajMestoSO extends ApstraktnaGenerickaOperacija {
    
    List<Mesto> mesta;

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
        mesta = (List<Mesto>) broker.getAll(new Mesto(), null);
    }

    public List<Mesto> getMesta() {
        return mesta;
    }
}
