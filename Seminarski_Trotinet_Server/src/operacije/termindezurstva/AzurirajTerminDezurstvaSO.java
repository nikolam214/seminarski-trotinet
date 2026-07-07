/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.termindezurstva;

import domen.TerminDezurstva;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikola
 */
public class AzurirajTerminDezurstvaSO extends ApstraktnaGenerickaOperacija {
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof TerminDezurstva)) {
            throw new Exception("Sistem nije mogao da azurira termin dezurstva");
        }
        TerminDezurstva t = (TerminDezurstva) objekat;
        if (t.getTipSmene() == null || t.getTipSmene().isEmpty()) {
            throw new Exception("Tip smene ne sme biti prazan");
        }
        if (t.getVremePocetka() == null) {
            throw new Exception("Vreme pocetka ne sme biti prazno");
        }
        if (t.getTrajanje() <= 0) {
            throw new Exception("Trajanje mora biti pozitivan broj");
        }
    }

    @Override
    protected void izvsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.edit((TerminDezurstva) objekat);
    }
}
