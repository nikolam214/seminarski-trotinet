/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.ztd;

import domen.Klijent;
import domen.TerminDezurstva;
import domen.Zaposleni;
import domen.ZtD;
import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author nikola
 */
public class DodajZtDSOTest {
    
    private DodajZtDSO operacija;
    private ZtD ztd;

    @BeforeEach
    void setUp() {
        operacija = new DodajZtDSO();
        Zaposleni zaposleni = new Zaposleni(1, "nikola", "tajna123", "Nikola", "Markovic");
        TerminDezurstva termin = new TerminDezurstva(1, "prva", new Date(), 8);
        ztd = new ZtD(zaposleni, termin, new Date(), "zamena kolege");
    }

    @AfterEach
    void tearDown() {
        operacija = null;
        ztd = null;
    }

    @Test
    void testPreduslovSveOk() {
        assertDoesNotThrow(() -> operacija.preduslovi(ztd));
    }

    @Test
    void testPreduslovNull() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(null));
        assertEquals("Sistem nije mogao da doda ZtD", e.getMessage());
    }

    @Test
    void testPreduslovPogresnaKlasa() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(new Klijent()));
        assertEquals("Sistem nije mogao da doda ZtD", e.getMessage());
    }
    
    @Test
    void testDodajZtD() throws Exception {
        
        new operacije.zaposleni.DodajZaposlenogSO().izvrsi(new Zaposleni(1, "test", "test1234", "TestIme", "TestPrezime"), null);
        operacije.zaposleni.UcitajZaposleneSO uz = new operacije.zaposleni.UcitajZaposleneSO();
        uz.izvrsi(null, null);
        Zaposleni z = null;
        
        for (Zaposleni x : uz.getZaposleni()) {
            if ("test".equals(x.getKorisnickoIme())) {
                z = x;
            }
        }
        assertNotNull(z);

        new operacije.termindezurstva.DodajTerminDezurstvaSO().izvrsi(new TerminDezurstva(1, "ztd", new Date(), 8), null);
        operacije.termindezurstva.UcitajTermineDezurstvaSO ut = new operacije.termindezurstva.UcitajTermineDezurstvaSO();
        ut.izvrsi(null, null);
        
        TerminDezurstva t = null;
        
        for (TerminDezurstva x : ut.getTermini()) {
            if ("ztd".equals(x.getTipSmene())) {
                t = x;
            }
        }
        assertNotNull(t);

        ZtD novi = new ZtD(z, t, new Date(), "napomenaTest");
        operacija.izvrsi(novi, null);

        UcitajZtDSO u = new UcitajZtDSO();
        u.izvrsi(null, null);
        ZtD uBazi = null;
        for (ZtD x : u.getZtDList()) {
            if ("napomenaTest".equals(x.getNapomena())) {
                uBazi = x;
            }
        }
        assertNotNull(uBazi);

        new ObrisiZtDSO().izvrsi(uBazi, null);
        new operacije.zaposleni.ObrisiZaposlenogSO().izvrsi(z, null);
        new operacije.termindezurstva.ObrisiTerminDezurstvaSO().izvrsi(t, null);
    }
}
