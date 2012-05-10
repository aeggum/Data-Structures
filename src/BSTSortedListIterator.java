import java.util.Iterator;
import java.util.*;

public class BSTSortedListIterator<K> implements Iterator<K> {
    private Stack<K> stack;
    private BSTnode<K> root;

    public BSTSortedListIterator(BSTnode<K> root) {
	this.root = root;
	stack = new Stack<K>();
	stack = reverseOrder(root);
    }

    private Stack<K> reverseOrder(BSTnode<K> node) {
	if (node == null) 
	    return stack;
	reverseOrder(node.getRight());
	stack.push(node.getKey());
	reverseOrder(node.getLeft());
	return stack;
    }

    public boolean hasNext() {
	return !stack.empty();
    }

    public K next() {
	if (stack.empty()) 
	    throw new NoSuchElementException();
	else
	    return stack.pop();
    }

    public void remove() {
	throw new UnsupportedOperationException();
    }
    
}
