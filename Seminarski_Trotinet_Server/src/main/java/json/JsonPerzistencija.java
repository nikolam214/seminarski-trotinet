/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package json;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import domen.Iznajmljivanje;
import domen.StavkaIznajmljivanja;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
/**
 *Klasa zaduzena za perzistenciju domenskih objekata u JSON formatu.
 * Omogucava cuvanje liste iznajmljivanja u JSON fajl i njihovo ponovno
 * ucitavanje iz JSON fajla.
 * 
 * @author nikola
 */
public class JsonPerzistencija {
    
    /**
     * Gson objekat podesen za rad sa domenskim klasama
     */
    private final Gson gson;

    /**
     * Pravi nov objekat klase JsonPerzistencija i podesava Gson tako da
     * preskace povratnu referencu stavke na iznajmljivanje (sprecava
     * beskonacnu rekurziju pri serijalizaciji).
     */
    public JsonPerzistencija() {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .addSerializationExclusionStrategy(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass() == StavkaIznajmljivanja.class
                                && f.getName().equals("iznajmljivanje");
                    }
                    
                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .create();
    }
    
    /**
     * Cuva prosledjenu listu iznajmljivanja u JSON fajl na zadatoj putanji.
     *
     * @param iznajmljivanja Lista iznajmljivanja koja se cuva
     * @param putanja Putanja do JSON fajla
     * @throws IOException ako dodje do greske pri upisu u fajl
     * @throws IllegalArgumentException ako je lista null
     */
    public void sacuvajIznajmljivanja(List<Iznajmljivanje> iznajmljivanja, String putanja) throws IOException {
        if (iznajmljivanja == null) {
            throw new IllegalArgumentException("Lista iznajmljivanja ne sme biti null");
        }
        try (FileWriter fw = new FileWriter(putanja)) {
            gson.toJson(iznajmljivanja, fw);
        }
    }

    /**
     * Ucitava listu iznajmljivanja iz JSON fajla sa zadate putanje.
     *
     * @param putanja Putanja do JSON fajla
     * @return Lista ucitanih iznajmljivanja
     * @throws IOException ako fajl ne postoji ili dodje do greske pri citanju
     */
    public List<Iznajmljivanje> ucitajIznajmljivanja(String putanja) throws IOException {
        try (FileReader fr = new FileReader(putanja)) {
            Type tip = new TypeToken<List<Iznajmljivanje>>() {}.getType();
            return gson.fromJson(fr, tip);
        }
        
    }
}
