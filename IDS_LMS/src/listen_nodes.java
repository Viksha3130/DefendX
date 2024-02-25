
import java.net.ServerSocket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sumit
 */
public class listen_nodes extends Thread{
    public listen_nodes(){
    
     start();
    
    }
    
    public void run(){
    try{
    ServerSocket ss=new ServerSocket(7002);
        System.out.println("LMC Listening Nodes");
        while(true)
        {
            
        }
    }catch(Exception e)
    {
    e.printStackTrace();
    }
    
    }
}
