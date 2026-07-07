/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Iznajmljivanje;
import domen.Klijent;
import domen.Mesto;
import domen.TerminDezurstva;
import domen.Trotinet;
import domen.Zaposleni;
import domen.ZtD;
import java.util.List;
import operacije.LoginOperacija;
import operacije.iznajmljivanje.IzmeniIznajmljivanjeSO;
import operacije.iznajmljivanje.KreirajIznajmljivanjeSO;
import operacije.iznajmljivanje.UcitajIznajmljivanjaSO;
import operacije.klijenti.AzurirajKlijentaSO;
import operacije.klijenti.DodajKlijentaSO;
import operacije.klijenti.ObrisiKlijentaSO;
import operacije.klijenti.UcitajKlijenteSO;
import operacije.mesto.AzurirajMestoSO;
import operacije.mesto.DodajMestoSO;
import operacije.mesto.ObrisiMestoSO;
import operacije.mesto.UcitajMestoSO;
import operacije.termindezurstva.AzurirajTerminDezurstvaSO;
import operacije.termindezurstva.DodajTerminDezurstvaSO;
import operacije.termindezurstva.ObrisiTerminDezurstvaSO;
import operacije.termindezurstva.UcitajTermineDezurstvaSO;
import operacije.trotinet.AzurirajTrotinetSO;
import operacije.trotinet.DodajTrotinetSO;
import operacije.trotinet.ObrisiTrotinetSO;
import operacije.trotinet.UcitajTrotineteSO;
import operacije.zaposleni.AzurirajZaposlenogSO;
import operacije.zaposleni.DodajZaposlenogSO;
import operacije.zaposleni.ObrisiZaposlenogSO;
import operacije.zaposleni.UcitajZaposleneSO;
import operacije.ztd.DodajZtDSO;
import operacije.ztd.ObrisiZtDSO;
import operacije.ztd.UcitajZtDSO;

/**
 *
 * @author nikola
 */
public class Controller {
    
    public static Controller instance;

    private Controller() {
    }

    public static Controller getInstance() {
        if(instance==null){
            instance= new Controller();
        }
        return instance;
    }

    public Zaposleni login(Zaposleni z) throws Exception {
        
        LoginOperacija lo=new LoginOperacija();
        lo.izvrsi(z, null);
        
        System.out.println("Klasa kontroller"+lo.getZaposleni());
        return lo.getZaposleni();
        
    }

    public List<Klijent> ucitajKlijente() throws Exception  {
        UcitajKlijenteSO operacija= new UcitajKlijenteSO();
        operacija.izvrsi(null, null);
        
        System.out.println("Klasa kontroller"+operacija.getKlijenti());
        
        return operacija.getKlijenti();
                
    }

    public void obrisiKlijenta(Klijent k) throws Exception {
        ObrisiKlijentaSO operacija= new ObrisiKlijentaSO();
        operacija.izvrsi(k, null);
    }
    
    public List<Mesto> ucitajMesta() throws Exception {
        
        UcitajMestoSO operacija = new UcitajMestoSO();
        operacija.izvrsi(null, null);
        return operacija.getMesta();
    }
    
    public void dodajKlijenta(Klijent k) throws Exception {
        
        DodajKlijentaSO operacija = new DodajKlijentaSO();
        operacija.izvrsi(k, null);
    }

    public void azurirajKlijenta(Klijent k) throws Exception {
        
        AzurirajKlijentaSO operacija = new AzurirajKlijentaSO();
        operacija.izvrsi(k, null);

    }
    
    public List<Zaposleni> ucitajZaposlene() throws Exception {
        
        UcitajZaposleneSO operacija = new UcitajZaposleneSO();
        operacija.izvrsi(null, null);
        
        return operacija.getZaposleni();
    }

    public void dodajZaposlenog(Zaposleni z) throws Exception {
        
        DodajZaposlenogSO operacija = new DodajZaposlenogSO();
        operacija.izvrsi(z, null);
    }

