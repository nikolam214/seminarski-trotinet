/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koordinator;

import controller.DodajKlijentaController;
import controller.DodajMestoController;
import controller.DodajTerminDezurstvaController;
import controller.DodajTrotinetController;
import controller.DodajZaposlenogController;
import controller.GlavnaFormaController;
import controller.IzmeniIznajmljivanjeController;
import controller.KreirajIznajmljivanjeController;
import controller.LoginController;
import controller.PretraziIznajmljivanjeController;
import controller.PretraziKlijenteController;
import controller.PretraziMestoController;
import controller.PretraziTerminDezurstvaController;
import controller.PretraziTrotinetController;
import controller.PretraziZaposleneController;
import controller.ZtDController;
import domen.Zaposleni;
import forme.DodajKlijentaForma;
import forme.DodajMestoForma;
import forme.DodajTerminDezurstvaForma;
import forme.DodajTrotinetForma;
import forme.DodajZaposlenog;
import forme.FormaMod;
import forme.GlavnaForma;
import forme.IzmeniIznajmljivanjeForma;
import forme.KreirajIznajmljivanjeForma;
import forme.LoginForma;
import forme.PretraziIznamljivanjeForma;
import forme.PretraziKlijenteForma;
import forme.PretraziMestoForma;
import forme.PretraziTermineDezurstvaForma;
import forme.PretraziTrotinetForma;
import forme.PretraziZaposlene;
import forme.ZtDForma;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author nikola
 */
public class Koordinator {
    private static Koordinator instance;
    private LoginController loginController;
    private GlavnaFormaController glavnaFormaController;
    private Zaposleni ulogovani;
    private PretraziKlijenteController pkController;
    private DodajKlijentaController dkController;
    private Map<String, Object> parametri;
    private PretraziZaposleneController pzController;
    private DodajZaposlenogController dzController;
    private PretraziMestoController pmController;
    private DodajMestoController dmController;
    private PretraziTrotinetController ptController;
    private DodajTrotinetController dtController;
    private PretraziTerminDezurstvaController ptdController;
    private DodajTerminDezurstvaController dtdController;
    private PretraziIznajmljivanjeController piController;
    private KreirajIznajmljivanjeController kiController;
    private ZtDController ztdController;

    private Koordinator() {
        parametri=new HashMap<>();
    }

    public static Koordinator getInstance() {
        
        if(instance==null){
            instance= new Koordinator();
        }
        return instance;
    }

    public void otvoriLoginFormu() {
        loginController= new LoginController(new LoginForma());
        loginController.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
        glavnaFormaController= new GlavnaFormaController(new GlavnaForma());
        glavnaFormaController.otvoriFormu();
    }

    public Zaposleni getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Zaposleni ulogovani) {
        this.ulogovani = ulogovani;
    }

    public void otvoriPetraziKlijenteForma() {
        pkController= new PretraziKlijenteController(new PretraziKlijenteForma());
        pkController.otvoriFormu();
    }
    
    public void otvoriDodajKlijenteForm() {
        dkController= new DodajKlijentaController(new DodajKlijentaForma());
        dkController.otvoriFormu(FormaMod.DODAJ);
    }
    
    public void osveziTabeluKlijenata() {
        if (pkController != null) {
            pkController.pripremiFormu();
        }
    }
    
    public void dodajParam(String s, Object o){
        parametri.put(s,o);
    }
    
    public Object vratiParam(String s){
        return parametri.get(s);
    }

    public void otvoriAzurirajKlijenteForma() {
        dkController= new DodajKlijentaController(new DodajKlijentaForma());
        dkController.otvoriFormu(FormaMod.IZMENI);
    }
    
    public void otvoriPretraziZaposleneFormu() {
        pzController = new PretraziZaposleneController(new PretraziZaposlene());
        pzController.otvoriFormu();
    }

    public void otvoriDodajZaposlenogFormu() {
        dzController = new DodajZaposlenogController(new DodajZaposlenog());
        dzController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriAzurirajZaposlenogFormu() {
        dzController = new DodajZaposlenogController(new DodajZaposlenog());
        dzController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziTabeluZaposlenih() {
        if (pzController != null) {
            pzController.pripremiFormu();
        }
    }

    public void otvoriPretraziMestoFormu() {
        pmController = new PretraziMestoController(new PretraziMestoForma());
        pmController.otvoriFormu();
    }

    public void otvoriDodajMestoFormu() {
        dmController = new DodajMestoController(new DodajMestoForma());
        dmController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriAzurirajMestoFormu() {
        dmController = new DodajMestoController(new DodajMestoForma());
        dmController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziTabeluMesta() {
        if (pmController != null) {
            pmController.pripremiFormu();
        }
    }
    
    public void otvoriPretraziTrotinetFormu() {
        ptController = new PretraziTrotinetController(new PretraziTrotinetForma());
        ptController.otvoriFormu();
    }

    public void otvoriDodajTrotinetFormu() {
        dtController = new DodajTrotinetController(new DodajTrotinetForma());
        dtController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriAzurirajTrotinetFormu() {
        dtController = new DodajTrotinetController(new DodajTrotinetForma());
        dtController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziTabeluTrotineta() {
        if (ptController != null) {
            ptController.pripremiFormu();
        }
    }
    

    public void otvoriPretraziTermineDezurstvaFormu() {
        ptdController = new PretraziTerminDezurstvaController(new PretraziTermineDezurstvaForma());
        ptdController.otvoriFormu();
    }

    public void otvoriDodajTerminDezurstvaFormu() {
        dtdController = new DodajTerminDezurstvaController(new DodajTerminDezurstvaForma());
        dtdController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriAzurirajTerminDezurstvaFormu() {
        dtdController = new DodajTerminDezurstvaController(new DodajTerminDezurstvaForma());
        dtdController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziTabeluTerminaDezurstva() {
        if (ptdController != null) {
            ptdController.pripremiFormu();
        }
    }

    

    public void otvoriPretraziIznajmljivanjeFormu() {
        piController = new PretraziIznajmljivanjeController(new PretraziIznamljivanjeForma());
        piController.otvoriFormu();
    }

    public void otvoriKreirajIznajmljivanjeFormu() {
        kiController = new KreirajIznajmljivanjeController(new KreirajIznajmljivanjeForma());
        kiController.otvoriFormu();
    }

    public void otvoriIzmeniIznajmljivanjeFormu() {
        new IzmeniIznajmljivanjeController(new IzmeniIznajmljivanjeForma()).otvoriFormu();
    }

    public void osveziTabeluIznajmljivanja() {
        if (piController != null) {
            piController.pripremiFormu();
        }
    }
    
    public void otvoriZtDFormu() {
        ztdController = new ZtDController(new ZtDForma());
        ztdController.otvoriFormu();
    }
}
