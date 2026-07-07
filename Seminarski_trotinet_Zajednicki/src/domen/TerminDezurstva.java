/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Objects;



/**
 *
 * @author nikola
 */
public class TerminDezurstva implements ApstraktniDomenskiObjekat{
    
    private int id;
    private String tipSmene;
    private Date vremePocetka;
    private int trajanje;

    public TerminDezurstva() {
    }

    public TerminDezurstva(int id, String tipSmene, Date vremePocetka, int trajanje) {
        this.id = id;
        this.tipSmene = tipSmene;
        this.vremePocetka = vremePocetka;
        this.trajanje = trajanje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipSmene() {
        return tipSmene;
    }

    public void setTipSmene(String tipSmene) {
        this.tipSmene = tipSmene;
    }

    public Date getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(Date vremePocetka) {
        this.vremePocetka = vremePocetka;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
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
    final TerminDezurstva other = (TerminDezurstva) obj;
    return this.id == other.id;
}

    @Override
    public String toString() {
        if (vremePocetka == null) return tipSmene;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return tipSmene + " - " + sdf.format(vremePocetka);
    }

    @Override
    public String vratiNazivTabele() {
        return "terminDezurstva";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("terminDezurstva.id");
            String tipSmene = rs.getString("terminDezurstva.tipSmene");
            java.util.Date vremePocetka = rs.getTime("terminDezurstva.vremePocetka");
            int trajanje = rs.getInt("terminDezurstva.trajanje");

            TerminDezurstva termin = new TerminDezurstva(id, tipSmene, vremePocetka, trajanje);
            lista.add(termin);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "tipSmene, vremePocetka, trajanje";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + tipSmene + "', '" + new java.sql.Time(vremePocetka.getTime()) + "', " + trajanje;
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
        return "tipSmene='" + tipSmene + "', vremePocetka='" + new java.sql.Time(vremePocetka.getTime()) + "', trajanje=" + trajanje;
    }
    
    
}
