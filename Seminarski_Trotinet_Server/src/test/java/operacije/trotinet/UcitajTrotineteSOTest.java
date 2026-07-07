/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.trotinet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author nikola
 */
public class UcitajTrotineteSOTest {
    
    private UcitajTrotineteSO operacija;

    @BeforeEach
    void setUp() {
        operacija = new UcitajTrotineteSO();
    }

    @AfterEach
    void tearDown() {
        operacija = null;
    }

    @Test
    void testPreduslovNemaOgranicenja() {
        assertDoesNotThrow(() -> operacija.preduslovi(null));
    }
}
