/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.trotinet;

import domen.Trotinet;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za azuriranje postojeceg trotineta u bazi podataka.
 *
 * @author nikola
 */
public class AzurirajTrotinetSO extends ApstraktnaGenerickaOperacija {
    
    
    /**
     * Proverava preduslove za izvrsavanje operacije.
     *
     * @param objekat Objekat nad kojim se operacija izvrsava
     * @throws Exception ako objekat nije odgovarajuce klase ili ne ispunjava uslove operacije
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Trotinet)) {
            throw new Exception("Sistem nije mogao da azurira trotinet");
        }
        Trotinet t = (Trotinet) objekat;
        if (t.getModelTrotineta() == null || t.getModelTrotineta().isEmpty() || t.getModelTrotineta().length() < 2) {
            throw new Exception("Model mora imati najmanje 2 karaktera");
        }
        if (t.getCenaPoMinutu() <= 0) {
            throw new Exception("Cena po minutu mora biti pozitivan broj");
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
        broker.edit((Trotinet) objekat);
    }
}
