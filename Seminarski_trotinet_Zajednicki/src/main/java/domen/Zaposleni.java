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
public class Zaposleni implements ApstraktniDomenskiObjekat{
    
    private int id;
    private String korisnickoIme;
    private String sifra;
    private String ime;
    private String prezime;

    public Zaposleni() {
    }

    public Zaposleni(int id, String korisnickoIme, String sifra, String ime, String prezime) {
        setId(id);
        setKorisnickoIme(korisnickoIme);
        setSifra(sifra);
        setIme(ime);
        setPrezime(prezime);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id mora biti veci od nule");
        }
        this.id = id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        if (korisnickoIme == null || korisnickoIme.isEmpty()) {
            throw new IllegalArgumentException("Korisnicko ime ne sme biti null ili prazno");
        }
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        if (sifra == null || sifra.isEmpty()) {
            throw new IllegalArgumentException("Sifra ne sme biti null ili prazna");
        }
        this.sifra = sifra;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        if (ime == null || ime.isEmpty()) {
            throw new IllegalArgumentException("Ime ne sme biti null ili prazno");
        }
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        if (prezime == null || prezime.isEmpty()) {
            throw new IllegalArgumentException("Prezime ne sme biti null ili prazno");
        }
        this.prezime = prezime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(korisnickoIme, sifra);
    }

    @Override
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
        final Zaposleni other = (Zaposleni) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.sifra, other.sifra);
    }

 
    @Override
    public String toString() {
        return ime +" "+prezime;
    }

    @Override
    public String vratiNazivTabele() {
        return "zaposleni";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista= new ArrayList<>();
        while(rs.next()){
            int id= rs.getInt("zaposleni.id");
            String korisnickoIme= rs.getString("zaposleni.korisnickoIme");
            String sifra= rs.getString("zaposleni.sifra");
            String ime= rs.getString("zaposleni.ime");
            String prezime= rs.getString("zaposleni.prezime");
            
            Zaposleni z= new Zaposleni(id, korisnickoIme, sifra, ime, prezime);
            lista.add(z);
            
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "korisnickoIme, sifra, ime, prezime";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        
        return "'"+korisnickoIme+"','"+sifra+"','"+ime+"','"+prezime+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "id=" + id;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {

        return "korisnickoIme='" + korisnickoIme + "', sifra='" + sifra + "', ime='" + ime + "', prezime='" + prezime + "'";
    }
    
    
}
