//CS2210a 2020
//Jessica Yang
//Student Number: 251083596
//ID: jyang762
//Date: November 5, 2020

public class BinarySearchTree {
	
	Node root;

	/**
	 * Constructor method creates an emprt tree with a null root
	 */
	public BinarySearchTree() {
		root = new Node();
	}

	/**
	 * setRoot sets the root as the given node
	 * @param root
	 */
	public void setRoot(Node root) {
		this.root = root;
	}

	/**
	 * searchNode method recursively searches the tree for the node of the given key and 'root' from where you want to start searching
	 * @param k
	 * @param root
	 * @return
	 */
	public Node searchNode (Key k, Node root) {
		if (root.getData() == null) return null; // if the tree is empty then return null
		if (root.getData().getKey().compareTo(k)== 0) { //if the key is found then return the current node that you are on
			return root;
		}
		else if (isLeaf(root)) { // if the end of the tree is reached then return null
			return null;
		}
		else if (root.getData().getKey().compareTo(k)> 0) return searchNode(k, root.getLeftChild()); // if the key is smaller than the current node then find the node starting from the left child using recursion
		else return searchNode(k, root.getRightChild()); // if the current node is smaller than the key then check the right side for the key
	}

	/**
	 * getSmallest method will search the tree starting from the node root for the smallest key name and type
	 * @param root
	 * @return
	 */
	public Node getSmallest(Node root) {
		if (root.getLeftChild().getData()== null) return root; // if there is no left child then the current root node is the smallest
		else {
			root = getSmallest(root.getLeftChild()); // if there is a left child then continue searching for the smallest node
		}
		return root;
	}

	/**
	 * getLargest method will search the tree for the largest node
	 * @param root
	 * @return
	 */
	public Node getLargest(Node root) {
		if (root.getRightChild().getData() == null) return root; // if there is no right child then the current root node is the largest
		else {
			root = getLargest(root.getRightChild()); // if there is a right child then continue searching for the largest
		}
		return root;
	}

	/**
	 * put method will add a node to the tree
	 * @param newNode
	 * @param root
	 */
	public void put(Node newNode, Node root) {
		if (isLeaf(root)) { // if the current root node is a leaf, then add the new node as a child
			if(newNode.getData().getKey().compareTo(root.getData().getKey())<0) root.setLeftChild(newNode); // if the new node is smaller than the current node then set it as the left child
			else root.setRightChild(newNode); // if bigger, then set it as the right child
			newNode.setParent(root);
		}
		else if (newNode.getData().getKey().compareTo(root.getData().getKey())<0) { // if the current root node is not a leaf and the key is smaller
			if (root.getLeftChild().getData()== null) { // if there isn't any left child yet then set it as the left child
				root.setLeftChild(newNode);
				newNode.setParent(root);
			}
			else put(newNode, root.getLeftChild()); // if there is already a node there then checks the left child by calling the put method using the left child as the root
		}
		else { // if the key is larger than the current root
			if (root.getRightChild().getData()== null) {// if there isn't any right child yet then set the new node as the right child
				root.setRightChild(newNode);
				newNode.setParent(root);
			}
			else put(newNode, root.getRightChild()); // if there is then call the method again with the right child as the new root
		}
	}

	/**
	 * remove function will remove the given node from the tree
	 * @param removeNode
	 */
	public void remove(Node removeNode) {
		if(removeNode.getParent()==null) {// if the root is being removed
			if(removeNode.getRightChild().getData()!= null) { // if there is a right child then set that as the new root
				root = getSmallest(removeNode.getRightChild());
				root.setParent(null);
				root.setLeftChild(removeNode.getLeftChild());
				root.setRightChild(removeNode.getRightChild());
			}
			else if(removeNode.getLeftChild().getData()!= null) { // otherwise set the left child as the new root
				root = getLargest(removeNode.getLeftChild());
				root.setParent(null);
				root.setLeftChild(removeNode.getLeftChild());
				root.setRightChild(removeNode.getRightChild());
			}
			else root = new Node();
		}
		// if the node to be removed has 2 children
		else if (removeNode.getRightChild().getData() !=null && removeNode.getLeftChild().getData()!=null) {
			Node replacement = getSmallest(removeNode.getRightChild()); // replace its position with the successor
			replacement.getParent().setLeftChild(new Node(null));
			replacement.setParent(removeNode.getParent());
			replacement.setLeftChild(removeNode.getLeftChild());
			replacement.setRightChild(removeNode.getRightChild());
			// if the node to be removed was the right child, then set the replacement as the right child
			if (removeNode.getData().getKey().compareTo(removeNode.getParent().getData().getKey())>0) removeNode.getParent().setRightChild(replacement);
			// if the node to be removed was the left child, then set the replacement as the left child
			else removeNode.getParent().setLeftChild(replacement);
		}
		else if(removeNode.getRightChild().getData()!=null) { // if the node to be removed does not have a right child
			// replace its position with its left child
			removeNode.getRightChild().setParent(removeNode.getParent()); 
			removeNode.getParent().setRightChild(removeNode.getRightChild());
		}
		else if(removeNode.getLeftChild().getData()!=null){ // if the node to be removed does not have a left child
			// replace its position with its right child
			removeNode.getLeftChild().setParent(removeNode.getParent());
			removeNode.getParent().setRightChild(removeNode.getLeftChild());
		}
		else if (isLeaf(removeNode)) { // if the node to be removed was a leaf (no children)
			// checks if the node was a left or right child of its parent and remove that child from the parent
			if(removeNode.getData().getKey().compareTo(removeNode.getParent().getData().getKey())<0) { 
				removeNode.getParent().setLeftChild(new Node(null));
			}
			else removeNode.getParent().setRightChild(new Node(null));
			removeNode.setParent(null);
		}

	}

	/**
	 * isLeaf will check if a node is a leaf or not
	 * @param node
	 * @return
	 */
	private boolean isLeaf(Node node) {
		return (node.getLeftChild().getData()==null && node.getRightChild().getData()==null);
	}

	/**
	 * getRoot method will return the root of the tree
	 * @return
	 */
	public Node getRoot(){
		return root; 
	}
	
	

}
