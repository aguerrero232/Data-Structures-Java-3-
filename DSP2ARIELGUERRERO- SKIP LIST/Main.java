import java.io.File;
import java.util.*;

/**
 * Name: Main()
 * Parameters: N/A
 * Description: Driver for the program
 * 				File is very finicky and doesn't allow me to access the file 
 * 				without being very detailed of its location in memory  
 * History: 10/14/2018 - Wrote in notes
 */
public class Main {
/**
 * Name: main()
 * Parameters: @param args
 * Description: Every program needs a public static void main this is just 
 * 				where mine is. I use it to boot-strap and run my program 
 */
public static void main(String[] args) {
	 //Database = new File("Database.txt");
	SkipList sl = new SkipList();
	sl.createPostNode();
	sl.fill("C:\\Users\\holai\\eclipse\\Projects\\DsProject2\\src\\Database.txt");
	sl.menu();

}




}
