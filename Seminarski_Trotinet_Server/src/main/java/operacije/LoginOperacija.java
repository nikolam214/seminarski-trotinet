/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import domen.ApstraktniDomenskiObjekat;
import domen.Zaposleni;
import java.util.List;

/**
 * 
 * Sistemska operacija za prijavu zaposlenog na sistem.
 * Proverava da li postoji zaposleni sa prosledjenim korisnickim imenom i sifrom.
 * @author nikola
 */
public class LoginOperacija extends ApstraktnaGenerickaOperacija{

    Zaposleni zaposleni;
    /**
     * Proverava preduslove za izvrsavanje operacije.
     *
     * @param objekat Objekat nad kojim se operacija izvrsava
     * @throws Exception ako objekat nije odgovarajuce klase ili ne ispunjava uslove operacije
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Zaposleni)){
            throw new Exception("Sistem nije mogao da pronadje zaposlenog");
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
        List<ApstraktniDomenskiObjekat> sviZaposleni = broker.getAll((Zaposleni) objekat, null);
        System.out.println("Klasa login operacija" + sviZaposleni);

        if (sviZaposleni.contains((Zaposleni) objekat)) {
            for (ApstraktniDomenskiObjekat z : sviZaposleni) {
                if (z.equals((Zaposleni) objekat)) {
                    zaposleni = (Zaposleni) z;
                    return;
                }
            }
        } else {
            zaposleni = null;
        }
    }

    /**
     * Vraca pronadjenog zaposlenog nakon uspesne prijave.
     *
     * @return Pronadjeni zaposleni ili null ako prijava nije uspela
     */
    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }
    
}
