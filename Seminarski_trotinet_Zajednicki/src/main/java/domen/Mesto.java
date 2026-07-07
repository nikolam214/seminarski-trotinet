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
 * @author nikola
 */
public class Mesto implements ApstraktniDomenskiObjekat{
    private int id;
    private String naziv;
    private int postanskiBroj;

    public Mesto() {
    }

    public Mesto(int id, String Naziv, int postanskiBroj) {
        setId(id);
        setNaziv(Naziv);
        setPostanskiBroj(postanskiBroj);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id mora biti veci od nule");
        }
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String Naziv) {
        if (Naziv == null || Naziv.isEmpty()) {
            throw new IllegalArgumentException("Naziv ne sme biti null ili prazan");
        }
        this.naziv = Naziv;
    }

    public int getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(int postanskiBroj) {
        if (postanskiBroj <= 0) {
            throw new IllegalArgumentException("Postanski broj mora biti veci od nule");
        }
        this.postanskiBroj = postanskiBroj;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv, postanskiBroj);
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
        final Mesto other = (Mesto) obj;
        if (this.postanskiBroj != other.postanskiBroj) {
            return false;
        }
        return Objects.equals(this.naziv, other.naziv);
    }

    @Override
    public String vratiNazivTabele() {
        return "mesto";
    }

    @Override
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
    public String vratiKoloneZaUbacivanje() {
        return "naziv, postanskiBroj";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+ naziv+"',"+postanskiBroj;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "id=" + id;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='" + naziv + "', postanskiBroj=" + postanskiBroj;
    }
    
    
    
}
