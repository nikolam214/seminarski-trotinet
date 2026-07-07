/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

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
public class StavkaIznajmljivanjaTest {
    
    
    private StavkaIznajmljivanja stavka;
    private Iznajmljivanje iznajmljivanje;
    private Trotinet trotinet;
    private Date ranije;
    private Date kasnije;

    @BeforeEach
    void setUp() {
        stavka = new StavkaIznajmljivanja();
        Zaposleni zaposleni = new Zaposleni(1, "nikola", "tajna123", "Nikola", "Markovic");
        Klijent klijent = new Klijent(1, "Marko", "Markovic", 641234567L, new Mesto(1, "Beograd", 11000));
        iznajmljivanje = new Iznajmljivanje(1, 450.0, zaposleni, klijent);
        trotinet = new Trotinet(1, "Xiaomi Pro 2", 15.5);

        Calendar cal = Calendar.getInstance();
        ranije = cal.getTime();
        cal.add(Calendar.MINUTE, 30);
        kasnije = cal.getTime();
    }

    @AfterEach
    void tearDown() {
        stavka = null;
        iznajmljivanje = null;
        trotinet = null;
        ranije = null;
        kasnije = null;
    }

    @Test
    void testSetRbSveOk() {
        stavka.setRb(1);
        assertEquals(1, stavka.getRb());
    }

    @Test
    void testSetRbNula() {
        assertThrows(IllegalArgumentException.class, () -> stavka.setRb(0));
    }

    @Test
    void testSetRbNegativan() {
        assertThrows(IllegalArgumentException.class, () -> stavka.setRb(-1));
    }

    @Test
    void testSetIznajmljivanjeSveOk() {
        stavka.setIznajmljivanje(iznajmljivanje);
        assertEquals(iznajmljivanje, stavka.getIznajmljivanje());
    }

    @Test
    void testSetIznajmljivanjeNull() {
        assertThrows(IllegalArgumentException.class, () -> stavka.setIznajmljivanje(null));
    }

    @Test
    void testSetVremeuzimanjaSveOk() {
        stavka.setVremeuzimanja(ranije);
        assertEquals(ranije, stavka.getVremeuzimanja());
    }

    @Test
    void testSetVremeuzimanjaNull() {
        assertThrows(IllegalArgumentException.class, () -> stavka.setVremeuzimanja(null));
    }

    @Test
    void testSetVremeVracanjaSveOk() {
        stavka.setVremeuzimanja(ranije);
        stavka.setVremeVracanja(kasnije);
        assertEquals(kasnije, stavka.getVremeVracanja());
    }

    @Test
    void testSetVremeVracanjaNull() {
        assertThrows(IllegalArgumentException.class, () -> stavka.setVremeVracanja(null));
    }

    @Test
    void testSetVremeVracanjaPreUzimanja() {
        stavka.setVremeuzimanja(kasnije);
        assertThrows(IllegalArgumentException.class,
                () -> stavka.setVremeVracanja(ranije));
    }

    @Test
    void testSetCenaPoMinutuSveOk() {
        stavka.setCenaPoMinutu(15.5);
        assertEquals(15.5, stavka.getCenaPoMinutu(), 0.001);
    }

    @Test
    void testSetCenaPoMinutuNula() {
        assertThrows(IllegalArgumentException.class, () -> stavka.setCenaPoMinutu(0));
    }

    @Test
    void testSetCenaPoMinutuNegativna() {
        assertThrows(IllegalArgumentException.class, () -> stavka.setCenaPoMinutu(-15.5));
    }

    @Test
    void testSetIznosSveOk() {
        stavka.setIznos(465.0);
        assertEquals(465.0, stavka.getIznos(), 0.001);
    }

    @Test
    void testSetIznosNulaDozvoljena() {
        stavka.setIznos(0);
        assertEquals(0, stavka.getIznos(), 0.001);
    }

    @Test
    void testSetIznosNegativan() {
        assertThrows(IllegalArgumentException.class, () -> stavka.setIznos(-465));
    }

    @Test
    void testSetTrotinetSveOk() {
        stavka.setTrotinet(trotinet);
        assertEquals(trotinet, stavka.getTrotinet());
    }

    @Test
    void testSetTrotinetNull() {
        assertThrows(IllegalArgumentException.class, () -> stavka.setTrotinet(null));
    }

    @Test
    void testKonstruktorSaParametrima() {
        StavkaIznajmljivanja s = new StavkaIznajmljivanja(1, iznajmljivanje, ranije, kasnije, 15.5, 465.0, trotinet);
        assertEquals(1, s.getRb());
        assertEquals(iznajmljivanje, s.getIznajmljivanje());
        assertEquals(ranije, s.getVremeuzimanja());
        assertEquals(kasnije, s.getVremeVracanja());
        assertEquals(15.5, s.getCenaPoMinutu(), 0.001);
        assertEquals(465.0, s.getIznos(), 0.001);
        assertEquals(trotinet, s.getTrotinet());
    }

    @Test
    void testKonstruktorNeispravneVrednosti() {
        assertThrows(IllegalArgumentException.class,
                () -> new StavkaIznajmljivanja(0, null, null, null, -1, -1, null));
    }

    @Test
    void testEqualsIsti() {
        StavkaIznajmljivanja s1 = new StavkaIznajmljivanja(1, iznajmljivanje, ranije, kasnije, 15.5, 465.0, trotinet);
        StavkaIznajmljivanja s2 = new StavkaIznajmljivanja(1, iznajmljivanje, ranije, kasnije, 20.0, 600.0, trotinet);
        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());
    }

    @Test
    void testEqualsRazliciti() {
        StavkaIznajmljivanja s1 = new StavkaIznajmljivanja(1, iznajmljivanje, ranije, kasnije, 15.5, 465.0, trotinet);
        StavkaIznajmljivanja s2 = new StavkaIznajmljivanja(2, iznajmljivanje, ranije, kasnije, 15.5, 465.0, trotinet);
        assertNotEquals(s1, s2);
    }

    @Test
    void testEqualsNullIDrugaKlasa() {
        assertFalse(stavka.equals(null));
        assertFalse(stavka.equals("string"));
    }

    @Test
    void testToString() {
        stavka.setCenaPoMinutu(15.5);
        assertTrue(stavka.toString().contains("15.5"));
    }

}
