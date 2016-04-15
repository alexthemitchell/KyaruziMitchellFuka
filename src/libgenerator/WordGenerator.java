/**
 * 
 */
package libgenerator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Ajuna Kyaruzi
 * @lastModified April 15th, 2016
 */
public class WordGenerator {

	public static void main(String[] args) {
		/**
		 * While we only support 6 functions and methods, the input file of keywords 
		 * should have the following format where each keyword is separated by four spaces
		 * and the different intentions that are associated with that word. The list
		 * below is the complete list for the 6 beginning methods we have. The starting
		 * file is provided with the code. Please provide the name of the input file as
		 * command line input when prompted.
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
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Please provide name of your input file: ");
		String inputFile = scan.nextLine();
		scan.close();
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("WordIntentions", "UTF-8");
		} catch (FileNotFoundException e) {
			System.out.println("Bad Output Formatting");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Bad Output Formatting");
		}
		
		//writer.println("Test Output File Created At " + dateFormat.format(date));
		Map<String, String> keywords = getKeyWords(inputFile);
		
		for (String key : keywords.keySet()) {
			String intentions = keywords.get(key);
			writer.printf("%s    %s\n", key, intentions);
			List<String> typoList = generateTypos(key);
			for (String typo : typoList){
				writer.printf("%s    %s\n", typo, intentions);
			}
		}//for
		
		writer.close();
	}//main

	private static Map<String, String> getKeyWords(String inputFile){
			
		Map<String, String> keyWordMap = new HashMap<String, String>();
		
		try {
		Scanner inputScanner = new Scanner(new FileReader(inputFile)).useDelimiter("[\\s+]");
			while(inputScanner.hasNext()){
				String key = inputScanner.next().toLowerCase();
				String value = null ;
				if (inputScanner.hasNext()){
					value = inputScanner.next();
				}
				keyWordMap.put(key, value);
			}		
		} catch (IOException e) {
			System.out.println("Trouble with input file");
		}
		
		return keyWordMap;
	}//getKeyWords
	
	private static List<String> generateTypos(String str) {
		List<String> lst = new LinkedList<String>();
		int strLength = str.length();
		Map<String, String> prox = proximityTypoMap();
		
		for (int a = 0; a < strLength; a++) {

		    String temp = prox.get(str.substring(a, a+1)); 
		    
		    int len = temp.length();
			for (int b = 0; b < len ; b++) {
			    String typo = replaceAt(str, a, temp.charAt(b));
			    lst.add(typo);
			}
		}//for

		return lst;
	}//generateTypos

	
	private static List<String> generatePhonetic(String word) {
		// TODO Use the Double Metaphone Library 
		// https://commons.apache.org/proper/commons-codec/apidocs/org/apache/commons/codec/language/DoubleMetaphone.html
		// Will be supported in next version
		return null;
	}//generatePhonetic
	
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
	}//replaceAt
	
	private static Map<String, String> proximityTypoMap(){
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
	}//proximityTypoMap
	
}//class Main