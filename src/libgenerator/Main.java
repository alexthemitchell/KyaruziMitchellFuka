/**
 * 
 */
package libgenerator;

import java.util.List;

/**
 * @author Ajuna Kyaruzi
 * @lastModified April 10th, 2016
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String word = "test";
		
		List<String> phoneticList = generatePhonetic(word);
		List<String> typoList = generateTypos(word);
		List<String> conjugateList = generateConjugations(word);
	
		//Add each list to a file
	}

	private static List<String> generatePhonetic(String word) {
		// TODO Use the Double Metaphone Library 
		// https://commons.apache.org/proper/commons-codec/apidocs/org/apache/commons/codec/language/DoubleMetaphone.html
		
		return null;
	}

	private static List<String> generateTypos(String word) {
		// TODO Use the Qwerty Pure, Soundex or Damerau-Levenshtein Algorithm
		//https://github.com/KevinStern/software-and-algorithms/blob/master/src/main/java/blogspot/software_and_algorithms/stern_library/string/DamerauLevenshteinAlgorithm.java
		return null;
	}

	private static List<String> generateConjugations(String word) {
		// TODO Using English dictionary library
		return null;
	}

}
