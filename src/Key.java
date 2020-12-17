//CS2210a 2020
//Jessica Yang
//Student Number: 251083596
//ID: jyang762
//Date: November 5, 2020
public class Key {
	private String name;
	private String kind;
	
	/**
	 * Constructor key will create a key containing the word and type of the key
	 * @param word
	 * @param type
	 */
	public Key(String word, String type) {
		name = word;
		kind = type;
	}
	/**
	 * getName method returns the name of the key
	 * @return
	 */
    public String getName() {
    	return name;
    }
    /**
     * getKind method returns the type of the key
     * @return
     */
    public String getKind() {
    	return kind;
    }
    /**
     * compareTo method will compare two keys in lexicographical order using its name and type
     * @param k
     * @return
     */
    public int compareTo(Key k) {
    	int nameCompare = name.compareTo(k.getName()); //stores the lexicographical value of comparing the name of the two keys
    	int kindCompare = kind.compareTo(k.getKind()); //stores the lexicographical value of comparing the types of the keys
    	
    	if (nameCompare == 0) { //if the names are the same then compare the type
    		if (kindCompare == 0) return 0; 
    		else if(kindCompare >0) return 1; 
    		else return -1;
    	}
    	else { // if names are different then compare the names
    		if (nameCompare>0) return 1;
    		else return -1;
    	}
    }
}
