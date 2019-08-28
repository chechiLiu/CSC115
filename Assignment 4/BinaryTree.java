//Che-Chi (Jack) Liu
//V00850558

/*
 * BinaryTree is a basic generic BinaryTree data structure. 
 * It is referenced based, using TreeNodes to connect the tree. 
 * It contains any element determined by the placeholder E.
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
		root = null;
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
		if(root == null) {
			return true;
		}else {
			return false;
		}
	}
	
	//This method removes all the nodes from the tree, making it empty.
	public void makeEmpty() {
		root = null;
	}
	
	//This private helper method is a recursive method, it returns the length of longest downward path from its root(the height of the tree).
	//It increments the counter as it descends the tree, returning the maximum counter.
	private int findHeight(TreeNode<E> root) {
		if(root == null) {
			return 0;
		}else {
			return 1 + Math.max(findHeight(root.left), findHeight(root.right));
		}
	}
}
