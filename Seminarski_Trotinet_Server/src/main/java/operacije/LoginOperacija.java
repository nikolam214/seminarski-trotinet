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
 * @author nikola
 */
public class LoginOperacija extends ApstraktnaGenerickaOperacija{

    Zaposleni zaposleni;
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Zaposleni)){
            throw new Exception("Sistem nije mogao da pronadje zaposlenog");
        }
    }

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

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }
    
    
}
