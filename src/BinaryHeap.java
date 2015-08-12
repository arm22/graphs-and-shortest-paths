
public class BinaryHeap implements PriorityQueue {
	
	//Declare a global array as our structure
	private double[] array;
	//Declare a global int size
	private int size;
	
	//Construct a new queue
	//Using an array of size 10
	//Set the size to 0
	public BinaryHeap() {
		array = new double[10];
		size = 0;
    }
	
	//Check if the Heap is empty
	//Checks the size
	//Returns true if empty, false otherwise
	@Override
    public boolean isEmpty() {
    	if(size < 1) {
    		return true;
    	}
    	return false;
    }

	//Returns the size of the Heap
	@Override
    public int size() {
    	return size;
    }

	//Return the value with the highest priority
	//Throw exception if Heap is empty
	@Override
    public double findMin() {
		if(isEmpty()){
			throw new EmptyPQException();
		}
		return array[1];
    }

	//Insert a value into the Heap
	//Accepts a double to insert
	@Override
    public void insert(double val) {
		//Call size up if the array is full
		if(size==array.length-1) {
			sizeUp();
		}
		//Increment size by 1
		size++;
		//Call method to find correct index to place  value
	 	int i = percolateUp(size, val);
	 	//Place value in Heap
	 	array[i] = val;

    }

	//Delete the item in the Heap with the highest priority
	//Return it's value
	@Override
    public double deleteMin() {
		//Throw an exception if the Heap is empty
		if(this.isEmpty()){
			throw new EmptyPQException();
		}
		//Save the value of the item
		double ans = array[1];
		//Find the hole that needs to be filled
		int hole = percolateDown(1, array[size]);
		//Set the holes value to the last element in the Heap
		array[hole] = array[size];
		//Decrement the size
		size--;
		//Return the value of the item deleted
		return ans;
    }

	//Make the Heap empty by just setting the size back to 0
	@Override
    public void makeEmpty() {
    	size = 0;
    }
	
	//Double the size of the array
	private void sizeUp() {
		//Declare a new array to copy the old array
		double[] temp = new double[size*2];
		for (int i = 1; i <= size; i++){
			//Set values of old array to values of copy
			temp[i] = array[i];
		}
		//Set the global array to the new bigger array
		array = temp;
	}
	
	//Helper method to place value at correct position in heap
	//Accepts the empty index and a value as parameters
	//Returns the index of the empty space
    private int percolateUp(int hole, double val) {
    	//Loop through the structure while the hole is not at the top
    	//And the input value is a higher priority than the parent
    	while(hole > 1 && val < array[hole/2]) {
    		//Replace the empty space with its parents value
    		array[hole] = array[hole/2];
    		//Loop again with the parent index as hole
    	 	hole = hole / 2;
    	}
    	//Return the index of the hole
    	return hole;
	}
    
    //Helper method to place value at correct position in heap
  	//Accepts the empty index and a value as parameters
  	//Returns the index of the empty space
    private int percolateDown(int hole, double val) {
    	//Declare an int that will become our target
    	int target;
    	//Loop through the structure while the hole
    	//Is within the array
    	while(2*hole <= size) {
    		//Get the left and right children of the hole
    		int left = hole*2;
    		int right = left + 1;
    		//If there is no right child or the right child
    		//Has a lower priority set target to left
    		if(right > size || array[left] < array[right]) {
    			target = left;
    		//Otherwise set the target to right
    		} else {
    			target = right;
    		}
    		//If the value is lower priority than
    		//the value of the target, set the 
    		//hole at this index
    		if(array[target] < val){
    			array[hole] = array[target];
    			hole = target;
			//Otherwise stop looping
    		} else {
    			break;
    		}
    	}
    	//Return the empty index
		return hole;
	}
}
