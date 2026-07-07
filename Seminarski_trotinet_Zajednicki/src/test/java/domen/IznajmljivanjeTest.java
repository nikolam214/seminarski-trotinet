/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author nikola
 */
public class IznajmljivanjeTest {
    
    
    private Iznajmljivanje iznajmljivanje;
    private Zaposleni zaposleni;
    private Klijent klijent;

    @BeforeEach
    void setUp() {
        iznajmljivanje = new Iznajmljivanje();
        zaposleni = new Zaposleni(1, "nikola", "tajna123", "Nikola", "Markovic");
        klijent = new Klijent(1, "Marko", "Markovic", 641234567L, new Mesto(1, "Beograd", 11000));
    }

    @AfterEach
    void tearDown() {
        iznajmljivanje = null;
        zaposleni = null;
        klijent = null;
    }

    @Test
    void testSetIdSveOk() {
        iznajmljivanje.setId(10);
        assertEquals(10, iznajmljivanje.getId());
    }

    @Test
    void testSetIdNula() {
        assertThrows(IllegalArgumentException.class, () -> iznajmljivanje.setId(0));
    }

    @Test
    void testSetIdNegativan() {
        assertThrows(IllegalArgumentException.class, () -> iznajmljivanje.setId(-10));
    }

    @Test
    void testSetUkupnaCenaSveOk() {
        iznajmljivanje.setUkupnaCena(450.0);
        assertEquals(450.0, iznajmljivanje.getUkupnaCena(), 0.001);
    }

    @Test
    void testSetUkupnaCenaNulaDozvoljena() {
        iznajmljivanje.setUkupnaCena(0);
        assertEquals(0, iznajmljivanje.getUkupnaCena(), 0.001);
    }

    @Test
    void testSetUkupnaCenaNegativna() {
        assertThrows(IllegalArgumentException.class, () -> iznajmljivanje.setUkupnaCena(-450));
    }

    @Test
    void testSetZaposleniSveOk() {
        iznajmljivanje.setZaposleni(zaposleni);
        assertEquals(zaposleni, iznajmljivanje.getZaposleni());
    }

    @Test
    void testSetZaposleniNull() {
        assertThrows(IllegalArgumentException.class, () -> iznajmljivanje.setZaposleni(null));
    }

    @Test
    void testSetKlijentSveOk() {
        iznajmljivanje.setKlijent(klijent);
        assertEquals(klijent, iznajmljivanje.getKlijent());
    }

    @Test
    void testSetKlijentNull() {
        assertThrows(IllegalArgumentException.class, () -> iznajmljivanje.setKlijent(null));
    }

    @Test
    void testSetStavkeIznajmljivanjaSveOk() {
        List<StavkaIznajmljivanja> stavke = new ArrayList<>();
        iznajmljivanje.setStavkeIznajmljivanja(stavke);
        assertEquals(stavke, iznajmljivanje.getStavkeIznajmljivanja());
    }

    @Test
    void testSetStavkeIznajmljivanjaNull() {
        assertThrows(IllegalArgumentException.class,
                () -> iznajmljivanje.setStavkeIznajmljivanja(null));
    }

    @Test
    void testPrazanKonstruktorInicijalizujeListu() {
        assertNotNull(iznajmljivanje.getStavkeIznajmljivanja());
        assertTrue(iznajmljivanje.getStavkeIznajmljivanja().isEmpty());
    }

    @Test
    void testKonstruktorSaParametrima() {
        Iznajmljivanje i = new Iznajmljivanje(1, 450.0, zaposleni, klijent);
        assertEquals(1, i.getId());
        assertEquals(450.0, i.getUkupnaCena(), 0.001);
        assertEquals(zaposleni, i.getZaposleni());
        assertEquals(klijent, i.getKlijent());
        assertNotNull(i.getStavkeIznajmljivanja());
    }

    @Test
    void testKonstruktorNeispravneVrednosti() {
        assertThrows(IllegalArgumentException.class,
                () -> new Iznajmljivanje(-1, -450, null, null));
    }

    @Test
    void testEqualsIsti() {
        Iznajmljivanje i1 = new Iznajmljivanje(1, 450.0, zaposleni, klijent);
        Iznajmljivanje i2 = new Iznajmljivanje(1, 900.0, zaposleni, klijent);
        assertEquals(i1, i2);
        assertEquals(i1.hashCode(), i2.hashCode());
    }

    @Test
    void testEqualsRazliciti() {
        Iznajmljivanje i1 = new Iznajmljivanje(1, 450.0, zaposleni, klijent);
        Iznajmljivanje i2 = new Iznajmljivanje(2, 450.0, zaposleni, klijent);
        assertNotEquals(i1, i2);
    }

    @Test
    void testEqualsNullIDrugaKlasa() {
        assertFalse(iznajmljivanje.equals(null));
        assertFalse(iznajmljivanje.equals("string"));
    }

    @Test
    void testToStringSadrziId() {
        iznajmljivanje.setId(15);
        assertTrue(iznajmljivanje.toString().contains("15"));
    }

}
