/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.termindezurstva;

import domen.TerminDezurstva;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikola
 */
public class UcitajTermineDezurstvaSO extends ApstraktnaGenerickaOperacija {
    
    List<TerminDezurstva> termini;

    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvsiOperaciju(Object objekat, String kljuc) throws Exception {
        try {
            termini = (List<TerminDezurstva>) (List<?>) broker.getAll(new TerminDezurstva(), null);
        } catch (Exception e) {
            System.out.println("GRESKA: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public List<TerminDezurstva> getTermini() {
        return termini;
    }
}
