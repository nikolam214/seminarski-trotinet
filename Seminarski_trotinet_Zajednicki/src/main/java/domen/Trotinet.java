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
public class Trotinet implements ApstraktniDomenskiObjekat{
    
    private int id;
    private String modelTrotineta;
    private double cenaPoMinutu;

    public Trotinet() {
    }

    public Trotinet(int id, String modelTrotineta, double cenaPoMinutu) {
        setId(id);
        setModelTrotineta(modelTrotineta);
        setCenaPoMinutu(cenaPoMinutu);
    }

    public double getCenaPoMinutu() {
        return cenaPoMinutu;
    }

    public void setCenaPoMinutu(double cenaPoMinutu) {
        if (cenaPoMinutu <= 0) {
            throw new IllegalArgumentException("Cena po minutu mora biti veca od nule");
        }
        this.cenaPoMinutu = cenaPoMinutu;
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

    public String getModelTrotineta() {
        return modelTrotineta;
    }

    public void setModelTrotineta(String modelTrotineta) {
        if (modelTrotineta == null || modelTrotineta.isEmpty()) {
            throw new IllegalArgumentException("Model trotineta ne sme biti null ili prazan");
        }
        this.modelTrotineta = modelTrotineta;
    }

    

 

    @Override
    public String toString() {
        return "Trotinet{" + "modelTrotineta=" + modelTrotineta + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, modelTrotineta);
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
        final Trotinet other = (Trotinet) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.modelTrotineta, other.modelTrotineta);
    }

    @Override
    public String vratiNazivTabele() {
        return "trotinet";
    }

    @Override
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
    public String vratiKoloneZaUbacivanje() {
        return "modelTrotineta, cenaPoMinutu";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+modelTrotineta+"',"+cenaPoMinutu;
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
        return "modelTrotineta='" + modelTrotineta + "', cenaPoMinutu=" + cenaPoMinutu;
    }
    
    
    
}
