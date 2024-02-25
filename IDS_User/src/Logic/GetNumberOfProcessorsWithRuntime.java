/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author sumit
 */

 public class GetNumberOfProcessorsWithRuntime {
     
    public static void main(String[] args) {
         
        // get the runtime object associated with the current Java application
        Runtime runtime = Runtime.getRuntime();
         
        // get the number of processors available to the Java virtual machine
        int numberOfProcessors = runtime.availableProcessors();
          
        System.out.println("Number of processors available to this JVM: " + numberOfProcessors);
         
    }
 
}   

