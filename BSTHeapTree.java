import java.util.Random;


/* CAN DUYAR - 171044075 
* PART - 2
* BSTHeapTree class that extends Comparable class
*/

//
public class BSTHeapTree <E extends Comparable<E>>{

    //Node class to keep information
    static class Node<E extends Comparable<E>>
    {
        /** data for each node*/
        public E data;
        /**to count number of data in BSTHeapTree*/  
        public int count;
       
        /**
        *@param init  - to initialize data for the node
        */

        public Node(E init)
        {
            data = init;
            count = 1;
        }
    }

    /**maximum number of elements included in a node is 7*/
        public final int   root = 0,
                            sNode = 1,
                            ssNode = 2,
                            sbNode = 3,
                            bNode = 4,
                            bsNode = 5,
                            bbNode = 6;
        
        /**To keep nodes*/
         @SuppressWarnings("unchecked")
        public Node<E>[] nodes = new Node[7]; 
        /**BSTHeapTree's left and right elements to process it*/
        public BSTHeapTree<E> left, right;

        /**No parameter constructor*/
        public BSTHeapTree(){}
        
        
        /**
        *@param init - constructor with one parameter to initialize array of nodes 
        */
        
        public BSTHeapTree(E init)
        {
            nodes[root] = new Node<E>(init);
        }
       

        /**
        *This method returns the number of occurences 
        *@param i - data to add for a node
        *@param index - index of the node 
        *@return occurrences
        */
        public int addingData(E i, int index)
        {
            if(nodes[index] == null)
            {
                nodes[index] = new Node<E>(i);
                return 1;
            }
            else if(i == nodes[index].data)
            {
                nodes[index].count++;
                return nodes[index].count;  // it returns the number of occurences
            }
            else if(index == ssNode || index == bsNode || index == bbNode || index == sbNode)
                return 0;
            else if(i.compareTo(nodes[index].data)==0)
            {
                return addingData(i, getSmall(index));
            }
            else 
            {
                return addingData(i, getBig(index));
            }
        }

        /**
        * This method returns the index of the small node
        *@param Node i
        *@return next Node index
        */
        public int getSmall(int i)
        {
            if(i == root)
                return sNode;
            else if(i == sNode)
                return ssNode;
            else if(i == bNode)
                return bsNode;
            else return 0;
        }


        /**
        * This method returns the index of the big node
        *@param Node i
        *@return next Node index
        */
        
        public int getBig(int i)
        {
            if(i == root)
                return bNode;
            else if(i == bNode)
                return bbNode;
            else if(i == sNode)
                return sbNode;
            else return 0;
        }

        /**
        *@return true if it is empty else returns false
        */
        public boolean isEmpty()
        {
            boolean val = nodes[root] == null;
            boolean val2 = left == null ? true : left.isEmpty();
            boolean val3 = right == null ? true : right.isEmpty();
            
            return val && val2 && val3;
        }
         /**
        *@return c - number of total Nodes
        */
        public int getNumberOfNodes()
        {
            int c = 0;
            for(Node<E> n : nodes)
                if(n != null)
                    c++;
            
            if(left != null)
                c += left.getNumberOfNodes();
            if(right != null)
                c += right.getNumberOfNodes();
                
            return c;
        }
        /**
        * @return array - all Nodes
        */
         @SuppressWarnings("unchecked")
        public Node<E>[] getArrayOfNodes()
        {
            int size = getNumberOfNodes();
            
            if(size == 0)
                return null;
                
            Node<E>[] array = new Node[size];
            int i = 0;
            
            for(Node<E> n : nodes)
                if(n != null)
                    array[i++] = n;
            
            if(left != null)
            {
                Node<E>[] array2 = left.getArrayOfNodes();
                for(Node<E> n : array2)
                array[i++] = n;
            }
            if(right != null)
            {
                Node<E>[] array2 = right.getArrayOfNodes();
                for(Node<E> n : array2)
                array[i++] = n;
            }
            return array;
        }
        
