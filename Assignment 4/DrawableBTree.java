import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * DrawableBTree class is a class created specifically for CSC115 
 * students to visualize an existing BinaryTree structure.
 * The tree can be visualized in a Frame or applet that
 * creates the DrawableBTree, then shows it.
 * To be shown on its own, it needs to be created,
 * and then "showFrame()" called.
 * This will give the drawing its own frame which can
 * then be re-sized and the BinaryTree should stretch
 * or shrink to fit the window.
 *
 * Last modification: 2016-02-27
 * Modified to fit the assignment during the current term.
 * 
 * This java code is open source and may be used as learning tool 
 * and can be shared, but should not be claimed as original work 
 * by anyone using this file.
 * Modifications are encouraged, but should include credit to the
 * original author.
 *
 * @author Bette Bultena
 * @version 5.0.2
 */

//Provided
public class DrawableBTree<E> extends JPanel{

	private static final long serialVersionUID = 16L;
	/**
	 * The approximate diameter, in pixels, of the initial node sizes.
	 * Used when the tree has its own frame.
	 */
	public static final int NODE_START_SIZE = 30;
	/**
	 * The approximate length, in pixels, of the initial edge sizes.
	 * Used when the tree has its own frame.
	 */
	public static final int EDGE_START_SIZE = 20;

	private BinaryTree<E> tree;

	private int size, height;
	private DrawableBTNode<E> root;
	private LinkedList<DrawableBTNode<E>> listing; //used for its iterator

	/**
	 * Does nothing really, except create an
	 * empty JPanel with a preferred size.
	 * Useful only in setting up an empty panel for another
	 * GUI application.
	 * @param canvasSize The preferred size of the initial canvas.
	 */
	public DrawableBTree(Dimension canvasSize) {
		super();
		this.setPreferredSize(canvasSize);
		repaint();
 	}
 
	/**
	 * Generates a Drawn BinaryTree object in a Frame window.
	 * The BinaryTree must have the following method(s):
	 * <ul>
	 * <li>a <tt>getRoot</tt> method that returns the generic TreeNode
	 * 			that is the root of the BinaryTree to be drawn.</li>
	 *</ul>
	 * @param tree The BinaryTree object that is to be drawn.
	 * @param canvasSize The size of the canvas that will show the panel.
	 */
	public DrawableBTree(BinaryTree<E> tree, Dimension canvasSize) {
 	  	super();
 	  	setTree(tree, canvasSize);
		repaint();
	}
 
	/**
	 * Generates a Drawn BinaryTree object in a Frame window.
	 * The BinaryTree must have the following method(s):
	 * <ul>
	 * <li>a <tt>getRoot</tt> method that returns the generic TreeNode
	 * 			that is the root of the BinaryTree to be drawn.</li>
	 *</ul>
	 * The panel size is determined by the number of nodes in the
	 * tree and the height, which is calculated in this class.
	 * @param tree The BinaryTree object that is to be drawn.
	 */
	public DrawableBTree(BinaryTree<E> tree) {
 	  	super();
 	  	setTree(tree);
		int nodeWidth = power2(height-1);
		int canvasWidth = nodeWidth*(NODE_START_SIZE + EDGE_START_SIZE);
		int canvasHeight = height*(NODE_START_SIZE + EDGE_START_SIZE);
		this.setPreferredSize(new Dimension( canvasWidth, canvasHeight));
		repaint();
	}
	
