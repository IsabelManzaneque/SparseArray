package es.uned.lsi.eped.pract2021_2022;

import es.uned.lsi.eped.DataStructures.*; 


public class SparseArrayBTree<E> extends Collection<E> implements SparseArrayIF<E> {  

	protected BTreeIF<IndexedPair<E>> btree;       
	
	/* Constructor: Creates an empty Sparse Array */
	
	public SparseArrayBTree() {
		this.btree = new BTree<IndexedPair<E>>(); 

	}
	
	
	/* Given a number, returns a stack of its binary representation */
	private StackIF<Boolean> num2bin(int n) {
		Stack<Boolean> salida = new Stack<Boolean>();
		if ( n == 0 ) {
			salida.push(false);
		} else {
			while ( n != 0 ) {
				salida.push((n % 2) == 1);
				n = n / 2;
			}
		}
		return salida;
	}
	
	
	
	/* Returns true if the element is in the collection. */		
	@Override
	public boolean contains(E e) {		
		
		   IteratorIF<IndexedPair<E>> it = btree.iterator(BTreeIF.IteratorModes.INORDER);    
	    		    		
			while(it.hasNext()) {
				IndexedPair<E> next = it.getNext();
				if(next!= null && e.equals(next.getValue())) { 
					return true;					           
				}			   
			}	
	        return false;                                      
		}   
		
	
	/* Empties the collection*/
	@Override
	public void clear() {
		btree.clear();
		size = 0;
	}	
	
	
	/* Stores an element under the index "pos". If there was an element 
     * under that same index, the new elements replaces the previous one
     */
	public void set(int pos, E elem) {          
		                                       
		
		StackIF<Boolean> binStack = num2bin(pos);	   
		BTreeIF<IndexedPair<E>> auxTree = btree;		
		

		while (!binStack.isEmpty()) {	                                
			
			// if the top of the stack is 1, goes down the right
			if (binStack.getTop()) {                                    
	    	   	if (auxTree.getRightChild() == null) {                   
	    	   		auxTree.setRightChild(new BTree<IndexedPair<E>>()); 
	    	   	}
	    	   	auxTree = auxTree.getRightChild();	    	            
	    	// if the top of the stack is 0, goes down the left
			}else{                                                      
	    		if (auxTree.getLeftChild() == null) {                  
	    			auxTree.setLeftChild(new BTree<IndexedPair<E>>());
	    		}
	    		auxTree = auxTree.getLeftChild();                       
	    	}
			binStack.pop();    			                                
		}		
		
		// if there isn't an element stored under pos, increases the size of the array
	    if(auxTree.getRoot() == null) {	size ++;}      
	    auxTree.setRoot(new IndexedPair<>(pos, elem)); 
	 } 


	
	/* Returns the element stored under the index "pos". If there
	 * was no elements stored under that index, returns null.
	 */
	public E get(int pos) {  
		
		IteratorIF<IndexedPair<E>> it = btree.iterator(BTreeIF.IteratorModes.INORDER);            
		
		while(it.hasNext()) {                                                                      
			IndexedPair<E> next = it.getNext();                                               
			if(next!= null && pos == next.getIndex()) {         
				return next.getValue();				            
			}			   
		}	
        return null;                                                     
	}
		
	
    
	/* Deletes the element stored under the index "pos". Also deletes all memory
	 * that was alocated for that element. If there is no element stored under
	 * the index, this function doesnt do any changes to the array's structure
	 */
	public void delete(int pos) {          
		
		BTreeIF<IndexedPair<E>> auxTree = btree;                        
		StackIF<Boolean> binStack = num2bin(pos);	                    
		StackIF<Boolean> pathStack = new Stack<>();                     
		StackIF<BTreeIF<IndexedPair<E>>> treeStack = new Stack<>();     
		
 
	    if (get(pos) == null) {return;}                     	   
	   
	    while (!binStack.isEmpty()) {	     
	        
	    	// if binStack top is 1, goes down the right. If it's 0, down the left
	        if (binStack.getTop()) { auxTree = auxTree.getRightChild();}  
	        else { auxTree = auxTree.getLeftChild();}                             	
	        
	        // saves the visited nodes and followed path 
	        pathStack.push(binStack.getTop());                            
	        treeStack.push(auxTree);                                      
	        binStack.pop();   
	    } 
	    
	    // on treeStack's top is auxTree's father
	    auxTree.setRoot(null);                 
	    treeStack.pop();                       
	       
	    size--;	        
	    
	    // Prunes the potential dead branch, reached following the path described by pathStack
	    while (!treeStack.isEmpty()) {	         		          	
	    	if (auxTree.isEmpty()) {                  
	    		
	    		// prunes the child to the direction in which the deleted node is.
	    		if (pathStack.getTop()) { treeStack.getTop().removeRightChild();} 
			    else { treeStack.getTop().removeLeftChild();} 		              
			} 			        
	    	auxTree = treeStack.getTop();      
	    	treeStack.pop();
	    	pathStack.pop();
	    }
	    	
	 }  
	                              
		                                 

	/* Returns an iterador over the indexes that are being used, in ascending order */
	public IteratorIF<Integer> indexIterator() {
		
		// Breadth-First Traversal returns elements in ascending order
		IteratorIF<IndexedPair<E>> it = btree.iterator(BTreeIF.IteratorModes.BREADTH);  
        QueueIF<Integer> qE = new Queue<>();                                            
	    
        // if the IndexedPair contains an element, its index is inserted in the queue
		while(it.hasNext()) {
			IndexedPair<E> next = it.getNext();
			if(next!= null && next.getValue() != null) {          
				qE.enqueue(next.getIndex());                      
			}			   
		}	
        return qE.iterator();                                      
	}


	
	/* Returns an iterador for the sequence of elements */	
	@Override
	public IteratorIF<E> iterator() {
		
		// Breadth-First Traversal returns elements in ascending order
        IteratorIF<IndexedPair<E>> it = btree.iterator(BTreeIF.IteratorModes.BREADTH);  
		QueueIF<E> qE = new Queue<>();                                                  
	    
		// if the IndexedPair contains an element, it's inserted in the queue
		while(it.hasNext()) {
			IndexedPair<E> next = it.getNext();
			if(next!= null && next.getValue() != null) {         
				qE.enqueue(next.getValue());                      
			}			   
		}	
        return qE.iterator();                                     
	}	

}

