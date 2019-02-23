
import java.util.*;
/**
 * Name: Maze
 * 
 * Description: meat of the program
 *  
 *  Parameters:	String[] with hardcoded values 
 * 			
 *          
 * 	   History: 12/6/2018 - started to attempt to sort the words but not LDS
 * 							issues: going backwards, array out of bounds problems
 * 							getting specific char values to return and store the 
 * 							select string into its respective bucket  
 * 				
 * 				12/7/2018 - turns out going to school is important, shortly after class 
 * 							finished the code thanks to the explanation during class from 
 * 							Professor G. Implemented getIndex() fixed the code to work back from
 * 							last character and found out what was causing my array index 
 * 							out of bounds flag before leaving school and added in notes just 
 * 							in time to turn in the code before the day was over 
 * 
 * @author ARIEL GUERRERO
 *
 */
public class RadixSort {
	
	static String[] done, words = {"bar", "cat", "apple", "bug", "cog", "caper", "antler", "ankle", "bear"};
	
	/**]
	 * Name: main
	 * 
	 * Parameters: @param args - standard for the main class
	 * 
	 * Description: in the mainclass the code prints out the String[] used for the radix sort in its current order (Pre-radix order)
	 * 				sorts the unsorted Strings then prints them out now sorted using the radix sort data structure
	 */
	public static void main(String[] args) {
		System.out.println("PRE RADIX SORT: ");
		int commacounter = 0;
		for(String y: words) {
			System.out.print(y);
			if(commacounter < words.length-1) {
			System.out.print(", ");
			commacounter++;
			}
		}	
		System.out.println("");
	    
		radixsort(words);
		System.out.println("POST RADIX SORT: ");
	    commacounter = 0;
		for(String i: done) {
			System.out.print(i);
			if(commacounter < done.length-1) {
			System.out.print(", ");
			commacounter++;
			}
		}
	}
	
	
	/**
	 *        Name: radixsort
	 * 
	 *  Parameters: @param w - hard coded String[]
	 * 
	 * Description: Meat of the program where we cycle through the outermost for loop for the length of the 
	 * 				longest String in our array. We then go into the loop to sort the strings based on the 
	 * 				length of the word minus the current character index we are on. we also have a min value checker
	 * 				to see if the string being checked is even valid and if it isint it is then removed and added into our
	 * 				done array until there is only one word left which is then added at the very end
	 */
	public static void radixsort(String[] w) {
		int maxstrlen = getLongestStr(w), minlen=1, counter =0, doneCount =0;
		ArrayList<String>[] buckets = new ArrayList[26];
		ArrayList<String> working = new ArrayList<String>(),tmp = new ArrayList<String>();
		done = new String[w.length];
	
		 
		for (int i1 = 0; i1 < buckets.length; i1++) {
	        buckets[i1] = new ArrayList<String>();
	    }
	  
		for(int i = 0; i< w.length;i++) {
			working.add(w[i]);
		}
		
		for(int i = 0; i <=maxstrlen; i++){

			System.out.println("");
			System.out.println("COUNTER: "+counter);
			int strcnt = 0;
			int indexVal =0;
			
			for(int i1=0;i1 < working.size(); i1++) { 
				tmp.add(i1, working.get(i1));
			}
			
			for (String i1 : tmp) {
			
				if(i1.length()<minlen) {
			 		done[doneCount] = i1;
			 		System.out.print("REMOVED:"+i1+" ");
			 		doneCount++;
			 		System.out.println("  Size of working list: "+ working.size() +" ");
				 		if(working.size() <=2) {
				 			continue;
				 		}
				 		working.remove(strcnt);
			 	}
			 	else {
			 		indexVal = i1.length()-counter-1;
			 		buckets[getIndex(i1.charAt(indexVal))].add(i1);
			 	}
			 	strcnt++;
			}
			
			System.out.println("");
			strcnt=0;
			counter++;
			tmp.clear();
			
			working.clear();
			
			for (int b = 0; b < 26; b++) {
				
				for (String i1 : buckets[b]) {
			    working.add(i1);
			    System.out.print(i1+", ");
				}
			  buckets[b].clear();
			}
			System.out.println("");
			System.out.println("-----------------------------------------------------------------");
			minlen+=1;
	}
		
		int finishL = done.length - working.size(),arlstndx=0;
		
		for(int i = finishL; i<done.length;i++) {
			done[i] = working.get(arlstndx);
			arlstndx++;
		}
		
		System.out.println("");
	}
	
	
	/**
	 *        Name: getIndex
	 * 
	 *  Parameters: @param letter - the letter that will let us store our string in the correct bucket
	 * 
	 * Description: gets an int value based on the char passed in subtracted by a (a has an ascii value of 97)
	 * @return bucket int value representation of that character in the bucket[]
	 */
	static int getIndex (char letter) {
        return letter - 'a';
    }
 
	/**
	 *        Name: getLongestStr
	 *  
	 *  Parameters: @param w - String[] of words we hard-coded
	 * 
	 * Description: gets the longest strings length int val and returns it so we know how many times 
	 * 				we need to loop in our radix sort
	 * 			    @return - longest strings length 
	 */
    static int getLongestStr(String[] w){
		int length = w.length-1;
		int longestSTR=0;
		
		for(int i=0; i<length; i++) {
			if(w[i].length()-1>longestSTR) {
				longestSTR = w[i].length(); 
			}
		}
		return longestSTR;
	}
}
