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
 * Predstavlja jednu stavku iznajmljivanja - jedan iznajmljen trotinet
 * 
 *
 * @author nikola
 */
public class StavkaIznajmljivanja implements ApstraktniDomenskiObjekat{
 
    /**
     * Redni broj stavke u okviru iznajmljivanja kao ceo broj
     */
    private int rb;
    /**
     * Iznajmljivanje kome stavka pripada kao objekat klase Iznajmljivanje
     */
    private Iznajmljivanje iznajmljivanje;
    /**
     * Vreme uzimanja trotineta kao datum
     */
    private Date vremeUzimanja;
    /**
     * Vreme vracanja trotineta kao datum
     */
    private Date vremeVracanja;
    /**
     * Cena po minutu u trenutku iznajmljivanja kao realan broj
     */
    private double cenaPoMinutu;
    /**
     * Iznos stavke kao realan broj
     */
    private double iznos;
    /**
     * Trotinet koji se iznajmljuje kao objekat klase Trotinet
     */
    private Trotinet trotinet;

    /**
     * Pravi nov objekat klase StavkaIznajmljivanja sa podrazumevanim vrednostima atributa.
     */
    public StavkaIznajmljivanja() {
    }

    /**
     * Pravi nov objekat klase StavkaIznajmljivanja i postavlja vrednosti svih atributa
     * pozivom odgovarajucih set metoda.
     *
     * @param rb Vrednost za atribut rb
     * @param iznajmljivanje Vrednost za atribut iznajmljivanje
     * @param vremeuzimanja Vrednost za atribut vremeuzimanja
     * @param vremeVracanja Vrednost za atribut vremeVracanja
     * @param cenaPoMinutu Vrednost za atribut cenaPoMinutu
     * @param iznos Vrednost za atribut iznos
     * @param trotinet Vrednost za atribut trotinet
     * @throws IllegalArgumentException ako neka od prosledjenih vrednosti nije validna
     */
    public StavkaIznajmljivanja(int rb, Iznajmljivanje iznajmljivanje, Date vremeuzimanja, Date vremeVracanja, double cenaPoMinutu, double iznos, Trotinet trotinet) {
        setRb(rb);
        setIznajmljivanje(iznajmljivanje);
        setVremeuzimanja(vremeuzimanja);
        setVremeVracanja(vremeVracanja);
        setCenaPoMinutu(cenaPoMinutu);
        setIznos(iznos);
        setTrotinet(trotinet);
    }

    /**
     * Vraca redni broj stavke u okviru iznajmljivanja kao ceo broj.
     *
     * @return Redni broj stavke u okviru iznajmljivanja kao ceo broj
     */
    public int getRb() {
        return rb;
    }

    /**
     * Postavlja redni broj stavke u okviru iznajmljivanja kao ceo broj.
     *
     * @param rb Redni broj stavke u okviru iznajmljivanja kao ceo broj
     * @throws IllegalArgumentException ako redni broj nije veci od nule
     */
    public void setRb(int rb) {
        if (rb <= 0) {
            throw new IllegalArgumentException("Redni broj mora biti veci od nule");
        }
        this.rb = rb;
    }

    /**
     * Vraca iznajmljivanje kome stavka pripada kao objekat klase Iznajmljivanje.
     *
     * @return Iznajmljivanje kome stavka pripada kao objekat klase Iznajmljivanje
     */
    public Iznajmljivanje getIznajmljivanje() {
        return iznajmljivanje;
    }

