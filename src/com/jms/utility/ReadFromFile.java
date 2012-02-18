package com.jms.utility;
import java.io.*;
import java.nio.channels.FileChannel;

import javax.jms.Message;
import javax.swing.JFileChooser;

import org.apache.activemq.BlobMessage;


@SuppressWarnings("unused")
public class ReadFromFile {
	
	

	private Message message = null;
	private Object File=null;
	private String FileName=null;
	private File file=null;
	public String extension=null;
	public String Data=null;
	 int dotPos=0;
	 FileChannel source = null;
	 
	public ReadFromFile()
	{
		
		
	}

//return the  extension 
	
	public int GetFileType( String Filename)
	{
		File file=new File(Filename);
		/// String extension=null;
		JFileChooser chooser = new JFileChooser();
		 String fileTypeName = chooser.getTypeDescription(file);
		 System.out.println("File Type= "+fileTypeName);
		dotPos = Filename.lastIndexOf(".");
		 System.out.println("dotPos "+ dotPos);
		 dotPos++;
		  extension = Filename.substring(dotPos);
		 System.out.println("extension "+extension);
		 
		return 0;
		
	}
	
	
	/* Find the type of the file  by looking into the ext.
	 * 
	 * */
	
	public String ReadAFileToStringUsingChar(String Filename)
	{
		
	
		
		  if (Filename.length() < 1) {
		        System.err.println("missing filename and i am about to exit");
		        System.exit(1);
		      }
		  File file = new File(Filename);
		     
		    char[] buffer = null;
		     
		    try {
		        BufferedReader bufferedReader = new BufferedReader(
		                new FileReader(file));
		 
		        buffer = new char[(int)file.length()];
		 
		        int i = 0;
		        int c = bufferedReader.read();
		 
		        while (c != -1) {
		            buffer[i++] = (char)c;
		            c = bufferedReader.read();
		        }
		    } catch (FileNotFoundException e) {
		    	System.out.println(e);
		    //    log.error(e.getMessage());
		    } catch (IOException e) {
		      //  log.error(e.getMessage());
		    	System.out.println(e);
		    	
		    }
		 
		    return new String(buffer);
		   
		  
	}
	/*  Read a file into buffer and save it in a string **/
	
	 public String readFileAsStringUsingByte(String filePath) throws java.io.IOException{
	    byte[] buffer = new byte[(int) new File(filePath).length()];
	    BufferedInputStream f = null;
	    try {
	        f = new BufferedInputStream(new FileInputStream(filePath));
	        f.read(buffer);
	    } finally {
	        if (f != null) try { f.close(); } catch (IOException ignored) { }
	    }
	    return new String(buffer);
	}
	/*
	 * Read xml file to a string
	 */

}