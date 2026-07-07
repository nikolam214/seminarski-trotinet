/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Zaposleni;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nikola
 */
public class ModelTabeleZaposleni extends AbstractTableModel {

    private List<Zaposleni> zaposleni;
    private List<Zaposleni> sviZaposleni;
    private String[] kolone = {"ID", "Ime", "Prezime", "Korisnicko ime"};
    
    public ModelTabeleZaposleni(List<Zaposleni> zaposleni) {
        this.zaposleni = zaposleni;
        this.sviZaposleni = zaposleni;
    }
    
    @Override
    public int getRowCount() {
        return zaposleni.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Zaposleni z = zaposleni.get(rowIndex);
        
        switch (columnIndex) {
            case 0: return z.getId();
            case 1: return z.getIme();
            case 2: return z.getPrezime();
            case 3: return z.getKorisnickoIme();
            default: return "NA";
        }
    }
    
    public List<Zaposleni> getZaposleni() {
        return zaposleni;
    }

    public void pretrazi(String ime, String prezime, String korIme) {
        List<Zaposleni> filteredList = sviZaposleni.stream()
            .filter(z -> (ime == null || ime.isEmpty() || z.getIme().toLowerCase().contains(ime.toLowerCase())))
            .filter(z -> (prezime == null || prezime.isEmpty() || z.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
            .filter(z -> (korIme == null || korIme.isEmpty() || z.getKorisnickoIme().toLowerCase().contains(korIme.toLowerCase())))
            .collect(java.util.stream.Collectors.toList());

        this.zaposleni = filteredList;
        fireTableDataChanged();
    }

    
}
