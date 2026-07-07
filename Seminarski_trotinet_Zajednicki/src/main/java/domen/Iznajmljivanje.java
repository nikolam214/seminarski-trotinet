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
 * Predstavlja iznajmljivanje trotineta koje je klijent izvrsio kod zaposlenog.
 * Iznajmljivanje sadrzi listu stavki iznajmljivanja i ukupnu cenu.
 *
 * @author nikola
 */
public class Iznajmljivanje implements ApstraktniDomenskiObjekat{
 
     /**
     * Identifikator iznajmljivanja kao ceo broj
     */
    private int id;
    /**
     * Ukupna cena iznajmljivanja kao realan broj
     */
    private double ukupnaCena;
    /**
     * Zaposleni koji je evidentirao iznajmljivanje kao objekat klase Zaposleni
     */
    private Zaposleni zaposleni;
    /**
     * Klijent koji iznajmljuje trotinete kao objekat klase Klijent
     */
    private Klijent klijent;
    /**
     * Lista stavki iznajmljivanja
     */
    private List<StavkaIznajmljivanja> stavkeIznajmljivanja;

    /**
     * Pravi nov objekat klase Iznajmljivanje sa podrazumevanim vrednostima atributa.
     */
    public Iznajmljivanje() {
        this.stavkeIznajmljivanja = new ArrayList<>();
    }

    /**
     * Pravi nov objekat klase Iznajmljivanje i postavlja vrednosti svih atributa
     * pozivom odgovarajucih set metoda.
     *
     * @param id Vrednost za atribut id
     * @param ukupnaCena Vrednost za atribut ukupnaCena
     * @param zaposleni Vrednost za atribut zaposleni
     * @param klijent Vrednost za atribut klijent
     * @throws IllegalArgumentException ako neka od prosledjenih vrednosti nije validna
     */
    public Iznajmljivanje(int id, double ukupnaCena, Zaposleni zaposleni, Klijent klijent) {
        setId(id);
        setUkupnaCena(ukupnaCena);
        setZaposleni(zaposleni);
        setKlijent(klijent);
        this.stavkeIznajmljivanja = new ArrayList<>();
    }

    

    /**
     * Vraca identifikator iznajmljivanja kao ceo broj.
     *
     * @return Identifikator iznajmljivanja kao ceo broj
     */
    public int getId() {
        return id;
    }

