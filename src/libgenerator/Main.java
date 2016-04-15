/**
 * 
 */
package libgenerator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Ajuna Kyaruzi
 * @lastModified April 15th, 2016
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * While we only support 6 functions and methods, the input file of keywords 
		 * should have the following format where each keyword is separated by a tab
		 * and the different intentions that are associated with that word. The list
		 * below is the complete list for the 6 beginning methods we have. The starting
		 * file is provided with the code. Please name the input file as "FukaInput"
		 * 
		 * Complete	compSquare
		 * Square	compSquare,squareRoot
		 * Quadratic	quadForm,quadFact
		 * Formula	quadForm
		 * Factor	quadFact
		 * Method	eliMethod,subMethod
		 * Elimination	eliMethod
		 * Substitution	subMethod
		 * 
		 */
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("WordIntentions", "UTF-8");
		} catch (FileNotFoundException e) {
			System.out.println("Bad Output Formatting");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Bad Output Formatting");
		}
		
		Map<String, String> keywords = getKeyWords("FukaInput");
		
		for (String key : keywords.keySet()) {
			List<String> typoList = generateTypos(key);
			for (String word2 : typoList){
				writer.printf("%s\t%s\n", word2, keywords.get(key));
			}
		}
		
		writer.close();
	}

	private static Map<String, String> getKeyWords(String inputFile){
			
		Map<String, String> res = new HashMap<String, String>();
		
		try {
		Scanner inputScanner = new Scanner(new FileReader(inputFile)).useDelimiter("[\\s+]");
			while(inputScanner.hasNext()){
				String key = inputScanner.next().toLowerCase();
				String value = null ;
				if (inputScanner.hasNext()){
					value = inputScanner.next();
				}
				res.put(key, value);
			}		
		} catch (IOException e) {
			System.out.println("Trouble with input file");
		}
		
		return res;
	}

	
	private static List<String> generatePhonetic(String word) {
		// TODO Use the Double Metaphone Library 
		// https://commons.apache.org/proper/commons-codec/apidocs/org/apache/commons/codec/language/DoubleMetaphone.html
		// Will be supported in next version
		return null;
	}
	
	private static String replaceAt(String str, int index, char c){
		int len = str.length();
		String res = "";
		for (int i = 0; i < len ; i++){
			if (i == index){
				res+=c;
			} else {
				res+=str.charAt(i);
			}
		}// for
		return res;
	}
	
	private static Map<String, String> array_prox (){
		Map<String,String> prox = new HashMap<String,String>(40);
		
	    prox.put("a", "qwzx");
	    prox.put("b", "vfghn");
	    prox.put("c", "xsdfv");
	    prox.put("d", "xswerfvc");
	    prox.put("e", "wsdfr");
	    prox.put("f", "cdertgbv");
	    prox.put("g", "rfvtbyhn");
	    prox.put("h", "bgtyujmn");
	    prox.put("i", "ujklo");
	    prox.put("j", "nhyuikm");
	    prox.put("k", "ujmlo");
	    prox.put("l", "poikm");
	    prox.put("m", "nhjkl");
	    prox.put("n", "bghjm");
	    prox.put("o", "iklp");
	    prox.put("p", "ol");
	    prox.put("q", "was");
	    prox.put("r", "edfgt");
	    prox.put("s", "qwezxc");
	    prox.put("t", "rfghy");
	    prox.put("u", "yhjki");
	    prox.put("v", "cdfgb");    
	    prox.put("w", "qasde");
	    prox.put("x", "zasdc");
	    prox.put("y", "tghju");
	    prox.put("z", "xsa");
	    prox.put("1", "qw");
	    prox.put("2", "qwe");
	    prox.put("3", "wer");
	    prox.put("4", "ert");
	    prox.put("5", "rty");
	    prox.put("6", "tyu");
	    prox.put("7", "yui");
	    prox.put("8", "uio");
	    prox.put("9", "iop");
	    prox.put("0", "op");

	    return prox;
	}

	private static List<String> generateTypos(String str) {
			List<String> res = new LinkedList<String>();
			int strLength = str.length();
			Map<String, String> prox = array_prox();
			for (int a = 0; a < strLength; a++) {

			    String temp = prox.get(str.substring(a, a+1)); 
			    
			    int len = temp.length();
				for (int b = 0; b < len ; b++) {
				    String typo = replaceAt(str, a, temp.charAt(b));
				    res.add(typo);
				}
			}

			return res;
	}
}
