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
public class ZtdTest {

    private ZtD ztd;
    private Zaposleni zaposleni;
    private TerminDezurstva termin;

    @BeforeEach
    void setUp() {
        ztd = new ZtD();
        zaposleni = new Zaposleni(1, "nikola", "tajna123", "Nikola", "Markovic");
        termin = new TerminDezurstva(1, "prva", new Date(), 8);
    }

    @AfterEach
    void tearDown() {
        ztd = null;
        zaposleni = null;
        termin = null;
    }

    @Test
    void testSetZaposleniSveOk() {
        ztd.setZaposleni(zaposleni);
        assertEquals(zaposleni, ztd.getZaposleni());
    }

    @Test
    void testSetZaposleniNull() {
        assertThrows(IllegalArgumentException.class, () -> ztd.setZaposleni(null));
    }

    @Test
    void testSetTerminDezurstvaSveOk() {
        ztd.setTerminDezurstva(termin);
        assertEquals(termin, ztd.getTerminDezurstva());
    }

    @Test
    void testSetTerminDezurstvaNull() {
        assertThrows(IllegalArgumentException.class, () -> ztd.setTerminDezurstva(null));
    }

    @Test
    void testSetDatumSveOk() {
        Date danas = new Date();
        ztd.setDatum(danas);
        assertEquals(danas, ztd.getDatum());
    }

    @Test
    void testSetDatumNull() {
        assertThrows(IllegalArgumentException.class, () -> ztd.setDatum(null));
    }

    @Test
    void testSetNapomenaSveOk() {
        ztd.setNapomena("zamena kolege");
        assertEquals("zamena kolege", ztd.getNapomena());
    }

    @Test
    void testSetNapomenaNull() {
        assertThrows(IllegalArgumentException.class, () -> ztd.setNapomena(null));
    }

    @Test
    void testSetNapomenaPrazna() {
        assertThrows(IllegalArgumentException.class, () -> ztd.setNapomena(""));
    }

    @Test
    void testKonstruktorSaParametrima() {
        Date danas = new Date();
        ZtD z = new ZtD(zaposleni, termin, danas, "zamena kolege");
        assertEquals(zaposleni, z.getZaposleni());
        assertEquals(termin, z.getTerminDezurstva());
        assertEquals(danas, z.getDatum());
        assertEquals("zamena kolege", z.getNapomena());
    }

    @Test
    void testKonstruktorNeispravneVrednosti() {
        assertThrows(IllegalArgumentException.class,
                () -> new ZtD(null, null, null, ""));
    }

    @Test
    void testEqualsIsti() {
        Date danas = new Date();
        ZtD z1 = new ZtD(zaposleni, termin, danas, "napomena jedan");
        ZtD z2 = new ZtD(zaposleni, termin, danas, "napomena dva");
        assertEquals(z1, z2);
        assertEquals(z1.hashCode(), z2.hashCode());
    }

    @Test
    void testEqualsRazliciti() {
        Date danas = new Date();
        Zaposleni drugi = new Zaposleni(2, "pera", "sifra12", "Pera", "Peric");
        ZtD z1 = new ZtD(zaposleni, termin, danas, "napomena");
        ZtD z2 = new ZtD(drugi, termin, danas, "napomena");
        assertNotEquals(z1, z2);
    }

    @Test
    void testEqualsNullIDrugaKlasa() {
        assertFalse(ztd.equals(null));
        assertFalse(ztd.equals("string"));
    }

    @Test
    void testToString() {
        ztd.setNapomena("zamena kolege");
        assertTrue(ztd.toString().contains("zamena kolege"));
    }

}
