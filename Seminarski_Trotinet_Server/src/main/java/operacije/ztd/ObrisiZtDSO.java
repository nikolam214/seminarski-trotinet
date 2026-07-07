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
public class ObrisiZtDSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof ZtD)) {
            throw new Exception("Sistem nije mogao da obrise ZtD");
        }
    }

    @Override
    protected void izvsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.delete((ZtD) objekat);
    }
    
}
