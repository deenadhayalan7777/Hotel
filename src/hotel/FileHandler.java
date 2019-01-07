package hotel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileHandler {

public void write(String filename,Object o)
{
	try
    {  
        FileOutputStream filestream = new FileOutputStream(filename); 
        ObjectOutputStream out = new ObjectOutputStream(filestream); 
        out.writeObject(o); 
        out.close(); 
        filestream.close(); 
     } 
      
    catch(IOException ex) 
    { 
        System.out.println("IOException is caught"); 
    } 
}
public Object read(String filename)
{   
	Object o=null;
	 
	try
     {    
         FileInputStream filestream = new FileInputStream(filename); 
         ObjectInputStream in = new ObjectInputStream(filestream); 
          o = in.readObject(); 
          in.close(); 
         filestream.close(); 
     } 
       
     catch(IOException ex) 
     { 
         System.out.println("IOException is caught"); 
     } 
       
     catch(ClassNotFoundException ex) 
     { 
         System.out.println("ClassNotFoundException is caught"); 
     } 
	return o;
	
}
	
	
}
