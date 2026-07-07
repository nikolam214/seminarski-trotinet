/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.trotinet;

import domen.Trotinet;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikola
 */
public class UcitajTrotineteSO extends ApstraktnaGenerickaOperacija {
    
    List<Trotinet> trotineti;

    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvsiOperaciju(Object objekat, String kljuc) throws Exception {
        trotineti = (List<Trotinet>) (List<?>) broker.getAll(new Trotinet(), null);
    }

    public List<Trotinet> getTrotineti() {
        return trotineti;
    }
}
