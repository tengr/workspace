#Ruichen Teng / tengr / 678693
#Ruichen Teng / tengr / 678693
#Tiehua Zhang / tiehuaz / 645227
#Chao Lin / clin2 / 658584
#Xueqiang Ma/ xueqiangm/ 668190
#Zheng Zheng/ zzheng2/ 651384
#Ruichen Teng / tengr / 678693
#Tiehua Zhang / tiehuaz / 645227
#Chao Lin / clin2 / 658584
#Xueqiang Ma/ xueqiangm/ 668190
#Zheng Zheng/ zzheng2/ 651384
#Ruichen Teng / tengr / 678693
#Tiehua Zhang / tiehuaz / 645227
#Chao Lin / clin2 / 658584
#Xueqiang Ma/ xueqiangm/ 668190
#Zheng Zheng/ zzheng2/ 651384
#Tiehua Zhang / tiehuaz / 645227
#Chao Lin / clin2 / 658584
#Xueqiang Ma/ xueqiangm/ 668190
#Zheng Zheng/ zzheng2/ 651384
package SortingTest;
#Ruichen Teng / tengr / 678693
#Tiehua Zhang / tiehuaz / 645227
#Chao Lin / clin2 / 658584
#Xueqiang Ma/ xueqiangm/ 668190
#Zheng Zheng/ zzheng2/ 651384
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class RunLengthEncoding {
 
    public static String encode(String source) {
        StringBuffer dest = new StringBuffer();
        for (int i = 0; i < source.length(); i++) {
            int runLength = 1;
            while (i+1 < source.length() && source.charAt(i) == source.charAt(i+1)) {
                runLength++;
                i++;
            }
            dest.append(runLength);
            dest.append(source.charAt(i));
        }
        return dest.toString();
    }
 
    public static String decode(String source) {
        StringBuffer dest = new StringBuffer();
        Pattern pattern = Pattern.compile("[0-9]+|[a-zA-Z]");
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            int number = Integer.parseInt(matcher.group());
            matcher.find();
            while (number-- != 0) {
                dest.append(matcher.group());
            }
        }
        return dest.toString();
    }
 
    public static void main(String[] args) {
        //String example = "uuuhiullllmmmmNNddd";
        //System.out.println(encode(example));
        Scanner sc = null;
		try {
			sc = new Scanner(new File("input.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        int length = sc.nextInt();
        for(int i = 0 ; i < length; i ++){
        	String input = sc.nextLine();
        	System.out.println(encode(input));
        }
        sc.close();
        
        //System.out.println(decode("1W1B1W1B1W1B1W1B1W1B1W1B1W1B"));
    }
}