import java.io.FileNotFoundException;



/**
 * Name: Driver
 * 
 * Parameters: N/A
 * 
 * Description: Driver for the program
 * 				File is very finicky and doesn't allow me to access the file 
 * 				without being very detailed of its location in memory  
 * 				
 * 				UPDATE: solved issue with file 
 * 	
 * History: 11/01/18 - wrote in notes 
 * 			
 * 			11/03/18 - figured out that you can't have the .txt file in src 
 * 					   you need it in the project folder!
 */

public class Driver 
{
	public static void main(String[] arg) throws FileNotFoundException, InterruptedException{
		Hospital p = new Hospital();
	}
}
