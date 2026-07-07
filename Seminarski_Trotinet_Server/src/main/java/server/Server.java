/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import niti.ObradaKlijentskihZahteva;

/**
 *
 * @author nikola
 */
public class Server extends Thread{
    
   boolean kraj=false;
   ServerSocket serverSocket;
    List<ObradaKlijentskihZahteva> klijenti= new ArrayList<>();

    @Override
    public void run() {
       try {
           serverSocket=new ServerSocket(9000);
       
        while(!kraj){
            Socket s;
           
                s = serverSocket.accept();
           
                System.out.println("Klijen povezan");

                ObradaKlijentskihZahteva okz= new ObradaKlijentskihZahteva(s);
                klijenti.add(okz);
                okz.start();
        }
       } catch (IOException ex) {
           System.getLogger(Server.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
       }
    }
    
    
   
    
    public void zaustaviServer(){
        kraj=true;
       try {
           for (ObradaKlijentskihZahteva k : klijenti) {
               k.prekiniNit();
           }
           serverSocket.close();
       } catch (IOException ex) {
           System.getLogger(Server.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
       }
    }
    
}
