/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.ZtD;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nikola
 */
public class ModelTabeleZtD extends AbstractTableModel {
    
    private List<ZtD> ztdList;
    private String[] kolone = {"Zaposleni", "Termin dezurstva", "Datum", "Napomena"};
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    
    public ModelTabeleZtD(List<ZtD> ztdList) {
        this.ztdList = ztdList;
    }

    @Override
    public int getRowCount() {
        return ztdList.size();
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
        ZtD z = ztdList.get(rowIndex);
        switch (columnIndex) {
            case 0: return z.getZaposleni().getIme() + " " + z.getZaposleni().getPrezime();
            case 1: return z.getTerminDezurstva().toString();
            case 2: return sdf.format(z.getDatum());
            case 3: return z.getNapomena();
            default: return "NA";
        }
    }
    
    public List<ZtD> getZtDList() {
        return ztdList;
    }
}
