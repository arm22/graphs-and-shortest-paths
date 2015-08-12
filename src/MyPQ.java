
public class MyPQ implements PriorityQueue {
	//Define a Node as the top reference
	private Node top;
	//Define an int to track the size
	private int size;
	
	//Define the node class
	private class Node {
		//Define a double field called data
		private double data;
		//Define a Node field called next
		private Node next;
		
		//Constructor takes a parameter of type double
		public Node(double d) {
			//Set the data field to the parameter
			data = d;
			//Set the next field to null
			next = null;
		}
	}
	
	//Construct a new Queue with no top node and no size
	public MyPQ() {
		top = null;
		size = 0;
	}
	
	//Return true if the list is empty
	@Override
    public boolean isEmpty(){
		return (top == null);
	}

	//Return the size of the array
	@Override
    public int size(){
    	return size;
    }

	//Return the value of the highest priority item in the queue
	@Override
    public double findMin(){
		//Throw an exception if this is called on an empty queue
		if(isEmpty()){
			throw new EmptyPQException();
		} else {
			return top.data;
		}
    }

	//Insert a new value into the queue
	//which should be passed in
	@Override
    public void insert(double x){
		//Create a new node with the input
    	Node created = new Node(x);
    	//Create a node for the parent
    	Node parent = null;
    	//Create a copy of the top node
    	Node first = top;
    	
    	//Loop through the list as long as the 
    	//fist node is not empty and the input
    	//value has a higher priority than the node
    	while (first != null && x > first.data){
    		//Set the parent to the current node
    		parent = first;
    		//Set the current node to the next node
    		first = first.next;
    	}
    	//If we have reached the top of the list
    	//set this node as the top and its next field 
    	//to the rest of the list
    	if (parent == null){
    		created.next = top;
    		top = created;
    	} else {
    		parent.next = created;
    		created.next = first;
    	}
    	//Increase the size
    	size++;
    }

	//Delete the item in the queue with the highest priority
	//Return its value
	@Override
    public double deleteMin(){
		//Throw an exception if the list is empty
		if (this.isEmpty()){
			throw new EmptyPQException();
		}
		//Save the value of the top node
		//And set the top reference to the 2nd
		//node if possible
		double val = top.data;
		if(top.next != null){
			top = top.next;
		}
		//Decrement the size and return the value of the top node
		size--;
    	return val;
    }

	//Remove reference to the other nodes
	//And set this node to null
	@Override
    public void makeEmpty(){
    	top = null;
    	top.next = null;
	}
}
