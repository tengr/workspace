package ruichen;
import java.util.ArrayList;
import java.util.Collections;

public class Hand implements Comparable<Hand>{
	private ArrayList<Card> cards;
	private int size;
	private int[] nOfAKind;
	private int[] significantBits;
	private int[] ranks;
	private boolean isStraight;
	private boolean isFlush;
	private static enum Classification{
		StraightFlush, FourOfAKind, FullHouse, Flush, Straight, ThreeOfAKind, TwoPair, OnePair, HighCard
	};
	private Enum<Classification> classification;
	
	public Hand(ArrayList<Card> cards){
		Collections.sort(cards);
		this.cards = cards;
		this.size = cards.size();
		this.setState();
	}
	
	public boolean isFlush(){
		return this.isFlush;
	}
	
	public boolean isStraight() {
		return this.isStraight;
	}
	
	public boolean isStraightFlush(){
		return this.isFlush && this.isStraight;
	}
	
	public void setState(){
		this.setISStraight();
		this.setIsFlush();
		this.setNOfAKind();
		if(this.isStraightFlush()){
			this.classification = Classification.StraightFlush;
		}
		else if(this.isFourOfAKind()){
			this.classification = Classification.FourOfAKind;
		}
		else if(this.isFullHouse()){
			this.classification = Classification.FullHouse;
		}
		else if(this.isFlush()){
			this.classification = Classification.Flush;
		}
		else if(this.isStraight()){
			this.classification = Classification.Straight;
		}
		else if(this.isThreeOfAKind()){
			this.classification = Classification.ThreeOfAKind;
		}
		else if(this.isTwoPair()){
			this.classification = Classification.TwoPair;
		}
		else if(this.isOnePair()){
			this.classification = Classification.OnePair;
		}
		else if(this.isHighCard()){
			this.classification = Classification.HighCard;
		}
	}
	
	public void setISStraight(){
		for(int i = 0; i < this.size - 1; i++) {
			if(cards.get(i + 1).getRank() != cards.get(i).getRank() + 1) {
				this.isStraight = false;
				break;
			}
		}
		this.isStraight = true;
	}
	
	public void setIsFlush(){
		for(int i = 0; i < this.size - 1; i++) {
			if(!cards.get(i + 1).getSuit().equals(cards.get(i).getSuit())) {
				this.isFlush = false;
				break;
			}
		}
		this.isFlush = true;
	}
	
	public void setNOfAKind(){
		int[] resultKind = {1, 1};
		int[] resultRanks = new int[this.size];
		int[] resultBits = {-1, -1, -1};
		
		for(int i = 0; i < resultRanks.length; i++){
			resultRanks[i] = cards.get(i).getRank(); //first 5 bits are the ranks
		}
		
		if (this.isStraight) {
			this.nOfAKind = resultKind;
			return;
		}

		int num = 1;
		for(int i = this.size - 1; i > 0; i--) {
			int thisCardRank = cards.get(i).getRank();
			int nextCardRank = cards.get(i - 1).getRank();
			if(thisCardRank == nextCardRank) {
				num++;
			}
			else{
				//more than one of a kind
				if (num > 1){
					if(resultKind[0] >= num) resultKind[1] = num;
					else {
						resultKind[1] = resultKind[0];
						resultKind[0] = num;
					}
					
					if(num == 3) {
						resultBits[2] = thisCardRank;
					}
					else if(num == 2){
						if(resultBits[1] < 0) resultBits[1] = thisCardRank;
						else resultBits[0] = thisCardRank;
					}
					num = 1;
				}
			}
		}
		this.nOfAKind = resultKind;
		this.ranks = resultRanks;
		this.significantBits = resultBits;
	}
	
	public boolean isFourOfAKind(){
		return (this.nOfAKind[0] == 4);
	}
	
	public boolean isFullHouse(){
		return (this.nOfAKind[0] == 3 && this.nOfAKind[1] == 2);	
	}

	public boolean isThreeOfAKind(){
		return (this.nOfAKind[0] == 3 && this.nOfAKind[1] == 1);
	}
	
	public boolean isTwoPair(){
		return (this.nOfAKind[0] == 2 && this.nOfAKind[1] == 2);
	}
	
	public boolean isOnePair(){
		return (this.nOfAKind[0] == 2 && this.nOfAKind[1] == 1);
	}
	
	public boolean isHighCard(){
		return ((!this.isStraight) && this.nOfAKind[0] == 1 && this.nOfAKind[1] == 1);
	}

	@Override
	public int compareTo(Hand anotherHand) {
		if(this.classification.ordinal() != anotherHand.classification.ordinal()){
			return new Integer(this.classification.ordinal()).compareTo(anotherHand.classification.ordinal());
		}
		else{
			for(int i = this.significantBits.length - 1; i >= 0; i--){
				if(this.significantBits[i] != anotherHand.significantBits[i]) {
					return new Integer(this.significantBits[i]).compareTo(anotherHand.significantBits[i]);
				}
			}
			for(int i = this.ranks.length - 1; i >= 0; i-- ){
				if(this.ranks[i] != anotherHand.ranks[i]) {
					return new Integer(this.ranks[i]).compareTo(anotherHand.ranks[i]);
				}
			}
		}
		return 0;
	}
}
