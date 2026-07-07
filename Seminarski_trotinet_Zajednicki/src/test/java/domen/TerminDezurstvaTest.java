/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author nikola
 */
public class TerminDezurstvaTest {
    
    private TerminDezurstva termin;

    @BeforeEach
    void setUp() {
        termin = new TerminDezurstva();
    }

    @AfterEach
    void tearDown() {
        termin = null;
    }

    @Test
    void testSetIdSveOk() {
        termin.setId(7);
        assertEquals(7, termin.getId());
    }

    @Test
    void testSetIdNula() {
        assertThrows(IllegalArgumentException.class, () -> termin.setId(0));
    }

    @Test
    void testSetIdNegativan() {
        assertThrows(IllegalArgumentException.class, () -> termin.setId(-7));
    }

    @Test
    void testSetTipSmeneSveOk() {
        termin.setTipSmene("prva");
        assertEquals("prva", termin.getTipSmene());
    }

    @Test
    void testSetTipSmeneNull() {
        assertThrows(IllegalArgumentException.class, () -> termin.setTipSmene(null));
    }

    @Test
    void testSetTipSmenePrazan() {
        assertThrows(IllegalArgumentException.class, () -> termin.setTipSmene(""));
    }

    @Test
    void testSetVremePocetkaSveOk() {
        Date sad = new Date();
        termin.setVremePocetka(sad);
        assertEquals(sad, termin.getVremePocetka());
    }

    @Test
    void testSetVremePocetkaNull() {
        assertThrows(IllegalArgumentException.class, () -> termin.setVremePocetka(null));
    }

    @Test
    void testSetTrajanjeSveOk() {
        termin.setTrajanje(8);
        assertEquals(8, termin.getTrajanje());
    }

    @Test
    void testSetTrajanjeNula() {
        assertThrows(IllegalArgumentException.class, () -> termin.setTrajanje(0));
    }

    @Test
    void testSetTrajanjeNegativno() {
        assertThrows(IllegalArgumentException.class, () -> termin.setTrajanje(-8));
    }

    @Test
    void testKonstruktorSaParametrima() {
        Date sad = new Date();
        TerminDezurstva t = new TerminDezurstva(1, "prva", sad, 8);
        assertEquals(1, t.getId());
        assertEquals("prva", t.getTipSmene());
        assertEquals(sad, t.getVremePocetka());
        assertEquals(8, t.getTrajanje());
    }

    @Test
    void testKonstruktorNeispravneVrednosti() {
        assertThrows(IllegalArgumentException.class,
                () -> new TerminDezurstva(0, null, null, -1));
    }

    @Test
    void testEqualsIsti() {
        Date sad = new Date();
        TerminDezurstva t1 = new TerminDezurstva(1, "prva", sad, 8);
        TerminDezurstva t2 = new TerminDezurstva(1, "druga", sad, 12);
        assertEquals(t1, t2);
        assertEquals(t1.hashCode(), t2.hashCode());
    }

    @Test
    void testEqualsRazliciti() {
        Date sad = new Date();
        TerminDezurstva t1 = new TerminDezurstva(1, "prva", sad, 8);
        TerminDezurstva t2 = new TerminDezurstva(2, "prva", sad, 8);
        assertNotEquals(t1, t2);
    }

    @Test
    void testEqualsNullIDrugaKlasa() {
        assertFalse(termin.equals(null));
        assertFalse(termin.equals("string"));
    }

    @Test
    void testToString() {
        termin.setTipSmene("prva");
        assertTrue(termin.toString().contains("prva"));
    }
}

