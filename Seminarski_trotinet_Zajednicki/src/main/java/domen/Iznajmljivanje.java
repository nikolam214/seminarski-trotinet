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
 *
 * @author nikola
 */
public class Iznajmljivanje implements ApstraktniDomenskiObjekat{
 
    private int id;
    private double ukupnaCena;
    private Zaposleni zaposleni;
    private Klijent klijent;
    private List<StavkaIznajmljivanja> stavkeIznajmljivanja;

    public Iznajmljivanje() {
        this.stavkeIznajmljivanja = new ArrayList<>();
    }

    public Iznajmljivanje(int id, double ukupnaCena, Zaposleni zaposleni, Klijent klijent) {
        this.id = id;
        this.ukupnaCena = ukupnaCena;
        this.zaposleni = zaposleni;
        this.klijent = klijent;
        this.stavkeIznajmljivanja = new ArrayList<>();
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public List<StavkaIznajmljivanja> getStavkeIznajmljivanja() {
        return stavkeIznajmljivanja;
    }

    public void setStavkeIznajmljivanja(List<StavkaIznajmljivanja> stavkeIznajmljivanja) {
        this.stavkeIznajmljivanja = stavkeIznajmljivanja;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

   @Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    final Iznajmljivanje other = (Iznajmljivanje) obj;
    return this.id == other.id;
}


    @Override
    public String toString() {
        return "Iznajmljivanje{" + "ukupnaCena=" + ukupnaCena + ", zaposleni=" + zaposleni + ", klijent=" + klijent + '}';
    }

    @Override
    public String vratiNazivTabele() {
        return "iznajmljivanje";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
    while (rs.next()) {
        
        int idZaposleni = rs.getInt("zaposleni.id");
        String korIme = rs.getString("zaposleni.korisnickoIme");
        String sifra = rs.getString("zaposleni.sifra");
        String imeZ = rs.getString("zaposleni.ime");
        String prezimeZ = rs.getString("zaposleni.prezime");
        Zaposleni zaposleni = new Zaposleni(idZaposleni, korIme, sifra, imeZ, prezimeZ);

        int idKlijent = rs.getInt("klijent.id");
        String imeK = rs.getString("klijent.ime");
        String prezimeK = rs.getString("klijent.prezime");
        long telK = rs.getLong("klijent.brojTelefona");
        Klijent klijent = new Klijent(idKlijent, imeK, prezimeK, telK, null);

        int id = rs.getInt("iznajmljivanje.id");
        double ukupnaCena = rs.getDouble("iznajmljivanje.ukupnaCena");

        Iznajmljivanje iznajmljivanje = new Iznajmljivanje(id, ukupnaCena, zaposleni, klijent);
        lista.add(iznajmljivanje);
    }
    return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ukupnaCena, zaposleni, klijent";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return ukupnaCena + ", " + zaposleni.getId() + ", " + klijent.getId();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "id="+id;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ukupnaCena=" + ukupnaCena + ", zaposleni=" + zaposleni.getId() + ", klijent=" + klijent.getId();
    }

    
    
    
    
}
