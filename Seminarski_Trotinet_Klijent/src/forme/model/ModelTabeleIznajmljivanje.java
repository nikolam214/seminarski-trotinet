/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Iznajmljivanje;
import domen.Klijent;
import domen.Zaposleni;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nikola
 */
public class ModelTabeleIznajmljivanje extends AbstractTableModel {
    
    private List<Iznajmljivanje> iznajmljivanja;
    private List<Iznajmljivanje> svaIznajmljivanja;
    private String[] kolone = {"ID", "Klijent", "Zaposleni", "Ukupna cena"};

    public ModelTabeleIznajmljivanje(List<Iznajmljivanje> iznajmljivanja) {
        this.iznajmljivanja = iznajmljivanja;
        this.svaIznajmljivanja = iznajmljivanja;
    }

    @Override
    public int getRowCount() {
        return iznajmljivanja.size();
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
        Iznajmljivanje i = iznajmljivanja.get(rowIndex);
        switch (columnIndex) {
            case 0: return i.getId();
            case 1: return i.getKlijent().getIme() + " " + i.getKlijent().getPrezime();
            case 2: return i.getZaposleni().getIme() + " " + i.getZaposleni().getPrezime();
            case 3: return i.getUkupnaCena();
            default: return "NA";
        }
    }

    public List<Iznajmljivanje> getIznajmljivanja() {
        return iznajmljivanja;
    }
    
    public void pretrazi(Klijent klijent, Zaposleni zaposleni) {
        List<Iznajmljivanje> filteredList = svaIznajmljivanja.stream()
            .filter(i -> (klijent == null || i.getKlijent().getId() == klijent.getId()))
            .filter(i -> (zaposleni == null || i.getZaposleni().getId() == zaposleni.getId()))
            .collect(java.util.stream.Collectors.toList());

        this.iznajmljivanja = filteredList;
        fireTableDataChanged();
    }
}