    public void azurirajZaposlenog(Zaposleni z) throws Exception {
        
        AzurirajZaposlenogSO operacija = new AzurirajZaposlenogSO();
        operacija.izvrsi(z, null);
    }

    public void obrisiZaposlenog(Zaposleni z) throws Exception {
        
        ObrisiZaposlenogSO operacija = new ObrisiZaposlenogSO();
        operacija.izvrsi(z, null);
    }

    public void dodajMesto(Mesto m) throws Exception {
        
        DodajMestoSO operacija = new DodajMestoSO();
        operacija.izvrsi(m, null);
    }

    public void azurirajMesto(Mesto m) throws Exception {
        
        AzurirajMestoSO operacija = new AzurirajMestoSO();
        operacija.izvrsi(m, null);
    }

    public void obrisiMesto(Mesto m) throws Exception {
        
        ObrisiMestoSO operacija = new ObrisiMestoSO();
        operacija.izvrsi(m, null);
    }
    
    public List<Trotinet> ucitajTrotinete() throws Exception {
        UcitajTrotineteSO operacija = new UcitajTrotineteSO();
        operacija.izvrsi(null, null);
        return operacija.getTrotineti();
    }

    public void dodajTrotinet(Trotinet t) throws Exception {
        DodajTrotinetSO operacija = new DodajTrotinetSO();
        operacija.izvrsi(t, null);
    }

    public void azurirajTrotinet(Trotinet t) throws Exception {
        AzurirajTrotinetSO operacija = new AzurirajTrotinetSO();
        operacija.izvrsi(t, null);
    }

    public void obrisiTrotinet(Trotinet t) throws Exception {
        ObrisiTrotinetSO operacija = new ObrisiTrotinetSO();
        operacija.izvrsi(t, null);
    }
    
    public List<TerminDezurstva> ucitajTermineDezurstva() throws Exception {
        UcitajTermineDezurstvaSO operacija = new UcitajTermineDezurstvaSO();
        operacija.izvrsi(null, null);
        return operacija.getTermini();
    }

    public void dodajTerminDezurstva(TerminDezurstva t) throws Exception {
        DodajTerminDezurstvaSO operacija = new DodajTerminDezurstvaSO();
        operacija.izvrsi(t, null);
    }

    public void azurirajTerminDezurstva(TerminDezurstva t) throws Exception {
        AzurirajTerminDezurstvaSO operacija = new AzurirajTerminDezurstvaSO();
        operacija.izvrsi(t, null);
    }

    public void obrisiTerminDezurstva(TerminDezurstva t) throws Exception {
        ObrisiTerminDezurstvaSO operacija = new ObrisiTerminDezurstvaSO();
        operacija.izvrsi(t, null);
    }
    
    public List<Iznajmljivanje> ucitajIznajmljivanja() throws Exception {
        UcitajIznajmljivanjaSO operacija = new UcitajIznajmljivanjaSO();
        operacija.izvrsi(null, null);
        return operacija.getIznajmljivanja();
    }

    public void kreirajIznajmljivanje(Iznajmljivanje i) throws Exception {
        KreirajIznajmljivanjeSO operacija = new KreirajIznajmljivanjeSO();
        operacija.izvrsi(i, null);
    }

    public void izmeniIznajmljivanje(Iznajmljivanje i) throws Exception {
        IzmeniIznajmljivanjeSO operacija = new IzmeniIznajmljivanjeSO();
        operacija.izvrsi(i, null);
    }
    
    public List<ZtD> ucitajZtD() throws Exception {
        UcitajZtDSO operacija = new UcitajZtDSO();
        operacija.izvrsi(null, null);
        return operacija.getZtDList();
    }

    public void dodajZtD(ZtD z) throws Exception {
        DodajZtDSO operacija = new DodajZtDSO();
        operacija.izvrsi(z, null);
    }

    public void obrisiZtD(ZtD z) throws Exception {
        ObrisiZtDSO operacija = new ObrisiZtDSO();
        operacija.izvrsi(z, null);
    }
}
