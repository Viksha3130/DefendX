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
public class emp_listener_temp extends Thread{
 //   Login l;
    first l;
    public emp_listener_temp(first l){//Login l
    this.l=l;
    start();
    }
    
    
    public void run(){
         try{
             System.out.println("temp listener");
    ServerSocket ss=new ServerSocket(4000);
        while(true)
        {


            Socket soc=ss.accept();
            ObjectInputStream oin=new ObjectInputStream(soc.getInputStream());
            String task=oin.readUTF();
            if(task.equals("login_response"))
            {
                String user=oin.readUTF();
                String status=oin.readUTF();
                String lms_name=oin.readUTF();
                String cluster=oin.readUTF();
                String lms_ip=oin.readUTF();
                int lms_port=oin.readInt();
            
            if(status.equals("blocked"))
            {
                JOptionPane.showMessageDialog(null, "You are blocked!!! Please Contact Admin");
              l.setVisible(false);
              
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
              
              
            }
            else if (status.equals("failed"))
            {
            
            JOptionPane.showMessageDialog(null, "Login Failed");
            }
            else{
            new Home(user,cluster,lms_name,lms_ip,lms_port).setVisible(true);
            l.setVisible(false);
            }
            }
           
            
            
            oin.close();
            soc.close();
            }
        
        
    }catch(Exception e)
    {
    e.printStackTrace();
    }
    }
    
}
