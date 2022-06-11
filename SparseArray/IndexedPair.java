package es.uned.lsi.eped.pract2021_2022;

/* Indexed pair formed by an index and a value */
public class IndexedPair<E> {

	private int index;
	private E value;
	
	/* Constructor */
	public IndexedPair(int index,E value) {
		this.index = index;
		this.value = value;
	}
	
	/* Gets the IndexedPair's index */
	public int getIndex() {
		return this.index;
	}
	
	/* Gets the IndexedPair's value */
	public E getValue() {
		return this.value;
	}
	
	/* Sets the IndexedPair's value */
	public void setValue(E value) {
		this.value = value;
	}
	
}
