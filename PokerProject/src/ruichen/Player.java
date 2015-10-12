package ruichen;

public class Player implements Comparable<Player>{
	public int id;
	private Hand hand;
	
	public Player(int id, Hand hand){
		this.id = id;
		this.hand = hand;
	}

	@Override
	public int compareTo(Player anotherPlayer) {
		return (this.hand).compareTo(anotherPlayer.hand);
	}
	
    public @Override String toString() {
        int rank = this.isStraightFlush();
        if(rank > 0) return convertToName(rank) + "-high straight flush";

        rank = this.isFourOfKind();
        if(rank > 0) return "Four " + convertToName(rank) + "s";

        int[] r = this.isFullHouse();
        if(r != null) return convertToName(r[0]) + "s full of " + convertToName(r[1]) + "s";

        rank = this.isFlush();
        if(rank > 0) return convertToName(rank) + "-high flush";

        rank = this.isStraight();
        if(rank > 0) return convertToName(rank) + "-high straight";

        rank = this.isThreeOfKind();
        if(rank > 0) return "Three " + convertToName(rank) + "s";

        r = this.isTwoPair();
        if(r != null) return convertToName(r[0]) + "s over " + convertToName(r[1]) + "s";

        r = this.isOnePair();
        if(r != null) return "Pair of " + convertToName(r[0]) + "s";

        return cards.get(4).getName() + "-high";
    }
}
