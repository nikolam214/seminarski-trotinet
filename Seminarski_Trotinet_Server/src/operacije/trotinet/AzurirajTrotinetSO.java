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
public class AzurirajTrotinetSO extends ApstraktnaGenerickaOperacija {
    
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

    @Override
    protected void izvsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.edit((Trotinet) objekat);
    }
}
