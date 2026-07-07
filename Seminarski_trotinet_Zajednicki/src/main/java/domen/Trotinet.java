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
 * Predstavlja trotinet koji se iznajmljuje klijentima.
 * Trotinet ima identifikator, model i cenu po minutu.
 * 
 * @author nikola
 */
public class Trotinet implements ApstraktniDomenskiObjekat{
    /**
     * Identifikator trotineta kao ceo broj
     */
    private int id;
    /**
     * Model trotineta kao string
     */
    private String modelTrotineta;
    /**
     * Cena iznajmljivanja trotineta po minutu kao realan broj
     */
    private double cenaPoMinutu;

    /**
     * Pravi nov objekat klase Trotinet sa podrazumevanim vrednostima atributa.
     */
    public Trotinet() {
    }

    /**
     * Pravi nov objekat klase Trotinet i postavlja vrednosti svih atributa
     * pozivom odgovarajucih set metoda.
     *
     * @param id Vrednost za atribut id
     * @param modelTrotineta Vrednost za atribut modelTrotineta
     * @param cenaPoMinutu Vrednost za atribut cenaPoMinutu
     * @throws IllegalArgumentException ako neka od prosledjenih vrednosti nije validna
     */
    public Trotinet(int id, String modelTrotineta, double cenaPoMinutu) {
        setId(id);
        setModelTrotineta(modelTrotineta);
        setCenaPoMinutu(cenaPoMinutu);
    }

    /**
     * Vraca cena iznajmljivanja trotineta po minutu kao realan broj.
     *
     * @return Cena iznajmljivanja trotineta po minutu kao realan broj
     */
    public double getCenaPoMinutu() {
        return cenaPoMinutu;
    }

    /**
     * Postavlja cena iznajmljivanja trotineta po minutu kao realan broj.
     *
     * @param cenaPoMinutu Cena iznajmljivanja trotineta po minutu kao realan broj
     * @throws IllegalArgumentException ako cena po minutu nije veca od nule
     */
    public void setCenaPoMinutu(double cenaPoMinutu) {
        if (cenaPoMinutu <= 0) {
            throw new IllegalArgumentException("Cena po minutu mora biti veca od nule");
        }
        this.cenaPoMinutu = cenaPoMinutu;
    }

   

    

    /**
     * Vraca identifikator trotineta kao ceo broj.
     *
     * @return Identifikator trotineta kao ceo broj
     */
    public int getId() {
        return id;
    }

    /**
     * Postavlja identifikator trotineta kao ceo broj.
     *
     * @param id Identifikator trotineta kao ceo broj
     * @throws IllegalArgumentException ako id nije veci od nule
     */
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id mora biti veci od nule");
        }
        this.id = id;
    }

    /**
     * Vraca model trotineta kao string.
     *
     * @return Model trotineta kao string
     */
    public String getModelTrotineta() {
        return modelTrotineta;
    }

    /**
     * Postavlja model trotineta kao string.
     *
     * @param modelTrotineta Model trotineta kao string
     * @throws IllegalArgumentException ako je model trotineta null ili prazan
     */
    public void setModelTrotineta(String modelTrotineta) {
        if (modelTrotineta == null || modelTrotineta.isEmpty()) {
            throw new IllegalArgumentException("Model trotineta ne sme biti null ili prazan");
        }
        this.modelTrotineta = modelTrotineta;
    }

    

 

    @Override
    /**
     * Vraca string reprezentaciju objekta klase Trotinet.
     *
     * @return String reprezentacija objekta kao string
     */
    public String toString() {
        return "Trotinet{" + "modelTrotineta=" + modelTrotineta + '}';
    }

    @Override
    /**
     * Vraca hash kod objekta izracunat na osnovu atributa koji se koriste u equals metodi.
     *
     * @return Hash kod objekta kao ceo broj
     */
    public int hashCode() {
        return Objects.hash(id, modelTrotineta);
    }

    @Override
    /**
     * Poredi dva objekta klase Trotinet.
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
        final Trotinet other = (Trotinet) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.modelTrotineta, other.modelTrotineta);
    }

    @Override
    /**
     * Vraca naziv tabele u bazi podataka koja odgovara klasi Trotinet.
     *
     * @return Naziv tabele kao string
     */
    public String vratiNazivTabele() {
        return "trotinet";
    }

    @Override
    /**
     * Vraca listu objekata klase Trotinet procitanih iz prosledjenog ResultSet-a.
     *
     * @param rs ResultSet sa podacima iz baze
     * @return Lista procitanih objekata
     * @throws Exception ako dodje do greske pri citanju podataka
     */
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista= new ArrayList<>();
        while(rs.next()){
            int id= rs.getInt("trotinet.id");
            String modelTrotineta= rs.getString("trotinet.modelTrotineta");
            double cenaPoMinutu=rs.getDouble("trotinet.cenaPoMinutu");
            
            Trotinet t= new Trotinet(id, modelTrotineta, cenaPoMinutu);
            lista.add(t);
            
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
        return "modelTrotineta, cenaPoMinutu";
    }

    @Override
    /**
     * Vraca vrednosti atributa za ubacivanje novog reda u tabelu.
     *
     * @return Vrednosti atributa kao string
     */
    public String vratiVrednostiZaUbacivanje() {
        return "'"+modelTrotineta+"',"+cenaPoMinutu;
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
     * Vraca objekat klase Trotinet procitan iz prosledjenog ResultSet-a.
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
        return "modelTrotineta='" + modelTrotineta + "', cenaPoMinutu=" + cenaPoMinutu;
    }
}
