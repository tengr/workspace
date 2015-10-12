package ruichen;

public class Card implements Comparable<Card>{
	public Enum<Rank> rank;
	public Enum<Suit> suit;
	public Card(Enum<Rank> rank, Enum<Suit> suit) {
		this.rank = rank;
		this.suit = suit;
	}
	@Override
	public int compareTo(Card anotherCard) {
		return Integer.compare(this.rank.ordinal(), anotherCard.rank.ordinal());
	}
		
	public int getRank(){
		return this.rank.ordinal();
	}
	
	public String getSuit(){
		return this.suit.toString();
	}
}
