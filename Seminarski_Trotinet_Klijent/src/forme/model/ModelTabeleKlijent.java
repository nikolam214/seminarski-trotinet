/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Klijent;
import domen.Mesto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nikola
 */
public class ModelTabeleKlijent extends AbstractTableModel {
    
    private List<Klijent> sviKlijenti;
    private List<Klijent> klijenti;
    private String[] kolone = {"ID", "Ime", "Prezime", "Broj telefona", "Mesto"};

    public ModelTabeleKlijent(List<Klijent> klijenti) {
        this.klijenti = klijenti;
        this.sviKlijenti = klijenti;
    }

    
    @Override
    public int getRowCount() {
        return klijenti.size();
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
        Klijent k=klijenti.get(rowIndex);
        switch (columnIndex) {
            case 0:return k.getId();
            case 1:return k.getIme();
            case 2:return k.getPrezime();
            case 3:return k.getBrojtelefona();
            case 4:return k.getMesto();   
            default: return "NA";
        }
   
    }

    public List<Klijent> getKlijenti() {
        return klijenti;
    }
    
    public void pretrazi(String ime, String prezime, long telefon, Mesto mesto) {
        List<Klijent> filteredList = sviKlijenti.stream()
            .filter(k -> (ime == null || ime.isEmpty() || k.getIme().toLowerCase().contains(ime.toLowerCase())))
            .filter(k -> (prezime == null || prezime.isEmpty() || k.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
            .filter(k -> (telefon == 0 || k.getBrojtelefona() == telefon))
            .filter(k -> (mesto == null || k.getMesto().getId() == mesto.getId()))
            .collect(java.util.stream.Collectors.toList());

        this.klijenti = filteredList;
        fireTableDataChanged();
    }
}
