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
public class MestoTest {
    
    
    private Mesto mesto;

    @BeforeEach
    void setUp() {
        mesto = new Mesto();
    }

    @AfterEach
    void tearDown() {
        mesto = null;
    }

    @Test
    void testSetIdSveOk() {
        mesto.setId(3);
        assertEquals(3, mesto.getId());
    }
    
    @Test
    void testSetIdNula() {
        assertThrows(IllegalArgumentException.class, () -> mesto.setId(0));
    }

    @Test
    void testSetIdNegativan() {
        assertThrows(IllegalArgumentException.class, () -> mesto.setId(-2));
    }

    @Test
    void testSetNazivSveOk() {
        mesto.setNaziv("Beograd");
        assertEquals("Beograd", mesto.getNaziv());
    }
    
    @Test
    void testSetNazivNull() {
        assertThrows(IllegalArgumentException.class, () -> mesto.setNaziv(null));
    }

    @Test
    void testSetNazivPrazan() {
        assertThrows(IllegalArgumentException.class, () -> mesto.setNaziv(""));
    }

    @Test
    void testSetPostanskiBrojSveOk() {
        mesto.setPostanskiBroj(11000);
        assertEquals(11000, mesto.getPostanskiBroj());
    }

    @Test
    void testSetPostanskiBrojNula() {
        assertThrows(IllegalArgumentException.class, () -> mesto.setPostanskiBroj(0));
    }
    
    @Test
    void testSetPostanskiBrojNegativan() {
        assertThrows(IllegalArgumentException.class, () -> mesto.setPostanskiBroj(-11000));
    }

    @Test
    void testKonstruktorSaParametrima() {
        Mesto m = new Mesto(1, "Beograd", 11000);
        assertEquals(1, m.getId());
        assertEquals("Beograd", m.getNaziv());
        assertEquals(11000, m.getPostanskiBroj());
    }

    @Test
    void testKonstruktorNeispravneVrednosti() {
        assertThrows(IllegalArgumentException.class,
                () -> new Mesto(0, null, -5));
    }
    
    @Test
    void testEqualsIsti() {
        Mesto m1 = new Mesto(1, "Beograd", 11000);
        Mesto m2 = new Mesto(2, "Beograd", 11000);
        assertEquals(m1, m2);
        assertEquals(m1.hashCode(), m2.hashCode());
    }

    @Test
    void testEqualsRazliciti() {
        Mesto m1 = new Mesto(1, "Beograd", 11000);
        Mesto m2 = new Mesto(1, "Novi Sad", 21000);
        assertNotEquals(m1, m2);
    }

    @Test
    void testEqualsNullIDrugaKlasa() {
        assertFalse(mesto.equals(null));
        assertFalse(mesto.equals("string"));
    }
    
    @Test
    void testToString() {
        mesto.setNaziv("Beograd");
        assertTrue(mesto.toString().contains("Beograd"));
    }
}
