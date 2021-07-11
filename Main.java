public class Main{


    /**TEST - DRIVER FUNCTION*/
    public static void main(String[] args)
    {

        System.out.println("\nHEAP(MaxHeap) TEST");
         
        Heap<Integer> heapTest = new Heap<Integer>(50);
        Heap<Integer> heapTest2 = new Heap<Integer>(50);
    
        //for Heap-1
        heapTest.add(34);
        heapTest.add(58);
        heapTest.add(11);
        heapTest.add(8);
        heapTest.add(14);
        heapTest.add(13);
        heapTest.add(1);
        heapTest.add(192);     
        //for Heap-2
        heapTest2.add(111);
        heapTest2.add(73);
        heapTest2.add(81);
        heapTest2.add(3);
        heapTest2.add(19);
        heapTest2.add(17);
        heapTest2.add(33);


        System.out.println("\nHeap:");
        heapTest.showHeap();
        System.out.println("\nTEST - Search for an element (99)");
        System.out.println(heapTest.searchElement(99));
         
        System.out.println("\nTEST - Search for an element (13)");
        System.out.println(heapTest.searchElement(13));
         
        System.out.println("\nTEST - Merge with another heap");
        System.out.println("\nHeap-1: ");
        heapTest.showHeap();
        System.out.println("\nHeap-2: ");
        heapTest2.showHeap();
        System.out.println("\nMerged Heap: ");
        heapTest.mergeHeap(heapTest2);
        heapTest.showHeap();


        System.out.println("\nAfter removing the 0. index largest element");
        heapTest.nthLargestRemove(0);
        heapTest.showHeap();
        System.out.println("\nAfter removing the 3. index largest element");
        heapTest.nthLargestRemove(3);
        heapTest.showHeap();
        Heap<Integer>.HeapIterator it = heapTest.iterator();
        System.out.println("TEST-Iterator's next() method (  it.next()  )");
        System.out.println(it.next());
        System.out.println("TEST-Iterator's next() method (  it.next()  )");
        System.out.println(it.next());

        System.out.println("\nAfter 'setLast(313)'  (extended iterator's method)");
        System.out.println(it.setLast(313));
        heapTest.showHeap();
        System.out.println("\nAfter 'setLast(41)'  (extended iterator's method)");
        System.out.println(it.setLast(41));
        heapTest.showHeap();

        System.out.println("\n*****************************************************\n");

        System.out.println("BSTHeapTree TEST\n\n");
		BSTHeapTree<Integer> tree = new BSTHeapTree<Integer>();
		
		int[] array = tree.getRandomNumbers(3000, 0, 5000);
		
		for(int a : array)
		{
		    tree.add(a);
		}
		
		tree.sort(array);
		
		int[] subarr = new int[100];
		
		int j = 0;
		
		for(int i = 0; i < 100; i++)
		{
            if(i == 0)
                subarr[i] = array[j++];
            else
            {
                while(subarr[i-1] == array[j++]);
                subarr[i] = array[j++];
            }
        }

        
        System.out.println("find mode:"+tree.find_mode()+"\n");
        
        System.out.println("Searching 100 numbers in the array\n");
        for(int num : subarr)
        {
            System.out.println("Number "+num+" occurrences "+tree.find(num));
        }
        
        System.out.println("\nSearching 10 numbers not in the array\n");
        for(int num = 5000; num < 5100; num+=10)  
        {
            System.out.println("Number "+num+" occurrences "+tree.find(num));
        }
        
        System.out.println("After removing 100 numbers in the array\n");
        for(int num : subarr)
        {
            System.out.println("Number "+num+" occurrences "+tree.remove(num));
        }
        
        System.out.println("\nAfter removing 10 numbers not in the array\n");
        for(int num = 5000; num < 5100; num+=10)  
        {
            System.out.println("Number "+num+" occurrences "+tree.remove(num));
        }
	}

}