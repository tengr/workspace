import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;

public class Card implements Comparable<Card> {
	public static final HashMap<String, Integer> nameRankMap;
	public static final HashMap<String, String> nameFullnameMap;
	public static final ArrayList<String> suitList;
    private int rank;
    private String suit;
    private String name;
    private String fullName;
    
    static {
    		nameRankMap = new HashMap<String, Integer>();
    		nameFullnameMap = new HashMap<String, String>();
    		suitList = new ArrayList<String>(Arrays.asList("C","D","H","S"));
    		
    		for (int num = 2; num <= 9; num++) {
    			nameRankMap.put(String.valueOf(num), num);
    			nameFullnameMap.put(String.valueOf(num), String.valueOf(num));
    		}
    		nameRankMap.put("T", 10);
    		nameRankMap.put("J", 11);
    		nameRankMap.put("Q", 12);
    		nameRankMap.put("K", 13);
    		nameRankMap.put("A", 14);
    		
    		nameFullnameMap.put("T", "10");
    		nameFullnameMap.put("J", "Jack");
    		nameFullnameMap.put("Q", "Queen");
    		nameFullnameMap.put("K", "King");
    		nameFullnameMap.put("A", "Ace");
    		
    }

    public Card() {}

    public Card(String suit, String name) {
        this.suit = suit.toUpperCase();
        this.name = name.toUpperCase();
        this.fullName = Card.nameFullnameMap.get(name);
        this.rank = Card.nameRankMap.get(name);
    }

    public int compareTo(Card anotherCard) {
        	return Integer.compare(this.getRank(), anotherCard.getRank());
    }

    public int getRank(){
    		return rank;
    }
    
    public String getSuit() {
		return suit;
	}
    
	public String getName() {
        return name;
    }
	
	public String getFullname() {
		return fullName;
	}
	
}

