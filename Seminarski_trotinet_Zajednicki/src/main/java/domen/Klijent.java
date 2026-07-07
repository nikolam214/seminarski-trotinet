/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *Predstavlja klijenta koji iznajmljuje trotinete.
 * Klijent ima id, ime, prezime, broj telefona i mesto u kom zivi.
 * 
 * @author nikola
 */
public class Klijent implements ApstraktniDomenskiObjekat {
    
    /**
     * Identifikator klijenta kao ceo broj
     */
    private int id;
    /**
     * Ime klijenta kao string
     */
    private String ime;
    /**
     * Prezime klijenta kao string
     */
    private String prezime;
    /**
     * Broj telefona klijenta kao ceo broj
     */
    private long brojTelefona;
    /**
     * Mesto u kome klijent zivi kao objekat klase Mesto
     */
    private Mesto mesto;

    /**
     * Pravi nov objekat klase Klijent sa podrazumevanim vrednostima atributa.
     */
    public Klijent() {
    }

    /**
     * Pravi nov objekat klase Klijent i postavlja vrednosti svih atributa
     * pozivom odgovarajucih set metoda.
     *
     * @param id Vrednost za atribut id
     * @param ime Vrednost za atribut ime
     * @param prezime Vrednost za atribut prezime
     * @param brojtelefona Vrednost za atribut brojtelefona
     * @param mesto Vrednost za atribut mesto
     * @throws IllegalArgumentException ako neka od prosledjenih vrednosti nije validna
     */
    public Klijent(int id, String ime, String prezime, long brojtelefona, Mesto mesto) {
        setId(id);
        setIme(ime);
        setPrezime(prezime);
        setBrojtelefona(brojtelefona);
        setMesto(mesto);
    }

    /**
     * Vraca identifikator klijenta kao ceo broj.
     *
     * @return Identifikator klijenta kao ceo broj
     */
    public int getId() {
        return id;
    }

