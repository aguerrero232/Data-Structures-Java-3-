import java.io.*;
//you will need to import a few things in order for this to work(Hint: if you do import java.io.*;
import java.util.Scanner;
/**
 * Name: SkipList
 * 
 * Description: meat of the program this is where the post nodes are created, 
 * 				skip and linear searches are done here, add in data 
 * 				and menu also accessed in here 
 *  
 * Parameters:	File filled with data SSN;LAST NAME;FIRST NAME 
 * 				must be used with a ';' as a delimiter
 *          
 * History:	 10/12/2018 - Fixed sorting just needed to add .Level4 and now fills 
 * 						  just like its supposed to 
 * 
 * 			 10/13/2018 - Added in properish notes (good notes are great bad notes are good!)
 * 
 * 
 * @author ARIEL GUERRERO
 *
 */
public class SkipList {


		// this is what we are using to fill out our nodes 
		String SSN, first, last;
		
		// bunch o
		Node node = null;
		
		Node tmp = null;
		Node tmp1 = null;
		Node tmp2 = null;
		
		Node curr = null;
		
		Node head = null;

		
			//next you will need to make your create method to set up the skip list structure
				//inside this method you will have 3 nested For loops that set up the structure 
		/**
		 * Name: createPostNode()
		 * Parameters: N/A
		 * Description: Creates the 1000 nodes needed to go through the skip list 
		 * 				taking advantage of the Node(String SSN) constructor
		 */
		public void createPostNode() {
			
			// have to start timing here using a long 
			long startTime = System.nanoTime();
			for(int i =9;i>=0;i--) {  /// hundred million
				
				for(int j =9;j>=0;j--) { // ten million  
					
					for(int k =9;k>=0;k--) { // one million 
				
					 // string starting at 999000000 for our
					 // post nodes so we can compare to the SSN strings
					 curr  = new Node(""+i+j+k+"000000");
					 	// remember level 3 and level 4 will be the same till we start
						// adding stuff in
					 	curr.Level3 = tmp;
						curr.Level4 = tmp;
						tmp = curr;		
										// millions 
					}		
					curr.Level2 = tmp2;
					tmp2 = curr;		//	ten millions 
				}	
				curr.Level1 = tmp1;
				tmp1 = curr;		    // hundred millions
			}
			
			head = tmp;
			// stop timing here then do start - end time and divide total time by  1000000
			// for nano secs
		
			// get the end time it took to run the skip implementation 
			long endTime   = System.nanoTime();
		
			// get total time it took to run 
			long totalTime = endTime - startTime;
			
			// get the total time in microseconds
			totalTime = totalTime / 1000000;
			
			System.out.println( totalTime + " microseconds to load Post Node data" );
			
		}
		
		/**
		 * Name: Fill
		 * Parameters: String File 
		 * 			   (filled with data SSN;Last name; First name)
		 * Description: Implements Skip list data structure to fill new nodes 
		 * 				with the data passed in from the file 
		 * 
		 */
		public void fill(String file) {
			long startTime = System.nanoTime();
			try {
		
				// need 4 while loops that that change the level based on the number being tested.
				//each while loop will deal with one level.
				Scanner outerScn = new Scanner(new File(file));
				while (outerScn.hasNext()){
					
					// read in from the file 
					Scanner innerScn = new Scanner(outerScn.next());
					
					// using a semicolon to separate what we look through 
					innerScn.useDelimiter(";");
				   
				   // get our data from the file 
				   SSN = innerScn.next();
				   last = innerScn.next();
				   first = innerScn.next();
				  
				   tmp = head;
				   
				   node = new Node(SSN,last,first);
				 
				   //  skip for hundereds
				   while(tmp!= null && SSN.charAt(0) > tmp.SSN.charAt(0)) {
					   tmp = tmp.Level1;
				   }
				  
				   // skip for tens
				   while(tmp!= null && SSN.charAt(1) > tmp.SSN.charAt(1)) {
					   tmp = tmp.Level2;
				   }
				   
				   //											 012
				   //											   v
				   // skip for ones comparing char @ 2 == to ex) 999   <- compare this one  
				   while(tmp!= null && SSN.charAt(2) > tmp.SSN.charAt(2)) {
					   tmp = tmp.Level3;
				   }
				   
				   // slowest part here going through at most 1million nodes to get to where
				   // we want we compare the whole ssn to the other ssn 
				   while(tmp.Level4 != null && SSN.compareTo(tmp.Level4.SSN) > 0) {
					   tmp = tmp.Level4;
				   }
				   
				 tmp.Level4 = node;
				 node = tmp.Level4 ;
				}
				}catch(FileNotFoundException e) {
					System.out.println(e);
				}
			// get the end time it took to run the skip implementation 
			long endTime   = System.nanoTime();
		
			// get total time it took to run 
			long totalTime = endTime - startTime;
			
			// get the total time in microseconds
			totalTime = totalTime / 1000000;
			
			// let user know how long you took 
			System.out.println( totalTime + " microseconds to load Skip List data" );
			
		}
		
		
		/**
		 * Name: skipSearch()
		 * Parameters: N/A
		 * Description: Here is where the user can input a SSN to search for
		 * 				using the skip list data structure using a scanner
		 * 				
		 */
		public void skipSearch() 
		{
			/*
			 *  this is where we search for certain ssn numbers using the skip search 
			 */
			
			String tmpSSN;
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter SSN: ");
			
			tmpSSN = scan.nextLine();
			
			long startTime = System.nanoTime();
			
			tmp = head;
			
			while(tmp != null && tmpSSN.charAt(0) > tmp.SSN.charAt(0)) {
				tmp = tmp.Level1;
			}
			
			while(tmp != null && tmpSSN.charAt(1) > tmp.SSN.charAt(1)) {
				tmp = tmp.Level2;
			}
			
			while(tmp != null && tmpSSN.charAt(2) > tmp.SSN.charAt(2)) {
				tmp = tmp.Level3;
			}
			
			while(tmp != null && tmpSSN.compareTo(tmp.Level4.SSN) > 0) {
				tmp = tmp.Level4;
			}
			
			if (tmp.Level4 != null && tmpSSN.compareTo(tmp.Level4.SSN) == 0) {
				System.out.println("The record has been found: " + tmp.Level4.SSN + " " + tmp.Level4.LastName + " " + tmp.Level4.FirstName);
			}
			else { 
				System.out.println("Record was not found");
			}
			
			long endTime   = System.nanoTime();
			long totalTime = endTime - startTime;
			totalTime = totalTime / 1000000;
			System.out.println( totalTime + " microseconds to SkipSearch" );
		
		}
	
