/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.ztd;

import domen.ZtD;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za dodavanje novog veze zaposleni-termin dezurstva (ZtD) u bazi podataka.
 *
 * @author nikola
 */
public class DodajZtDSO extends ApstraktnaGenerickaOperacija {
    
    
    
    /**
     * Proverava preduslove za izvrsavanje operacije.
     *
     * @param objekat Objekat nad kojim se operacija izvrsava
     * @throws Exception ako objekat nije odgovarajuce klase ili ne ispunjava uslove operacije
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof ZtD)) {
            throw new Exception("Sistem nije mogao da doda ZtD");
        }
        ZtD z = (ZtD) objekat;
        if (z.getZaposleni() == null) {
            throw new Exception("Zaposleni mora biti izabran");
        }
        if (z.getTerminDezurstva() == null) {
            throw new Exception("Termin dezurstva mora biti izabran");
        }
        if (z.getDatum() == null) {
            throw new Exception("Datum mora biti unet");
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
        broker.add((ZtD) objekat);
    }
}
