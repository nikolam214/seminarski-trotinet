/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.ztd;

import domen.Klijent;
import domen.TerminDezurstva;
import domen.Zaposleni;
import domen.ZtD;
import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author nikola
 */
public class DodajZtDSOTest {
    
    private DodajZtDSO operacija;
    private ZtD ztd;

    @BeforeEach
    void setUp() {
        operacija = new DodajZtDSO();
        Zaposleni zaposleni = new Zaposleni(1, "nikola", "tajna123", "Nikola", "Markovic");
        TerminDezurstva termin = new TerminDezurstva(1, "prva", new Date(), 8);
        ztd = new ZtD(zaposleni, termin, new Date(), "zamena kolege");
    }

    @AfterEach
    void tearDown() {
        operacija = null;
        ztd = null;
    }

    @Test
    void testPreduslovSveOk() {
        assertDoesNotThrow(() -> operacija.preduslovi(ztd));
    }

    @Test
    void testPreduslovNull() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(null));
        assertEquals("Sistem nije mogao da doda ZtD", e.getMessage());
    }

    @Test
    void testPreduslovPogresnaKlasa() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(new Klijent()));
        assertEquals("Sistem nije mogao da doda ZtD", e.getMessage());
    }
}
