
public class Node {
	
	public String SSN;
	public String FirstName;
	public String LastName;
	public Node Level1;	 // hundred millions  nodes 
	public Node Level2;	 // ten millions nodes
	public Node Level3;	 // one millions nodes 
	public Node Level4;  // ground level nodes

	public Node(String ssnInput, String fName, String lName)
	    {
	        this.SSN = ssnInput;
	        this.FirstName = fName;
	        this.LastName = lName;
	        
	    }
		
		// This will handle empty nodes
		public Node() {
			
		}
		// pretty much for post nodes
		public Node(String ssnNum){
			
			this.SSN = ssnNum;
		
		}
	
		public String toString() {
		//you will also need a toString to print out the SSN number to the usr.
		return "SSN: "+SSN+" Last Name: " +LastName + " First Name: "+ FirstName;
		}
}
