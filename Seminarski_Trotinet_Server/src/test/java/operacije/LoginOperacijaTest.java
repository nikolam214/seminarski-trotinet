/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

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
public class LoginOperacijaTest {
    
    private LoginOperacija operacija;

    @BeforeEach
    void setUp() {
        operacija = new LoginOperacija();
    }

    @AfterEach
    void tearDown() {
        operacija = null;
    }

    @Test
    void testPreduslovSveOk() {
        Zaposleni z = new Zaposleni();
        z.setKorisnickoIme("nikola");
        z.setSifra("tajna123");
        assertDoesNotThrow(() -> operacija.preduslovi(z));
    }
    
    @Test
    void testPreduslovNull() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(null));
        assertEquals("Sistem nije mogao da pronadje zaposlenog", e.getMessage());
    }

    @Test
    void testPreduslovPogresnaKlasa() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(new Klijent()));
        assertEquals("Sistem nije mogao da pronadje zaposlenog", e.getMessage());
    }
    
    @Test
    void testUspesnaPrijava() throws Exception {
        new operacije.zaposleni.DodajZaposlenogSO().izvrsi(new Zaposleni(1, "test", "test1234", "TestIme", "TestPrezime"), null);

        Zaposleni zahtev = new Zaposleni();
        zahtev.setKorisnickoIme("test");
        zahtev.setSifra("test1234");
        operacija.izvrsi(zahtev, null);

        assertNotNull(operacija.getZaposleni());
        assertEquals("TestIme", operacija.getZaposleni().getIme());

        new operacije.zaposleni.ObrisiZaposlenogSO().izvrsi(operacija.getZaposleni(), null);
    }
}
