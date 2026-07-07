/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.TerminDezurstva;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nikola
 */
public class ModelTabeleTerminDezurstva extends AbstractTableModel {
    
    private List<TerminDezurstva> termini;
    private List<TerminDezurstva> sviTermini;
    private String[] kolone = {"ID", "Tip smene", "Vreme pocetka", "Trajanje"};

    public ModelTabeleTerminDezurstva(List<TerminDezurstva> termini) {
        this.termini = termini;
        this.sviTermini = termini;
    }

    @Override
    public int getRowCount() {
        return termini.size();
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
        TerminDezurstva t = termini.get(rowIndex);
        switch (columnIndex) {
            case 0: return t.getId();
            case 1: return t.getTipSmene();
            case 2: return t.getVremePocetka();
            case 3: return t.getTrajanje() / 60 + " h";
            default: return "NA";
        }
    }

    public List<TerminDezurstva> getTermini() {
        return termini;
    }
    
    public void pretrazi(String tipSmene, String vremePocetka, String trajanje) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    List<TerminDezurstva> filteredList = sviTermini.stream()
        .filter(t -> (tipSmene == null || tipSmene.isEmpty() || t.getTipSmene().equalsIgnoreCase(tipSmene)))
        .filter(t -> {
            if (vremePocetka == null || vremePocetka.isEmpty()) return true;
            return sdf.format(t.getVremePocetka()).contains(vremePocetka);
        })
        .filter(t -> {
            if (trajanje == null || trajanje.isEmpty()) return true;
            try {
                return t.getTrajanje() / 60 == Integer.parseInt(trajanje);
            } catch (NumberFormatException e) {
                return false;
            }
        })
        .collect(java.util.stream.Collectors.toList());

    this.termini = filteredList;
    fireTableDataChanged();
}
}
