/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.iznajmljivanje;

import domen.Iznajmljivanje;
import domen.Klijent;
import domen.Mesto;
import domen.StavkaIznajmljivanja;
import domen.Trotinet;
import domen.Zaposleni;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author nikola
 */
public class KreirajIznajmljivanjeSOTest {
    
    private KreirajIznajmljivanjeSO operacija;
    private Iznajmljivanje iznajmljivanje;

    @BeforeEach
    void setUp() {
        operacija = new KreirajIznajmljivanjeSO();
        Zaposleni zaposleni = new Zaposleni(1, "nikola", "tajna123", "Nikola", "Markovic");
        Klijent klijent = new Klijent(1, "Marko", "Markovic", 641234567L, new Mesto(1, "Beograd", 11000));
        iznajmljivanje = new Iznajmljivanje(1, 450.0, zaposleni, klijent);

        Calendar cal = Calendar.getInstance();
        Date ranije = cal.getTime();
        cal.add(Calendar.MINUTE, 30);
        Date kasnije = cal.getTime();
        Trotinet trotinet = new Trotinet(1, "Xiaomi Pro 2", 15.5);
        StavkaIznajmljivanja stavka = new StavkaIznajmljivanja(1, iznajmljivanje, ranije, kasnije, 15.5, 465.0, trotinet);
        iznajmljivanje.getStavkeIznajmljivanja().add(stavka);
    }

    @AfterEach
    void tearDown() {
        operacija = null;
        iznajmljivanje = null;
    }

    @Test
    void testPreduslovSveOk() {
        assertDoesNotThrow(() -> operacija.preduslovi(iznajmljivanje));
    }

    @Test
    void testPreduslovNull() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(null));
        assertEquals("Sistem nije mogao da kreira iznajmljivanje", e.getMessage());
    }

    @Test
    void testPreduslovPogresnaKlasa() {
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(new Klijent()));
        assertEquals("Sistem nije mogao da kreira iznajmljivanje", e.getMessage());
    }

    @Test
    void testPreduslovBezStavki() {
        iznajmljivanje.getStavkeIznajmljivanja().clear();
        Exception e = assertThrows(Exception.class, () -> operacija.preduslovi(iznajmljivanje));
        assertEquals("Iznajmljivanje mora imati najmanje jednu stavku", e.getMessage());
    }
}
