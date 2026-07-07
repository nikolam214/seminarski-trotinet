/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author nikola
 */
public class TrotinetTest {
    
    
    private Trotinet trotinet;

    @BeforeEach
    void setUp() {
        trotinet = new Trotinet();
    }

    @AfterEach
    void tearDown() {
        trotinet = null;
    }

    @Test
    void testSetIdSveOk() {
        trotinet.setId(4);
        assertEquals(4, trotinet.getId());
    }

    @Test
    void testSetIdNula() {
        assertThrows(IllegalArgumentException.class, () -> trotinet.setId(0));
    }
    
    @Test
    void testSetIdNegativan() {
        assertThrows(IllegalArgumentException.class, () -> trotinet.setId(-1));
    }

    @Test
    void testSetModelTrotinetaSveOk() {
        trotinet.setModelTrotineta("Xiaomi Pro 2");
        assertEquals("Xiaomi Pro 2", trotinet.getModelTrotineta());
    }

    @Test
    void testSetModelTrotinetaNull() {
        assertThrows(IllegalArgumentException.class, () -> trotinet.setModelTrotineta(null));
    }

    @Test
    void testSetModelTrotinetaPrazan() {
        assertThrows(IllegalArgumentException.class, () -> trotinet.setModelTrotineta(""));
    }
    
    @Test
    void testSetCenaPoMinutuSveOk() {
        trotinet.setCenaPoMinutu(15.5);
        assertEquals(15.5, trotinet.getCenaPoMinutu(), 0.001);
    }

    @Test
    void testSetCenaPoMinutuNula() {
        assertThrows(IllegalArgumentException.class, () -> trotinet.setCenaPoMinutu(0));
    }

    @Test
    void testSetCenaPoMinutuNegativna() {
        assertThrows(IllegalArgumentException.class, () -> trotinet.setCenaPoMinutu(-10));
    }
    
    @Test
    void testKonstruktorSaParametrima() {
        Trotinet t = new Trotinet(1, "Xiaomi Pro 2", 15.5);
        assertEquals(1, t.getId());
        assertEquals("Xiaomi Pro 2", t.getModelTrotineta());
        assertEquals(15.5, t.getCenaPoMinutu(), 0.001);
    }

    @Test
    void testKonstruktorNeispravneVrednosti() {
        assertThrows(IllegalArgumentException.class,
                () -> new Trotinet(0, "", -1));
    }

    @Test
    void testEqualsIsti() {
        Trotinet t1 = new Trotinet(1, "Xiaomi Pro 2", 15.5);
        Trotinet t2 = new Trotinet(1, "Xiaomi Pro 2", 20.0);
        assertEquals(t1, t2);
        assertEquals(t1.hashCode(), t2.hashCode());
    }
    
    @Test
    void testEqualsRazliciti() {
        Trotinet t1 = new Trotinet(1, "Xiaomi Pro 2", 15.5);
        Trotinet t2 = new Trotinet(2, "Segway Ninebot", 15.5);
        assertNotEquals(t1, t2);
    }

    @Test
    void testEqualsNullIDrugaKlasa() {
        assertFalse(trotinet.equals(null));
        assertFalse(trotinet.equals("string"));
    }

    @Test
    void testToString() {
        trotinet.setModelTrotineta("Xiaomi Pro 2");
        assertTrue(trotinet.toString().contains("Xiaomi Pro 2"));
    }
}
