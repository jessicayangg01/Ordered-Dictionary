//CS2210a 2020
//Jessica Yang
//Student Number: 251083596
//ID: jyang762
//Date: November 5, 2020


public class OrderedDictionary implements OrderedDictionaryADT  {
	
	private BinarySearchTree tree;
	
	/**
	 * Constructor creates a new binary search tree
	 */
	public OrderedDictionary() {
		tree = new BinarySearchTree();
	}
	
	/**
	 * get method uses the binary search tree to get the data item of a given key
	 */
	public DataItem get(Key k) {
		Node getKey = tree.searchNode(k, tree.getRoot());
		if (getKey == null) { // if the key does not exist then return null
			return null;
		}
		return getKey.getData();
	}

	/**
	 * put method will add a data item to the tree
	 */
	public void put(DataItem d) throws DictionaryException {
		Node newNode = new Node(d); // creates a new node to add to the tree
		if (get(d.getKey()) != null) { // searches tree to see if it already contains this dataItem
			throw new DictionaryException("A record with the given key (" + d.getKey().getName() + " ," + d.getKey().getKind() + ") is already in the ordered dictionary.");
		}
		else if(tree.getRoot().getData()==null) tree.setRoot(newNode); // if the tree is empty then set the node as the root
		else {
			tree.put(newNode, tree.getRoot()); // otherwise add the item normally
		}
	}

	/**
	 * remove method will remove a data item from the tree
	 */
	public void remove(Key k) throws DictionaryException {
		Node removeNode = tree.searchNode(k, tree.getRoot()); // searches for the node in the tree
		// throws exception if the node does not exist
		if (removeNode == null) throw new DictionaryException("No record in the ordered dictionary has key (" + k.getName() + " ," + k.getKind() +")");
		else { 
			tree.remove(removeNode);
		}
	}

	/**
	 * successor finds the smallest data item larger than the given key
	 */
	public DataItem successor(Key k) {
		DataItem returnData; // stores the successor data item
		boolean exists = true; // checks if the given key exists yet
		if (tree.searchNode(k, tree.getRoot()) == null) { //adds it if it did not exists before
			exists = false;
			try {
				put(new DataItem(k, ""));
			} catch (DictionaryException e) {}
		}
		Node findK = tree.searchNode(k, tree.getRoot()); // searches for the key in the tree
		if (largest().getKey().compareTo(k) == 0) returnData = null; // if the node is the largest then there are no successors 
		else if (findK.getRightChild().getData()!=null) returnData = findK.getRightChild().getData(); // if it has a right child then that is the successor
		else { // if it doesn't have a right child 
			if (findK.getParent().getData().getKey().compareTo(k) > 0) returnData = findK.getParent().getData(); // if the parent of the key is bigger than the key, then the parent is the successor
			else { // if the parent is not bigger than the key 
				do {
					findK = findK.getParent(); // set the parent as the new node to check
				} while(findK.getParent().getData().getKey().compareTo(k) <= 0); // if the parent is bigger then that is the successor
				returnData = findK.getParent().getData();
			}
		}
		
		if(exists == false) { // removes it if it did not exists before
			try {
				remove(k);
			} catch (DictionaryException e) {}
		}
		return returnData;
	}

	/**
	 * predecessor method will return the largest data item that is smaller than the given key
	 */
	public DataItem predecessor(Key k) {
		DataItem returnData; // stores the predecessor
		boolean exists = true; // checks if the given key already exists
		if (tree.searchNode(k, tree.getRoot()) == null) { //adds it if it did not exists before
			exists = false;
			try {
				put(new DataItem(k, ""));

			} catch (DictionaryException e) {}
		}
		Node findK = tree.searchNode(k, tree.getRoot()); // finds the node in the tree
		if (smallest().getKey().compareTo(k)== 0) returnData = null; //if the node is the smallest in tree then there is no predecessor
		else if (findK.getLeftChild().getData()!=null) { // if the node has a left child then it is the predecessor
			returnData = findK.getLeftChild().getData();
		}
		else { // if there is no left child
			if (findK.getParent().getData().getKey().compareTo(k) < 0) { // if the parent is smaller than the key then the parent is the predecessor
				returnData = findK.getParent().getData(); 
			}
			else { // if the parent is larger than the key
				do { // loops until there is a parent that is smaller than the key
					findK = findK.getParent();
				} while(findK.getParent().getData().getKey().compareTo(k) >= 0);
				returnData = findK.getParent().getData();
			}
		}
		
		if(exists == false) { // removes it if it did not exists before
			try {
				remove(k);
			} catch (DictionaryException e) {}
		}
		return returnData;
		
	}

	/**
	 * smallest method will return the smallest key name in the tree
	 */
	public DataItem smallest() {
		Node smallest = tree.getSmallest(tree.getRoot());
		return smallest.getData();
	}

	/**
	 * largest method will return the largest key name in the tree
	 */
	public DataItem largest() {
		Node largest = tree.getLargest(tree.getRoot());
		return largest.getData();
	}
	


}
