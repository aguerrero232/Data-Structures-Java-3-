import java.io.*;
import java.util.*;


/**
 * Name: Maze
 * 
 * Description: meat of the program, first all the mazes are loaded into a 3d char array and stored for the user
 * 				to pick which they want to see completed. Next once the choice has been made we use recursion to 
 * 				solve a random path out of the maze in 5 directions (n,s,e,w,hyperspace). Once the traverse method
 * 				returns true then the whole thing unwinds and we get out the maze. We output the current row and 
 * 				column as well as when a wall is hit how many walls were hit on the run and how many times we took 
 * 				hyperspace to a valid location(does not count if we didn't teleport)
 *  
 *  Parameters:	File filled with data for the mazes
 * 			
 *          
 * 	   History: 11/19/2018 - Fixed loading issue finally found a way that guarantees to get 3 pairs of mazes
 * 						     would be nice if i could do this for a variable amount
 * 
 * 			    11/20/2018 - Added in Hyperspace dimension and also finally got isValid to do what I want 
 * 
 * 			    11/21/2018 - Deadline coming up and just noticed after 4k moves I should expect get a stack overflow
 * 						     and it probably doesn't help that I added in a wall hit counter and a hyperspace counter
 * 
 * 			    11/22/2018 - fixed as many issues as I could and finished up notes think I will leave off here
 * 
 * 
 * @author ARIEL GUERRERO
 *
 */
public class Maze {		
	
	char compChar = '0';
	boolean  done = false;
	Random rand = new Random();
	int counter = 0,hycnt=0, mazecounter = 0,mazetype =0,rowLD =0,colLD =0,wallhtcnt =0,dimensionLD =0, North = 0, South = 1, East =2, West = 3, Hyper = 4,dimension =0;
	public char[][][] maze1 = new char[15][15][2],maze2= new char[15][15][2],maze3 = new char[15][15][2]; 

	/**
	 * Name: Maze()
	 * Parameters: N/A
	 * Description: constructor calls on loadMazes() to initialize our 3 pairs of mazes 
	 * 				and then a call to menu() for the user to decide which to solve 
	 * 
	 */
	public Maze() throws FileNotFoundException {
		loadMazes("Mazes.txt");
		menu();
	}
	
	/**
	 * Name: Menu()
	 * Parameters: N/A
	 * Description: Once we have our mazes loaded we can select from the 3 option to solve that particular maze
	 * 				also notifies us when we are done and gives us our move count,wall hit count and hyperspace count
	 * 				as well as the starting point 
	 * 
	 */
	public void menu() {
		boolean mO = false;
		do{
			hycnt =0;
			counter=0;
			wallhtcnt =0;
			System.out.println("INPUT 1 = MAZE 1,  INPUT 2 = MAZE 2,  INPUT 3 = MAZE 3, ** INPUT 4 TO TERMINATE PROGRAM ** ");
			Scanner uChoice = new Scanner(System.in);
			
			switch(uChoice.nextInt()){
			
				case 1:
					System.out.println("------------------ MAZE 1 ------------------");
					System.out.println(this.prntMz(maze1));
					
					colLD=0;
					while(maze1[0][colLD][0] != '1') {
						colLD++;
	                }
					System.out.println("START POSITION|  ROW:" + rowLD + " COLUMN: " + colLD);
					this.traverse(maze1, rowLD, colLD, 0);
						System.out.println();
						System.out.println("!!!!!!COMPLETED MAZE 1 AFTER " + (counter-1) + " MOVES!!!!!!!");
						System.out.println("TOTAL WALLS HIT: "+ wallhtcnt+ "| VALID HYPERSPACE ACTVATIONS: " +hycnt);
						System.out.println();
				
					
				break;
				
				case 2:
					
					System.out.println("------------------ MAZE 2 ------------------");
					System.out.println(this.prntMz(maze2));
					
					colLD=0;
					while(maze2[0][colLD][0] != '1') {
						colLD++;
	                }
					
					System.out.println("START POSITION|  ROW:" + rowLD + " COLUMN: " + colLD);
					this.traverse(maze2, rowLD, colLD, 0);
						System.out.println();
						System.out.println("!!!!!!COMPLETED MAZE 2 AFTER " + (counter-1) + " MOVES!!!!!!!");
						System.out.println("TOTAL WALLS HIT: "+ wallhtcnt+ "| VALID HYPERSPACE ACTVATIONS: " + hycnt);
						System.out.println();
					
				break;
			
				case 3:
					System.out.println("------------------ MAZE 3 ------------------");
					System.out.println(this.prntMz(maze3));
		
					colLD=0;
					while(maze3[0][colLD][0] != '1') {
						colLD++;
	                }
					
					System.out.println("START POSITION|  ROW:" + rowLD + " COLUMN: " + colLD);
					this.traverse(maze3, rowLD, colLD, 0);
						System.out.println();
						System.out.println("!!!!!!COMPLETED MAZE 3 AFTER " + (counter-1) + " MOVES!!!!!!!");
						System.out.println("TOTAL WALLS HIT: "+ wallhtcnt+ "| VALID HYPERSPACE ACTVATIONS: " + hycnt);
						System.out.println();
				
				break;
				
				case 4:
					mO =true;
				break;
			}
	
		}while (mO == false);
	}
	
