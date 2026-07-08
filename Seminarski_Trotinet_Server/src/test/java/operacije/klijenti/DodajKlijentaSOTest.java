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
public class DodajKlijentaSOTest {
    
    private DodajKlijentaSO operacija;
    private Klijent klijent;

    @BeforeEach
    void setUp() {
        operacija = new DodajKlijentaSO();
        klijent = new Klijent(1, "Marko", "Markovic", 641234567L, new Mesto(1, "Beograd", 11000));
    }

    @AfterEach
    void tearDown() {
        operacija = null;
        klijent = null;
    }

    @Test
    void testPreduslovSveOk() {
        assertDoesNotThrow(() -> operacija.preduslovi(klijent));
    }

    @Test
    void testPreduslovNull() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(null));
        assertEquals("Sistem nije mogao da doda klijenta", e.getMessage());
    }
    
    @Test
    void testPreduslovPogresnaKlasa() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(new Zaposleni()));
        assertEquals("Sistem nije mogao da doda klijenta", e.getMessage());
    }

    @Test
    void testPreduslovImeKratko() {
        klijent.setIme("M");
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(klijent));
        assertEquals("Ime mora imati najmanje 2 karaktera", e.getMessage());
    }

    @Test
    void testPreduslovPrezimeKratko() {
        klijent.setPrezime("M");
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(klijent));
        assertEquals("Prezime mora imati najmanje 2 karaktera", e.getMessage());
    }
    
    @Test
    void testDodajeKlijenta() throws Exception {
        
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

        operacija.izvrsi(new Klijent(1, "TestIme", "TestPrezime", 123456789, mesto), null);

        UcitajKlijenteSO uk = new UcitajKlijenteSO();
        uk.izvrsi(null, null);
        Klijent uBazi = null;
        
        
        for (Klijent k : uk.getKlijenti()) {
            if ("TestIme".equals(k.getIme()) && "TestPrezime".equals(k.getPrezime())) {
                uBazi = k;
            }
        }

        assertNotNull(uBazi);
        assertEquals(123456789, uBazi.getBrojtelefona());

        new ObrisiKlijentaSO().izvrsi(uBazi, null);
        new operacije.mesto.ObrisiMestoSO().izvrsi(mesto, null);
    }
}
