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
public class Klijent implements ApstraktniDomenskiObjekat {
    
    private int id;
    private String ime;
    private String prezime;
    private long brojTelefona;
    private Mesto mesto;

    public Klijent() {
    }

    public Klijent(int id, String ime, String prezime, long brojtelefona, Mesto mesto) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojtelefona;
        this.mesto = mesto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public long getBrojtelefona() {
        return brojTelefona;
    }

    public void setBrojtelefona(long brojtelefona) {
        this.brojTelefona = brojtelefona;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    @Override
    public String toString() {
        return ime + " " + prezime + " " + brojTelefona;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
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
    public String vratiNazivTabele() {
        return "klijent";
    }

    @Override
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
    public String vratiKoloneZaUbacivanje() {
        return "ime, prezime, brojTelefona, mesto";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + ime + "', '" + prezime + "', " + brojTelefona + ", " + mesto.getId();
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
     
        return "ime='" + ime + "', prezime='" + prezime + "', brojTelefona=" + brojTelefona + ", mesto=" + mesto.getId();
    }
    
    
}
