
import Logic.info;
import java.io.File;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sumit
 */
public class listen_cms extends Thread{
    
    public listen_cms(){
    
     start();
            
    }
    
    public void run(){
    try{
            String ip=info.my_ip;//InetAddress.getLocalHost().getHostAddress().toString();
//            Socket soc=new Socket(info.cms_ip,info.cms_port);
//            ObjectOutputStream out=new ObjectOutputStream(soc.getOutputStream());
//            out.writeUTF("activate_lms");
//            out.writeUTF(info.lms_name);
//            out.writeUTF(info.my_cluster);
//            out.writeUTF(ip);
//            out.writeInt(info.my_port_cms);
//            
//            out.close();
//            soc.close();
    ServerSocket ss=new ServerSocket(info.my_port_cms);
        System.out.println("Monitoring Node is listening");
       
        while(true)
        {
            Socket soc1=ss.accept();
            ObjectInputStream oin=new ObjectInputStream(soc1.getInputStream());
            String task=oin.readUTF();
            System.out.println("task="+task);
            if(task.equals("new_node"))
            {
            String user=oin.readUTF();
            String uip=oin.readUTF();
            LMS.list1.add(uip+" --> "+user);
            File file = new File(info.alert_path);
                FileWriter fw_new=new FileWriter(file);
                fw_new.write(user+" Logged in "+uip);
                fw_new.close();
            oin.close();
            soc1.close();
            
            
            Socket soc=new Socket(uip,4000);
            ObjectOutputStream out=new ObjectOutputStream(soc.getOutputStream());
            out.writeUTF("login_response");
           out.writeUTF(user);
           out.writeUTF("OK");
            out.writeUTF(info.lms_name);
             out.writeUTF(info.my_cluster);
             out.writeUTF(info.my_ip);
             out.writeInt(info.my_port_cms);
            out.close();
            soc.close();
        
            
            
            
            
            }
            else if(task.equalsIgnoreCase("send_data"))
            {
                
                String eid=oin.readUTF();
                String msg=oin.readUTF();
                System.out.println("eid="+eid);
                System.out.println("msg="+msg);
                String eip=oin.readUTF();
                long rtime=oin.readLong();
                 long last_time=LMS.last_time;
                 if(last_time==0)
                 {
                 last_time=rtime;
                 }
                long diff=Math.abs(last_time-rtime);
               
                LMS.last_time=rtime;
                if(LMS.hm.get(eip)==null)
                {
                    LMS.hm.put(eip, "0");
                    LMS.hm1.put(eip, rtime);
                    LMS.hm2.put(eip, eid);
                    LMS.ta.append(eid+" sent message "+msg+" from IP address "+eip+" Request time "+rtime+" Time Difference "+diff+"\n");
               
                }
                else{
                String sc=LMS.hm.get(eip).toString();
                String slast_time=LMS.hm1.get(eip).toString();
//                if(sc.equals("")||sc.equals("0"))
//                {
//                LMS.hm.put(eip, "0");
//                
//                }
//                else{
                  int c=Integer.parseInt(sc);
                  long dlast_time=Long.parseLong(slast_time);
                   long diff1=Math.abs(dlast_time-rtime);
                   LMS.ta.append(eid+" sent message "+msg+" from IP address "+eip+" Request time "+rtime+" Time Difference "+diff1+"\n");
               
                   if(diff1<10000)
                   {
                   
                    LMS.ta.append(eid+"----DDos Attack\t"+LMS.count+"\n");
                    if(LMS.count>3)
                    {
                     Socket soc=new Socket(eip,7500);
                    ObjectOutputStream out=new ObjectOutputStream(soc.getOutputStream());
                    out.writeUTF("process");

                    out.close();
                    soc.close();
                    
                    }
                    LMS.count++;
                   }
                  c++;
                  
                  
                  
                 String sc3=LMS.hm2.get(eip).toString();  
                  
                LMS.hm.put(eip, c+"");
                LMS.hm1.put(eip, rtime);
                
                    System.out.println("oeid="+sc3+"======"+eid);
                if(!sc3.equals(eid))
                {
                
                LMS.ta.append(eid+"----MiM Attack\n");
                
                }
                
                LMS.hm2.put(eip, eid);
               // }
                
                for(int i=0;i<LMS.hm.size();i++)
                {
                 String sc1=LMS.hm.get(eip).toString();
                    System.out.println(sc1);
                String sc2=LMS.hm1.get(eip).toString();
                    System.out.println(sc2);
                }
                }
                
                
                
                oin.close();
            soc1.close();
                
            }
            else if(task.equalsIgnoreCase("alert"))
            {
            String t=oin.readUTF();
            String uip=oin.readUTF();
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();  
            System.out.println(dtf.format(now));  
            
            LMS.ta.append("Alert "+t+"\tIP "+uip+"\t"+dtf.format(now)+"\n");
            File file = new File(info.alert_path);
                FileWriter fw_new=new FileWriter(file);
                fw_new.write("Alert "+t+"\tIP "+uip+"\t"+dtf.format(now));
                fw_new.close();
            oin.close();
            soc1.close();
            }
            else if(task.equalsIgnoreCase("file_modified"))
            {
                
                String emp_id=oin.readUTF();
                String eip=oin.readUTF();
                String fname=oin.readUTF();
                LMS.ta.append(emp_id+" using system with IP "+eip+" has modified the file "+fname+"\n");
            File file = new File(info.alert_path);
                FileWriter fw_new=new FileWriter(file);
                fw_new.write(emp_id+" using system with IP "+eip+" has modified the file "+fname);
                fw_new.close();
            oin.close();
            soc1.close();
            
            
            }
            else if(task.equalsIgnoreCase("resource_access"))
            {
                
                String emp_id=oin.readUTF();
                String eip=oin.readUTF();
                String rname=oin.readUTF();
                LMS.ta.append(emp_id+" using system with IP "+eip+" is trying to access "+rname+"\n");
             File file = new File(info.alert_path);
                FileWriter fw_new=new FileWriter(file);
                fw_new.write(emp_id+" using system with IP "+eip+" is trying to access "+rname);
                fw_new.close();
            oin.close();
            soc1.close();
            
            
            }
            
        }
    }catch(Exception e)
    {
    e.printStackTrace();
    }
    
    }
}
