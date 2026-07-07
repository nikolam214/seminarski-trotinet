/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Trotinet;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nikola
 */
public class ModelTabeleTrotinet extends AbstractTableModel {
    
    private List<Trotinet> trotineti;
    private List<Trotinet> sviTrotineti;
    private String[] kolone = {"ID", "Model", "Cena po minutu"};

    
    public ModelTabeleTrotinet(List<Trotinet> trotineti) {
        this.trotineti = trotineti;
        this.sviTrotineti = trotineti;
    }

    @Override
    public int getRowCount() {
        return trotineti.size();
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
        Trotinet t = trotineti.get(rowIndex);
        switch (columnIndex) {
            case 0: return t.getId();
            case 1: return t.getModelTrotineta();
            case 2: return t.getCenaPoMinutu();
            default: return "NA";
        }
    }

    public List<Trotinet> getTrotineti() {
        return trotineti;
    }
    
    public void pretrazi(String model, String cena) {
        List<Trotinet> filteredList = sviTrotineti.stream()
            .filter(t -> (model == null || model.isEmpty() || t.getModelTrotineta().toLowerCase().contains(model.toLowerCase())))
            .filter(t -> {
                if (cena == null || cena.isEmpty()) return true;
                try {
                    return t.getCenaPoMinutu() == Double.parseDouble(cena);
                } catch (NumberFormatException e) {
                    return false;
                }
            })
            .collect(java.util.stream.Collectors.toList());

        this.trotineti = filteredList;
        fireTableDataChanged();
    }
    
    
    
}
