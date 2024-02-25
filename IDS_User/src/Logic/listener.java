/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;



import GUI.Home;

import GUI.first;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author sumit
 */
public class listener extends Thread{
 //   Login l;
  
    public listener(){//Login l
   
    start();
    }
    
    
    public void run(){
         try{
             System.out.println("temp listener");
    ServerSocket ss=new ServerSocket(7500);
        while(true)
        {


            Socket soc=ss.accept();
            ObjectInputStream oin=new ObjectInputStream(soc.getInputStream());
            String task=oin.readUTF();
            if(task.equals("process"))
            {
                 Runtime runtime = Runtime.getRuntime();
                try
                {
                   System.out.println("Shutting down the PC after 5 seconds.");
                   runtime.exec("shutdown -s -t 5");
                }
                catch(Exception e)
                {
                   System.out.println("Exception: " +e);
                }
           
            
            
            oin.close();
            soc.close();
            }
        }
        
        
    }catch(Exception e)
    {
    e.printStackTrace();
    }
    }
    
}
