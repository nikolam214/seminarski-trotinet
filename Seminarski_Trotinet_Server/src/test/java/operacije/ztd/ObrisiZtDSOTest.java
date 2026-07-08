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
public class ObrisiZtDSOTest {
    
    private ObrisiZtDSO operacija;

    @BeforeEach
    void setUp() {
        operacija = new ObrisiZtDSO();
    }

    @AfterEach
    void tearDown() {
        operacija = null;
    }

    @Test
    void testPreduslovSveOk() {
        assertDoesNotThrow(() -> operacija.preduslovi(new ZtD()));
    }

    @Test
    void testPreduslovNull() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(null));
        assertEquals("Sistem nije mogao da obrise ZtD", e.getMessage());
    }

    @Test
    void testPreduslovPogresnaKlasa() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(new Klijent()));
        assertEquals("Sistem nije mogao da obrise ZtD", e.getMessage());
    }
    
    @Test
    void testObrisiZtD() throws Exception {
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

        new operacije.termindezurstva.DodajTerminDezurstvaSO().izvrsi(new TerminDezurstva(1, "ztdTest", new Date(), 8), null);
        operacije.termindezurstva.UcitajTermineDezurstvaSO ut = new operacije.termindezurstva.UcitajTermineDezurstvaSO();
        ut.izvrsi(null, null);
        TerminDezurstva t = null;
        for (TerminDezurstva x : ut.getTermini()) {
            if ("ztdTest".equals(x.getTipSmene())) {
                t = x;
            }
        }
        assertNotNull(t);

        new DodajZtDSO().izvrsi(new ZtD(z, t, new Date(), "napomenaTest"), null);
        UcitajZtDSO u = new UcitajZtDSO();
        u.izvrsi(null, null);
        ZtD uBazi = null;
        for (ZtD x : u.getZtDList()) {
            if ("napomenaTest".equals(x.getNapomena())) {
                uBazi = x;
            }
        }
        assertNotNull(uBazi);

        operacija.izvrsi(uBazi, null);

        UcitajZtDSO u2 = new UcitajZtDSO();
        u2.izvrsi(null, null);
        boolean postoji = false;
        for (ZtD x : u2.getZtDList()) {
            if ("napomenaTest".equals(x.getNapomena())) {
                postoji = true;
            }
        }
        assertFalse(postoji);

        new operacije.zaposleni.ObrisiZaposlenogSO().izvrsi(z, null);
        new operacije.termindezurstva.ObrisiTerminDezurstvaSO().izvrsi(t, null);
    }
    
}