	/**
	 * This method is called by the super class whenever the window
	 * is resized or moved.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawTree(g);
	}
	
	/**
	 * Creates a frame to house the DrawableBTree panel
	 * and renders it.
	 */
	public void showFrame() {
		JFrame frame = new JFrame("Binary Tree");
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setContentPane(this);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void clearTree() {
	  tree = null;
	  repaint();
	}
	
	public void setTree(BinaryTree<E> tree, Dimension canvasSize) {
	  setTree( tree );
	  this.setSize( canvasSize );
	}
	
	public void setTree(BinaryTree<E> tree) {
		this.tree = tree;
		TreeNode<E> top = tree.getRoot();
		if (top != null) {
			root = buildTree(top, 0);
		} else {
			throw new NullPointerException( "Cannot draw an empty tree");
		}
		height = getHeight( root );
		listing = new LinkedList<DrawableBTNode<E>>();
		inorderTraverse( root );
		size = listing.size();
	}
	
	/*
	 * Private method
	 * Calculates the height of the DrawableBTree
	 */
	private int getHeight(DrawableBTNode<E> subRoot) {
		if (subRoot == null) return 0;
		return 1 + Math.max( getHeight( subRoot.left ), getHeight( subRoot.right ));
	}
	
	/*
	 * A private method that orders the nodes "in order".
	 * into the LinkedList "listing".
	 */
	private void inorderTraverse(DrawableBTNode<E> dbtn) {
		if (dbtn == null) {
			return;
		}
		inorderTraverse(dbtn.left);
		listing.addLast(dbtn);
		inorderTraverse(dbtn.right);
	}
	
	/*
	 * A private recursive method that builds the 
	 * drawable BinaryTree.
	 */
	private DrawableBTNode<E> buildTree(TreeNode<E> top, int level) {
		if (top == null) return null;
		return new DrawableBTNode<E>(top.item,
			buildTree(top.left, level+1),
			buildTree(top.right, level+1),
			level);
	}
	
	/*
	 * A private method that actually draws the tree.
	 */
	public void drawTree(Graphics g) {
		g.setColor(Color.green); 
		if (root == null) {
			g.drawString("NOTHING TO DRAW!!!", 20, 20);
		} else {
			Rectangle drawingArea = this.getBounds();
			setPositions(drawingArea);
			Iterator<DrawableBTNode<E>> it = listing.iterator();
			while (it.hasNext()) {
				(it.next()).drawNode(g, true);
			}
		}
	}
	
	/*
	 * A private method that determines the actual pixel
	 * position of each of the nodes in the tree.
	 * Must be called everytime the window changes or
	 * moves.
	 */
	private void setPositions(Rectangle drawingArea) {
		int cornerX = drawingArea.x;
		int cornerY = drawingArea.y;
		int wGrid = drawingArea.width / size;
		int hGrid = drawingArea.height / (height);
		int nodeSize = Math.min( wGrid, hGrid ) / 2;
		int rX, rY;

		Iterator<DrawableBTNode<E>> it = listing.iterator();	
		DrawableBTNode<E> current;
		rX = 0;
		while( it.hasNext() ) {
			current = it.next();
			rY = current.yPos;
			current.setPosition( wGrid*rX+cornerX,
				hGrid*rY+cornerY, nodeSize );
			rX++;
		}
	}
	
	/*
	 * A very handy math/computer method to calculate power of 2
	 * results.
	 */
	private static int power2(int power) {
		if (power < 0) return 0;
		return 1<<power;
	}

	/*
	 * A private inner class.
	 * The DrawableBTNode are the nodes specific to this
	 * Drawable BinaryTree.
	 * The Rectange it extends is used as its bounding box.
	 */
 
	private class DrawableBTNode<E> extends Rectangle {
		private static final long serialVersionUID = 5L;
		private DrawableBTNode<E> left;
		private DrawableBTNode<E> right;
		E element;
		Point midPoint;
		int yPos;
	
		/*
		 * Creates the tree-position properties
		 * of the Node.
		 */
		DrawableBTNode(E element, DrawableBTNode<E> left, DrawableBTNode<E> right,
			int yPos) {
			super();
			this.element = element;
			this.left = left;
			this.right = right;
			this.yPos = yPos;
		}
	
		/*
		 * Sets the actual Rectangular coordinates
		 * of the super class (The Rectangle object).
		 */
		void setPosition(int xCoord, int yCoord, int size) {
			x = xCoord;
			y = yCoord;
			height = size;
			width = size;
			midPoint = new Point( (x+x+size)/2, (y+y+size)/2 );
		}
	
		/*
		 * Draws the node and the edges going to its children.
		 */
		void drawNode(Graphics g, boolean includeObj) {
			g.fillOval(x, y, width, height);
			if (left != null) {
				g.drawLine(midPoint.x, midPoint.y, left.midPoint.x, left.midPoint.y);
			}
			if (right != null) {
				g.drawLine(midPoint.x, midPoint.y, right.midPoint.x, right.midPoint.y);
			}
			if (includeObj) {
				int stringLength = element.toString().length();
				int stringX = x;
				int stringY = y + width/3 + width/3;
				int fontsize;
				Color tmp = g.getColor();
				g.setColor( Color.black );
				if (stringLength > 3) {
					stringX = x;
					stringY = y + width/3 + width/3;
					fontsize = (width-stringLength)/3;
				} else {
					stringX = x+width/3;
					fontsize = width/3;
				}
				stringY = y + width/3 + width/3;
				if (fontsize > 0) {
					g.setFont( new Font( "TimesRoman", Font.BOLD, fontsize ));
					g.drawString( element.toString(), stringX, stringY );
				}
				g.setColor( tmp );
			}
		}
	}
}