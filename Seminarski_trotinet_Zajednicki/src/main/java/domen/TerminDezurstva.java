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
 * Predstavlja termin dezurstva.
 * Poseduje identifikator, tip smene, vreme pocetka i vreme trajanja.
 *
 * @author nikola
 */
public class TerminDezurstva implements ApstraktniDomenskiObjekat{
    
    /**
     * Identifikator termina dezurstva kao ceo broj
     */
    private int id;
    /**
     * Tip smene (npr. prva, druga) kao string
     */
    private String tipSmene;
    /**
     * Vreme pocetka smene kao datum
     */
    private Date vremePocetka;
    /**
     * Trajanje smene u satima kao ceo broj
     */
    private int trajanje;

    /**
     * Pravi nov objekat klase TerminDezurstva sa podrazumevanim vrednostima atributa.
     */
    public TerminDezurstva() {
    }

    /**
     * Pravi nov objekat klase TerminDezurstva i postavlja vrednosti svih atributa
     * pozivom odgovarajucih set metoda.
     *
     * @param id Vrednost za atribut id
     * @param tipSmene Vrednost za atribut tipSmene
     * @param vremePocetka Vrednost za atribut vremePocetka
     * @param trajanje Vrednost za atribut trajanje
     * @throws IllegalArgumentException ako neka od prosledjenih vrednosti nije validna
     */
    public TerminDezurstva(int id, String tipSmene, Date vremePocetka, int trajanje) {
        setId(id);
        setTipSmene(tipSmene);
        setVremePocetka(vremePocetka);
        setTrajanje(trajanje);
    }

    /**
     * Vraca identifikator termina dezurstva kao ceo broj.
     *
     * @return Identifikator termina dezurstva kao ceo broj
     */
    public int getId() {
        return id;
    }

    /**
     * Postavlja identifikator termina dezurstva kao ceo broj.
     *
     * @param id Identifikator termina dezurstva kao ceo broj
     * @throws IllegalArgumentException ako id nije veci od nule
     */
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id mora biti veci od nule");
        }
        this.id = id;
    }

    /**
     * Vraca tip smene (npr. prva, druga) kao string.
     *
     * @return Tip smene (npr. prva, druga) kao string
     */
    public String getTipSmene() {
        return tipSmene;
    }

    /**
     * Postavlja tip smene (npr. prva, druga) kao string.
     *
     * @param tipSmene Tip smene (npr. prva, druga) kao string
     * @throws IllegalArgumentException ako je tip smene null ili prazan
     */
    public void setTipSmene(String tipSmene) {
        if (tipSmene == null || tipSmene.isEmpty()) {
            throw new IllegalArgumentException("Tip smene ne sme biti null ili prazan");
        }
        this.tipSmene = tipSmene;
    }

    /**
     * Vraca vreme pocetka smene kao datum.
     *
     * @return Vreme pocetka smene kao datum
     */
    public Date getVremePocetka() {
        return vremePocetka;
    }

    /**
     * Postavlja vreme pocetka smene kao datum.
     *
     * @param vremePocetka Vreme pocetka smene kao datum
     * @throws IllegalArgumentException ako je vreme pocetka null
     */
    public void setVremePocetka(Date vremePocetka) {
        if (vremePocetka == null) {
            throw new IllegalArgumentException("Vreme pocetka ne sme biti null");
        }
        this.vremePocetka = vremePocetka;
    }

    /**
     * Vraca trajanje smene u satima kao ceo broj.
     *
     * @return Trajanje smene u satima kao ceo broj
     */
    public int getTrajanje() {
        return trajanje;
    }

    /**
     * Postavlja trajanje smene u satima kao ceo broj.
     *
     * @param trajanje Trajanje smene u satima kao ceo broj
     * @throws IllegalArgumentException ako trajanje nije vece od nule
     */
    public void setTrajanje(int trajanje) {
        if (trajanje <= 0) {
            throw new IllegalArgumentException("Trajanje mora biti vece od nule");
        }
        this.trajanje = trajanje;
    }

    @Override
    /**
     * Vraca hash kod objekta izracunat na osnovu atributa koji se koriste u equals metodi.
     *
     * @return Hash kod objekta kao ceo broj
     */
    public int hashCode() {
        return Objects.hash(id);
    }

   @Override
/**
 * Poredi dva objekta klase TerminDezurstva.
 *
 * @param obj Objekat sa kojim se poredi
 * @return true ako su objekti isti, false u suprotnom
 */
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    final TerminDezurstva other = (TerminDezurstva) obj;
    return this.id == other.id;
}

    @Override
    /**
     * Vraca string reprezentaciju objekta klase TerminDezurstva.
     *
     * @return String reprezentacija objekta kao string
     */
    public String toString() {
        if (vremePocetka == null) return tipSmene;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return tipSmene + " - " + sdf.format(vremePocetka);
    }

    @Override
    /**
     * Vraca naziv tabele u bazi podataka koja odgovara klasi TerminDezurstva.
     *
     * @return Naziv tabele kao string
     */
    public String vratiNazivTabele() {
        return "terminDezurstva";
    }

    @Override
    /**
     * Vraca listu objekata klase TerminDezurstva procitanih iz prosledjenog ResultSet-a.
     *
     * @param rs ResultSet sa podacima iz baze
     * @return Lista procitanih objekata
     * @throws Exception ako dodje do greske pri citanju podataka
     */
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
    /**
     * Vraca nazive kolona za ubacivanje novog reda u tabelu.
     *
     * @return Nazivi kolona kao string
     */
    public String vratiKoloneZaUbacivanje() {
        return "tipSmene, vremePocetka, trajanje";
    }

    @Override
    /**
     * Vraca vrednosti atributa za ubacivanje novog reda u tabelu.
     *
     * @return Vrednosti atributa kao string
     */
    public String vratiVrednostiZaUbacivanje() {
        return "'" + tipSmene + "', '" + new java.sql.Time(vremePocetka.getTime()) + "', " + trajanje;
    }

    @Override
    /**
     * Vraca uslov sa primarnim kljucem za identifikaciju reda u tabeli.
     *
     * @return Uslov sa primarnim kljucem kao string
     */
    public String vratiPrimarniKljuc() {
        return "id="+id;
    }

    @Override
    /**
     * Vraca objekat klase TerminDezurstva procitan iz prosledjenog ResultSet-a.
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
        return "tipSmene='" + tipSmene + "', vremePocetka='" + new java.sql.Time(vremePocetka.getTime()) + "', trajanje=" + trajanje;
    }
    
    
}
