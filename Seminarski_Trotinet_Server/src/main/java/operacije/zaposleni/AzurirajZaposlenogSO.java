/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.zaposleni;

import domen.Zaposleni;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *Sistemska operacija za azuriranje postojeceg zaposlenog u bazi podataka.
 * @author nikola
 */
public class AzurirajZaposlenogSO extends ApstraktnaGenerickaOperacija {

    /**
     * Proverava preduslove za izvrsavanje operacije.
     *
     * @param objekat Objekat nad kojim se operacija izvrsava
     * @throws Exception ako objekat nije odgovarajuce klase ili ne ispunjava uslove operacije
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        
        if (objekat == null || !(objekat instanceof Zaposleni)) {
            throw new Exception("Sistem nije mogao da azurira zaposlenog");
        }
        Zaposleni z = (Zaposleni) objekat;
        if (z.getIme() == null || z.getIme().isEmpty() || z.getIme().length() < 2) {
            throw new Exception("Ime mora imati najmanje 2 karaktera");
        }
        if (z.getPrezime() == null || z.getPrezime().isEmpty() || z.getPrezime().length() < 2) {
            throw new Exception("Prezime mora imati najmanje 2 karaktera");
        }
        if (z.getKorisnickoIme() == null || z.getKorisnickoIme().isEmpty()) {
            throw new Exception("Korisnicko ime ne sme biti prazno");
        }
        if (z.getSifra() == null || z.getSifra().isEmpty() || z.getSifra().length() < 4) {
            throw new Exception("Sifra mora imati najmanje 4 karaktera");
        }
    }

    /**
     * Izvrsava konkretnu operaciju nad prosledjenim objektom.
     *
     * @param objekat Objekat nad kojim se operacija izvrsava
     * @param kljuc Dodatni kriterijum operacije
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.edit((Zaposleni) objekat);
    }
    
}
