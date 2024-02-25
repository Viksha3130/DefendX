/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import Logic.info;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
public class receiver extends Thread {
  
    String path=info.py_path;
    public receiver(){
    
    start();
    }
    public void run(){
        try {
            ServerSocket ss=new ServerSocket(8899);
            
            while(true)
            {
                Socket soc=ss.accept();
            DataInputStream dis = new DataInputStream(soc.getInputStream());
            
            String user=dis.readUTF();
            String fname=dis.readUTF();
            String eip=dis.readUTF();
           
	    FileOutputStream fos;
                        
            fos = new FileOutputStream(path+"test/"+fname);
            
                long fsize=dis.readLong();
                            
                      
                        
                System.out.println("user="+user);
                System.out.println("fname="+fname);
                System.out.println("fsize="+fsize);
                byte[] buffer = new byte[4096];
		
		int filesize = (int)fsize; //15123 Send file size in separate msg
		int read = 0;
		int totalRead = 0;
		int remaining = filesize;
		while((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
			totalRead += read;
			remaining -= read;
			System.out.println("read " + totalRead + " bytes.");
			fos.write(buffer, 0, read);
		}
		  String FILENAME = path+"res.txt";
                      BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			
			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(fname);

			System.out.println("Done");
                        
                        
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(receiver.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String fdata="";
                        File file1 = new File(path+"result.txt"); 

                        BufferedReader br = new BufferedReader(new FileReader(file1)); 

                        String st; 
                        while ((st = br.readLine()) != null) {
                        System.out.println(st); 
                        fdata=st;
                        }
                        
                        LMS.ta.append(user+" using system with IP "+eip+" has uploaded the file "+fname+"\nand it is "+fdata); 
                        File file = new File(info.alert_path);
                        FileWriter fw_new=new FileWriter(file);
                        fw_new.write(user+" using system with IP "+eip+" has uploaded the file "+fname+"\nand it is "+fdata);
                        fw_new.close();
                        

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

                }
                        
                       
		
		fos.close();
		dis.close();
           
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(receiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }

    public static void main(String[] args) {
        new receiver();
    }
}
