public class DblListnode<E> {
	private DblListnode<E> prev;
	private E data;
	private DblListnode<E> next;

	/**
	 * Constructor for a DblListnode.  Takes no parameters, sets all to null. 
	 */
	public DblListnode() {
		this.prev = null;
		this.data = null;
		this.next = null;
	}

	/**
	 * Constructor for a DblListnode. Sets the data of the first item to the 
	 * parameter passed in. 
	 * @param item
	 */
	public DblListnode(E item) {
		this.prev = null;
		this.data = item;
		this.next = null;
	}

	public DblListnode<E> getNext() {
		return this.next;
	}

	public DblListnode<E> getPrev() {
		return this.prev;
	}

	public E getData() {
		return this.data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public void setPrev(DblListnode<E> prev) {
		this.prev = prev;
	}

	public void setNext(DblListnode<E> next) {
		this.next = next;
	}


}

