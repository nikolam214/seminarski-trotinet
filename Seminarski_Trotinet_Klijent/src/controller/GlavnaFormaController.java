/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Zaposleni;
import forme.GlavnaForma;
import forme.LoginForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author nikola
 */
public class GlavnaFormaController {
    private final GlavnaForma gf;
    
     public GlavnaFormaController(GlavnaForma gf) {
        this.gf = gf;
        addActionListeneres();
        
    }

    private void addActionListeneres() {
        gf.addBtnOdjaviSeActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                koordinator.Koordinator.getInstance().setUlogovani(null);
                gf.dispose();
                koordinator.Koordinator.getInstance().otvoriLoginFormu();
            }
        });    }

    public void otvoriFormu() {
        Zaposleni ulogovani=koordinator.Koordinator.getInstance().getUlogovani();
        String imePrezime= ulogovani.getIme()+ " "+ ulogovani.getPrezime();
        gf.setVisible(true);
        gf.getjLabelUlogovani().setText(imePrezime);
    }
    
    
}
