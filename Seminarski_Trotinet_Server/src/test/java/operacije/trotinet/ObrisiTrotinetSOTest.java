/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.trotinet;

import domen.Klijent;
import domen.Trotinet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author nikola
 */
public class ObrisiTrotinetSOTest {
    
    private ObrisiTrotinetSO operacija;

    @BeforeEach
    void setUp() {
        operacija = new ObrisiTrotinetSO();
    }

    @AfterEach
    void tearDown() {
        operacija = null;
    }

    @Test
    void testPreduslovSveOk() {
        assertDoesNotThrow(() -> operacija.preduslovi(new Trotinet()));
    }

    @Test
    void testPreduslovNull() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(null));
        assertEquals("Sistem nije mogao da obrise trotinet", e.getMessage());
    }

    @Test
    void testPreduslovPogresnaKlasa() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(new Klijent()));
        assertEquals("Sistem nije mogao da obrise trotinet", e.getMessage());
    }
    
    @Test
    void testObriseTrotinet() throws Exception {
        
        new DodajTrotinetSO().izvrsi(new Trotinet(1, "TestTrotinet", 15.), null);
        UcitajTrotineteSO ucitaj = new UcitajTrotineteSO();
        ucitaj.izvrsi(null, null);
        Trotinet uBazi = null;
        
        for (Trotinet t : ucitaj.getTrotineti()) {
            if ("TestTrotinet".equals(t.getModelTrotineta())) {
                uBazi = t;
            }
        }
        assertNotNull(uBazi);

        operacija.izvrsi(uBazi, null);

        UcitajTrotineteSO ucitaj2 = new UcitajTrotineteSO();
        ucitaj2.izvrsi(null, null);
        boolean postoji = false;
        for (Trotinet t : ucitaj2.getTrotineti()) {
            if (t.getId() == uBazi.getId()) {
                postoji = true;
            }
        }
        assertFalse(postoji);
    }
}
