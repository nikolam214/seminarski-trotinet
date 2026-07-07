/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.ztd;

import domen.ZtD;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *Sistemska operacija za ucitavanje svih objekata klase veze zaposleni-termin dezurstva (ZtD) iz baze podataka.
 * 
 * @author nikola
 */
public class UcitajZtDSO extends ApstraktnaGenerickaOperacija {
    
    List<ZtD> ztdList;
    
    
    /**
     * Proverava preduslove za izvrsavanje operacije.
     *
     * @param objekat Objekat nad kojim se operacija izvrsava
     * @throws Exception ako objekat nije odgovarajuce klase ili ne ispunjava uslove operacije
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    /**
     * Izvrsava konkretnu operaciju nad prosledjenim objektom.
     *
     * @param objekat Objekat nad kojim se operacija izvrsava
     * @param kljuc Dodatni kriterijum operacije
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvsiOperaciju(Object objekat, String kljuc) throws Exception {
        ztdList = (List<ZtD>) (List<?>) broker.getAll(new ZtD(),
            " JOIN zaposleni ON ztd.zaposleni = zaposleni.id" +
            " JOIN terminDezurstva ON ztd.TerminDezurstva = terminDezurstva.id");
    }

    public List<ZtD> getZtDList() {
        return ztdList;
    }
}