		// you also need a linear search for searching through the list of nodes 
		// sorting algorith used 
		// WHILE(){
		// 
		//	 if(){
		//	 }
		//  else{
		//	 }
		//
		// }
		// 
		/**
		 * Name: linearSearch()
		 * Parameters: N/A
		 * Description: Here the user can input a SSN using a scanner and 
		 * 				
		 */
		public void linearSearch() {
		
			long startTime = System.nanoTime();
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("What SSN would you like to search for?");
			
			tmp.SSN = scan.nextLine();
			
			while(tmp != null && tmp.SSN.compareTo(tmp.Level4.SSN) > 0) {
				 tmp = tmp.Level4;
				 
			   }
			if (tmp.Level4 != null && tmp.SSN.compareTo(tmp.Level4.SSN) == 0) {
				System.out.println("The record has been found: " + tmp.Level4.SSN + " " + tmp.Level4.LastName + " " + tmp.Level4.FirstName);
			}
			else { 
				System.out.println("Record was not found");
			}
			
			
			long endTime   = System.nanoTime();
			long totalTime = endTime - startTime;
			totalTime = totalTime / 1000000;
			System.out.println( totalTime + " microseconds to linear walk" );
		
		}
		
		public void addNode() {

			Scanner addScan = new Scanner(System.in);
			
		   // get our data to add to the node 
			System.out.println("INPUT SSN:");
			SSN = addScan.next();
			 
			System.out.println("INPUT LAST NAME: ");
			last = addScan.next();

			System.out.println("INPUT FIRST NAME: ");
			first = addScan.next();

		   tmp = head;
		   node = new Node(SSN,last,first);
		   //  skip for hundereds
		   while(tmp!= null && SSN.charAt(0) > tmp.SSN.charAt(0)) {
			   tmp = tmp.Level1;
		   }
		   // skip for tens
		   while(tmp!= null && SSN.charAt(1) > tmp.SSN.charAt(1)) {
			   tmp = tmp.Level2;
		   }
		   //											 012
		   //											   v
		   // skip for ones comparing char @ 2 == to ex) 999   <- compare this one  
		   while(tmp!= null && SSN.charAt(2) > tmp.SSN.charAt(2)) {
			   tmp = tmp.Level3;   
		   }
		   // slowest part here going through at most 1million nodes to get to where
		   // we want we compare the whole ssn to the other ssn 
		   while(tmp.Level4 != null && SSN.compareTo(tmp.Level4.SSN) > 0) {
			   tmp = tmp.Level4;   
		   }
		
		   // put that node in its place
		   tmp.Level4 = node;
		   node = tmp.Level4 ;
			 
		  
		}	
	
		public void menu() {
			
			boolean choice = true;
		do {
				Scanner scan = new Scanner(System.in);
				
				System.out.println("****************************************************************\n"
				+"Please choose your option:\n"
				+"Enter 1 to Linearly search the list\n"
				+"Enter 2 for Skip search \n"
				+"Enter 3 to add in data \n"
				+"Enter 4 to quit the program \n"
				+"***************************************************************** \n"
				+"===>");
		
		switch(scan.nextInt()) {
		
		case 1 : linearSearch();
		choice = false;
		break;
		
		case 2 : skipSearch();
		choice = false;
		break;
		
		
		case 3:	addNode();
		choice = false;
		break;
		
		
		case 4:
		System.out.println("Exiting program. . . " );
		System.exit(0);
		break;
		
		default:
			System.out.println("INVALID OPTION ENTERED!");
			
		}
		
		}while(!choice);
			
	}
}