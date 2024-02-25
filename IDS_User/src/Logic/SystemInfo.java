/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 *
 * @author sumit
 */
public class SystemInfo {

    private Runtime runtime = Runtime.getRuntime();
    public static void main(String[] args) {
        SystemInfo si=new SystemInfo();
        ArrayList al=si.info();
        for(int i=0;i<al.size();i++)
        {
        System.out.println(al.get(i).toString());
        }
    }
    public ArrayList info() {
        ArrayList al=new ArrayList();
        StringBuilder sb = new StringBuilder();
        al.add(this.osInfo());
        al.add(this.memInfo());
        al.add(this.diskInfo());
        al.add(this.totalMem());
        al.add(this.usedMem());
        
        
        String arr[]=this.diskInfo().split("<br/>");
        for(int i=0;i<arr.length;i++)
        {
        System.out.println(arr[i].toString());
        }
        System.out.println("________OS info__________");
        String arr1[]=this.osInfo().split("<br/>");
        for(int i=0;i<arr1.length;i++)
        {
        System.out.println(arr1[i].toString());
        }
         System.out.println("________JVM Memory info__________");
        String arr2[]=this.memInfo().split("<br/>");
        for(int i=0;i<arr2.length;i++)
        {
        System.out.println(arr2[i].toString());
        }
//        sb.append(this.osInfo());
//        sb.append(this.memInfo());
//        sb.append(this.diskInfo());
  //      return sb.toString();
        return al;
    }

    public String osName() {
        return System.getProperty("os.name");
    }

    public String osVersion() {
        return System.getProperty("os.version");
    }

    public String osArch() {
        return System.getProperty("os.arch");
    }

    public long totalMem() {
        return Runtime.getRuntime().totalMemory();
    }

    public long usedMem() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public String memInfo() {
        NumberFormat format = NumberFormat.getInstance();
        StringBuilder sb = new StringBuilder();
        long maxMemory = runtime.maxMemory();
        long allocatedMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        sb.append("Free memory: ");
        sb.append(format.format(freeMemory / 1024));
        sb.append("<br/>");
        sb.append("Allocated memory: ");
        sb.append(format.format(allocatedMemory / 1024));
        sb.append("<br/>");
        sb.append("Max memory: ");
        sb.append(format.format(maxMemory / 1024));
        sb.append("<br/>");
        sb.append("Total free memory: ");
        sb.append(format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024));
        sb.append("<br/>");
        return sb.toString();

    }

    public String osInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("OS: ");
        sb.append(this.osName());
        sb.append("<br/>");
        sb.append("Version: ");
        sb.append(this.osVersion());
        sb.append("<br/>");
        sb.append(": ");
        sb.append(this.osArch());
        sb.append("<br/>");
        sb.append("Available processors (cores): ");
        sb.append(runtime.availableProcessors());
        sb.append("<br/>");
        return sb.toString();
    }

    public String diskInfo() {
        /* Get a list of all filesystem roots on this system */
        File[] roots = File.listRoots();
        StringBuilder sb = new StringBuilder();

        /* For each filesystem root, print some info */
        for (File root : roots) {
            sb.append("File system root: ");
            sb.append(root.getAbsolutePath());
            sb.append("<br/>");
            sb.append("Total space (bytes): ");
            sb.append(root.getTotalSpace());
            sb.append("<br/>");
            sb.append("Free space (bytes): ");
            sb.append(root.getFreeSpace());
            sb.append("<br/>");
            sb.append("Usable space (bytes): ");
            sb.append(root.getUsableSpace());
            sb.append("<br/>");
        }
        return sb.toString();
    }
}
