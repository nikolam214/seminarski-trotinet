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
public class KlijentTest {
    
    private Klijent klijent;
    
    @BeforeEach
    void setUp(){
        klijent=new Klijent();
    }
    
    @AfterEach
    void tearDown() {
        klijent = null;
    }

    @Test
    void testSetIdSveOk() {
        klijent.setId(5);
        assertEquals(5, klijent.getId());
    }

    @Test
    void testSetIdNula() {
        assertThrows(IllegalArgumentException.class, () -> klijent.setId(0));
    }

    @Test
    void testSetIdNegativan() {
        assertThrows(IllegalArgumentException.class, () -> klijent.setId(-3));
    }

    @Test
    void testSetImeSveOk() {
        klijent.setIme("Marko");
        assertEquals("Marko", klijent.getIme());
    }

    @Test
    void testSetImeNull() {
        assertThrows(IllegalArgumentException.class, () -> klijent.setIme(null));
    }
    
    @Test
    void testSetImePrazno() {
        assertThrows(IllegalArgumentException.class, () -> klijent.setIme(""));
    }

    @Test
    void testSetPrezimeSveOk() {
        klijent.setPrezime("Markovic");
        assertEquals("Markovic", klijent.getPrezime());
    }

    @Test
    void testSetPrezimeNull() {
        assertThrows(IllegalArgumentException.class, () -> klijent.setPrezime(null));
    }

    @Test
    void testSetPrezimePrazno() {
        assertThrows(IllegalArgumentException.class, () -> klijent.setPrezime(""));
    }

    @Test
    void testSetBrojtelefonaSveOk() {
        klijent.setBrojtelefona(641234567L);
        assertEquals(641234567L, klijent.getBrojtelefona());
    }

    @Test
    void testSetBrojtelefonaNula() {
        assertThrows(IllegalArgumentException.class, () -> klijent.setBrojtelefona(0));
    }

    @Test
    void testSetBrojtelefonaNegativan() {
        assertThrows(IllegalArgumentException.class, () -> klijent.setBrojtelefona(-1));
    }

    @Test
    void testSetMestoSveOk() {
        Mesto mesto = new Mesto(1, "Beograd", 11000);
        klijent.setMesto(mesto);
        assertEquals(mesto, klijent.getMesto());
    }
    
    @Test
    void testSetMestoNull() {
        assertThrows(IllegalArgumentException.class, () -> klijent.setMesto(null));
    }

    @Test
    void testKonstruktorSaParametrima() {
        Mesto mesto = new Mesto(1, "Beograd", 11000);
        Klijent k = new Klijent(2, "Marko", "Markovic", 641234567L, mesto);
        assertEquals(2, k.getId());
        assertEquals("Marko", k.getIme());
        assertEquals("Markovic", k.getPrezime());
        assertEquals(641234567L, k.getBrojtelefona());
        assertEquals(mesto, k.getMesto());
    }

    @Test
    void testKonstruktorNeispravneVrednosti() {
        assertThrows(IllegalArgumentException.class,
                () -> new Klijent(-1, null, "", 0, null));
    }

    @Test
    void testEqualsIsti() {
        Klijent k1 = new Klijent();
        k1.setId(1);
        k1.setIme("Marko");
        k1.setPrezime("Markovic");
        Klijent k2 = new Klijent();
        k2.setId(1);
        k2.setIme("Marko");
        k2.setPrezime("Markovic");
        assertEquals(k1, k2);
        assertEquals(k1.hashCode(), k2.hashCode());
    }

    @Test
    void testEqualsRazliciti() {
        Klijent k1 = new Klijent();
        k1.setId(1);
        k1.setIme("Marko");
        Klijent k2 = new Klijent();
        k2.setId(2);
        k2.setIme("Janko");
        assertNotEquals(k1, k2);
    }
    
    @Test
    void testEqualsNullIDrugaKlasa() {
        assertFalse(klijent.equals(null));
        assertFalse(klijent.equals("neki string"));
    }

    @Test
    void testToString() {
        klijent.setIme("Marko");
        klijent.setPrezime("Markovic");
        klijent.setBrojtelefona(641234567L);
        String s = klijent.toString();
        assertTrue(s.contains("Marko"));
        assertTrue(s.contains("Markovic"));
    }
}
