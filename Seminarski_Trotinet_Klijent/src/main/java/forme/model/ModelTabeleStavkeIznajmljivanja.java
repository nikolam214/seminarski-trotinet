/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.StavkaIznajmljivanja;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nikola
 */
public class ModelTabeleStavkeIznajmljivanja extends AbstractTableModel {
    
    private List<StavkaIznajmljivanja> stavke;
    private String[] kolone = {"RB", "Trotinet", "Vreme uzimanja", "Vreme vracanja", "Cena/min", "Iznos"};
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    public ModelTabeleStavkeIznajmljivanja() {
        this.stavke = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return stavke.size();
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
        StavkaIznajmljivanja s = stavke.get(rowIndex);
        switch (columnIndex) {
            case 0: return s.getRb();
            case 1: return s.getTrotinet().getModelTrotineta();
            case 2: return sdf.format(s.getVremeuzimanja());
            case 3: return sdf.format(s.getVremeVracanja());
            case 4: return s.getCenaPoMinutu();
            case 5: return s.getIznos();
            default: return "NA";
        }
    }

    public List<StavkaIznajmljivanja> getStavke() {
        return stavke;
    }

    public void dodajStavku(StavkaIznajmljivanja stavka) {
        stavke.add(stavka);
        fireTableDataChanged();
    }
    
    public void obrisiStavku(int index) {
        stavke.remove(index);
        fireTableDataChanged();
    }

    public double izracunajUkupnuCenu() {
        return stavke.stream().mapToDouble(StavkaIznajmljivanja::getIznos).sum();
    }
    
    
}
