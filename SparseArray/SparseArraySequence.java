package es.uned.lsi.eped.pract2021_2022;
import es.uned.lsi.eped.DataStructures.*;



public class SparseArraySequence<E>  extends Collection<E>  implements SparseArrayIF<E> {

	
	protected ListIF<IndexedPair<E>> sequence;                                                            
	
	/* Constructor: Creates an empty Sparse Array */
	
	public SparseArraySequence() {
		sequence = new List<IndexedPair<E>>();    
	}		

		
	/* Returns true if the element is in the collection. */	
	@Override
	public boolean contains(E e) {  		
		
		IteratorIF<IndexedPair<E>> it = sequence.iterator();  
		while(it.hasNext()){                                  
			if(e.equals(it.getNext().getValue())){        
				return true;                              
			}
		}
		return false;		                              
	}
	
	
	/* Empties the collection*/
	@Override
	public void clear() {
		sequence.clear();
		size = 0;
	}	
	
	
	/* Stores an element under the index "pos". If there was an element 
     * under that same index, the new elements replaces the previous one
     */
	public void set(int pos, E elem) {  	
		
		IteratorIF<IndexedPair<E>> it = sequence.iterator();
		int posicionInsertar = 1;				              
			
			while(it.hasNext()) {
				IndexedPair<E> e = it.getNext();
				if(e.getIndex() == pos) {e.setValue(elem);}	  
				if(e.getIndex() < pos) 	{posicionInsertar++;} 				
			}	
			
			// if there is no element of index pos, insert indexedPair and 
			// increse the array's size
			
			if (get(pos) == null) {                                                 
				sequence.insert(posicionInsertar, new IndexedPair<>(pos, elem));     
				size ++;			                                                 
		    }	                                                                        
	}	 
	
	
	/* Returns the element stored under the index "pos". If there
	 * was no elements stored under that index, returns null.
	 */
	public E get(int pos) {		

		IteratorIF<IndexedPair<E>> it = sequence.iterator();  
		while(it.hasNext()){
			IndexedPair<E> next = it.getNext();
			if(next.getIndex() == pos){               
				return next.getValue();               
			}
		}
		return null;			                      
	}

	
	/* Deletes the element stored under the index "pos". Also deletes all memory
	 * that was allocated for that element. If there is no element stored under
	 * the index, this function doesnt do any changes to the array's structure
	 */
	public void delete(int pos) {	
		
		// first check if there is an element of index pos
		if (get(pos) != null) {	                                  
			
			// then find the actual position of the IndexedPair in the list
			IteratorIF<IndexedPair<E>> it = sequence.iterator();
			int posicionBorrar = 1;				                  
				
			while(it.hasNext()) {
				IndexedPair<E> e = it.getNext();
				if(e.getIndex() == pos) {break;}	              
				posicionBorrar ++;								  
			}	
			sequence.remove(posicionBorrar);                      
			size --;                                              
		}	 
	}

	
	/* Returns an iterador over the indexes that are being used, in ascending order */
	public IteratorIF<Integer> indexIterator() {
    	
    	
    	IteratorIF<IndexedPair<E>> iterador = sequence.iterator();          
    	QueueIF<Integer> q = new Queue<>();                        
    	
    	// if the IndexedPair contains an element, its index is inserted in the queue
    	while (iterador.hasNext()) {                               		
    		IndexedPair<E> next = iterador.getNext();    
    		if(next.getValue() != null) {                          
    			q.enqueue(next.getIndex());                               
    		}
    	}
    	 
    	return q.iterator();                                       
    }
	
	
	
	/* Returns an iterador for the sequence of elements */	
	@Override
    public IteratorIF<E> iterator() {   
    	
    	IteratorIF<IndexedPair<E>> iterador = sequence.iterator();   
    	QueueIF<E> q = new Queue<>();                         
    	
    	// if the IndexedPair contains an element, it's inserted in the queue
    	while (iterador.hasNext()) {                                       		
    		E next = iterador.getNext().getValue();   
    		if(next != null) {                                    
    			q.enqueue(next);                                 
    		}
    	}    	
    	return q.iterator();                                         	    	
    }	

}

       
