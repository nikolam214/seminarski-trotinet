/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.ztd;

import domen.ZtD;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikola
 */
public class DodajZtDSO extends ApstraktnaGenerickaOperacija {
    
    
    
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

    @Override
    protected void izvsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.add((ZtD) objekat);
    }
}
