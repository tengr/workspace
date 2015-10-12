//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Set;
//
//
//public class TestTestTest {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		String token = "(something-9somethingelse)";
//		 //Matcher m = Pattern.compile("\\((\\S+?)\\s(\\S+?)\\)").matcher(token);	
//		 
//		 Matcher m = Pattern.compile("(\\S+)-(\\d+)/(\\S+)").matcher(token);
//		 System.out.println("start ");
//	        if(m.find()) {
//	        	System.out.println("match");
//	        	//int pos = Integer.parseInt(m.group(2));
//	        	//System.out.println(pos);
//	        }
//
//
//
//	}
//	
//}


import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.parser.lexparser.*;
import edu.stanford.nlp.process.*;
import com.chaoticity.dependensee.*;
import java.util.Collection;
import java.util.*;
import java.io.*;

class TestTestTest {
   public static void main(String []args) throws Exception {
      String text = "A quick brown fox jumped over the lazy dog.";
      TreebankLanguagePack tlp = new PennTreebankLanguagePack();
      GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
      LexicalizedParser lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz" );
      lp.setOptionFlags(new String[]{"-maxLength", "500", "-retainTmpSubcategories"});
      TokenizerFactory tokenizerFactory = PTBTokenizer.factory( new CoreLabelTokenFactory(), "");
      List wordList = tokenizerFactory.getTokenizer(new StringReader(text)).tokenize();
      Tree tree = lp.apply(wordList);
      GrammaticalStructure gs = gsf.newGrammaticalStructure(tree);
      Collection tdl = gs.typedDependenciesCCprocessed(true);
      Main.writeImage(tree,tdl, "image.png",3);
   }
}