    /**
     * Postavlja identifikator klijenta kao ceo broj.
     *
     * @param id Identifikator klijenta kao ceo broj
     * @throws IllegalArgumentException ako id nije veci od nule
     */
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id mora biti veci od nule");
        }
        this.id = id;
    }

    /**
     * Vraca ime klijenta kao string.
     *
     * @return Ime klijenta kao string
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime klijenta kao string.
     *
     * @param ime Ime klijenta kao string
     * @throws IllegalArgumentException ako je ime null ili prazno
     */
    public void setIme(String ime) {
        if (ime == null || ime.isEmpty()) {
            throw new IllegalArgumentException("Ime ne sme biti null ili prazno");
        }
        this.ime = ime;
    }

    /**
     * Vraca prezime klijenta kao string.
     *
     * @return Prezime klijenta kao string
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Postavlja prezime klijenta kao string.
     *
     * @param prezime Prezime klijenta kao string
     * @throws IllegalArgumentException ako je prezime null ili prazno
     */
    public void setPrezime(String prezime) {
        if (prezime == null || prezime.isEmpty()) {
            throw new IllegalArgumentException("Prezime ne sme biti null ili prazno");
        }
        this.prezime = prezime;
    }

    /**
     * Vraca broj telefona klijenta kao ceo broj.
     *
     * @return Broj telefona klijenta kao ceo broj
     */
    public long getBrojtelefona() {
        return brojTelefona;
    }

    /**
     * Postavlja broj telefona klijenta kao ceo broj.
     *
     * @param brojtelefona Broj telefona klijenta kao ceo broj
     * @throws IllegalArgumentException ako broj telefona nije veci od nule
     */
    public void setBrojtelefona(long brojtelefona) {
        if (brojtelefona <= 0) {
            throw new IllegalArgumentException("Broj telefona mora biti veci od nule");
        }
        this.brojTelefona = brojtelefona;
    }

    /**
     * Vraca mesto u kome klijent zivi kao objekat klase Mesto.
     *
     * @return Mesto u kome klijent zivi kao objekat klase Mesto
     */
    public Mesto getMesto() {
        return mesto;
    }

    /**
     * Postavlja mesto u kome klijent zivi kao objekat klase Mesto.
     *
     * @param mesto Mesto u kome klijent zivi kao objekat klase Mesto
     * @throws IllegalArgumentException ako je mesto null
     */
    public void setMesto(Mesto mesto) {
        if (mesto == null) {
            throw new IllegalArgumentException("Mesto ne sme biti null");
        }
        this.mesto = mesto;
    }

    @Override
    /**
     * Vraca string reprezentaciju objekta klase Klijent.
     *
     * @return String reprezentacija objekta kao string
     */
    public String toString() {
        return ime + " " + prezime + " " + brojTelefona;
    }

    @Override
    /**
     * Vraca hash kod objekta izracunat na osnovu atributa koji se koriste u equals metodi.
     *
     * @return Hash kod objekta kao ceo broj
     */
    public int hashCode() {
        return Objects.hash(id, ime, prezime);
    }

    @Override
    /**
     * Poredi dva objekta klase Klijent.
     *
     * @param obj Objekat sa kojim se poredi
     * @return true ako su objekti isti, false u suprotnom
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Klijent other = (Klijent) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        return Objects.equals(this.prezime, other.prezime);
    }

    @Override
    /**
     * Vraca naziv tabele u bazi podataka koja odgovara klasi Klijent.
     *
     * @return Naziv tabele kao string
     */
    public String vratiNazivTabele() {
        return "klijent";
    }

    @Override
    /**
     * Vraca listu objekata klase Klijent procitanih iz prosledjenog ResultSet-a.
     *
     * @param rs ResultSet sa podacima iz baze
     * @return Lista procitanih objekata
     * @throws Exception ako dodje do greske pri citanju podataka
     */
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
     while (rs.next()) {
            int idMesto = rs.getInt("mesto.id");
            String nazivMesta = rs.getString("mesto.naziv");
            int postanskiBroj = rs.getInt("mesto.postanskiBroj");
            Mesto mesto = new Mesto(idMesto, nazivMesta, postanskiBroj);

            int id = rs.getInt("klijent.id");
            String ime = rs.getString("klijent.ime");
            String prezime = rs.getString("klijent.prezime");
            long brojTelefona = rs.getLong("klijent.brojTelefona");

            Klijent klijent = new Klijent(id, ime, prezime, brojTelefona, mesto);
            lista.add(klijent);
        }
        return lista;   
    }

    @Override
    /**
     * Vraca nazive kolona za ubacivanje novog reda u tabelu.
     *
     * @return Nazivi kolona kao string
     */
    public String vratiKoloneZaUbacivanje() {
        return "ime, prezime, brojTelefona, mesto";
    }

    @Override
    /**
     * Vraca vrednosti atributa za ubacivanje novog reda u tabelu.
     *
     * @return Vrednosti atributa kao string
     */
    public String vratiVrednostiZaUbacivanje() {
        return "'" + ime + "', '" + prezime + "', " + brojTelefona + ", " + mesto.getId();
    }

    @Override
    /**
     * Vraca uslov sa primarnim kljucem za identifikaciju reda u tabeli.
     *
     * @return Uslov sa primarnim kljucem kao string
     */
    public String vratiPrimarniKljuc() {
        return "id=" + id;
    }

    @Override
    /**
     * Vraca objekat klase Klijent procitan iz prosledjenog ResultSet-a.
     *
     * @param rs ResultSet sa podacima iz baze
     * @return Procitan objekat
     * @throws Exception ako dodje do greske pri citanju podataka
     */
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    /**
     * Vraca vrednosti atributa za izmenu postojeceg reda u tabeli.
     *
     * @return Vrednosti atributa kao string
     */
    public String vratiVrednostiZaIzmenu() {
     
        return "ime='" + ime + "', prezime='" + prezime + "', brojTelefona=" + brojTelefona + ", mesto=" + mesto.getId();
    }
     
}
