/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.ztd;

import domen.ZtD;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikola
 */
public class UcitajZtDSO extends ApstraktnaGenerickaOperacija {
    
    List<ZtD> ztdList;
    
    

    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

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
