import java.util.NoSuchElementException;
import java.util.Iterator;


/**
* CAN DUYAR - 171044075
* PART - 1
* Implementation of MaxHeap with using array based design
*/

/**Heap class that extends Comparable and implements Iterable*/
public class Heap<E extends Comparable<E>> implements Iterable<E>{
	
	/** for operations of parents and children*/
	private static final int traverse = 2;
	/**array of heap*/
	private E[] heap;
	/**array of temp heap*/
	private E[] heapTemp;
	/**size of the heap*/
	private int size;
	
	/**
	 * Heap constructor with one parameter
	 * @param total_size - to initialize the total size of the heap
	 */
	 @SuppressWarnings("unchecked")
	public Heap(int total_size){
		size = 0;
		heap = (E[])new Comparable<?>[total_size+1];
		heapTemp = (E[])new Comparable<?>[total_size+1];		
	}
	

	/**
	 * Merge with another heap
	 * @param mergedHeap - another heap to merge
	 * @return returns true when merge operation is done
	 */
	public boolean mergeHeap(Heap<E> mergedHeap){
        for(int t=0 ; t < mergedHeap.getSize(); t++){
            add(mergedHeap.get(t));
        }

        return true;
    }


	/**
	 *  @return true or false - if heap is full then it returns true otherwise returns false
	 */
	public boolean isFull(){
		return size == heap.length;
	}
	
	/**
	 * @return true or false - if heap is empty then it returns true otherwise returns false
	 */
	public boolean isEmpty(){
		return size==0;
	}
	
	
	/**
	* @param parent - to find the index it 
	* @return it returns index of the parent
	*/
	private int indexParent(int parent){
		return (parent-1)/traverse;
	}

	/**
	* @param child - to find the index of the child 
	* @param param - helper parameter to find the index of the child properly
	* @return it returns index of the child
	*/
	
	private int indexChild(int child,int param){
		return traverse*child + param;
	}
	


	/**
	* @param ind - to get the element which is in given index  
	* @return specific data which is in given index in the heap
	*/

	private E get(int ind){
        return heap[ind];
    }

	
	/**
	* @return size of the heap
	*/
    public int getSize(){
    	return size;
    }


	/**
	* @param addParam - for adding the element to heap
	*/
	public void add(E addParam){
		if(isFull())
			throw new NoSuchElementException("Heap is completely full !!!");
		heap[size++] = addParam;
		heapUp(size-1);
	}
	
	/**
	 * This method is helper for the "nthLargestRemove" method
	 * @param del - index of the element that we want to delete
	 * @return it returns the element that we deleted
	 */
	public E delete(int del){
		if(isEmpty())
			throw new NoSuchElementException("Heap is already empty!!!");
		E temp = heap[del];
		heap[del] = heap[size -1];
		size--;
		heapDown(del);
		return temp;
	}

	/**
	 *  This method used to protect properly after when we add an element.
	 * @param up - index of the added element in the heap
	 */
	private void heapUp(int up) {
		E keep = heap[up];
		while(up>0 && keep.compareTo(heap[indexParent(up)]) == 1){
			heap[up] = heap[indexParent(up)];
			up = indexParent(up);
		}
		heap[up] = keep;
	}
	
	/**
	 *  This method used to protect properly after when we remove an element.
	 * @param down - index of the removed element in the heap
	 */
	private void heapDown(int down){
		int ch;
		E keep = heap[down];
		while(indexChild(down, 1) < size){
			ch = findMax(down);
			if(keep.compareTo(heap[ch]) < 0){
				heap[down] = heap[ch];
			}else
				break;
			
			down = ch;
		}
		heap[down] = keep;
	}

	
	/**
	 *  This method used to find the child that has maximum value.
	 * @param ind - index 
	 * @return left child or right child, it depends on their size
	 */

	private int findMax(int ind) {
		int lc = indexChild(ind, 1);
		int rc = indexChild(ind, 2);
		
		return heap[lc].compareTo(heap[rc]) == 1 ?lc:rc;
	}
	
	/**
	 * This method prints all of the elements of the heap
	 */
	public void showHeap(){
	        for (int i = 0; i < size; i++)
	            System.out.print(heap[i] +" ");
	        System.out.println();
	    }





    /**
    *This method searches for an element
    * @param item - item that we search
    * @return if item is found then it returns true, otherwise returns false
    */
	 public boolean searchElement(E item){
	 	if(isEmpty())
			 throw new NoSuchElementException("Heap is empty !!!.");
		for(int t = 0; t < heap.length; t++){
			if(heap[t] == item)
				return true;
		}

		return false;
	 }


    /**
    *This method searches for an element
    * @param ind - order of the largest element(ex: 2 -> to remove second largest element from the heap) that we want to remove
    */

  public void nthLargestRemove(int ind){
	  E swp; 
	  int position = 0;
	  System.arraycopy(heap, 0, heapTemp, 0,size);
	  ind++;

	  for(int i=0;i<ind;i++){
	     for(int t=0;t<size-1;t++)
	        {
	          if(heapTemp[t].compareTo(heapTemp[t+1]) == 1){
	            swp = heapTemp[t];
	            heapTemp[t] = heapTemp[t+1];
	            heapTemp[t+1] = swp;
	          }
	        }
	   }

	   for(int t = 0; t < size; t++){
	   	if(heap[t] == heapTemp[size-ind])
	   		position = t;
	   }

	   delete(position);
	}

/**HeapIterator class that implements Iterator class*/
 public class HeapIterator implements Iterator<E>{

   	/**to keep index of next item*/
    private int itemNext;

    /**No parameter constructor for the HeapIterator class*/
    public HeapIterator(){
       itemNext = 0;
    }

    /** @return It returns next elements in the iterator*/

    @SuppressWarnings("unchecked")
    public E next(){
        return heap[itemNext++];
    }
 
    /**@return if iterator has next then it returns true, otherwise returns false*/
    public boolean hasNext() { 
    	if (itemNext < heap.length)
    		return true;
    	else
    		return false;

    }

    /**
	*method to set the value (value passed as parameter) of the last element returned by the next methods.
	*@param item - value for setting
    *@return it returns the value of last element according to pdf instructions
    */
    public E setLast(E item){
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        E last_value = heap[size-1];
        heap[size-1] = item;
        heapUp(size-1);
        return last_value;
        }


    /**remove method is not allowed in this class*/
    public void remove(){
        System.out.println("Remove is not allowed!!!");
    }

 }

    
	/**We can reach the HeapIterator class from the outer classes with the help of this method
	* @return HeapIterator class object
	*/
    @Override
    public HeapIterator iterator() {
        return new HeapIterator();
    }  

}
