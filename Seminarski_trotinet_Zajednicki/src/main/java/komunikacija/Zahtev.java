/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.Serializable;

/**
 *
 * @author nikola
 */
public class Zahtev implements Serializable {
    
    private Operacija peracija;
    private Object parametar;

    public Zahtev() {
    }

    public Zahtev(Operacija peracija, Object parametar) {
        this.peracija = peracija;
        this.parametar = parametar;
    }

    public Operacija getPeracija() {
        return peracija;
    }

    public void setPeracija(Operacija peracija) {
        this.peracija = peracija;
    }

    public Object getParametar() {
        return parametar;
    }

    public void setParametar(Object parametar) {
        this.parametar = parametar;
    }
 
    
}
