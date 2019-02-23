
/**
 * Name: Node
 * Parameters: N/A
 * Description: Creates our nodes for our Linked list
 * 				(notice only next so thats how you can tell 
 * 				its a Singly linked list)
 * 
 *  
 * 
 * History: 11/03/2018 - Wrote in notes
 */

public class Node
{
	protected String name;
	int val;
	Node next;
	
	public Node(String n, int d) {
		this.name = n;
		this.val = d;
		this.next = null;
	}
	
	public String toString(){
		return name;
		
	}
}
