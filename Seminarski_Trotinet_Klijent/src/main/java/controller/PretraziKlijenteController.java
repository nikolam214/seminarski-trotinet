/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Klijent;
import domen.Mesto;
import forme.PretraziKlijenteForma;
import forme.model.ModelTabeleKlijent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author nikola
 */
public class PretraziKlijenteController {
    
    private PretraziKlijenteForma pkf;

    public PretraziKlijenteController(PretraziKlijenteForma pkf) {
        this.pkf=pkf;
        addActionListener();
        
    }

    public void otvoriFormu() {
        pripremiFormu();
        pkf.setVisible(true);
    }

    public void pripremiFormu() {
        
        List <Klijent> klijenti=komunikacija.Komunikacija.getInstance().ucitajKlijente();
        
        ModelTabeleKlijent mtk= new ModelTabeleKlijent(klijenti);
        pkf.getjTableKlijenti().setModel(mtk);
        
        pkf.getjComboBox1().addItem(null);
        
        List<Mesto> mesta = komunikacija.Komunikacija.getInstance().ucitajMesta();
        for (Mesto m : mesta) {
            pkf.getjComboBox1().addItem(m);
        }
    }
    

    private void addActionListener() {
        pkf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red= pkf.getjTableKlijenti().getSelectedRow();
                if(red==-1){
                    JOptionPane.showMessageDialog(pkf, "Sistem ne moze da obrise klijenta", "Greska", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleKlijent mtk= (ModelTabeleKlijent) pkf.getjTableKlijenti().getModel();
                    Klijent k=(Klijent) mtk.getKlijenti().get(red);
                    try{
                    komunikacija.Komunikacija.getInstance().obrisiKlijenta(k);
                    JOptionPane.showMessageDialog(pkf, "Sistem nje uspesno obrisao klijenta", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                    pripremiFormu();
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(pkf, "Sistem ne moze da obrise klijenta", "Greska", JOptionPane.ERROR_MESSAGE);
                    };
                }
                
            }
        });
        pkf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red= pkf.getjTableKlijenti().getSelectedRow();
                if(red==-1){
                    JOptionPane.showMessageDialog(pkf, "Morate izabrati klijenta", "Greska", JOptionPane.ERROR_MESSAGE);
                    
                }else{
                    ModelTabeleKlijent mtk= (ModelTabeleKlijent) pkf.getjTableKlijenti().getModel();
                    Klijent k=(Klijent) mtk.getKlijenti().get(red);
                    koordinator.Koordinator.getInstance().dodajParam("klijent", k);
                    koordinator.Koordinator.getInstance().otvoriAzurirajKlijenteForma();
                }
                
            }
        });
        
        pkf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = pkf.getjTextFieldIme().getText().trim();
                String prezime = pkf.getjTextFieldPrezime().getText().trim();
                long telefon = 0;

                    if (!pkf.getjTextFieldBrojTelefona().getText().trim().isEmpty()) {
                        try {
                            telefon = Long.parseLong(pkf.getjTextFieldBrojTelefona().getText().trim());
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(pkf, "Broj telefona mora biti broj", "Greska", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }

                Mesto mesto = (Mesto) pkf.getjComboBox1().getSelectedItem();
                ModelTabeleKlijent mtk = (ModelTabeleKlijent) pkf.getjTableKlijenti().getModel();
                mtk.pretrazi(ime, prezime, telefon, mesto);
            }
        });
                            
    
    
        }
}
