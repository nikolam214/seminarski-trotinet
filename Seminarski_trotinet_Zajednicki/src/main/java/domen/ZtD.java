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
 * Predstavlja vezu izmedju zaposlenog i termina dezurstva.
 *
 * @author nikola
 */
public class ZtD implements ApstraktniDomenskiObjekat{
    
    /**
     * Zaposleni koji dezura kao objekat klase Zaposleni
     */
    private Zaposleni zaposleni;
    /**
     * Termin u kome zaposleni dezura kao objekat klase TerminDezurstva
     */
    private TerminDezurstva terminDezurstva;
    /**
     * Datum dezurstva
     */
    private Date datum;
    /**
     * Napomena vezana za dezurstvo kao string
     */
    private String napomena;

    /**
     * Pravi nov objekat klase ZtD sa podrazumevanim vrednostima atributa.
     */
    public ZtD() {
    }

    /**
     * Pravi nov objekat klase ZtD i postavlja vrednosti svih atributa
     * pozivom odgovarajucih set metoda.
     *
     * @param zaposleni Vrednost za atribut zaposleni
     * @param terminDezurstva Vrednost za atribut terminDezurstva
     * @param datum Vrednost za atribut datum
     * @param napomena Vrednost za atribut napomena
     * @throws IllegalArgumentException ako neka od prosledjenih vrednosti nije validna
     */
    public ZtD(Zaposleni zaposleni, TerminDezurstva terminDezurstva, Date datum, String napomena) {
        setZaposleni(zaposleni);
        setTerminDezurstva(terminDezurstva);
        setDatum(datum);
        setNapomena(napomena);
    }

    /**
     * Vraca zaposleni koji dezura kao objekat klase Zaposleni.
     *
     * @return Zaposleni koji dezura kao objekat klase Zaposleni
     */
    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    /**
     * Postavlja zaposleni koji dezura kao objekat klase Zaposleni.
     *
     * @param zaposleni Zaposleni koji dezura kao objekat klase Zaposleni
     * @throws IllegalArgumentException ako je zaposleni null
     */
    public void setZaposleni(Zaposleni zaposleni) {
        if (zaposleni == null) {
            throw new IllegalArgumentException("Zaposleni ne sme biti null");
        }
        this.zaposleni = zaposleni;
    }

    /**
     * Vraca termin u kome zaposleni dezura kao objekat klase TerminDezurstva.
     *
     * @return Termin u kome zaposleni dezura kao objekat klase TerminDezurstva
     */
    public TerminDezurstva getTerminDezurstva() {
        return terminDezurstva;
    }

    /**
     * Postavlja termin u kome zaposleni dezura kao objekat klase TerminDezurstva.
     *
     * @param terminDezurstva Termin u kome zaposleni dezura kao objekat klase TerminDezurstva
     * @throws IllegalArgumentException ako je termin dezurstva null
     */
    public void setTerminDezurstva(TerminDezurstva terminDezurstva) {
        if (terminDezurstva == null) {
            throw new IllegalArgumentException("Termin dezurstva ne sme biti null");
        }
        this.terminDezurstva = terminDezurstva;
    }

    /**
     * Vraca datum dezurstva.
     *
     * @return Datum dezurstva
     */
    public Date getDatum() {
        return datum;
    }

    /**
     * Postavlja datum dezurstva.
     *
     * @param datum Datum dezurstva
     * @throws IllegalArgumentException ako je datum null
     */
    public void setDatum(Date datum) {
        if (datum == null) {
            throw new IllegalArgumentException("Datum ne sme biti null");
        }
        this.datum = datum;
    }

    /**
     * Vraca napomena vezana za dezurstvo kao string.
     *
     * @return Napomena vezana za dezurstvo kao string
     */
    public String getNapomena() {
        return napomena;
    }

    /**
     * Postavlja napomena vezana za dezurstvo kao string.
     *
     * @param napomena Napomena vezana za dezurstvo kao string
     * @throws IllegalArgumentException ako je napomena null ili prazna
     */
    public void setNapomena(String napomena) {
        if (napomena == null || napomena.isEmpty()) {
            throw new IllegalArgumentException("Napomena ne sme biti null ili prazna");
        }
        this.napomena = napomena;
    }

    @Override
    /**
     * Vraca string reprezentaciju objekta klase ZtD.
     *
     * @return String reprezentacija objekta kao string
     */
    public String toString() {
        return "ZtD{" + "zaposleni=" + zaposleni + ", terminDezurstva=" + terminDezurstva + ", datum=" + datum + ", napomena=" + napomena + '}';
    }

    @Override
    /**
     * Vraca hash kod objekta izracunat na osnovu atributa koji se koriste u equals metodi.
     *
     * @return Hash kod objekta kao ceo broj
     */
    public int hashCode() {
        return Objects.hash(zaposleni, terminDezurstva, datum);
    }

    @Override
    /**
     * Poredi dva objekta klase ZtD.
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
    /**
     * Vraca naziv tabele u bazi podataka koja odgovara klasi ZtD.
     *
     * @return Naziv tabele kao string
     */
    public String vratiNazivTabele() {
        return "ztd";
    }

    @Override
    /**
     * Vraca listu objekata klase ZtD procitanih iz prosledjenog ResultSet-a.
     *
     * @param rs ResultSet sa podacima iz baze
     * @return Lista procitanih objekata
     * @throws Exception ako dodje do greske pri citanju podataka
     */
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

            ZtD ztd = new ZtD();
            ztd.setZaposleni(zaposleni);
            ztd.setTerminDezurstva(termin);
            ztd.setDatum(datum);
            if (napomena != null && !napomena.isEmpty()) {
                ztd.setNapomena(napomena);
            }
            lista.add(ztd);
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
        return "zaposleni, TerminDezurstva, datum, napomena";
    }

    @Override
    /**
     * Vraca vrednosti atributa za ubacivanje novog reda u tabelu.
     *
     * @return Vrednosti atributa kao string
     */
    public String vratiVrednostiZaUbacivanje() {
        return zaposleni.getId() + ", " + 
           terminDezurstva.getId() + ", " + 
           "'" + new java.sql.Date(datum.getTime()) + "', " + 
           "'" + napomena + "'";
    }

    @Override
    /**
     * Vraca uslov sa primarnim kljucem za identifikaciju reda u tabeli.
     *
     * @return Uslov sa primarnim kljucem kao string
     */
    public String vratiPrimarniKljuc() {
        return "zaposleni=" + zaposleni.getId() + 
               " AND TerminDezurstva=" + terminDezurstva.getId() + 
               " AND datum='" + new java.sql.Date(datum.getTime()) + "'";
    }

    @Override
    /**
     * Vraca objekat klase ZtD procitan iz prosledjenog ResultSet-a.
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
        return "datum='" + new java.sql.Date(datum.getTime()) + "', napomena='" + napomena + "'";
    }
    
    
    
    
}
