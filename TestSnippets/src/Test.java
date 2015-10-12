//import java.io.File;
//import java.io.FileFilter;
//
//public class TestTest {
//
//	public static void main(String[] args) {
//		String yourPath = "/Users/ruichen/Documents/COMP90055/DirectPull/asm-re/tengr_experiment";
//		File directory = new File(yourPath);
//		ParsedFileFilter pff = new ParsedFileFilter();
//		String[] myFiles = directory.list();
//		for(String s: myFiles){
//			if (pff.accept(directory))
//			System.out.println(s);
//		}
//	}
//
//}
//
//class ParsedFileFilter implements FileFilter {
//	@Override
//	public boolean accept(File pathname) {
//		return pathname.getName().endsWith("sh");
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

class Test {
   public static void main(String []args) throws Exception {
      String text = "A quick brown fox jumped over the lazy dog.";
      TreebankLanguagePack tlp = new PennTreebankLanguagePack();
      GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
      LexicalizedParser lp = LexicalizedParser.loadModel(            "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz" );
      lp.setOptionFlags(new String[]{"-maxLength", "500", "-retainTmpSubcategories"});
      TokenizerFactory tokenizerFactory = PTBTokenizer.factory( new CoreLabelTokenFactory(), "");
      List wordList = tokenizerFactory.getTokenizer(new StringReader(text)).tokenize();
      Tree tree = lp.apply(wordList);
      GrammaticalStructure gs = gsf.newGrammaticalStructure(tree);
      Collection tdl = gs.typedDependenciesCCprocessed(true);
      Main.writeImage(tree,tdl, "image.png",3);
   }
}