        /**
        *@return mode - array of highest occurrence and its value
        */
        
        public int[] get_mode()
        {
            int[] modeArr = {0, 0};
            for(Node<E> n : nodes)
                if(n != null && modeArr[0] < n.count)
                {
                    modeArr[0] = n.count;
                    modeArr[1] = (Integer)n.data;
                }
            
            if(left != null)
            {
                int[] result = left.get_mode();
                if(modeArr[0] < result[0])
                {
                    modeArr[0] = result[0];
                    modeArr[1] = result[1];
                }
            }
            if(right != null)
            {
                int[] result = right.get_mode();
                if(modeArr[0] < result[0])
                {
                    modeArr[0] = result[0];
                    modeArr[1] = result[1];
                }
            }
            
            return modeArr;
        }
    
    

        /**
        *@param item - data to add
        *@return result - occurrence
        */
        @SuppressWarnings("unchecked")
        public int add(E item)
        {
            int result = addingData(item, root);
            if(result > 0)
                return result;
            else
            {
                if(item.compareTo(nodes[root].data)==-1)
                {
                    if(left == null)
                    {
                        left = new BSTHeapTree<E>(item);
                        return 1;
                    }
                    else
                    {
                        return left.add(item);
                    }
                }
                else
                {
                    if(right == null)
                    {
                        right = new BSTHeapTree<E>(item);
                        return 1;
                    }
                    else
                    {
                        return right.add(item);
                    }
                }
            }
        }
        
        /**
        *@param i - index of the element that we want to remove
        *@return occurrence
        */
        @SuppressWarnings("unchecked")
        public int remove(E i)
        {

            for(int j = 0; j < 7; j++)
                if(nodes[j] != null)
                    if(i == nodes[j].data)
                        if(nodes[j].count == 1)
                        {
                            nodes[j] = null;
                            return 0;
                        }
                        else
                        {
                            nodes[j].count--;
                            return nodes[j].count;
                        }
            
            if(left != null)
            {
                int removed_result = left.remove(i);
                if(removed_result != 0)
                {
                    if(left.isEmpty())
                        left = null;
                    return removed_result;
                }
            }
            if(right != null)
            {
                int removed_result = right.remove(i);
                if(removed_result != 0)
                {
                    if(right.isEmpty())
                        right = null;
                    return removed_result;
                }
            }
            
            return 0;
        }
        
        /**
        *@param i - data that we want to find
        *@return occurrence
        */
        @SuppressWarnings("unchecked")
        public int find(E i)
        {
            int result = 0;
            for(Node<E> n : nodes)
                if(n != null)
                    if(i == n.data)
                        result = n.count;
            
            if(left != null && result == 0)
                result = left.find(i);
            if(right != null && result == 0)
                result = right.find(i);
            
            return result;
        }
 
        /**
        * this method returns the element that occurs most frequently
        *@return mode of the BSTHeapTree
        */
        public int find_mode()
        {
            int[] result = get_mode();
            return result[1];
        }
    
	/**
    *@param array - array of int to sort the elements
    */

	public  void sort(int[] array)
	{
	    for(int i = 0; i <= array.length - 1; i++)
        {
            for(int t = 0; t <= array.length - 2; t++)
            {
                if(array[t] > array[t + 1])
                {
                    int keep = array[t];
                    array[t] = array[t + 1];
                    array[t + 1] = keep;
                }
            }
        }
    }

    /**
    * It prints the all elements of the array
    *@param arr - array of int  
    */
    public void show(int[] arr){
    	for(int t = 0; t < arr.length; t++)
    		System.out.print(arr[t] + " ");

    	System.out.println("\n");
    }

	/**
    *@param size - total random numbers
    *@param minimum
    *@param maximum
    *@return array - array of random numbers
    */
	public int[] getRandomNumbers(int size, int min, int max)
	{
        Random r = new Random();
        int[] array = new int[size];
        
        for(int i = 0; i < size; i++)
        {
            array[i] = r.nextInt(max - min) + min;
        }
        
        return array;
    }
}
