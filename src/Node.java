//CS2210a 2020
//Jessica Yang
//Student Number: 251083596
//ID: jyang762
//Date: November 5, 2020

public class Node {
	private Node leftChild;
	private Node rightChild;
	private Node parent;
	private DataItem data;
	
	/**
	 * Constructor method will create an internal node with the given data item
	 * @param data
	 */
	public Node(DataItem data) {
		this.data = data;
		leftChild = new Node();
		rightChild = new Node();
		parent = null;
	}
	/**
	 * Constructor method will create a leaf node with no data
	 */
	public Node() {
		data = null;
		leftChild = null;
		rightChild = null;
		parent = null;
	}
	/**
	 * setLeftChild will set the left child of the node to the given node
	 * @param leftChild
	 */
	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}
	/**
	 * setRightCild will set the right child of the node to the given node
	 * @param rightChild
	 */
	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}
	/**
	 * setParent will set the parent of the node to the given node
	 * @param parent
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}
	/**
	 * getLeftChild returns the left child of the node
	 * @return
	 */
	public Node getLeftChild() {
		return leftChild;
	}
	/**
	 * getRight returns the right child of the node
	 * @return
	 */
	public Node getRightChild() {
		return rightChild;
	}
	/**
	 * getParent returns the parent of the node
	 * @return
	 */
	public Node getParent() {
		return parent;
	}
	/**
	 * getData will return the data of the node
	 * @return
	 */
	public DataItem getData() {
		return data;
	}
}
