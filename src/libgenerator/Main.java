/**
 * 
 */
package libgenerator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Ajuna Kyaruzi
 * @lastModified April 15th, 2016
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//if a keyword has multiple actions put a comma but no space
		
		String[] keywords = {"Quadratic", "Formula", "Test"};
		//figure out how to read into keywords
	
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("KM-Fuka-1", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (String word : keywords) {
			word = word.toLowerCase();
			List<String> typoList = generateTypos(word);
			for (String word2 : typoList){
				writer.printf("%s\t%s\n", word2, word);
			}
		}
		
		writer.close();
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
			System.out.println(str);
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
