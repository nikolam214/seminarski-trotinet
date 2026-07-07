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
 * Predstavlja mesto u kom klijent zivi.
 * Mesto ima identifikator, naziv i postanski broj.
 *
 * @author nikola
 */
public class Mesto implements ApstraktniDomenskiObjekat{
    /**
     * Identifikator mesta kao ceo broj
     */
    private int id;
    /**
     * Naziv mesta kao string
     */
    private String naziv;
    /**
     * Postanski broj mesta kao ceo broj
     */
    private int postanskiBroj;

    /**
     * Pravi nov objekat klase Mesto sa podrazumevanim vrednostima atributa.
     */
    public Mesto() {
    }

    /**
     * Pravi nov objekat klase Mesto i postavlja vrednosti svih atributa
     * pozivom odgovarajucih set metoda.
     *
     * @param id Vrednost za atribut id
     * @param Naziv Vrednost za atribut Naziv
     * @param postanskiBroj Vrednost za atribut postanskiBroj
     * @throws IllegalArgumentException ako neka od prosledjenih vrednosti nije validna
     */
    public Mesto(int id, String Naziv, int postanskiBroj) {
        setId(id);
        setNaziv(Naziv);
        setPostanskiBroj(postanskiBroj);
    }

    /**
     * Vraca identifikator mesta kao ceo broj.
     *
     * @return Identifikator mesta kao ceo broj
     */
    public int getId() {
        return id;
    }

    /**
     * Postavlja identifikator mesta kao ceo broj.
     *
     * @param id Identifikator mesta kao ceo broj
     * @throws IllegalArgumentException ako id nije veci od nule
     */
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id mora biti veci od nule");
        }
        this.id = id;
    }

    /**
     * Vraca naziv mesta kao string.
     *
     * @return Naziv mesta kao string
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Postavlja naziv mesta kao string.
     *
     * @param Naziv Naziv mesta kao string
     * @throws IllegalArgumentException ako je naziv null ili prazan
     */
    public void setNaziv(String Naziv) {
        if (Naziv == null || Naziv.isEmpty()) {
            throw new IllegalArgumentException("Naziv ne sme biti null ili prazan");
        }
        this.naziv = Naziv;
    }

    /**
     * Vraca postanski broj mesta kao ceo broj.
     *
     * @return Postanski broj mesta kao ceo broj
     */
    public int getPostanskiBroj() {
        return postanskiBroj;
    }

    /**
     * Postavlja postanski broj mesta kao ceo broj.
     *
     * @param postanskiBroj Postanski broj mesta kao ceo broj
     * @throws IllegalArgumentException ako postanski broj nije veci od nule
     */
    public void setPostanskiBroj(int postanskiBroj) {
        if (postanskiBroj <= 0) {
            throw new IllegalArgumentException("Postanski broj mora biti veci od nule");
        }
        this.postanskiBroj = postanskiBroj;
    }

    @Override
    /**
     * Vraca string reprezentaciju objekta klase Mesto.
     *
     * @return String reprezentacija objekta kao string
     */
    public String toString() {
        return naziv;
    }

    @Override
    /**
     * Vraca hash kod objekta izracunat na osnovu atributa koji se koriste u equals metodi.
     *
     * @return Hash kod objekta kao ceo broj
     */
    public int hashCode() {
        return Objects.hash(naziv, postanskiBroj);
    }

    @Override
    /**
     * Poredi dva objekta klase Mesto.
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
        final Mesto other = (Mesto) obj;
        if (this.postanskiBroj != other.postanskiBroj) {
            return false;
        }
        return Objects.equals(this.naziv, other.naziv);
    }

    @Override
    /**
     * Vraca naziv tabele u bazi podataka koja odgovara klasi Mesto.
     *
     * @return Naziv tabele kao string
     */
    public String vratiNazivTabele() {
        return "mesto";
    }

    @Override
    /**
     * Vraca listu objekata klase Mesto procitanih iz prosledjenog ResultSet-a.
     *
     * @param rs ResultSet sa podacima iz baze
     * @return Lista procitanih objekata
     * @throws Exception ako dodje do greske pri citanju podataka
     */
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista= new ArrayList<>();
        while(rs.next()){
            int id= rs.getInt("mesto.id");
            String naziv= rs.getString("mesto.naziv");
            int postanskibroj=rs.getInt("mesto.postanskiBroj");
            
            Mesto m= new Mesto(id, naziv, postanskibroj);
            lista.add(m);
            
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
        return "naziv, postanskiBroj";
    }

    @Override
    /**
     * Vraca vrednosti atributa za ubacivanje novog reda u tabelu.
     *
     * @return Vrednosti atributa kao string
     */
    public String vratiVrednostiZaUbacivanje() {
        return "'"+ naziv+"',"+postanskiBroj;
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
     * Vraca objekat klase Mesto procitan iz prosledjenog ResultSet-a.
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
        return "naziv='" + naziv + "', postanskiBroj=" + postanskiBroj;
    }
}
