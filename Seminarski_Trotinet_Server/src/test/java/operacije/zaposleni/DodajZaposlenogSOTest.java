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
public class DodajZaposlenogSOTest {
    
    private DodajZaposlenogSO operacija;
    private Zaposleni zaposleni;

    @BeforeEach
    void setUp() {
        operacija = new DodajZaposlenogSO();
        zaposleni = new Zaposleni(1, "nikola", "tajna123", "Nikola", "Markovic");
    }

    @AfterEach
    void tearDown() {
        operacija = null;
        zaposleni = null;
    }

    @Test
    void testPreduslovSveOk() {
        assertDoesNotThrow(() -> operacija.preduslovi(zaposleni));
    }

    @Test
    void testPreduslovNull() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(null));
        assertEquals("Sistem nije mogao da doda zaposlenog", e.getMessage());
    }

    @Test
    void testPreduslovPogresnaKlasa() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(new Klijent()));
        assertEquals("Sistem nije mogao da doda zaposlenog", e.getMessage());
    }

    @Test
    void testPreduslovImeKratko() {
        zaposleni.setIme("N");
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(zaposleni));
        assertEquals("Ime mora imati najmanje 2 karaktera", e.getMessage());
    }

    @Test
    void testPreduslovPrezimeKratko() {
        zaposleni.setPrezime("M");
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(zaposleni));
        assertEquals("Prezime mora imati najmanje 2 karaktera", e.getMessage());
    }

    @Test
    void testPreduslovSifraKratka() {
        zaposleni.setSifra("abc");
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(zaposleni));
        assertEquals("Sifra mora imati najmanje 4 karaktera", e.getMessage());
    }
}
