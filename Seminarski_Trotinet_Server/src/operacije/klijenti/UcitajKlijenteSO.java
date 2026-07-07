/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.klijenti;

import domen.Klijent;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikola
 */
public class UcitajKlijenteSO extends ApstraktnaGenerickaOperacija {
    List<Klijent> klijenti;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvsiOperaciju(Object objekat, String kljuc) throws Exception {
        klijenti = broker.getAll(new Klijent(), " JOIN mesto ON klijent.mesto = mesto.id");
    }

    public List<Klijent> getKlijenti() {
    return klijenti;
}

 
    
}
