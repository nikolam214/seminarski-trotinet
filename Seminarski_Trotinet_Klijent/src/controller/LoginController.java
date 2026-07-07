/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.sun.java.accessibility.util.AWTEventMonitor;
import domen.Zaposleni;
import forme.LoginForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import koordinator.Koordinator;

/**
 *
 * @author nikola
 */
public class LoginController {
    
    private final LoginForma lf;

    public LoginController(LoginForma lf) {
        this.lf = lf;
        addActionListeneres();
        
    }

    private void addActionListeneres() {
        
        lf.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava(e);
            }

            private void prijava(ActionEvent e) {
                
                String ki= lf.getjTextFieldUsername().getText().trim();
                String pass=String.valueOf(lf.getjPasswordField1().getPassword());
                
                komunikacija.Komunikacija.getInstance().konekcija();
                Zaposleni ulogovani=komunikacija.Komunikacija.getInstance().login(ki, pass);
                
                if(ulogovani==null){
                    JOptionPane.showMessageDialog(lf, "Neuspesna prijava na sistem", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }else{
                    Koordinator.getInstance().setUlogovani(ulogovani);
                    JOptionPane.showMessageDialog(lf, "Uspesna prijava na sistem", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                    Koordinator.getInstance().otvoriGlavnuFormu();
                    lf.dispose();
                }
                
            }
        });
        
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }
    
    
}
