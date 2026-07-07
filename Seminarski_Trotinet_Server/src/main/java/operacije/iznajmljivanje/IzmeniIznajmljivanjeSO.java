/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.iznajmljivanje;

import domen.Iznajmljivanje;
import domen.StavkaIznajmljivanja;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikola
 */
public class IzmeniIznajmljivanjeSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Iznajmljivanje)) {
            throw new Exception("Sistem nije mogao da izmeni iznajmljivanje");
        }
        Iznajmljivanje i = (Iznajmljivanje) objekat;
        if (i.getKlijent() == null) {
            throw new Exception("Klijent mora biti izabran");
        }
        if (i.getZaposleni() == null) {
            throw new Exception("Zaposleni mora biti izabran");
        }
        if (i.getStavkeIznajmljivanja() == null || i.getStavkeIznajmljivanja().isEmpty()) {
            throw new Exception("Iznajmljivanje mora imati najmanje jednu stavku");
        }
    }

    @Override
    protected void izvsiOperaciju(Object objekat, String kljuc) throws Exception {
        Iznajmljivanje i = (Iznajmljivanje) objekat;
        broker.edit(i);

        for (StavkaIznajmljivanja stavka : i.getStavkeIznajmljivanja()) {
            broker.edit(stavka);
        }
    }
    
}
