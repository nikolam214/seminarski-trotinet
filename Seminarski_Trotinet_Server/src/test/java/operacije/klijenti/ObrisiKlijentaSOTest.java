/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.klijenti;

import domen.Klijent;
import domen.Mesto;
import domen.Zaposleni;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author nikola
 */
public class ObrisiKlijentaSOTest {
    
    private ObrisiKlijentaSO operacija;

    @BeforeEach
    void setUp() {
        operacija = new ObrisiKlijentaSO();
    }

    @AfterEach
    void tearDown() {
        operacija = null;
    }

    @Test
    void testPreduslovSveOk() {
        assertDoesNotThrow(() -> operacija.preduslovi(new Klijent()));
    }

    @Test
    void testPreduslovNull() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(null));
        assertEquals("Sistem nije mogao da obrise klijenta", e.getMessage());
    }
    
    @Test
    void testPreduslovPogresnaKlasa() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(new Zaposleni()));
        assertEquals("Sistem nije mogao da obrise klijenta", e.getMessage());
    }
    
    @Test
    void testObrisiKlijenta() throws Exception {
        new operacije.mesto.DodajMestoSO().izvrsi(new Mesto(1, "TestMesto", 99999), null);
        operacije.mesto.UcitajMestoSO um = new operacije.mesto.UcitajMestoSO();
        um.izvrsi(null, null);
        Mesto mesto = null;
        for (Mesto m : um.getMesta()) {
            if ("TestMesto".equals(m.getNaziv())) {
                mesto = m;
            }
        }
        assertNotNull(mesto);

        new DodajKlijentaSO().izvrsi(new Klijent(1, "TestIme", "TestPrezime", 123456789, mesto), null);
        UcitajKlijenteSO uk = new UcitajKlijenteSO();
        uk.izvrsi(null, null);
        Klijent uBazi = null;
        
        for (Klijent k : uk.getKlijenti()) {
            if ("TestIme".equals(k.getIme()) && "TestPrezime".equals(k.getPrezime())) {
                uBazi = k;
            }
        }
        assertNotNull(uBazi);

        operacija.izvrsi(uBazi, null);

        UcitajKlijenteSO uk2 = new UcitajKlijenteSO();
        uk2.izvrsi(null, null);
        boolean postoji = false;
        for (Klijent k : uk2.getKlijenti()) {
            if (k.getId() == uBazi.getId()) {
                postoji = true;
            }
        }
        assertFalse(postoji);

        new operacije.mesto.ObrisiMestoSO().izvrsi(mesto, null);
    }
}
