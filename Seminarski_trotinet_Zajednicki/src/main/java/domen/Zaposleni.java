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
 * Predstavlja zaposlenog koji radi na sistemu za iznajmljivanje trotineta.
 * Zaposleni ima identifikator, korisnicko ime, sifru, ime i prezime
 *
 * @author nikola
 */
public class Zaposleni implements ApstraktniDomenskiObjekat{
    
    /**
     * Identifikator zaposlenog kao ceo broj
     */
    private int id;
    /**
     * Korisnicko ime zaposlenog za prijavu na sistem kao string
     */
    private String korisnickoIme;
    /**
     * Sifra zaposlenog za prijavu na sistem kao string
     */
    private String sifra;
    /**
     * Ime zaposlenog kao string
     */
    private String ime;
    /**
     * Prezime zaposlenog kao string
     */
    private String prezime;

    /**
     * Pravi nov objekat klase Zaposleni sa podrazumevanim vrednostima atributa.
     */
    public Zaposleni() {
    }

    /**
     * Pravi nov objekat klase Zaposleni i postavlja vrednosti svih atributa
     * pozivom odgovarajucih set metoda.
     *
     * @param id Vrednost za atribut id
     * @param korisnickoIme Vrednost za atribut korisnickoIme
     * @param sifra Vrednost za atribut sifra
     * @param ime Vrednost za atribut ime
     * @param prezime Vrednost za atribut prezime
     * @throws IllegalArgumentException ako neka od prosledjenih vrednosti nije validna
     */
    public Zaposleni(int id, String korisnickoIme, String sifra, String ime, String prezime) {
        setId(id);
        setKorisnickoIme(korisnickoIme);
        setSifra(sifra);
        setIme(ime);
        setPrezime(prezime);
    }

    /**
     * Vraca identifikator zaposlenog kao ceo broj.
     *
     * @return Identifikator zaposlenog kao ceo broj
     */
    public int getId() {
        return id;
    }

    /**
     * Postavlja identifikator zaposlenog kao ceo broj.
     *
     * @param id Identifikator zaposlenog kao ceo broj
     * @throws IllegalArgumentException ako id nije veci od nule
     */
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id mora biti veci od nule");
        }
        this.id = id;
    }

    /**
     * Vraca korisnicko ime zaposlenog za prijavu na sistem kao string.
     *
     * @return Korisnicko ime zaposlenog za prijavu na sistem kao string
     */
    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    /**
     * Postavlja korisnicko ime zaposlenog za prijavu na sistem kao string.
     *
     * @param korisnickoIme Korisnicko ime zaposlenog za prijavu na sistem kao string
     * @throws IllegalArgumentException ako je korisnicko ime null ili prazno
     */
    public void setKorisnickoIme(String korisnickoIme) {
        if (korisnickoIme == null || korisnickoIme.isEmpty()) {
            throw new IllegalArgumentException("Korisnicko ime ne sme biti null ili prazno");
        }
        this.korisnickoIme = korisnickoIme;
    }

    /**
     * Vraca sifra zaposlenog za prijavu na sistem kao string.
     *
     * @return Sifra zaposlenog za prijavu na sistem kao string
     */
    public String getSifra() {
        return sifra;
    }

    /**
     * Postavlja sifra zaposlenog za prijavu na sistem kao string.
     *
     * @param sifra Sifra zaposlenog za prijavu na sistem kao string
     * @throws IllegalArgumentException ako je sifra null ili prazna
     */
    public void setSifra(String sifra) {
        if (sifra == null || sifra.isEmpty()) {
            throw new IllegalArgumentException("Sifra ne sme biti null ili prazna");
        }
        this.sifra = sifra;
    }

    /**
     * Vraca ime zaposlenog kao string.
     *
     * @return Ime zaposlenog kao string
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime zaposlenog kao string.
     *
     * @param ime Ime zaposlenog kao string
     * @throws IllegalArgumentException ako je ime null ili prazno
     */
    public void setIme(String ime) {
        if (ime == null || ime.isEmpty()) {
            throw new IllegalArgumentException("Ime ne sme biti null ili prazno");
        }
        this.ime = ime;
    }

    /**
     * Vraca prezime zaposlenog kao string.
     *
     * @return Prezime zaposlenog kao string
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Postavlja prezime zaposlenog kao string.
     *
     * @param prezime Prezime zaposlenog kao string
     * @throws IllegalArgumentException ako je prezime null ili prazno
     */
    public void setPrezime(String prezime) {
        if (prezime == null || prezime.isEmpty()) {
            throw new IllegalArgumentException("Prezime ne sme biti null ili prazno");
        }
        this.prezime = prezime;
    }

    @Override
    /**
     * Vraca hash kod objekta izracunat na osnovu atributa koji se koriste u equals metodi.
     *
     * @return Hash kod objekta kao ceo broj
     */
    public int hashCode() {
        return Objects.hash(korisnickoIme, sifra);
    }

    @Override
    /**
     * Poredi dva objekta klase Zaposleni.
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
        final Zaposleni other = (Zaposleni) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.sifra, other.sifra);
    }

 
    @Override
    /**
     * Vraca string reprezentaciju objekta klase Zaposleni.
     *
     * @return String reprezentacija objekta kao string
     */
    public String toString() {
        return ime +" "+prezime;
    }

    @Override
    /**
     * Vraca naziv tabele u bazi podataka koja odgovara klasi Zaposleni.
     *
     * @return Naziv tabele kao string
     */
    public String vratiNazivTabele() {
        return "zaposleni";
    }

    @Override
    /**
     * Vraca listu objekata klase Zaposleni procitanih iz prosledjenog ResultSet-a.
     *
     * @param rs ResultSet sa podacima iz baze
     * @return Lista procitanih objekata
     * @throws Exception ako dodje do greske pri citanju podataka
     */
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
    /**
     * Vraca nazive kolona za ubacivanje novog reda u tabelu.
     *
     * @return Nazivi kolona kao string
     */
    public String vratiKoloneZaUbacivanje() {
        return "korisnickoIme, sifra, ime, prezime";
    }

    @Override
    /**
     * Vraca vrednosti atributa za ubacivanje novog reda u tabelu.
     *
     * @return Vrednosti atributa kao string
     */
    public String vratiVrednostiZaUbacivanje() {
        
        return "'"+korisnickoIme+"','"+sifra+"','"+ime+"','"+prezime+"'";
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
     * Vraca objekat klase Zaposleni procitan iz prosledjenog ResultSet-a.
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

        return "korisnickoIme='" + korisnickoIme + "', sifra='" + sifra + "', ime='" + ime + "', prezime='" + prezime + "'";
    }
    
}
