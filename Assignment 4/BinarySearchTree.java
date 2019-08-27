//Che-Chi (Jack) Liu
//V00850558

/*
 * BinarySearchTree is an ordered binary tree, where the element in each node comes after all the elements in the left subtree rooted at that node and before all the elements in the right subtree rooted at that node. 
 * For this assignment, we can assume that there are no elements that are identical in this tree. 
 * A small modification will allow duplicates.
 */

import java.util.ArrayList;

public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {
	// The root is inherited from BinaryTree.
	
	//Create an empty BinarySearchTree.
	public BinarySearchTree() {
		super();
	}
	
	//Finds the item that is equivalent to the key and removes it, if it's in the tree.
	public void delete(E key) {
		root = removeItem(root, key);
	}
	
	//Private helper method for delete() method. It looks for the left most node.
	private E lookLeftEnd(TreeNode<E> node) {
		if(node.left == null) {
			return node.item;
		}else {
			return lookLeftEnd(node.left);
		}
	}
	
	//Private helper method for delete() method. It removes the left most node.
	private TreeNode<E> removeLeftEnd(TreeNode<E> node) {
		if(node.left == null) {
			return node.right;
		}else {
			node.left = removeLeftEnd(node.left);
			return node;
		}
	}

	//Private helper method for delete() method. It removes the item.
	private TreeNode<E> removeItem(TreeNode<E> node, E item) {
		TreeNode<E> newSub;
		E x2 = node.item;
		if(item.compareTo(x2) == 0) {
			node = removeNode(node);
		}else if(item.compareTo(x2) < 0) {
			newSub = removeItem(node.left, item);
			node.left = newSub;
		}else{
			newSub = removeItem(node.right, item);
			node.right = newSub;
		}
		
		return node;
	}
	
	//private helper method for delete() method. It removes the node.
	private TreeNode<E> removeNode(TreeNode<E> node) {
		E addedItem;
		if(node.left == null && node.right == null) {
			return null;
		}else if(node.left == null) {
			return node.right;
		}else if(node.right == null) {
			return node.left;
		}else {
			addedItem = lookLeftEnd(node.right);
			node.item = addedItem;
			node.right = removeLeftEnd(node.right);
			return node;
		}
	}
	
	//Inserts a new item into the tree, maintaining its order. If the item already exists in the tree, nothing happens.
	public void insert(E item) {
		root = addItem(root, item);
	}
	
	//Private helper method for insert() method. It adds item to the tree, if no tree exists, it creates one with the item.
	private TreeNode<E> addItem(TreeNode<E> node, E item) {
		if(node == null) {
			node = new TreeNode<E>(item);
		}else {
			E x2 = node.item;
			if(item.compareTo(x2) <= 0) {
				node.left = addItem(node.left, item);
			}else {
				node.right = addItem(node.right, item);
			}
		}
		
		return node;
	}
	
	//Looks for the item in the tree that is equivalent to the key.
	//It returns the item if it's in the tree, null if it is not.
	public E retrieve(E key) {
		return findItem(root, key);
	}
	
	//Private helper method for retrieve() method.
	private E findItem(TreeNode<E> node, E item) {
		E x1;
		if(node == null) {
			x1 = null;
		}else{
			E x2 = node.item;
			if(item.compareTo(x2) == 0) {
				x1 = node.item;
			}else if(item.compareTo(x2) < 0) {
				x1 = findItem(node.left, item);
			}else {
				x1 = findItem(node.right, item);
			}
		}
		
		return x1;
	}
	
	//Places all the items in the tree into a sorted list.
	public ArrayList<E> inOrder() {
		ArrayList<E> list = new ArrayList<E>();
		collectInOrder(list,root);
		return list;
	}
	
	//This recursive method is traversing the tree in order and placing items into the list.
	private void collectInOrder(ArrayList<E> list, TreeNode<E> node) {
 		if(node != null) {
			collectInOrder(list, node.left);
			list.add(node.item);
			collectInOrder(list, node.right);
		}
	}
	
	public static void main(String[] args) {
		BinarySearchTree<PatientLocation> tree = new BinarySearchTree<PatientLocation>();
		PatientLocation p1 = new PatientLocation("Duck", "Donald", 338);
		PatientLocation p2 = new PatientLocation("Mouse", "Minnie", 116);
		PatientLocation p3 = new PatientLocation("Dog", "Goofie", 422);
		PatientLocation p4 = new PatientLocation("Newman", "Alfred", 607);
		
		//Testing the insert(E item) method.
		tree.insert(p1);
		tree.insert(p4);
		tree.insert(p3);
		tree.insert(p2);
		
		ArrayList<PatientLocation> list  = tree.inOrder();
		System.out.println(list);
		
		//Drawing the tree in its current state:
		DrawableBTree<PatientLocation> dbt = new DrawableBTree<PatientLocation>(tree);
		dbt.showFrame();
		
		//Testing the height() method
		System.out.println("The height of this tree is "+ tree.height());

		//Testing the isEmpty() method
		System.out.println("Is this tree empty? "+ tree.isEmpty());

		//Testing the getroot() method
		//p1 = root = Duck Donald
		System.out.println("The root of this tree should be Duck Donald");
		System.out.println(tree.getRoot().item);

		//Testing the delete() method
		tree.delete(p4);
		System.out.println("The height of this tree after deleting p4 is : "+ tree.height());

		//Testing the retrieve() method
		//p3 is Dog Goofie
		System.out.println("Retrieving p3 Dog Goofie");
		System.out.println(tree.retrieve(p3));

		//Testing the makeEmpty() method.
		tree.makeEmpty();
		
		System.out.println("After calling makeEmpyty is this tree empty? "+tree.isEmpty());
		System.out.println("What is the height of this tree after calling makeEmpyty? "+tree.height());
	}
}
