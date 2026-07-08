/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.zaposleni;

import domen.Klijent;
import domen.Zaposleni;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author nikola
 */
public class AzurirajZaposlenogSOTest {
    
    private AzurirajZaposlenogSO operacija;
    private Zaposleni zaposleni;

    @BeforeEach
    void setUp() {
        operacija = new AzurirajZaposlenogSO();
        zaposleni = new Zaposleni(1, "nikola", "tajna123", "Nikola", "Markovic");
    }

    @AfterEach
    void tearDown() {
        operacija = null;
        zaposleni = null;
    }

    @Test
    void testPreduslovSveOk() {
        assertDoesNotThrow(() -> operacija.preduslovi(zaposleni));
    }

    @Test
    void testPreduslovNull() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(null));
        assertEquals("Sistem nije mogao da azurira zaposlenog", e.getMessage());
    }

    @Test
    void testPreduslovPogresnaKlasa() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(new Klijent()));
        assertEquals("Sistem nije mogao da azurira zaposlenog", e.getMessage());
    }

    @Test
    void testPreduslovImeKratko() {
        zaposleni.setIme("N");
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(zaposleni));
        assertEquals("Ime mora imati najmanje 2 karaktera", e.getMessage());
    }

    @Test
    void testPreduslovSifraKratka() {
        zaposleni.setSifra("abc");
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(zaposleni));
        assertEquals("Sifra mora imati najmanje 4 karaktera", e.getMessage());
    }
    
    @Test
    void testIzmenaZaposlenog() throws Exception {
        
        new DodajZaposlenogSO().izvrsi(new Zaposleni(1, "test", "test1234", "TestIme", "TestPrezime"), null);
        UcitajZaposleneSO ucitaj = new UcitajZaposleneSO();
        ucitaj.izvrsi(null, null);
        Zaposleni uBazi = null;
        
        for (Zaposleni z : ucitaj.getZaposleni()) {
            if ("test".equals(z.getKorisnickoIme())) {
                uBazi = z;
            }
        }
        assertNotNull(uBazi);

        uBazi.setIme("IzmenjenoIme");
        uBazi.setPrezime("IzmenjenoPrezime");
        uBazi.setSifra("novaSifra1");
        operacija.izvrsi(uBazi, null);

        UcitajZaposleneSO ucitaj2 = new UcitajZaposleneSO();
        ucitaj2.izvrsi(null, null);
        Zaposleni izmenjen = null;
        
        for (Zaposleni z : ucitaj2.getZaposleni()) {
            if (z.getId() == uBazi.getId()) {
                izmenjen = z;
            }
        }

        assertNotNull(izmenjen);
        assertEquals("IzmenjenoIme", izmenjen.getIme());
        assertEquals("IzmenjenoPrezime", izmenjen.getPrezime());
        assertEquals("novaSifra1", izmenjen.getSifra());

        new ObrisiZaposlenogSO().izvrsi(izmenjen, null);
    }
}
