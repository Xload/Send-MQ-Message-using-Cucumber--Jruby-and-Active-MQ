package com.jms.utility;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.jms.messaging.*;
import com.jms.utility.ReadDataFromDataSheet;
import com.jms.utility.ReadFromFile;


@SuppressWarnings("unused")
public class SearchAndFind{
	
	String Main=null;
	String Sub=null;
	int location=0;
	int Occurrence=0;
	String [] stringarray;
	boolean PASS=true;
	boolean FAIL=true;
	ReadDataFromDataSheet data=new ReadDataFromDataSheet() ;
     ReadFromFile File=new ReadFromFile();
	String Name=null;
	
	 public SearchAndFind()
	 {
		 
	 }
	 /*
	  * Find Number of occurrence of Sustring within String using the function substring
	  */
	 
	 //TO BE IMPLEMENTED 
	 
	 
	 public int FindOccurrenceSubStringinString(String Main,	String Sub){
		 
		 int i = 0,j=0;
		 int len=Main.length();
		 int sublen=Sub.length();
		 
		 
		 while(i<len)
		 {
			 int index=Main.indexOf(Sub);
			 if (index>0 || index < len){
				 Occurrence++;
				 i=index;
			 }
				 
		 }
		 
		 
		return Occurrence;
		
		 
		 
		
	 }
	/*
	 * The function is designed to return the location of substring within string.Can be used for data validations
	 * when Searching of a sub string within s string.
	 * if it found it will return the location of the first char and if not found it will return value 0
	 */
	public int ReturnLocationOfSubstringinString(String Main,String Sub)
	{
		
		int i=0;
		int j=0;
		int location=0;
		if (Main==null)
		{
			return 0;
		}
		int Mainlength=Main.length();
		int Sublength=Sub.length();
		for (i=0,j=0;i<(Mainlength-Sublength);i++)
		{
			if (Main.toUpperCase().charAt(i)==Sub.toUpperCase().charAt(j))
			{
				j++;
				location=i;
			}
			else{
				j=0;
				location=0;
			}
			
			if (j==Sublength)
			{
				return location;
			}
		
			
		}//For Loop
		
		return location;
		
		
	}//Main Search function
	
	//return true if location is not 0
	boolean Retrurnstatus(int loc)
	{
		
		if (loc >0 )
		{
		return true;
		}
		else return false;
		
	}
	
	
	/*
	 * replace every parameter that start with { and end with } within any file with the values allocated within
	 * the data sheet and then return the new file data as string.It will call the Wrapper first to add ${} to the Columns names
	 */
	@SuppressWarnings({ "rawtypes" })
	public String Parametrize_Data_From_File(String File, HashMap map) throws InvalidFormatException, FileNotFoundException, IOException{
		
	
		String ModFile=null;
		int NumberOfKeys=0;
		//boolean status=map.containsKey("TEST1");
		Object[] data;
	//get the keys from hashmap and save it to an array of objects
		
		data=map.keySet().toArray();	
	for (int i=0; i<map.size();i++)
		{		
			String temp=new String( WrapperForBracketsAndDolorSign((String) data[i]));
		//	System.out.println(temp.length());
			File=File.replace(temp, (CharSequence) map.get(data[i]));
			
		
	}
				
	//System.out.println(File);
	
		return File;
		
	}
	/*
	 * Function to Wrap the String value of the keys param from HashMap with ${} to ensure its unique
	 * when used with the  function Parametrize_Data_From_File 
	 */
	public String WrapperForBracketsAndDolorSign(String str){
		
		int len=str.length();
		
		String temp;
		char[] temparray =new  char[len+3];
	
		temparray[0]='$';
		temparray[1]='{';
		str=str.concat("}");
		int j=2;
	
		len=str.length();
		for (int i=0;i<len;i++)
		{
			temparray[j]=str.charAt(i);
			j++;
		
		}
		
	
		
		String NewString = new String(temparray);
	///	System.out.println(newString1);
		
		
		return NewString;
		
		
		
	}
	
	
	/*
	 * //Return Specific String based on Left and Right Boundaries.
	 */
	
	public String FindSaveValue(String Left, String Right,String MainBuffer)
	{
		int i,j=0;
		
		i=MainBuffer.indexOf(Left);
		j=MainBuffer.indexOf(Right);
		
		if (i > j || i ==j)
		{
			return null;
		}
		
		else
		{
			int len=j-i;
			String Str=new String();
			Str=MainBuffer.substring(i, j);
			
			return Str;
		}
			
		
	}
	
		
	
	
	

}//Main Class
