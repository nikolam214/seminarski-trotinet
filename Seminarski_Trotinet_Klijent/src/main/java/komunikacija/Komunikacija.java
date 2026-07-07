/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Iznajmljivanje;
import domen.Klijent;
import domen.Mesto;
import domen.TerminDezurstva;
import domen.Trotinet;
import domen.Zaposleni;
import domen.ZtD;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author nikola
 */
public class Komunikacija {
    
    private Socket socket;
    private static Komunikacija instance;
    private Primalac primalac;
    private Posiljalac posiljalac;

    private Komunikacija() {
    }

    public static Komunikacija getInstance() {
        if(instance==null){
            instance=new Komunikacija();
        }
        return instance;
    }
    
    public void konekcija(){
        try {
            socket= new Socket("localhost", 9000);
            posiljalac= new Posiljalac(socket);
            primalac= new Primalac(socket);
        } catch (IOException ex) {
            System.out.println("SERVER NIJE POVEZAN");
        }
    }

    public Zaposleni login(String ki, String pass) {
        Zaposleni z= new Zaposleni(0, ki, pass, null, null);
        Zahtev zahtev= new Zahtev(Operacija.LOGIN, z);
        posiljalac.posalji(zahtev);
        
        Odgovor odg= (Odgovor) primalac.primi();
        z=(Zaposleni) odg.getOdgovor();
        return z;
        
    }

    public List<Klijent> ucitajKlijente() {
        Zahtev zahtev= new Zahtev(Operacija.UCITAJ_KLIJENTE, null);
        List<Klijent> klijenti= new ArrayList<>();
        posiljalac.posalji(zahtev);
        
        Odgovor odg= (Odgovor) primalac.primi();
        klijenti= (List<Klijent>) odg.getOdgovor();
        
        
        return klijenti;
    }

    public void obrisiKlijenta(Klijent k) throws Exception {
        Zahtev zahtev= new Zahtev(Operacija.OBRISI_KLIJENTA, k);
        posiljalac.posalji(zahtev);
        
        Odgovor odg= (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("Klijent obrisan");
        }else{
            System.out.println("Greska");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("Greska");
        }
    }
    
    public List<Mesto> ucitajMesta() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_MESTA, null);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        return (List<Mesto>) odg.getOdgovor();
    }

    public void dodajKlijenta(Klijent k) throws Exception{
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_KLIJENTA, k);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() != null) {
            System.out.println("Greska");
        }
    }

    public void azurirajKlijenta(Klijent k) throws Exception{
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_KLIJENTA, k);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("Uspesno azuriran klijent");
            koordinator.Koordinator.getInstance().osveziTabeluKlijenata();
        }
        else {
            System.out.println("Greska pri azuriranju klijenta");
        }
    }

    public List<Zaposleni> ucitajZaposlene() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_ZAPOSLENE, null);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        return (List<Zaposleni>) odg.getOdgovor();
    }

    public void dodajZaposlenog(Zaposleni z) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_ZAPOSLENOG, z);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() != null) {
            throw new Exception("Greska pri dodavanju zaposlenog");
        }
    }

    public void azurirajZaposlenog(Zaposleni z) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_ZAPOSLENOG, z);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() != null) {
            throw new Exception("Greska pri azuriranju zaposlenog");
        }
    }

    public void obrisiZaposlenog(Zaposleni z) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_ZAPOSLENOG, z);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() != null) {
            throw new Exception("Greska pri brisanju zaposlenog");
        }
    }
    
    public void dodajMesto(Mesto m) throws Exception {
        
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_MESTO, m);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() != null) {
            throw new Exception("Greska pri dodavanju mesta");
        }
    }

    public void azurirajMesto(Mesto m) throws Exception {
        
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_MESTO, m);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() != null) {
            throw new Exception("Greska pri azuriranju mesta");
        }
    }
    
    public void obrisiMesto(Mesto m) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_MESTO, m);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() != null) {
            throw new Exception("Greska pri brisanju mesta");
        }
    }
    
    public List<Trotinet> ucitajTrotinete() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_TROTINETE, null);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        return (List<Trotinet>) odg.getOdgovor();
    }

    public void dodajTrotinet(Trotinet t) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_TROTINET, t);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() != null) {
            throw new Exception("Greska pri dodavanju trotineta");
        }
    }

    public void azurirajTrotinet(Trotinet t) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_TROTINET, t);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() != null) {
            throw new Exception("Greska pri azuriranju trotineta");
        }
    }

    public void obrisiTrotinet(Trotinet t) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_TROTINET, t);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() != null) {
            throw new Exception("Greska pri brisanju trotineta");
        }
    }

    public List<TerminDezurstva> ucitajTermineDezurstva() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_TERMINE_DEZURSTVA, null);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        return (List<TerminDezurstva>) odg.getOdgovor();
    }

    public void dodajTerminDezurstva(TerminDezurstva t) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_TERMIN_DEZURSTVA, t);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() != null) {
            throw new Exception("Greska pri dodavanju termina dezurstva");
        }
    }

    public void azurirajTerminDezurstva(TerminDezurstva t) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_TERMIN_DEZURSTVA, t);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() != null) {
            throw new Exception("Greska pri azuriranju termina dezurstva");
        }
    }

    public void obrisiTerminDezurstva(TerminDezurstva t) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_TERMIN_DEZURSTVA, t);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() != null) {
            throw new Exception("Greska pri brisanju termina dezurstva");
        }
    }
    
    public List<Iznajmljivanje> ucitajIznajmljivanja() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_IZNAJMLJIVANJA, null);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        return (List<Iznajmljivanje>) odg.getOdgovor();
    }

    public void kreirajIznajmljivanje(Iznajmljivanje i) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.KREIRAJ_IZNAJMLJIVANJE, i);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() != null) {
            throw new Exception("Greska pri kreiranju iznajmljivanja");
        }
    }

    public void izmeniIznajmljivanje(Iznajmljivanje i) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_IZNAJMLJIVANJE, i);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() != null) {
            throw new Exception("Greska pri izmeni iznajmljivanja");
        }
    }

    public List<ZtD> ucitajZtD() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_ZTD, null);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        return (List<ZtD>) odg.getOdgovor();
    }

    public void dodajZtD(ZtD ztd) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_ZTD, ztd);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() != null) {
            throw new Exception("Greska pri dodavanju ZtD");
        }
    }

    public void obrisiZtD(ZtD ztd) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_ZTD, ztd);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() != null) {
            throw new Exception("Greska pri brisanju ZtD");
        }
    }
   
}
