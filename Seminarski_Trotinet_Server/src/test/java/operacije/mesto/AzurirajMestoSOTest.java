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
public class AzurirajMestoSOTest {
    
    private AzurirajMestoSO operacija;
    private Mesto mesto;

    @BeforeEach
    void setUp() {
        operacija = new AzurirajMestoSO();
        mesto = new Mesto(1, "Beograd", 11000);
    }

    @AfterEach
    void tearDown() {
        operacija = null;
        mesto = null;
    }

    @Test
    void testPreduslovSveOk() {
        assertDoesNotThrow(() -> operacija.preduslovi(mesto));
    }

    @Test
    void testPreduslovNull() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(null));
        assertEquals("Sistem nije mogao da azurira mesto", e.getMessage());
    }
    
    @Test
    void testPreduslovPogresnaKlasa() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(new Klijent()));
        assertEquals("Sistem nije mogao da azurira mesto", e.getMessage());
    }

    @Test
    void testPreduslovNazivKratak() {
        mesto.setNaziv("B");
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(mesto));
        assertEquals("Naziv mora imati najmanje 2 karaktera", e.getMessage());
    }
}
