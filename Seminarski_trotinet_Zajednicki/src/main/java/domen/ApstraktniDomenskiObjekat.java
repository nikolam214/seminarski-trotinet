/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * Predstavlja opsti domenski objekat koji se moze cuvati u bazi podataka.
 * Definise metode koje svaka domenska klasa mora da implementira kako bi genericki repozitorijum mogao da radi sa njom
 * (naziv tabele, kolone i vrednosti za ubacivanje, izmenu i citanje iz baze)
 * 
 * @author nikola
 */
public interface ApstraktniDomenskiObjekat extends Serializable {
    /**
     * Vraca naziv tabele u bazi podataka koja odgovara domenskoj klasi.
     *
     * @return Naziv tabele kao string
     */
    public String vratiNazivTabele();
    
    /**
     * Vraca listu domenskih objekata procitanih iz prosledjenog ResultSet-a.
     *
     * @param rs ResultSet sa podacima iz baze
     * @return Lista procitanih domenskih objekata
     * @throws Exception ako dodje do greske pri citanju podataka
     */
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception;
    
    /**
     * Vraca nazive kolona za ubacivanje novog reda u tabelu.
     *
     * @return Nazivi kolona kao string
     */
    public String vratiKoloneZaUbacivanje();
    
    /**
     * Vraca vrednosti atributa za ubacivanje novog reda u tabelu.
     *
     * @return Vrednosti atributa kao string
     */
    public String vratiVrednostiZaUbacivanje();
    
    /**
     * Vraca uslov sa primarnim kljucem za identifikaciju reda u tabeli.
     *
     * @return Uslov sa primarnim kljucem kao string
     */
    public String vratiPrimarniKljuc();
    
    /**
     * Vraca domenski objekat procitan iz prosledjenog ResultSet-a.
     *
     * @param rs ResultSet sa podacima iz baze
     * @return Procitan domenski objekat
     * @throws Exception ako dodje do greske pri citanju podataka
     */
    public ApstraktniDomenskiObjekat vratiObjekatIzRS (ResultSet rs) throws Exception;
    
    /**
     * Vraca vrednosti atributa za izmenu postojeceg reda u tabeli.
     *
     * @return Vrednosti atributa kao string
     */
    public String vratiVrednostiZaIzmenu();
    
}
