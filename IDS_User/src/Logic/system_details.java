/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.io.File;
import java.lang.management.ManagementFactory;

/**
 *
 * @author sumit
 */
public class system_details {
    public static void main(String[] args) {
        String userName = System.getProperty("user.name");
        System.out.println("userName="+userName);
        long diskSize = new File("/").getTotalSpace();
        System.out.println("diskSize="+diskSize);
        long ds=diskSize/1024/1024;
        System.out.println("ds="+ds);
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("maxMemory="+maxMemory);
        long ram= ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
        long sizekb = ram /1000;
        long sizemb = sizekb / 1000;
        long sizegb = sizemb / 1000 ;
        System.out.println("System Ram ="+sizegb+"gb");
        
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("cores="+cores);
        
        Runtime rt=Runtime.getRuntime();
        System.out.println(rt.totalMemory()/1000/1000);
    }
}
