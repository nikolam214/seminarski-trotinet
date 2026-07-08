/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.mesto;

import domen.Klijent;
import domen.Mesto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author nikola
 */
public class ObrisiMestoSOTest {
    
    private ObrisiMestoSO operacija;

    @BeforeEach
    void setUp() {
        operacija = new ObrisiMestoSO();
    }

    @AfterEach
    void tearDown() {
        operacija = null;
    }

    @Test
    void testPreduslovSveOk() {
        assertDoesNotThrow(() -> operacija.preduslovi(new Mesto()));
    }

    @Test
    void testPreduslovNull() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(null));
        assertEquals("Sistem nije mogao da obrise mesto", e.getMessage());
    }

    @Test
    void testPreduslovPogresnaKlasa() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(new Klijent()));
        assertEquals("Sistem nije mogao da obrise mesto", e.getMessage());
    }
    
    @Test
    void testObrisiMesto() throws Exception {
        
        new DodajMestoSO().izvrsi(new Mesto(1, "TestMesto", 99999), null);
        UcitajMestoSO ucitaj = new UcitajMestoSO();
        ucitaj.izvrsi(null, null);
        Mesto uBazi = null;
        
        for (Mesto m : ucitaj.getMesta()) {
            if ("TestMesto".equals(m.getNaziv())) {
                uBazi = m;
            }
        }
        assertNotNull(uBazi);

        operacija.izvrsi(uBazi, null);

        UcitajMestoSO ucitaj2 = new UcitajMestoSO();
        ucitaj2.izvrsi(null, null);
        boolean postoji = false;
        for (Mesto m : ucitaj2.getMesta()) {
            if (m.getId() == uBazi.getId()) {
                postoji = true;
            }
        }
        assertFalse(postoji);
    }
}
