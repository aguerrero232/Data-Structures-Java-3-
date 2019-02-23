import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Name: Hospital 
 * 
 * Description: meat of the program this is where the 3 Linked lists are created, 
 * 				to sort the different patients add them to the respective list in
 * 				the appropriate order and then print them all out in the proper
 * 				increment of time (2,4,8)
 *  
 * Parameters:	File filled with data NAME;EMP;INS;DISEASE 
 * 				must be used with a ';' as a delimiter
 *          
 * History:	 11/01/2018 - Fixed sorting of line order just needed to print
 * 
 * 			 11/03/2018 - Added in more comments!  
 * 
 * @author ARIEL GUERRERO
 *
 */
public class Hospital 
{
  Node mIHead = null, mITmp = null, mINext = null,
		gAHead = null, gATmp = null, gANext = null,
		cHead = null,  cTmp = null,  cNext = null;
   
  Scanner fileScanner,patientScanner; 
   
  String firstName, lastName, fullName, emplStr, insStr, disease;
   
  int diseaseholder, emplholder, insholder, eiHolder, count1 =0, counter = 0;
	
  
  /**
	 * Name: Hospital()
	 * Parameters: N/A
	 * Description: Creates the 3 patient lists and patient nodes needed to 
	 * 				print the patients out and see what type of disease they have 
	 * 				while also putting them in order to exit the hospital after 
	 * 				they are treated
	 */
  public Hospital() throws FileNotFoundException, InterruptedException
	{
		
		mIHead = new Node("", 0);
		gAHead = new Node(" ", 0);
		cHead = new Node("",0);
		fileScanner = new Scanner(new File("Patient.txt"));
		int count =0;

//**************************************************************************************************
//**************************************************************************************************

/*
 *  	create our nodes here using two scanners and a delimiter of ; 	
 */
		
		while(fileScanner.hasNext())
		{
			count++;
			if(count%2 !=0)
				firstName = fileScanner.next();
			else
			{
				patientScanner = new Scanner(fileScanner.next()).useDelimiter(";");
				lastName = patientScanner.next();
				emplStr = patientScanner.next();
				if(emplStr.contains("true"))
					emplStr = "1";
				else
					emplStr = "0";
				insStr = patientScanner.next();
				if(insStr.contains("true"))
					insStr = "1";
				else
					insStr = "0";
					
				disease = patientScanner.next();
				diseaseholder = Integer.parseInt(disease);
				emplholder = Integer.parseInt(emplStr);
				insholder = Integer.parseInt(insStr);
				fullName = firstName + " " + lastName;

//**************************************************************************************************
//**************************************************************************************************

	/*
	 *  here is we separate them for our truth table to determine
	 *  the spot in the line 
	 *  
	 *  val |  emp  | ins
	 *  --------------------
	 *  0 = | true  | true
	 *  1 = | true  | false
	 *  2 = | false | true
	 *  3 = | false | false
	 *  
	 */
	
				if(emplholder == 1 && insholder ==1){
					eiHolder = 0;
				}
				if(emplholder == 1 && insholder ==0){
					eiHolder = 1;
				}
				if(emplholder == 0 && insholder ==1){
					eiHolder = 2;
				}
				if(emplholder == 0 && insholder ==0){
					eiHolder = 3;
				}
				
				// creating a new MI node here 
				if(diseaseholder == 0)
				{	
					mINext = mIHead;
					mITmp = new Node(fullName, eiHolder);
					
				while(mINext.next !=null && mINext.next.val <= eiHolder){
					mINext = mINext.next;
				}
				mITmp.next = mINext.next;
				mINext.next = mITmp;
				}
				
				// creating a new GA node here
				if(diseaseholder == 1) 
				{
					gANext = gAHead;
					gATmp = new Node(fullName,eiHolder);
					while(gANext.next !=null && gANext.next.val <= eiHolder){
						gANext = gANext.next;
					}
					gATmp.next = gANext.next;										
					gANext.next = gATmp;	
				}
				
				// creating a new C node here
				if(diseaseholder == 2)
				{
					cNext = cHead;
					cTmp = new Node(fullName,eiHolder);
					while(cNext.next !=null && cNext.next.val <= eiHolder){
						cNext = cNext.next;
					}
					cTmp.next = cNext.next;										
					cNext.next = cTmp;	
				}
				
			}	
			
		}	
		
//**************************************************************************************************
//**************************************************************************************************
		
	/* 
	 *  as long as one of the heads are not null then keep printing out 
	 *  the head then setting it to head.next to get rid of the one just printed
	 *  until all head nodes are = to null 
	 *  
	 *  prints MI every 2 sec
	 *  prints GA every 4 sec
	 *  prints  C every 8 sec 
	 *  
	 */
		while(mIHead !=null || gAHead !=null || cHead !=null)
		{
			Thread.sleep(2000);
			counter = counter + 2;
			
			if(mIHead == null)
				System.out.print("no Myocardinal Infraction patients |");
			else
			{
				if(counter %2 == 0){
				System.out.print("Myocardinal Infraction patient: " +mIHead.name + "  | ");
				mIHead = mIHead.next;
				}
			}
			
			if(gAHead == null)
				System.out.print(" | no Gushing Artery patients |");
			else
			{
				if(counter %4 == 0){
				System.out.print(" Gushing Artery patient : " +gAHead.name + " | ");
				gAHead = gAHead.next;
				}
			}
			
			if(cHead == null)
				System.out.print("| no Cephalagia patients ");
			else
			{
				if(counter %8 == 0){
				System.out.print("| Cephalagia patient:  " + cHead.name);
				cHead = cHead.next;
				}
			}
			System.out.println();
		}
		System.out.println("No more patients");
		
	}
	
}
