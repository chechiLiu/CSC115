//Che-Chi Jack Liu
//V00850558


/*
 * The shell of the class, to be completed as part of CSC115 Assignment 4 : Patient Location.
 */

 
public class BinaryTree<E> {

	/* The root is inherited by any subclass
	 * so it must be protected instead of private.
	 */
	protected TreeNode<E> root;

	/**
	 * Create an empty BinaryTree.
	 */
	public BinaryTree() {
	}

	/**
	 * Create a BinaryTree with a single item.
	 * @param item The only item in the tree.
	 */
	public BinaryTree(E item) {
		root = new TreeNode<E>(item);
	}

	/**
	 * Used only by subclasses and classes in the same package (directory).
	 * @return The root node of the tree.
	 */
	protected TreeNode<E> getRoot() {
		return root;
	}

	//This method returns the height of the tree. If it is a empty tree, it returns 0.
	public int height() {
		int height = findHeight(root);
		return height;
	}
	
	//This method returns true if the tree is empty. False if the tree is not empty.
	public boolean isEmpty() {
		if (root == null) {
			return true;
		}else {
			return false;
		}
	}
	
	//This method removes all the nodes from the tree, making it empty.
	public void makeEmpty() {
		root = null;
	}
 	/******* COMPLETE *******/
	
	//This private helper method is a recursive method, it returns the length of longest downward path from it's root(the height of the tree).
	//It just increments counters as it descends the tree, returning the maximum counter (the counter on the lowest node).
	private int findHeight(TreeNode<E> root) {
		if (root == null) {
			return 0;
		}else {
			return 1 + Math.max(findHeight(root.left), findHeight(root.right));
		}
	
	}

}

	
