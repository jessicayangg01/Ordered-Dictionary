//CS2210a 2020
//Jessica Yang
//Student Number: 251083596
//ID: jyang762
//Date: November 5, 2020

public class DataItem {
	private Key theKey;
	private String content;
	
	/**
	 * Constructor method creates a data item that stores the given key and data
	 * @param k
	 * @param data
	 */
	public DataItem(Key k, String data) {
		theKey = k;
		content = data;
	}
	
	/**
	 * getKey returns the key
	 * @return
	 */
	public Key getKey() {
		return theKey;
	}
	
	/**
	 * getContent returns the data
	 * @return
	 */
	public String getContent() {
		return content;
	}
}
