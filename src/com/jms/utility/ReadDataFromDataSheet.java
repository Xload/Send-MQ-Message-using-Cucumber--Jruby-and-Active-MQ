package com.jms.utility;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.apache.poi.hslf.model.Sheet;
import org.apache.poi.hssf.record.formula.functions.Column;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

@SuppressWarnings("unused")
public class ReadDataFromDataSheet {
	@SuppressWarnings({"rawtypes" })
	
	HashMap	  map = new HashMap();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	HashMap	<? extends CharSequence, ? extends CharSequence>  map1 = new HashMap();
	
	@SuppressWarnings("rawtypes")
	List data = new ArrayList();
	@SuppressWarnings("rawtypes")
	HashMap <String, List > LargMap=new HashMap <String, List> ();
	String fileName=null;
	int ColumnNumber=0;
	int RowNumber=0;
	String ColumnName=new String();
	static Cell cell=null;
	static Cell Cellhead=null;
	Column column=null;
	 Workbook workbook=null;
	 org.apache.poi.ss.usermodel.Sheet mysheet=null;
	Row row=null;
	 @SuppressWarnings("rawtypes")
	Iterator iterator=null;
	public  ReadDataFromDataSheet ()
	 {
		 
	 }
	 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  HashMap ReadSpecificRow(int rownumber) throws InvalidFormatException, IOException
	{
		      mysheet = workbook.getSheetAt(0);
		      row = mysheet.getRow(rownumber);
		      Row   Rowhead= mysheet.getRow(0);
		      Iterator iterator = row.cellIterator();
		      Iterator iterhead=Rowhead.cellIterator();
		      cell= row.getCell(0);
		      Cellhead=Rowhead.getCell(0);
		      while(iterator.hasNext() || iterhead.hasNext())
		      {
		    	   Cellhead=(Cell)iterhead.next();
		    	  cell=(Cell)iterator.next();
		    	  map.put(Cellhead.toString(),cell.toString()); 
		      }	 
		  map1=(HashMap<? extends CharSequence, ? extends CharSequence>) map.clone();
		return map;
		
	}
	
	
	public void Set_Workbook(String filename) throws InvalidFormatException, FileNotFoundException, IOException
	{
		 workbook = WorkbookFactory.create(new FileInputStream(filename));
		
	}

}//end of Class
