/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.io.IOException;
import java.net.NetworkInterface;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sumit
 */
public class check_system_mac_address {
    public static void main(String[] args) {
       
        try {
   
            Process p = Runtime.getRuntime().exec("getmac /fo csv /nh");
            java.io.BufferedReader in = new java.io.BufferedReader(new  java.io.InputStreamReader(p.getInputStream()));
            String line;
            line = in.readLine();
                        System.out.println(line);
            String[] result = line.split(",");

            System.out.println(result[0].replace('"', ' ').trim());
        } catch (IOException ex) {
            Logger.getLogger(check_system_mac_address.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   public String get_mac(){
   String mac="";
   try {
   
            Process p = Runtime.getRuntime().exec("getmac /fo csv /nh");
            java.io.BufferedReader in = new java.io.BufferedReader(new  java.io.InputStreamReader(p.getInputStream()));
            String line;
            line = in.readLine();
                        System.out.println(line);
            String[] result = line.split(",");
            mac=result[0].replace('"', ' ').trim();
            System.out.println(mac);
        } catch (IOException ex) {
            Logger.getLogger(check_system_mac_address.class.getName()).log(Level.SEVERE, null, ex);
        }
   
   return mac;
   }
}
