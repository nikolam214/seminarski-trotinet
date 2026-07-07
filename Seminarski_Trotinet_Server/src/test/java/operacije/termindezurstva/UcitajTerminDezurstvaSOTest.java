/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.termindezurstva;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author nikola
 */
public class UcitajTerminDezurstvaSOTest {
    
    private UcitajTermineDezurstvaSO operacija;

    @BeforeEach
    void setUp() {
        operacija = new UcitajTermineDezurstvaSO();
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
