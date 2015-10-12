package ruichen;

public class Card implements Comparable<Card>{
	public Rank rank;
	public Suit suit;
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}
	@Override
	public int compareTo(Card anotherCard) {
		return new Integer(this.rank.ordinal()).compareTo(anotherCard.rank.ordinal());
	}
		
	public int getRank(){
		return this.rank.ordinal();
	}
	
	public String getSuit(){
		return this.suit.toString();
	}
    public @Override String toString() {
    	return this.rank.toString();
    }
}