    /**
     * Postavlja iznajmljivanje kome stavka pripada kao objekat klase Iznajmljivanje.
     *
     * @param iznajmljivanje Iznajmljivanje kome stavka pripada kao objekat klase Iznajmljivanje
     * @throws IllegalArgumentException ako je iznajmljivanje null
     */
    public void setIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        if (iznajmljivanje == null) {
            throw new IllegalArgumentException("Iznajmljivanje ne sme biti null");
        }
        this.iznajmljivanje = iznajmljivanje;
    }

    /**
     * Vraca vreme uzimanja trotineta kao datum.
     *
     * @return Vreme uzimanja trotineta kao datum
     */
    public Date getVremeuzimanja() {
        return vremeUzimanja;
    }

    /**
     * Postavlja vreme uzimanja trotineta kao datum.
     *
     * @param vremeuzimanja Vreme uzimanja trotineta kao datum
     * @throws IllegalArgumentException ako je vreme uzimanja null
     */
    public void setVremeuzimanja(Date vremeuzimanja) {
        if (vremeuzimanja == null) {
            throw new IllegalArgumentException("Vreme uzimanja ne sme biti null");
        }
        this.vremeUzimanja = vremeuzimanja;
    }

    /**
     * Vraca vreme vracanja trotineta kao datum.
     *
     * @return Vreme vracanja trotineta kao datum
     */
    public Date getVremeVracanja() {
        return vremeVracanja;
    }

    /**
     * Postavlja vreme vracanja trotineta kao datum.
     *
     * @param vremeVracanja Vreme vracanja trotineta kao datum
     * @throws IllegalArgumentException ako je vreme vracanja null
     */
    public void setVremeVracanja(Date vremeVracanja) {
        if (vremeVracanja == null) {
            throw new IllegalArgumentException("Vreme vracanja ne sme biti null");
        }
        if (vremeUzimanja != null && vremeVracanja.before(vremeUzimanja)) {
            throw new IllegalArgumentException("Vreme vracanja ne sme biti pre vremena uzimanja");
        }
        this.vremeVracanja = vremeVracanja;
    }

    /**
     * Vraca cena po minutu u trenutku iznajmljivanja kao realan broj.
     *
     * @return Cena po minutu u trenutku iznajmljivanja kao realan broj
     */
    public double getCenaPoMinutu() {
        return cenaPoMinutu;
    }

    /**
     * Postavlja cena po minutu u trenutku iznajmljivanja kao realan broj.
     *
     * @param cenaPoMinutu Cena po minutu u trenutku iznajmljivanja kao realan broj
     * @throws IllegalArgumentException ako cena po minutu nije veca od nule
     */
    public void setCenaPoMinutu(double cenaPoMinutu) {
        if (cenaPoMinutu <= 0) {
            throw new IllegalArgumentException("Cena po minutu mora biti veca od nule");
        }
        this.cenaPoMinutu = cenaPoMinutu;
    }

    /**
     * Vraca iznos stavke kao realan broj.
     *
     * @return Iznos stavke kao realan broj
     */
    public double getIznos() {
        return iznos;
    }

    /**
     * Postavlja iznos stavke kao realan broj.
     *
     * @param iznos Iznos stavke kao realan broj
     * @throws IllegalArgumentException ako je iznos negativan
     */
    public void setIznos(double iznos) {
        if (iznos < 0) {
            throw new IllegalArgumentException("Iznos ne sme biti negativan");
        }
        this.iznos = iznos;
    }

    /**
     * Vraca trotinet koji se iznajmljuje kao objekat klase Trotinet.
     *
     * @return Trotinet koji se iznajmljuje kao objekat klase Trotinet
     */
    public Trotinet getTrotinet() {
        return trotinet;
    }

    /**
     * Postavlja trotinet koji se iznajmljuje kao objekat klase Trotinet.
     *
     * @param trotinet Trotinet koji se iznajmljuje kao objekat klase Trotinet
     * @throws IllegalArgumentException ako je trotinet null
     */
    public void setTrotinet(Trotinet trotinet) {
        if (trotinet == null) {
            throw new IllegalArgumentException("Trotinet ne sme biti null");
        }
        this.trotinet = trotinet;
    }

    @Override
    /**
     * Vraca string reprezentaciju objekta klase StavkaIznajmljivanja.
     *
     * @return String reprezentacija objekta kao string
     */
    public String toString() {
        return "StavkaIznajmljivanja{" + "iznajmljivanje=" + iznajmljivanje + ", vremeuzimanja=" + vremeUzimanja + ", vremeVracanja=" + vremeVracanja + ", cenaPoMinutu=" + cenaPoMinutu + ", iznos=" + iznos + ", trotinet=" + trotinet + '}';
    }

    @Override
    /**
     * Vraca hash kod objekta izracunat na osnovu atributa koji se koriste u equals metodi.
     *
     * @return Hash kod objekta kao ceo broj
     */
    public int hashCode() {
        return Objects.hash(rb, iznajmljivanje);
    }

    @Override
/**
 * Poredi dva objekta klase StavkaIznajmljivanja.
 *
 * @param obj Objekat sa kojim se poredi
 * @return true ako su objekti isti, false u suprotnom
 */
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    final StavkaIznajmljivanja other = (StavkaIznajmljivanja) obj;
    if (this.rb != other.rb) return false;
    return Objects.equals(this.iznajmljivanje, other.iznajmljivanje);
}

    @Override
    /**
     * Vraca naziv tabele u bazi podataka koja odgovara klasi StavkaIznajmljivanja.
     *
     * @return Naziv tabele kao string
     */
    public String vratiNazivTabele() {
        return "stavkaIznajmljivanja";
    }

    @Override
    /**
     * Vraca listu objekata klase StavkaIznajmljivanja procitanih iz prosledjenog ResultSet-a.
     *
     * @param rs ResultSet sa podacima iz baze
     * @return Lista procitanih objekata
     * @throws Exception ako dodje do greske pri citanju podataka
     */
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

            StavkaIznajmljivanja stavka = new StavkaIznajmljivanja();
            stavka.setRb(rb);
            stavka.setIznajmljivanje(iznajmljivanje);
            stavka.setVremeuzimanja(uzet);
            if (vracen != null) {
                stavka.setVremeVracanja(vracen);
            }
            stavka.setCenaPoMinutu(cenaPoMinutu);
            stavka.setIznos(iznos);
            stavka.setTrotinet(trotinet);
            lista.add(stavka);
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
        return "iznajmljivanje, rb, vremeUzimanja, vremeVracanja, cenaPoMinutu, iznos, trotinet";
    }

    @Override
    /**
     * Vraca vrednosti atributa za ubacivanje novog reda u tabelu.
     *
     * @return Vrednosti atributa kao string
     */
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
    /**
     * Vraca uslov sa primarnim kljucem za identifikaciju reda u tabeli.
     *
     * @return Uslov sa primarnim kljucem kao string
     */
    public String vratiPrimarniKljuc() {
        return "iznajmljivanje=" + iznajmljivanje.getId() + " AND rb=" + rb;
    }

    @Override
    /**
     * Vraca objekat klase StavkaIznajmljivanja procitan iz prosledjenog ResultSet-a.
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
        return "vremeUzimanja='" + new java.sql.Timestamp(vremeUzimanja.getTime()) + 
               "', vremeVracanja='" + new java.sql.Timestamp(vremeVracanja.getTime()) + 
               "', cenaPoMinutu=" + cenaPoMinutu + 
               ", iznos=" + iznos + 
               ", trotinet=" + trotinet.getId();
    }
    
    
    
}
