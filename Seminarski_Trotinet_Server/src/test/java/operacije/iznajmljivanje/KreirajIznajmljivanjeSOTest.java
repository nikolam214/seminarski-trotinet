/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.iznajmljivanje;

import domen.Iznajmljivanje;
import domen.Klijent;
import domen.Mesto;
import domen.StavkaIznajmljivanja;
import domen.Trotinet;
import domen.Zaposleni;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author nikola
 */
public class KreirajIznajmljivanjeSOTest {
    
    private KreirajIznajmljivanjeSO operacija;
    private Iznajmljivanje iznajmljivanje;

    @BeforeEach
    void setUp() {
        operacija = new KreirajIznajmljivanjeSO();
        Zaposleni zaposleni = new Zaposleni(1, "nikola", "tajna123", "Nikola", "Markovic");
        Klijent klijent = new Klijent(1, "Marko", "Markovic", 641234567L, new Mesto(1, "Beograd", 11000));
        iznajmljivanje = new Iznajmljivanje(1, 450.0, zaposleni, klijent);

        Calendar cal = Calendar.getInstance();
        Date ranije = cal.getTime();
        cal.add(Calendar.MINUTE, 30);
        Date kasnije = cal.getTime();
        Trotinet trotinet = new Trotinet(1, "Xiaomi Pro 2", 15.5);
        StavkaIznajmljivanja stavka = new StavkaIznajmljivanja(1, iznajmljivanje, ranije, kasnije, 15.5, 465.0, trotinet);
        iznajmljivanje.getStavkeIznajmljivanja().add(stavka);
    }

    @AfterEach
    void tearDown() {
        operacija = null;
        iznajmljivanje = null;
    }

    @Test
    void testPreduslovSveOk() {
        assertDoesNotThrow(() -> operacija.preduslovi(iznajmljivanje));
    }

    @Test
    void testPreduslovNull() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(null));
        assertEquals("Sistem nije mogao da kreira iznajmljivanje", e.getMessage());
    }

    @Test
    void testPreduslovPogresnaKlasa() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(new Klijent()));
        assertEquals("Sistem nije mogao da kreira iznajmljivanje", e.getMessage());
    }

    @Test
    void testPreduslovBezStavki() {
        iznajmljivanje.getStavkeIznajmljivanja().clear();
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(iznajmljivanje));
        assertEquals("Iznajmljivanje mora imati najmanje jednu stavku", e.getMessage());
    }
    
    @Test
    void testKreirajIznajmljivanje() throws Exception {
        
        new operacije.mesto.DodajMestoSO().izvrsi(new Mesto(1, "TestMesto", 99999), null);
        operacije.mesto.UcitajMestoSO um = new operacije.mesto.UcitajMestoSO();
        um.izvrsi(null, null);
        Mesto mesto = null;
        
        for (Mesto m : um.getMesta()) if ("TestMesto".equals(m.getNaziv())) mesto = m;

        new operacije.klijenti.DodajKlijentaSO().izvrsi(new Klijent(1, "TestIme", "TestPrezime", 123456789, mesto), null);
        operacije.klijenti.UcitajKlijenteSO uk = new operacije.klijenti.UcitajKlijenteSO();
        uk.izvrsi(null, null);
        Klijent klijent = null;
        
        for (Klijent k : uk.getKlijenti()) if ("TestIme".equals(k.getIme())) klijent = k;

        new operacije.zaposleni.DodajZaposlenogSO().izvrsi(new Zaposleni(1, "test", "test1234", "TestIme", "TestPrezime"), null);
        operacije.zaposleni.UcitajZaposleneSO uz = new operacije.zaposleni.UcitajZaposleneSO();
        uz.izvrsi(null, null);
        Zaposleni zaposleni = null;
        
        for (Zaposleni z : uz.getZaposleni()) if ("test".equals(z.getKorisnickoIme())) zaposleni = z;

        new operacije.trotinet.DodajTrotinetSO().izvrsi(new Trotinet(1, "TestTrotinet", 15), null);
        operacije.trotinet.UcitajTrotineteSO ut = new operacije.trotinet.UcitajTrotineteSO();
        ut.izvrsi(null, null);
        Trotinet trotinet = null;
        for (Trotinet t : ut.getTrotineti()) if ("TestTrotinet".equals(t.getModelTrotineta())) trotinet = t;

        Iznajmljivanje novo = new Iznajmljivanje(1, 465.0, zaposleni, klijent);
        Calendar cal = Calendar.getInstance();
        Date ranije = cal.getTime();
        cal.add(Calendar.MINUTE, 30);
        StavkaIznajmljivanja stavka = new StavkaIznajmljivanja(1, novo, ranije, cal.getTime(), 15, 465.0, trotinet);
        novo.getStavkeIznajmljivanja().add(stavka);

        operacija.izvrsi(novo, null);

        assertTrue(novo.getId() > 0);

        UcitajIznajmljivanjaSO ui = new UcitajIznajmljivanjaSO();
        ui.izvrsi(null, null);
        Iznajmljivanje uBazi = null;
        for (Iznajmljivanje i : ui.getIznajmljivanja()) if (i.getId() == novo.getId()) uBazi = i;

        assertNotNull(uBazi);
        assertEquals(465.0, uBazi.getUkupnaCena(), 0.001);
        assertEquals(1, uBazi.getStavkeIznajmljivanja().size());

        repository.db.impl.DbRepositoryGeneric broker = new repository.db.impl.DbRepositoryGeneric();
        broker.connect();
        broker.delete(stavka);
        broker.delete(novo);
        broker.commit();
        new operacije.klijenti.ObrisiKlijentaSO().izvrsi(klijent, null);
        new operacije.zaposleni.ObrisiZaposlenogSO().izvrsi(zaposleni, null);
        new operacije.trotinet.ObrisiTrotinetSO().izvrsi(trotinet, null);
        new operacije.mesto.ObrisiMestoSO().izvrsi(mesto, null);
    }
}
