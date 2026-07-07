/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import domen.Iznajmljivanje;
import domen.Klijent;
import domen.Mesto;
import domen.TerminDezurstva;
import domen.Trotinet;
import domen.Zaposleni;
import domen.ZtD;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import komunikacija.Odgovor;
import komunikacija.Operacija;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;

/**
 *
 * @author nikola
 */
public class ObradaKlijentskihZahteva extends Thread {
 
    Socket socket;
    Primalac primalac;
    Posiljalac posiljalac;
    boolean kraj=false;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket=socket;
        posiljalac= new Posiljalac(socket);
        primalac=new Primalac(socket);
                
    }

    
    @Override
    public void run() {
        while(!kraj){
            try {
                Zahtev zahtev=(Zahtev) primalac.primi();
                Odgovor odgovor= new Odgovor();
                switch (zahtev.getPeracija()) {
                   case Operacija.LOGIN:

                       Zaposleni z= (Zaposleni) zahtev.getParametar();
                       z=controller.Controller.getInstance().login(z);
                       odgovor.setOdgovor(z);

                     break;
                     
                   case Operacija.UCITAJ_KLIJENTE:
                         
                       List<Klijent> klijenti=controller.Controller.getInstance().ucitajKlijente();
                       
                       odgovor.setOdgovor(klijenti);

                     break;
                     
                   case Operacija.OBRISI_KLIJENTA:
                        try{
                            Klijent k= (Klijent) zahtev.getParametar();
                            controller.Controller.getInstance().obrisiKlijenta(k);
                            odgovor.setOdgovor(null);
                        }catch(Exception e){
                             odgovor.setOdgovor(e);
                        }

                     break;
                     
                   case Operacija.UCITAJ_MESTA:
                        List<Mesto> mesta = controller.Controller.getInstance().ucitajMesta();
                        odgovor.setOdgovor(mesta);
                     break;
                     
                   case Operacija.DODAJ_KLIJENTA:
                        try {
                            Klijent k = (Klijent) zahtev.getParametar();
                            controller.Controller.getInstance().dodajKlijenta(k);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                     break;
                     
                   case Operacija.AZURIRAJ_KLIJENTA:
                        try {
                            Klijent k = (Klijent) zahtev.getParametar();
                            controller.Controller.getInstance().azurirajKlijenta(k);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                        
                   case Operacija.UCITAJ_ZAPOSLENE:
                        try {
                            List<Zaposleni> zaposleni = controller.Controller.getInstance().ucitajZaposlene();
                            odgovor.setOdgovor(zaposleni);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;

                    case Operacija.DODAJ_ZAPOSLENOG:
                        try {
                            Zaposleni noviZaposleni = (Zaposleni) zahtev.getParametar();
                            controller.Controller.getInstance().dodajZaposlenog(noviZaposleni);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;

                    case Operacija.AZURIRAJ_ZAPOSLENOG:
                        try {
                            Zaposleni azuriraniZaposleni = (Zaposleni) zahtev.getParametar();
                            controller.Controller.getInstance().azurirajZaposlenog(azuriraniZaposleni);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;

                    case Operacija.OBRISI_ZAPOSLENOG:
                        try {
                            Zaposleni obrisiZaposleni = (Zaposleni) zahtev.getParametar();
                            controller.Controller.getInstance().obrisiZaposlenog(obrisiZaposleni);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                        
                    case Operacija.DODAJ_MESTO:
                        try {
                            Mesto novoMesto = (Mesto) zahtev.getParametar();
                            controller.Controller.getInstance().dodajMesto(novoMesto);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;

                    case Operacija.AZURIRAJ_MESTO:
                        try {
                            Mesto azuriranMesto = (Mesto) zahtev.getParametar();
                            controller.Controller.getInstance().azurirajMesto(azuriranMesto);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;

                    case Operacija.OBRISI_MESTO:
                        try {
                            Mesto obrisanoMesto = (Mesto) zahtev.getParametar();
                            controller.Controller.getInstance().obrisiMesto(obrisanoMesto);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                        
                    case Operacija.UCITAJ_TROTINETE:
                        try {
                            List<Trotinet> trotineti = controller.Controller.getInstance().ucitajTrotinete();
                            odgovor.setOdgovor(trotineti);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;

                    case Operacija.AZURIRAJ_TROTINET:
                        try {
                            Trotinet azuriranTrotinet = (Trotinet) zahtev.getParametar();
                            controller.Controller.getInstance().azurirajTrotinet(azuriranTrotinet);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;

                    case Operacija.OBRISI_TROTINET:
                        try {
                            Trotinet obrisanTrotinet = (Trotinet) zahtev.getParametar();
                            controller.Controller.getInstance().obrisiTrotinet(obrisanTrotinet);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                        
                    case Operacija.DODAJ_TROTINET:
                        try {
                            Trotinet noviTrotinet = (Trotinet) zahtev.getParametar();
                            controller.Controller.getInstance().dodajTrotinet(noviTrotinet);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case Operacija.UCITAJ_TERMINE_DEZURSTVA:
                        try {
                            List<TerminDezurstva> termini = controller.Controller.getInstance().ucitajTermineDezurstva();
                            odgovor.setOdgovor(termini);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;

                    case Operacija.DODAJ_TERMIN_DEZURSTVA:
                        try {
                            TerminDezurstva noviTermin = (TerminDezurstva) zahtev.getParametar();
                            controller.Controller.getInstance().dodajTerminDezurstva(noviTermin);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    
                    case Operacija.AZURIRAJ_TERMIN_DEZURSTVA:
                        try {
                            TerminDezurstva azuriranTermin = (TerminDezurstva) zahtev.getParametar();
                            controller.Controller.getInstance().azurirajTerminDezurstva(azuriranTermin);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;

                    case Operacija.OBRISI_TERMIN_DEZURSTVA:
                        try {
                            TerminDezurstva obrisanTermin = (TerminDezurstva) zahtev.getParametar();
                            controller.Controller.getInstance().obrisiTerminDezurstva(obrisanTermin);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                        
                    case Operacija.UCITAJ_IZNAJMLJIVANJA:
                        try {
                            List<Iznajmljivanje> iznajmljivanja = controller.Controller.getInstance().ucitajIznajmljivanja();
                            odgovor.setOdgovor(iznajmljivanja);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;

                    case Operacija.KREIRAJ_IZNAJMLJIVANJE:
                        try {
                            Iznajmljivanje novoIznajmljivanje = (Iznajmljivanje) zahtev.getParametar();
                            controller.Controller.getInstance().kreirajIznajmljivanje(novoIznajmljivanje);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case Operacija.IZMENI_IZNAJMLJIVANJE:
                        try {
                            Iznajmljivanje izmenjeno = (Iznajmljivanje) zahtev.getParametar();
                            controller.Controller.getInstance().izmeniIznajmljivanje(izmenjeno);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                    
                    case Operacija.UCITAJ_ZTD:
                        try {
                            List<ZtD> ztdList = controller.Controller.getInstance().ucitajZtD();
                            odgovor.setOdgovor(ztdList);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;

                    case Operacija.DODAJ_ZTD:
                        try {
                            ZtD noviZtD = (ZtD) zahtev.getParametar();
                            controller.Controller.getInstance().dodajZtD(noviZtD);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;

                    case Operacija.OBRISI_ZTD:
                        try {
                            ZtD obrisanZtD = (ZtD) zahtev.getParametar();
                            controller.Controller.getInstance().obrisiZtD(obrisanZtD);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    default:
                        System.out.println("Greska operacija");
                        
                }
                

                posiljalac.posalji(odgovor);
            } catch (Exception ex) {
                            System.getLogger(ObradaKlijentskihZahteva.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                        }

        }
    }
    
    public void prekiniNit(){
        kraj=true;
        try {
            socket.close();
            interrupt();
        } catch (IOException ex) {
            System.getLogger(ObradaKlijentskihZahteva.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    
    
}
