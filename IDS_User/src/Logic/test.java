/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.Socket;

/**
 *
 * @author sumit
 */
public class test {
    public static void main(String[] args) {
        try{
             String ip=info.my_ip;
      //  new emp_listener_temp(this);
            Socket soc=new Socket(info.cms_ip,info.cms_port);
            ObjectOutputStream out=new ObjectOutputStream(soc.getOutputStream());
            out.writeUTF("new_node");
           out.writeUTF(Inet4Address.getLocalHost().getHostName());
            out.writeUTF(ip);
            out.close();
            soc.close();
        }catch(Exception e)
        {
        e.printStackTrace();
        }
    }
}
