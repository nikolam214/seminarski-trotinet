/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.termindezurstva;

import domen.Klijent;
import domen.TerminDezurstva;
import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author nikola
 */
public class AzurirajTerminDezurstvaSOTest {
    
    private AzurirajTerminDezurstvaSO operacija;
    private TerminDezurstva termin;

    @BeforeEach
    void setUp() {
        operacija = new AzurirajTerminDezurstvaSO();
        termin = new TerminDezurstva(1, "prva", new Date(), 8);
    }

    @AfterEach
    void tearDown() {
        operacija = null;
        termin = null;
    }

    @Test
    void testPreduslovSveOk() {
        assertDoesNotThrow(() -> operacija.preduslovi(termin));
    }

    @Test
    void testPreduslovNull() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(null));
        assertEquals("Sistem nije mogao da azurira termin dezurstva", e.getMessage());
    }

    @Test
    void testPreduslovPogresnaKlasa() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(new Klijent()));
        assertEquals("Sistem nije mogao da azurira termin dezurstva", e.getMessage());
    }
    
    @Test
    void testIzmenaTermin() throws Exception {
        
        new DodajTerminDezurstvaSO().izvrsi(new TerminDezurstva(1, "test", new Date(), 8), null);
        UcitajTermineDezurstvaSO ucitaj = new UcitajTermineDezurstvaSO();
        ucitaj.izvrsi(null, null);
        TerminDezurstva uBazi = null;
        
        for (TerminDezurstva t : ucitaj.getTermini()) {
            if ("test".equals(t.getTipSmene())) {
                uBazi = t;
            }
        }
        assertNotNull(uBazi);

        uBazi.setTipSmene("izmenjen");
        uBazi.setTrajanje(12);
        operacija.izvrsi(uBazi, null);

        UcitajTermineDezurstvaSO ucitaj2 = new UcitajTermineDezurstvaSO();
        ucitaj2.izvrsi(null, null);
        TerminDezurstva izmenjen = null;
        for (TerminDezurstva t : ucitaj2.getTermini()) {
            if (t.getId() == uBazi.getId()) {
                izmenjen = t;
            }
        }

        assertNotNull(izmenjen);
        assertEquals("izmenjen", izmenjen.getTipSmene());
        assertEquals(12, izmenjen.getTrajanje());

        new ObrisiTerminDezurstvaSO().izvrsi(izmenjen, null);
    }
}
