
/**
 * Implements a node for a binary search tree (BST)
 * Originally for a project in CS 367
 * There will be at least one more file that goes along with this, in time.
 * 
 * @author Adam Eggum
 */
public class BSTnode<K> {
	private K key;
	private BSTnode<K> left, right;
	
	/**
	 * Constructs a BST node with the given key value and whose left and right
	 * children are null.
	 * @param keyValue 
	 */
	public BSTnode(K keyValue) {
		this(keyValue, null, null);
	}
	
	/**
	 * Constructs a BST node with the given key value, left child, and right child. 
	 * @param keyValue the key value
	 * @param leftChild the left child
	 * @param rightChild the right child
	 */
	public BSTnode(K keyValue, BSTnode<K> leftChild, BSTnode<K> rightChild) {
		this.key = keyValue;
		this.left = leftChild;
		this.right = rightChild;
	}
	
	/**
	 * Returns the key value for this BST node.
	 * @return the key value
	 */
	public K getKey() {
		return this.key;
	}
	
	
	/**
	 * Returns the left child for this BST node.
	 * @return the left child
	 */
	public BSTnode<K> getLeft() {
		return this.left;
	}
	
	/**
	 * Returns the right child for this BST node.
	 * @return the right child
	 */
	public BSTnode<K> getRight() {
		return this.right;
	}
	
	
	/**
	 * Changes the key value for this node to the one given
	 * @param key the new key value
	 */
	public void setKey(K key) {
		this.key = key;
	}
	
	
	/**
	 * Changes the left child for this node to the one given
	 * @param newLeft the new left child
	 */
	public void setLeft(BSTnode<K> newLeft) {
		this.left = newLeft;
	}
	
	
	/**
	 * Changes the right child for this node to the one given
	 * @param newRight the new right child
	 */
	public void setRight(BSTnode<K> newRight) {
		
	}
	
	
}	//end BSTnode class





