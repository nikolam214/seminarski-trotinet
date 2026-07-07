/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.mesto;

import domen.Mesto;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikola
 */
public class UcitajMestoSO extends ApstraktnaGenerickaOperacija {
    
    List<Mesto> mesta;

    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvsiOperaciju(Object objekat, String kljuc) throws Exception {
        mesta = (List<Mesto>) broker.getAll(new Mesto(), null);
    }

    public List<Mesto> getMesta() {
        return mesta;
    }
}
