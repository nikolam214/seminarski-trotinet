/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.trotinet;

import domen.Trotinet;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikola
 */
public class ObrisiTrotinetSO extends ApstraktnaGenerickaOperacija {
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Trotinet)) {
            throw new Exception("Sistem nije mogao da obrise trotinet");
        }
    }

    @Override
    protected void izvsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.delete((Trotinet) objekat);
    }
}
