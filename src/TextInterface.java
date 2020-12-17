//CS2210a 2020
//Jessica Yang
//Student Number: 251083596
//ID: jyang762
//Date: November 5, 2020


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextInterface {
	
	// variables needed
	private static OrderedDictionary dictionary;
	private static final String SOUND = "sound";
	private static final String PICTURE = "picture";
	private static final String URL = "url";
	private static final String PROGRAM = "program";
	private static final String DEFINITION = "definition";
	private static final String[] TYPES = {SOUND, PICTURE, URL, PROGRAM, DEFINITION};

	public static void main(String[] args) throws IOException, DictionaryException {
		dictionary = new OrderedDictionary();
		
		// reads the file and stores it into the dictionary
		BufferedReader in = new BufferedReader(new FileReader(args[0]));
		String word = in.readLine(); // gets the first line which is the key name
		String definition;
		String type;
		try {
			while (word != null) { // reads file until the end
				definition = in.readLine(); // gets the definition
				type = getType(definition); // gets the type of file for the key
				dictionary.put(new DataItem(new Key(word,type),definition)); // adds it to dictionary
				word = in.readLine(); // gets next key name
			}
		} catch (IOException e) {
			System.out.println("Cannot open file: " + args[0]);
		} catch (DictionaryException e) {}

		//user input
		StringReader keyboard = new StringReader();
		String line = ""; //stores the user input
		String[] words; // stores the user input broken up into words
		while (!line.equals("end")){ // if user types end then stops the function
			line = keyboard.read("Enter next command: ");
			if (line.equals("end")) return;  // if end then quit program
			words = line.split(" "); // separates the input into words
			
			// all possible user input functions
			try {
				switch (words[0]) { // based on the first word (user command) it will perform the following functions
				case "get": getMethod(words[1]); break;
				case "remove": removeMethod(new Key(words[1], words[2])); break;
				case "add": addMethod(new DataItem(new Key(words[1], getType(words[2])), words[3])); break;
				case "list": listMethod(words[1]); break;
				case "first": first(); break;
				case "last": last(); break;
				default : System.out.println("Invalid Entry."); //otherwise it is an invalid entry
				}
			} catch (ArrayIndexOutOfBoundsException e) { //catches if the user did input one of the cases, but not the correct amount of arguments
				System.out.println("Invalid Entry."); 
			}
		}

	}

	/**
	 * getMethod will get the given key name from the ordered dictionary and perform its dedicated function
	 * @param name
	 */
	private static void getMethod(String name){
		boolean exists = false; // checks if the key exists
		try {
			for(int i = 0; i<TYPES.length; i++) { // goes through each type and see if there exists the key name with the type
				DataItem get = dictionary.get(new Key (name, TYPES[i]));
				if (get!=null) { // if the key name, type pairing exists then performs its function
					exists = true;
					switch(i) {
					case 0:{ // case sound (.wav or .mid file)
						SoundPlayer sound = new SoundPlayer();
						sound.play(get.getContent());
						break;
					}
					case 1:{ // case picture (.gif or .jpg file)
						PictureViewer picture = new PictureViewer();
						picture.show(get.getContent());
						break;
					}
					case 2:{ // case website (.html file)
						ShowHTML url = new ShowHTML();
						url.show(get.getContent());
						break;
					}
					case 3:{ // case program (.exe file)
						RunCommand program = new RunCommand();
						program.run(get.getContent());
						break;
					}
					case 4:{ // case definition (not a file)
						System.out.println(get.getContent());
						break;
					}
					}
				}
			}
		}
		catch (MultimediaException e) {
			System.out.println(e.getMessage());
		}

		if (!exists) { // if they key does not exist in dictionary, then prints out the predecessor and successor
			System.out.println("The word " + name + " is not in the ordered dictionary.");
			// prints the predecessor
			DataItem prec = dictionary.predecessor(new Key(name, ""));
			if (prec == null) System.out.println("Preceding word: "); // the key is the smallest
			else System.out.println("Preceding word: " + prec.getKey().getName());
			// prints the successor 
			DataItem suc = dictionary.successor(new Key(name, ""));
			if (suc == null) System.out.println("Following word: "); // the key is the largest
			else System.out.println("Following word: " + suc.getKey().getName());
		}


	}
	
	/**
	 * removeMethod will remove a given key from the ordered dictionary
	 * @param key
	 */
	private static void removeMethod(Key key) {
		try {
			dictionary.remove(key);
		} catch (DictionaryException e) {}
	}

	/**
	 * addMethod will add a given data item to the ordered dictionary 
	 * @param data
	 */
	private static void addMethod(DataItem data) {
		try {
			dictionary.put(data);
		} catch (DictionaryException e) {}
	}

	/**
	 * listMethod will list the names of all data items in the dictionary that start with the given string
	 * @param string
	 */
	private static void listMethod(String string) {
		// start with the key storing the string
		Key getSuc = new Key (string, "");
		Key getPrec = new Key (string, "");
		String list = string + ", "; // will store all of the names for the list

		// checks the successor of the given string until it has either reached the largest dataItem or they don't begin with the prefix anymore
		while(dictionary.successor(getSuc) != null){ // if there are no successors then stop searching
			getSuc = dictionary.successor(getSuc).getKey();
			if (getSuc.getName().startsWith(string)) list += getSuc.getName() + ", "; //adds the name if it begins with the prefix
			else break; 
		} 
		// checks the predecessor of the given string until it has reached the smallest dataItem or they don't begin with the prefix anymore
		while(dictionary.predecessor(getPrec) != null) {// if there are no predecessors then stop searching
			getPrec = dictionary.predecessor(getPrec).getKey();
			if (getPrec.getName().startsWith(string)) list += getPrec.getName() + ", ";//adds the name if it begins with the prefix
			else break;
		} 

		if (list.equals(string + ", " ) ) { // if nothing was added then there are no names that start with the given string
			System.out.println("No name attributes in the ordered dictionary start with prefix " + string);
		}
		else { // otherwise print the list of all the names that start with the given string
			list = list.substring(0, list.length()-2); // cut off the comma at the end
			System.out.println(list);
		}
	}

	/**
	 * first method will print the smallest key name in ordered dictionary
	 */
	private static void first() {
		System.out.println(dictionary.smallest().getKey().getName());
	}

	/**
	 * last method will print the largest key name in the ordered dictionary
	 */
	private static void last() {
		System.out.println(dictionary.largest().getKey().getName());
	}

	/**
	 * getType method will return the type of the key given its description
	 * @param definition
	 * @return
	 */
	private static String getType(String definition) {
		if (definition.contains(".wav") || definition.contains(".mid")) return SOUND;
		else if (definition.contains(".jpg") || definition.contains(".gif")) return PICTURE;
		else if (definition.contains(".html")) return URL;
		else if (definition.contains(".exe")) return PROGRAM;
		else return DEFINITION; 
	}

}
