/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Mesto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nikola
 */
public class ModelTabeleMesto extends AbstractTableModel {
    
    private List<Mesto> mesta;
    private List<Mesto> svaMesta;
    private String[] kolone = {"ID", "Naziv", "Postanski broj"};

    
    public ModelTabeleMesto(List<Mesto> mesta) {
        this.mesta = mesta;
        this.svaMesta = mesta;
    }

    @Override
    public int getRowCount() {
        return mesta.size();
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
        Mesto m = mesta.get(rowIndex);
        switch (columnIndex) {
            case 0: return m.getId();
            case 1: return m.getNaziv();
            case 2: return m.getPostanskiBroj();
            default: return "NA";
        }
    }

    public List<Mesto> getMesta() {
        return mesta;
    }
    
    public void pretrazi(String naziv, String postanskiBroj) {
        List<Mesto> filteredList = svaMesta.stream()
            .filter(m -> (naziv == null || naziv.isEmpty() || m.getNaziv().toLowerCase().contains(naziv.toLowerCase())))
            .filter(m -> {
                if (postanskiBroj == null || postanskiBroj.isEmpty()) return true;
                try {
                    return m.getPostanskiBroj() == Integer.parseInt(postanskiBroj);
                } catch (NumberFormatException e) {
                    return false;
                }
            })
            .collect(java.util.stream.Collectors.toList());

        this.mesta = filteredList;
        fireTableDataChanged();
    }
}
