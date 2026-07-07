/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.trotinet;

import domen.Klijent;
import domen.Trotinet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author nikola
 */
public class DodajTrotinetSOTest {
    
    
    private DodajTrotinetSO operacija;
    private Trotinet trotinet;

    @BeforeEach
    void setUp() {
        operacija = new DodajTrotinetSO();
        trotinet = new Trotinet(1, "Xiaomi Pro 2", 15.5);
    }

    @AfterEach
    void tearDown() {
        operacija = null;
        trotinet = null;
    }

    @Test
    void testPreduslovSveOk() {
        assertDoesNotThrow(() -> operacija.preduslovi(trotinet));
    }

    @Test
    void testPreduslovNull() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(null));
        assertEquals("Sistem nije mogao da doda trotinet", e.getMessage());
    }

    @Test
    void testPreduslovPogresnaKlasa() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(new Klijent()));
        assertEquals("Sistem nije mogao da doda trotinet", e.getMessage());
    }

    @Test
    void testPreduslovModelKratak() {
        trotinet.setModelTrotineta("X");
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(trotinet));
        assertEquals("Model mora imati najmanje 2 karaktera", e.getMessage());
    }
}
