/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.iznajmljivanje;

import domen.Iznajmljivanje;
import domen.StavkaIznajmljivanja;
import operacije.ApstraktnaGenerickaOperacija;
import repository.db.impl.DbRepositoryGeneric;

/**
 *
 * @author nikola
 */
public class KreirajIznajmljivanjeSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        
        if (objekat == null || !(objekat instanceof Iznajmljivanje)) {
            throw new Exception("Sistem nije mogao da kreira iznajmljivanje");
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
        broker.add(i);

        int id = ((DbRepositoryGeneric) broker).vratiMaxId(i);
        i.setId(id);

        int rb = 1;
        for (StavkaIznajmljivanja stavka : i.getStavkeIznajmljivanja()) {
            stavka.setRb(rb++);
            stavka.setIznajmljivanje(i);
            broker.add(stavka);
        }
    }
    
    
}