	/**
	 * Name: loadMazes()
	 * Parameters:
	 * @param String file - file of mazes we store into a char array
	 * @throws FileNotFoundException
	 * 
	 * Description: here is where we load the data into all three of the mazes using a scanner to input data 
	 * 				from the file one line at a time.
	 * 
	 */
	public void loadMazes(String file) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(file));
		while(scan.hasNext()) {
			String Line = scan.next();
			int currLine =0;
			
			if(mazetype==0) { 
				while(currLine < Line.length()) {
					maze1[rowLD][currLine][dimensionLD] = Line.charAt(currLine);
					currLine++;
				}
				rowLD++;
			}
			
			if(mazetype==1) {
				dimensionLD=1;
				while(currLine < Line.length()) {
					maze1[rowLD][currLine][dimensionLD] = Line.charAt(currLine);
					currLine++;
				}
				rowLD++;
			}
			
			
			if(mazetype==2) {
				while(currLine < Line.length()) {	
					maze2[rowLD][currLine][dimensionLD] = Line.charAt(currLine);
					currLine++;
				}
				rowLD++;
			}
			if(mazetype==3) {
				dimensionLD=1;
				while(currLine < Line.length()) {
					maze2[rowLD][currLine][dimensionLD] = Line.charAt(currLine);
					currLine++;
				}
				rowLD++;
			}
			
			
			if(mazetype==4) {
				while(currLine < Line.length()) {
					maze3[rowLD][currLine][dimensionLD] = Line.charAt(currLine);
					currLine++;
				}
				rowLD++;
			}
			if(mazetype==5) {
				dimensionLD=1;
				while(currLine < Line.length()) {
					maze3[rowLD][currLine][dimensionLD] = Line.charAt(currLine);
					currLine++;
				}
				rowLD++;
			}
						
			// RESETS EVERY MAZE LOADED IN PER 15 CYCLES
			mazecounter++;
			if(mazecounter%15 ==0){
				mazetype++;
				rowLD=0;
				colLD=0;
				dimensionLD=0;
			}	
			
		}
	   }
	 
	 
	/**
	 * Name: prntMZ()
	 * 
	 * Parameters:
	 * @param mzs - 3d char array filled with either maze 1 2 or 3
	 * @return
	 * 
	 * Description: pretty much one for loop per dimension. Most outer loop handles the dimension, middle
	 * 				handles row and inner handles columns. Most inner loop also handles all the printing.
	 * 				Middle and outer loops deal with adding in new lines so everything isn't one straight 
	 * 				straight string
	 */
	public String prntMz(char[][][] mzs)
	{
		String whole = "";
		for(int dimen =0; dimen <2; dimen++)
		{
			for(int row = 0; row < 15; row++)
			{
				for(int col = 0; col < 15; col++) {
					whole += mzs[row][col][dimen] + "";
				}
				whole += "\n";	
			}
			whole += "\n";
		}
		return whole;
	}
	
	/**
	 * Name: isValid()
	 * 
	 * Parameters: 
	 * @param maze - current maze trying to get solved
	 * @param row - row that needs to get validated
	 * @param col - column that needs to be validated 
	 * @return
	 * 
	 * 
	 * Description: passes in the parameters and checks if the location in the maze in use isn't a wall 
	 * 				or outside the bounds of the maze.we return true only if it passes all these checks or if row 
	 * 				is greater than 14 ( outside the maze and solved) 
	 * 			    If it is a wall this is where we increment the wall hit counter 
	 * 
	 */
	public boolean isValid(char maze[][][], int row, int col) {
		boolean currDir = false;
	
		if(row >=0 && row < 15 &&col >=0 && col <15){
			char validDir = maze[row][col][0];
			if(validDir=='1') {
				currDir = true;
			}
			else {
				System.out.println("OUCH!!!! WALL HIT AT ROW: " + row + " COLUMN: "+ col  );
				wallhtcnt+=1;
			}
		}
		if(row >14) {
			currDir =true;
		}
		return currDir;
	}
	
	/**
	 * Name: traverse()
	 * Parameters:
	 * @param maze - current 3d maze we are trying to solve
	 * @param row - current row 
	 * @param col - current column
	 * @param direc - current direction 
	 * @return boolean
	 * 
	 * Description: This is where we do our recursive call to solve the maze. I first check if we have used 
	 * 				up every direction if so the re back track. next I store and mark off the direction we 
	 * 				came from if we haven't used up every direction. Next we check if row is = to 15 which means
	 * 				completed the maze if we haven't we go into the actual recursion. We fall into a big while loop
	 * 				that checks if done is false and if all the directions are not used. we get our random direction 
	 * 				and re call the method to solve the maze recursively by passing in a new row or column or use 
	 * 				hyperspace to teleport.
	 * 
	 * 
	 */
	public boolean traverse(char maze[][][],int row, int col, int direc)
	{ 
		int directionTrav;
		done =false;
		counter++;
		int[] dir = new int[5];
		
		
		if(dir[0]==1 && dir[1]==1 && dir[2]==1 && dir[3]==1 && dir[4]==1) {
			System.out.println("ALL POSSIBLE DIRECTIONS TRIED .... BACKTRACKING");
			return false;
		}
		dir[direc] = 1;
		if(isValid(maze, row, col)) { //checks to see if its a valid path
	
		if(row == 15) { // finished maze found the path
			done = true;
		}
		while(done == false && (dir[0]!=1 ||dir[1]!=1 ||dir[2]!=1 ||dir[3]!=1 || dir[4]!=1)) {
			
			do{
				directionTrav = rand.nextInt(5);
			}while(dir[directionTrav] == 1);
			
			if(directionTrav ==0) {
				System.out.println("MOVE#: " + counter + "| HEADING NORTH TO ROW: " + (row-1) + "  COLUMN: "  + col);
				done = traverse(maze, row-1, col, South);
				dir[directionTrav]=1;
			}
			if(directionTrav ==1){
				System.out.println("MOVE#: " + counter + "| HEADING SOUTH TO ROW: " + (row+1) + "  COLUMN: "+ col);
				done = traverse(maze, row+1, col, North);
				dir[directionTrav]=1;
			}
			if(directionTrav ==2) { 
				System.out.println("MOVE#: " + counter + "| HEADING EAST  TO ROW: " + row + "  COLUMN: "+ (col+1));
				done = traverse(maze,row, col+1, West);
				dir[directionTrav]=1;
			}
			if(directionTrav ==3) { 
				System.out.println("MOVE#: " + counter + "| HEADING WEST  TO ROW: " + row + "  COLUMN: " + (col-1));
				done = traverse(maze, row, col-1,East);
				dir[directionTrav]=1;
			}
			if(directionTrav ==4){
				dimension =1; // enter hyperspace 
				System.out.println("MOVE#: " + counter + "| ********* HYPERSPACE ACTIVATED *********");
				char val = maze[row][col][dimension];
				int rowhold = row;    // holders for row and column just in case we go back and need the original values after changing them 
				int colhold = col; // below while searching for the twin char 
				dir[directionTrav]=1;
				if(val == 'A' ||val == 'B' || val =='C' || val =='D'){
					System.out.println("PORTAL: " + val);
					hycnt+=1;
					row=0; 
					col = 0; 
					while(compChar!= val || (row != rowhold && col != colhold)) {
						compChar = maze[row][col][dimension]; 
						if(col ==14)	{
							col =0;
							row++;
						}
						else
							col++;
					}
				}
				dimension=0; // exit hyperspace
				done = traverse(maze,row,col, Hyper);
			}	
		  }
		}
		
	return done;
	}
	
}
