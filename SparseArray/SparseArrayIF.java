package es.uned.lsi.eped.pract2021_2022;

import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.SequenceIF;

/* Sparse array in which the elements are indexed under an integer value and 
 * no memory is alocated for an element if it's index is not being used
 */
public interface SparseArrayIF<E> extends SequenceIF<E> {

    /* Stores an element under the index "pos". If there was an element 
     * under that same index, the new elements replaces the previous one
     */
	public void set(int pos,E elem);
	
	/* Returns the element stored under the index "pos". If there
	 * was no elements stored under that index, returns null.
	 */
	public E get(int pos);
	
	/* Deletes the element stored under the index "pos". Also deletes all memory
	 * that was alocated for that element. If there is no element stored under
	 * the index, this function doesnt do any changes to the array's structure
	 */
	public void delete(int pos);
	
	/* Returns an iterador over the indexes that are being used, in ascending order */
	public IteratorIF<Integer> indexIterator();
}
