/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.klijenti;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author nikola
 */
public class UcitajKlijentaSOTest {
    
    private UcitajKlijenteSO operacija;

    @BeforeEach
    void setUp() {
        operacija = new UcitajKlijenteSO();
    }

    @AfterEach
    void tearDown() {
        operacija = null;
    }

    @Test
    void testPreduslovNemaOgranicenja() {
        assertDoesNotThrow(() -> operacija.preduslovi(null));
    }
    
    void testUcitajKlijenta() throws Exception {
        
        operacija.izvrsi(null, null);
        assertNotNull(operacija.getKlijenti());
    }
}
