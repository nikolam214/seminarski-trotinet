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
public class ZaposleniTest {
    
    private Zaposleni zaposleni;

    @BeforeEach
    void setUp() {
        zaposleni = new Zaposleni();
    }

    @AfterEach
    void tearDown() {
        zaposleni = null;
    }

    @Test
    void testSetIdSveOk() {
        zaposleni.setId(2);
        assertEquals(2, zaposleni.getId());
    }

    @Test
    void testSetIdNula() {
        assertThrows(IllegalArgumentException.class, () -> zaposleni.setId(0));
    }

    @Test
    void testSetIdNegativan() {
        assertThrows(IllegalArgumentException.class, () -> zaposleni.setId(-2));
    }

    @Test
    void testSetKorisnickoImeSveOk() {
        zaposleni.setKorisnickoIme("nikola");
        assertEquals("nikola", zaposleni.getKorisnickoIme());
    }

    @Test
    void testSetKorisnickoImeNull() {
        assertThrows(IllegalArgumentException.class, () -> zaposleni.setKorisnickoIme(null));
    }

    @Test
    void testSetKorisnickoImePrazno() {
        assertThrows(IllegalArgumentException.class, () -> zaposleni.setKorisnickoIme(""));
    }

    @Test
    void testSetSifraSveOk() {
        zaposleni.setSifra("tajna123");
        assertEquals("tajna123", zaposleni.getSifra());
    }

    @Test
    void testSetSifraNull() {
        assertThrows(IllegalArgumentException.class, () -> zaposleni.setSifra(null));
    }

    @Test
    void testSetSifraPrazna() {
        assertThrows(IllegalArgumentException.class, () -> zaposleni.setSifra(""));
    }

    @Test
    void testSetImeSveOk() {
        zaposleni.setIme("Nikola");
        assertEquals("Nikola", zaposleni.getIme());
    }

    @Test
    void testSetImeNull() {
        assertThrows(IllegalArgumentException.class, () -> zaposleni.setIme(null));
    }

    @Test
    void testSetImePrazno() {
        assertThrows(IllegalArgumentException.class, () -> zaposleni.setIme(""));
    }

    @Test
    void testSetPrezimeSveOk() {
        zaposleni.setPrezime("Markovic");
        assertEquals("Markovic", zaposleni.getPrezime());
    }

    @Test
    void testSetPrezimeNull() {
        assertThrows(IllegalArgumentException.class, () -> zaposleni.setPrezime(null));
    }

    @Test
    void testSetPrezimePrazno() {
        assertThrows(IllegalArgumentException.class, () -> zaposleni.setPrezime(""));
    }

    @Test
    void testKonstruktorSaParametrima() {
        Zaposleni z = new Zaposleni(1, "nikola", "tajna123", "Nikola", "Markovic");
        assertEquals(1, z.getId());
        assertEquals("nikola", z.getKorisnickoIme());
        assertEquals("tajna123", z.getSifra());
        assertEquals("Nikola", z.getIme());
        assertEquals("Markovic", z.getPrezime());
    }

    @Test
    void testKonstruktorNeispravneVrednosti() {
        assertThrows(IllegalArgumentException.class,
                () -> new Zaposleni(0, null, "", null, ""));
    }

    @Test
    void testEqualsIsti() {
        Zaposleni z1 = new Zaposleni(1, "nikola", "tajna123", "Nikola", "Markovic");
        Zaposleni z2 = new Zaposleni(2, "nikola", "tajna123", "Petar", "Petrovic");
        assertEquals(z1, z2);
        assertEquals(z1.hashCode(), z2.hashCode());
    }

    @Test
    void testEqualsRazliciti() {
        Zaposleni z1 = new Zaposleni(1, "nikola", "tajna123", "Nikola", "Markovic");
        Zaposleni z2 = new Zaposleni(1, "pera", "druga", "Nikola", "Markovic");
        assertNotEquals(z1, z2);
    }

    @Test
    void testEqualsNullIDrugaKlasa() {
        assertFalse(zaposleni.equals(null));
        assertFalse(zaposleni.equals("string"));
    }

    @Test
    void testToString() {
        zaposleni.setIme("Nikola");
        zaposleni.setPrezime("Markovic");
        String s = zaposleni.toString();
        assertTrue(s.contains("Nikola"));
        assertTrue(s.contains("Markovic"));
    }

}
