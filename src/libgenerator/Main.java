/**
 * 
 */
package libgenerator;

import java.io.PrintWriter;
import java.util.List;
import org.apache.commons.codec.language.DoubleMetaphone;


/**
 * @author Ajuna Kyaruzi
 * @lastModified April 10th, 2016
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//if a keyword has multiple actions put a comma but no space
		
		String[] keywords = {"one", "two", "three"};
		//figure out how to read into keywords
	
		PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
		
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

		    String array_prox[] = {};
		    array_prox['a'] = {"q", "w", "z", "x"};
		    array_prox['b'] = {'v', 'f', 'g', 'h', 'n'};
		    array_prox['c'] = {'x', 's', 'd', 'f', 'v'};
		    array_prox['d'] = {'x', 's', 'w', 'e', 'r', 'f', 'v', 'c'};
		    array_prox['e'] = {'w', 's', 'd', 'f', 'r'};
		    array_prox['f'] = {'c', 'd', 'e', 'r', 't', 'g', 'b', 'v'};
		    array_prox['g'] = {'r', 'f', 'v', 't', 'b', 'y', 'h', 'n'};
		    array_prox['h'] = {'b', 'g', 't', 'y', 'u', 'j', 'm', 'n'};
		    array_prox['i'] = {'u', 'j', 'k', 'l', 'o'};
		    array_prox['j'] = {'n', 'h', 'y', 'u', 'i', 'k', 'm'};
		    array_prox['k'] = {'u', 'j', 'm', 'l', 'o'};
		    array_prox['l'] = {'p', 'o', 'i', 'k', 'm'};
		    array_prox['m'] = {'n', 'h', 'j', 'k', 'l'};
		    array_prox['n'] = {'b', 'g', 'h', 'j', 'm'};
		    array_prox['o'] = {'i', 'k', 'l', 'p'};
		    array_prox['p'] = {'o', 'l'};
		    array_prox['r'] = {'e', 'd', 'f', 'g', 't'};
		    array_prox['s'] = {'q', 'w', 'e', 'z', 'x', 'c'};
		    array_prox['t'] = {'r', 'f', 'g', 'h', 'y'};
		    array_prox['u'] = {'y', 'h', 'j', 'k', 'i'};
		    array_prox['v'] = {'', 'c', 'd', 'f', 'g', 'b'};    
		    array_prox['w'] = {'q', 'a', 's', 'd', 'e'};
		    array_prox['x'] = {'z', 'a', 's', 'd', 'c'};
		    array_prox['y'] = {'t', 'g', 'h', 'j', 'u'};
		    array_prox['z'] = {'x', 's', 'a'};
		    array_prox['1'] = {'q', 'w'};
		    array_prox['2'] = {'q', 'w', 'e'};
		    array_prox['3'] = {'w', 'e', 'r'};
		    array_prox['4'] = {'e', 'r', 't'};
		    array_prox['5'] = {'r', 't', 'y'};
		    array_prox['6'] = {'t', 'y', 'u'};
		    array_prox['7'] = {'y', 'u', 'i'};
		    array_prox['8'] = {'u', 'i', 'o'};
		    array_prox['9'] = {'i', 'o', 'p'};
		    array_prox['0'] = {'o', 'p'};

		    String arr[] = {};

			for(var a=0; a<str.length; a++) {
			    var temp = array_prox[str.charAt(a)];    
				for(var b=0; b<temp.length; b++) {
				    var typo = str.replaceAt(a, temp[b]);
				    arr.push(typo);
				}
			}

			return arr;
	}
}
