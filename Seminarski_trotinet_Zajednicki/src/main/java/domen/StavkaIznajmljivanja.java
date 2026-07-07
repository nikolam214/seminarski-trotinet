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
public class StavkaIznajmljivanja implements ApstraktniDomenskiObjekat{
 
    private int rb;
    private Iznajmljivanje iznajmljivanje;
    private Date vremeUzimanja;
    private Date vremeVracanja;
    private double cenaPoMinutu;
    private double iznos;
    private Trotinet trotinet;

    public StavkaIznajmljivanja() {
    }

    public StavkaIznajmljivanja(int rb, Iznajmljivanje iznajmljivanje, Date vremeuzimanja, Date vremeVracanja, double cenaPoMinutu, double iznos, Trotinet trotinet) {
        this.rb = rb;
        this.iznajmljivanje = iznajmljivanje;
        this.vremeUzimanja = vremeuzimanja;
        this.vremeVracanja = vremeVracanja;
        this.cenaPoMinutu = cenaPoMinutu;
        this.iznos = iznos;
        this.trotinet = trotinet;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public Iznajmljivanje getIznajmljivanje() {
        return iznajmljivanje;
    }

    public void setIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        this.iznajmljivanje = iznajmljivanje;
    }

    public Date getVremeuzimanja() {
        return vremeUzimanja;
    }

    public void setVremeuzimanja(Date vremeuzimanja) {
        this.vremeUzimanja = vremeuzimanja;
    }

    public Date getVremeVracanja() {
        return vremeVracanja;
    }

    public void setVremeVracanja(Date vremeVracanja) {
        this.vremeVracanja = vremeVracanja;
    }

    public double getCenaPoMinutu() {
        return cenaPoMinutu;
    }

    public void setCenaPoMinutu(double cenaPoMinutu) {
        this.cenaPoMinutu = cenaPoMinutu;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public Trotinet getTrotinet() {
        return trotinet;
    }

    public void setTrotinet(Trotinet trotinet) {
        this.trotinet = trotinet;
    }

    @Override
    public String toString() {
        return "StavkaIznajmljivanja{" + "iznajmljivanje=" + iznajmljivanje + ", vremeuzimanja=" + vremeUzimanja + ", vremeVracanja=" + vremeVracanja + ", cenaPoMinutu=" + cenaPoMinutu + ", iznos=" + iznos + ", trotinet=" + trotinet + '}';
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
    final StavkaIznajmljivanja other = (StavkaIznajmljivanja) obj;
    if (this.rb != other.rb) return false;
    return Objects.equals(this.iznajmljivanje, other.iznajmljivanje);
}

    @Override
    public String vratiNazivTabele() {
        return "stavkaIznajmljivanja";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
    while (rs.next()) {
        int idIznajmljivanje = rs.getInt("stavkaIznajmljivanja.iznajmljivanje");
        Iznajmljivanje iznajmljivanje = new Iznajmljivanje();
        iznajmljivanje.setId(idIznajmljivanje);

        int idTrotinet = rs.getInt("trotinet.id");
        String model = rs.getString("trotinet.modelTrotineta");
        double cenaMin = rs.getDouble("trotinet.cenaPoMinutu");
        Trotinet trotinet = new Trotinet(idTrotinet, model, cenaMin);

        int rb = rs.getInt("stavkaIznajmljivanja.rb");
        java.util.Date uzet = rs.getTimestamp("stavkaIznajmljivanja.vremeUzimanja");
        java.util.Date vracen = rs.getTimestamp("stavkaIznajmljivanja.vremeVracanja");
        double cenaPoMinutu = rs.getDouble("stavkaIznajmljivanja.cenaPoMinutu");
        double iznos = rs.getDouble("stavkaIznajmljivanja.iznos");

        StavkaIznajmljivanja stavka = new StavkaIznajmljivanja(rb, iznajmljivanje, uzet, vracen, cenaPoMinutu, iznos, trotinet);
        lista.add(stavka);
    }
    return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "iznajmljivanje, rb, vremeUzimanja, vremeVracanja, cenaPoMinutu, iznos, trotinet";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return iznajmljivanje.getId() + ", " +
               rb + ", " +
               "'" + new java.sql.Timestamp(vremeUzimanja.getTime()) + "', " +
               "'" + new java.sql.Timestamp(vremeVracanja.getTime()) + "', " +
               cenaPoMinutu + ", " +
               iznos + ", " +
               trotinet.getId();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "iznajmljivanje=" + iznajmljivanje.getId() + " AND rb=" + rb;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "vremeUzimanja='" + new java.sql.Timestamp(vremeUzimanja.getTime()) + 
               "', vremeVracanja='" + new java.sql.Timestamp(vremeVracanja.getTime()) + 
               "', cenaPoMinutu=" + cenaPoMinutu + 
               ", iznos=" + iznos + 
               ", trotinet=" + trotinet.getId();
    }
    
    
    
    
}
