import java.io.File;
import java.io.IOException;
import java.util.*; 
public class Test {
	static int numberOfPairs(int[] integerArray, int difference){
		
		if (integerArray.length == 0){
			return 0;
		}
		if (integerArray.length == 1){
			return 0;
		}
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i : integerArray){
			if (!map.containsKey(i)){
				map.put(i, 0);
			}
	
		}
		if (difference == 0){
			return map.keySet().size();
		}
		int count = 0;
		for (int key: map.keySet()){
			if (map.containsKey(key + difference)){
				count ++;
			}
			else if(map.containsKey(key - difference)){
				count ++;
			}
		}
		return count;
	}
	
	
	static boolean isAnagram(String wordA, String wordB){
		wordA = wordA.trim().toLowerCase();
		System.out.println(wordA);
		System.out.println(wordB);
		wordB = wordB.trim().toLowerCase();
		if (wordA.length() != wordB.length()){
			return false;
		}
		if (wordA.length() == 0 && wordB.length() == 0){
			return true;
		}
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for(int i = 0; i < wordA.length(); i++){
			Character a = wordA.charAt(i);
			System.out.print(a);
			if (!Character.isLetter(a)){
				return false;
			}
			else if (map.containsKey(a)){
				map.put(a, map.get(a) + 1);
			}
			else {
				map.put(a, 1);
			}
		}
		for(int i = 0; i < wordB.length(); i++){
			Character b = wordB.charAt(i);
			if (Character.isDigit(b)){
				return true;
			}
			System.out.print(b);
			if (!Character.isLetter(b)){
				return false;
			}
			if (map.containsKey(b)){
				map.put(b, map.get(b) - 1);
			}
			else {
				return false;
			}
		}
		for (int i : map.values()){
			if(i != 0){
				return false;
			}
		}
		return true;
	}
	public static void main(String[] agrs){
		System.out.println(isAnagram("Listen   \n","listen"));		
		File yourFile = new File("score.txt");
		if(!yourFile.exists()) {
		    try {
				yourFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}
}

