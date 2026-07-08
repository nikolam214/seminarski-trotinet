/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package json;

import domen.Iznajmljivanje;
import domen.Klijent;
import domen.Mesto;
import domen.StavkaIznajmljivanja;
import domen.Trotinet;
import domen.Zaposleni;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author nikola
 */
public class JsonPerzistencijaTest {
    
    private JsonPerzistencija jp;
    private List<Iznajmljivanje> lista;
    private String putanja;

    @BeforeEach
    void setUp() {
        jp = new JsonPerzistencija();
        Zaposleni zaposleni = new Zaposleni(1, "nikola", "tajna123", "Nikola", "Markovic");
        Klijent klijent = new Klijent(1, "Marko", "Markovic", 641234567L, new Mesto(1, "Beograd", 11000));
        Iznajmljivanje i = new Iznajmljivanje(1, 450.0, zaposleni, klijent);

        Calendar cal = Calendar.getInstance();
        Date ranije = cal.getTime();
        cal.add(Calendar.MINUTE, 30);
        Date kasnije = cal.getTime();
        StavkaIznajmljivanja stavka = new StavkaIznajmljivanja(1, i, ranije, kasnije, 15.5, 465.0, new Trotinet(1, "Xiaomi Pro 2", 15.5));
        i.getStavkeIznajmljivanja().add(stavka);

        lista = new ArrayList<>();
        lista.add(i);
        putanja = "target/test-iznajmljivanja.json";
    }
    
    @AfterEach
    void tearDown() {
        new File(putanja).delete();
        jp = null;
        lista = null;
    }

    @Test
    void testSacuvajPraviFajl() throws Exception {
        jp.sacuvajIznajmljivanja(lista, putanja);
        assertTrue(new File(putanja).exists());
    }

    @Test
    void testSacuvajNullLista() {
        assertThrows(IllegalArgumentException.class,
                () -> jp.sacuvajIznajmljivanja(null, putanja));
    }
    
    @Test
    void testSacuvajPaUcitaj() throws Exception {
        jp.sacuvajIznajmljivanja(lista, putanja);
        List<Iznajmljivanje> ucitana = jp.ucitajIznajmljivanja(putanja);

        assertNotNull(ucitana);
        assertEquals(1, ucitana.size());
        Iznajmljivanje i = ucitana.get(0);
        assertEquals(1, i.getId());
        assertEquals(450.0, i.getUkupnaCena(), 0.001);
        assertEquals("Marko", i.getKlijent().getIme());
        assertEquals(1, i.getStavkeIznajmljivanja().size());
        assertEquals(15.5, i.getStavkeIznajmljivanja().get(0).getCenaPoMinutu(), 0.001);
    }

    @Test
    void testUcitajNepostojeciFajl() {
        assertThrows(Exception.class,
                () -> jp.ucitajIznajmljivanja("target/ne-postoji.json"));
    }
}
