/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.zaposleni;

import domen.Klijent;
import domen.Zaposleni;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author nikola
 */
public class ObrisiZaposlenogSOTest {
    
    private ObrisiZaposlenogSO operacija;

    @BeforeEach
    void setUp() {
        operacija = new ObrisiZaposlenogSO();
    }

    @AfterEach
    void tearDown() {
        operacija = null;
    }

    @Test
    void testPreduslovSveOk() {
        assertDoesNotThrow(() -> operacija.preduslovi(new Zaposleni()));
    }

    @Test
    void testPreduslovNull() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(null));
        assertEquals("Sistem nije mogao da obrise zaposlenog", e.getMessage());
    }

    @Test
    void testPreduslovPogresnaKlasa() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(new Klijent()));
        assertEquals("Sistem nije mogao da obrise zaposlenog", e.getMessage());
    }
    
    @Test
    void testObrisiZaposlenog() throws Exception {
        
        new DodajZaposlenogSO().izvrsi(new Zaposleni(1, "test", "test1234", "TestIme", "TestPrezime"), null);
        
        UcitajZaposleneSO ucitaj = new UcitajZaposleneSO();
        ucitaj.izvrsi(null, null);
        Zaposleni uBazi = null;
        for (Zaposleni z : ucitaj.getZaposleni()) {
            if ("test".equals(z.getKorisnickoIme())) {
                uBazi = z;
            }
        }
        assertNotNull(uBazi);

        operacija.izvrsi(uBazi, null);

        UcitajZaposleneSO ucitaj2 = new UcitajZaposleneSO();
        ucitaj2.izvrsi(null, null);
        boolean postoji = false;
        for (Zaposleni z : ucitaj2.getZaposleni()) {
            if (z.getId() == uBazi.getId()) {
                postoji = true;
            }
        }
        assertFalse(postoji);
    }
}
