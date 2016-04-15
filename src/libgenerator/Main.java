/**
 * 
 */
package libgenerator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
		
		String[] keywords = {"one", "two", "three"};
		//figure out how to read into keywords
	
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("the-file-name.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (String word : keywords) {
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

	private static List<String> generateTypos(String str) {
			List<String> res = new LinkedList<String>();
			String[] array_prox = new String[36];
			int strLength = str.length();
			
		    array_prox['a'] = "qwzx";
		    array_prox['b'] = "vfghn";
		    array_prox['c'] = "xsdfv";
		    array_prox['d'] = "xswerfvc";
		    array_prox['e'] = "wsdfr";
		    array_prox['f'] = "cdertgbv";
		    array_prox['g'] = "rfvtbyhn";
		    array_prox['h'] = "bgtyujmn";
		    array_prox['i'] = "ujklo";
		    array_prox['j'] = "nhyuikm";
		    array_prox['k'] = "ujmlo";
		    array_prox['l'] = "poikm";
		    array_prox['m'] = "nhjkl";
		    array_prox['n'] = "bghjm";
		    array_prox['o'] = "iklp";
		    array_prox['p'] = "ol";
		    array_prox['r'] = "edfgt";
		    array_prox['s'] = "qwezxc";
		    array_prox['t'] = "rfghy";
		    array_prox['u'] = "yhjki";
		    array_prox['v'] = "cdfgb";    
		    array_prox['w'] = "qasde";
		    array_prox['x'] = "zasdc";
		    array_prox['y'] = "tghju";
		    array_prox['z'] = "xsa";
		    array_prox['1'] = "qw";
		    array_prox['2'] = "qwe";
		    array_prox['3'] = "wer";
		    array_prox['4'] = "ert";
		    array_prox['5'] = "rty";
		    array_prox['6'] = "tyu";
		    array_prox['7'] = "yui";
		    array_prox['8'] = "uio";
		    array_prox['9'] = "iop";
		    array_prox['0'] = "op";

			for (int a = 0; a < strLength; a++) {
			    String temp = array_prox[str.charAt(a)];    
			    int len = temp.length();
				for (int b = 0; b < len ; b++) {
				    String typo = replaceAt(str, a, temp.charAt(b));
				    res.add(typo);
				}
			}

			return res;
	}
}