    /**
     * Postavlja identifikator iznajmljivanja kao ceo broj.
     *
     * @param id Identifikator iznajmljivanja kao ceo broj
     * @throws IllegalArgumentException ako id nije veci od nule
     */
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id mora biti veci od nule");
        }
        this.id = id;
    }

    /**
     * Vraca ukupna cena iznajmljivanja kao realan broj.
     *
     * @return Ukupna cena iznajmljivanja kao realan broj
     */
    public double getUkupnaCena() {
        return ukupnaCena;
    }

    /**
     * Postavlja ukupna cena iznajmljivanja kao realan broj.
     *
     * @param ukupnaCena Ukupna cena iznajmljivanja kao realan broj
     * @throws IllegalArgumentException ako je ukupna cena negativna
     */
    public void setUkupnaCena(double ukupnaCena) {
        if (ukupnaCena < 0) {
            throw new IllegalArgumentException("Ukupna cena ne sme biti negativna");
        }
        this.ukupnaCena = ukupnaCena;
    }

    /**
     * Vraca zaposleni koji je evidentirao iznajmljivanje kao objekat klase Zaposleni.
     *
     * @return Zaposleni koji je evidentirao iznajmljivanje kao objekat klase Zaposleni
     */
    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    /**
     * Postavlja zaposleni koji je evidentirao iznajmljivanje kao objekat klase Zaposleni.
     *
     * @param zaposleni Zaposleni koji je evidentirao iznajmljivanje kao objekat klase Zaposleni
     * @throws IllegalArgumentException ako je zaposleni null
     */
    public void setZaposleni(Zaposleni zaposleni) {
        if (zaposleni == null) {
            throw new IllegalArgumentException("Zaposleni ne sme biti null");
        }
        this.zaposleni = zaposleni;
    }

    /**
     * Vraca klijent koji iznajmljuje trotinete kao objekat klase Klijent.
     *
     * @return Klijent koji iznajmljuje trotinete kao objekat klase Klijent
     */
    public Klijent getKlijent() {
        return klijent;
    }

    /**
     * Postavlja klijent koji iznajmljuje trotinete kao objekat klase Klijent.
     *
     * @param klijent Klijent koji iznajmljuje trotinete kao objekat klase Klijent
     * @throws IllegalArgumentException ako je klijent null
     */
    public void setKlijent(Klijent klijent) {
        if (klijent == null) {
            throw new IllegalArgumentException("Klijent ne sme biti null");
        }
        this.klijent = klijent;
    }

    /**
     * Vraca lista stavki iznajmljivanja.
     *
     * @return Lista stavki iznajmljivanja
     */
    public List<StavkaIznajmljivanja> getStavkeIznajmljivanja() {
        return stavkeIznajmljivanja;
    }

    /**
     * Postavlja lista stavki iznajmljivanja.
     *
     * @param stavkeIznajmljivanja Lista stavki iznajmljivanja
     * @throws IllegalArgumentException ako je lista stavki null
     */
    public void setStavkeIznajmljivanja(List<StavkaIznajmljivanja> stavkeIznajmljivanja) {
        if (stavkeIznajmljivanja == null) {
            throw new IllegalArgumentException("Lista stavki ne sme biti null");
        }
        this.stavkeIznajmljivanja = stavkeIznajmljivanja;
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
     * Poredi dva objekta klase Iznajmljivanje.
     *
     * @param obj Objekat sa kojim se poredi
     * @return true ako su objekti isti, false u suprotnom
     */
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final Iznajmljivanje other = (Iznajmljivanje) obj;
        return this.id == other.id;
    }


    @Override
    /**
     * Vraca string reprezentaciju objekta klase Iznajmljivanje.
     *
     * @return String reprezentacija objekta kao string
     */
    public String toString() {
        return "Iznajmljivanje{" + "id=" + id + ", ukupnaCena=" + ukupnaCena + ", zaposleni=" + zaposleni + ", klijent=" + klijent + '}';
    }

    @Override
    /**
     * Vraca naziv tabele u bazi podataka koja odgovara klasi Iznajmljivanje.
     *
     * @return Naziv tabele kao string
     */
    public String vratiNazivTabele() {
        return "iznajmljivanje";
    }

    @Override
    /**
     * Vraca listu objekata klase Iznajmljivanje procitanih iz prosledjenog ResultSet-a.
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

            int idKlijent = rs.getInt("klijent.id");
            String imeK = rs.getString("klijent.ime");
            String prezimeK = rs.getString("klijent.prezime");
            long telK = rs.getLong("klijent.brojTelefona");
            Klijent klijent = new Klijent();
            klijent.setId(idKlijent);
            klijent.setIme(imeK);
            klijent.setPrezime(prezimeK);
            klijent.setBrojtelefona(telK);

            int id = rs.getInt("iznajmljivanje.id");
            double ukupnaCena = rs.getDouble("iznajmljivanje.ukupnaCena");
            Iznajmljivanje iznajmljivanje = new Iznajmljivanje(id, ukupnaCena, zaposleni, klijent);
            lista.add(iznajmljivanje);
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
        return "ukupnaCena, zaposleni, klijent";
    }

    @Override
    /**
     * Vraca vrednosti atributa za ubacivanje novog reda u tabelu.
     *
     * @return Vrednosti atributa kao string
     */
    public String vratiVrednostiZaUbacivanje() {
        return ukupnaCena + ", " + zaposleni.getId() + ", " + klijent.getId();
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
     * Vraca objekat klase Iznajmljivanje procitan iz prosledjenog ResultSet-a.
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
        return "ukupnaCena=" + ukupnaCena + ", zaposleni=" + zaposleni.getId() + ", klijent=" + klijent.getId();
    }

    
    
}
