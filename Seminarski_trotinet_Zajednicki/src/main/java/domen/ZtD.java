/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author nikola
 */
public class ZtD implements ApstraktniDomenskiObjekat{
    
    private Zaposleni zaposleni;
    private TerminDezurstva terminDezurstva;
    private Date datum;
    private String napomena;

    public ZtD() {
    }

    public ZtD(Zaposleni zaposleni, TerminDezurstva terminDezurstva, Date datum, String napomena) {
        this.zaposleni = zaposleni;
        this.terminDezurstva = terminDezurstva;
        this.datum = datum;
        this.napomena = napomena;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public TerminDezurstva getTerminDezurstva() {
        return terminDezurstva;
    }

    public void setTerminDezurstva(TerminDezurstva terminDezurstva) {
        this.terminDezurstva = terminDezurstva;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    @Override
    public String toString() {
        return "ZtD{" + "zaposleni=" + zaposleni + ", terminDezurstva=" + terminDezurstva + ", datum=" + datum + ", napomena=" + napomena + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final ZtD other = (ZtD) obj;
        if (!Objects.equals(this.zaposleni, other.zaposleni)) {
            return false;
        }
        if (!Objects.equals(this.terminDezurstva, other.terminDezurstva)) {
            return false;
        }
        return Objects.equals(this.datum, other.datum);
    }

    @Override
    public String vratiNazivTabele() {
        return "ztd";
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

        int idTermin = rs.getInt("terminDezurstva.id");
        String tipSmene = rs.getString("terminDezurstva.tipSmene");
        java.util.Date vremePocetka = rs.getTimestamp("terminDezurstva.vremePocetka");
        int trajanje = rs.getInt("terminDezurstva.trajanje");
        TerminDezurstva termin = new TerminDezurstva(idTermin, tipSmene, vremePocetka, trajanje);

        java.util.Date datum = rs.getDate("ztd.datum");
        String napomena = rs.getString("ztd.napomena");

        ZtD ztd = new ZtD(zaposleni, termin, datum, napomena);
        lista.add(ztd);
    }
    return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "zaposleni, TerminDezurstva, datum, napomena";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return zaposleni.getId() + ", " + 
           terminDezurstva.getId() + ", " + 
           "'" + new java.sql.Date(datum.getTime()) + "', " + 
           "'" + napomena + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "zaposleni=" + zaposleni.getId() + 
               " AND TerminDezurstva=" + terminDezurstva.getId() + 
               " AND datum='" + new java.sql.Date(datum.getTime()) + "'";
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "datum='" + new java.sql.Date(datum.getTime()) + "', napomena='" + napomena + "'";
    }
    
    
    
}
