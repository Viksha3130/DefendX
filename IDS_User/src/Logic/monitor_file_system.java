/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import GUI.Home;
import static GUI.Home.emp_ID;
import java.io.File;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author sumit
 */
public class monitor_file_system extends Thread{
    
    public monitor_file_system(){
    
    start();
    }
    
    public void run(){
    
    while(true)
    {
        File directory = new File(Home.dir);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        int total_file=fList.length;
        System.out.println("Total files "+total_file);
        if(Home.file_check_count==0)
        {
        Home.initial_num_files=total_file;
       
        }
        for (File file : fList){
            //System.out.println(file.getName());
            String name=file.getName();
            //System.out.println(name);
            long fsize=file.length();
            if(Home.file_check_count==0)
                {
                    Home.al_initial_file_list.add(name);
                    Home.al_initial_file_size.add(fsize);

                }
            else{
                if(Home.al_initial_file_list.contains(name))
                {
                  int index=Home.al_initial_file_list.indexOf(name);
                  long osize=(long)Home.al_initial_file_size.get(index);
                  if(osize!=fsize)
                  {
                      System.out.println(name+" has been modified");
                      Home.al_initial_file_size.add(index, fsize);
                      if(Home.write_permission==0)
                      {
                       try{
                        Socket soc=new Socket(Home.lms_ip,Home.lms_port);
                        ObjectOutputStream out=new ObjectOutputStream(soc.getOutputStream());
                        out.writeUTF("file_modified");
                        out.writeUTF(Home.emp_ID);
                        out.writeUTF(Home.ip);
                        out.writeUTF(name);
                        out.close();
                        soc.close();
            
            
                        }catch(Exception e)
                        {
                        e.printStackTrace();
                        }
                      
                      }
                      
                  
                  }
                }
            }
            
        }
        Home.file_check_count++;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(monitor_file_system.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    }
    
}
