package avl;

import cle.Cle;

public class AVL { 
	
	class Node { 
		private Cle key;
		private int height; 
		private Node left, right; 
		
		Node(Cle d) { 
			key = d; 
			height = 1; 
		} 
	}
	
	private Node racine=null;

	public AVL() {}

	private Node rightRotate(Node y) { 
		Node x = y.left; 
		Node T2 = x.right; 

		// Perform rotation 
		x.right = y; 
		y.left = T2; 

		// Update heights 
		y.height = max(height(y.left), height(y.right)) + 1; 
		x.height = max(height(x.left), height(x.right)) + 1; 

		// Return new root 
		return x; 
	} 

	private Node leftRotate(Node x) { 
		Node y = x.right; 
		Node T2 = y.left; 

		// Perform rotation 
		y.left = x; 
		x.right = T2; 

		// Update heights 
		x.height = max(height(x.left), height(x.right)) + 1; 
		y.height = max(height(y.left), height(y.right)) + 1; 

		// Return new root 
		return y; 
	} 

	public Node insert(Node node, Cle key) { 

		/* 1. Perform the normal BST insertion */
		if (node == null) 
			return (new Node(key)); 

		if (key.inf(node.key)) 
			node.left = insert(node.left, key); 
		else if (node.key.inf(key)) 
			node.right = insert(node.right, key); 
		else // Duplicate keys not allowed 
			return node; 

		/* 2. Update height of this ancestor node */
		node.height = 1 + max(height(node.left), 
							height(node.right)); 

		/* 3. Get the balance factor of this ancestor 
			node to check whether this node became 
			unbalanced */
		int balance = getBalance(node); 

		// If this node becomes unbalanced, then there 
		// are 4 cases Left Left Case 
		if (balance > 1 && key.inf(node.left.key)) 
			return rightRotate(node); 

		// Right Right Case 
		if (balance < -1 && node.right.key.inf(key))
			return leftRotate(node); 

		// Left Right Case 
		if (balance > 1 && node.left.key.inf(key)) { 
			node.left = leftRotate(node.left); 
			return rightRotate(node); 
		} 

		// Right Left Case 
		if (balance < -1 && key.inf(node.right.key)) { 
			node.right = rightRotate(node.right); 
			return leftRotate(node); 
		} 

		/* return the (unchanged) node pointer */
		return node; 
	} 


	public static int height(Node N) { 
		if (N == null) 
			return 0; 

		return N.height; 
	} 

	public static int max(int a, int b) { 
		return (a > b) ? a : b; 
	} 
	
	public static int getBalance(Node N) { 
		if (N == null) 
			return 0; 

		return AVL.height(N.left) - height(N.right); 
	} 
	
	public static void preOrder(Node node) { 
		if (node != null) { 
			System.out.print(node.key + " "); 
			AVL.preOrder(node.left); 
			AVL.preOrder(node.right); 
		} 
	} 
	
	public Node getRacine() {
		return racine;
	}

	public static void main(String[] args) { 
		AVL tree = new AVL(); 

		/* Constructing tree given in the above figure */
		tree.racine = tree.insert(tree.racine, new Cle(0,0,0,1)); 
		tree.racine = tree.insert(tree.racine, new Cle(1,0,0,1)); 
		tree.racine = tree.insert(tree.racine, new Cle(0,1,0,1)); 
		tree.racine = tree.insert(tree.racine, new Cle(0,0,1,1)); 
		tree.racine = tree.insert(tree.racine, new Cle(0,1,1,1)); 
		tree.racine = tree.insert(tree.racine, new Cle(1,1,1,1)); 

		AVL.preOrder(tree.racine); 
	} 
} 
//This code has been contributed by Mayank Jaiswal 
