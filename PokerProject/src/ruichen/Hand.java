package ruichen;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Hand implements Comparable<Hand>{
	private ArrayList<Card> cards;
	private int size;
//	private int[] nOfAKind;
//	private int[] significantBits;
	private int[] ranks;
	private boolean isStraight;
	private boolean isFlush;
	private static enum Classification{
		StraightFlush, FourOfAKind, FullHouse, Flush, Straight, ThreeOfAKind, TwoPair, OnePair, HighCard
	};
	private Classification classification;
	
	private static enum Key{
		Four, Three, BiggerTwo, SmallerTwo
	}
	
	private HashMap<Key, Card> config;

	
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
		this.CheckIsStraight();
		this.CheckIsFlush();
		this.CheckNOfAKind();
//		if(this.isStraightFlush()){
//			this.classification = Classification.StraightFlush;
//		}
//		else if(this.isFourOfAKind()){
//			this.classification = Classification.FourOfAKind;
//		}
//		else if(this.isFullHouse()){
//			this.classification = Classification.FullHouse;
//		}
//		else if(this.isFlush()){
//			this.classification = Classification.Flush;
//		}
//		else if(this.isStraight()){
//			this.classification = Classification.Straight;
//		}
//		else if(this.isThreeOfAKind()){
//			this.classification = Classification.ThreeOfAKind;
//		}
//		else if(this.isTwoPair()){
//			this.classification = Classification.TwoPair;
//		}
//		else if(this.isOnePair()){
//			this.classification = Classification.OnePair;
//		}
//		else if(this.isHighCard()){
//			this.classification = Classification.HighCard;
//		}
		
		if(this.isStraight) {
			if(this.isFlush) 
				this.classification = Classification.StraightFlush;
			else 
				this.classification = Classification.Straight;
		}
		else if(this.isFlush)
			this.classification = Classification.Flush;
		else if (this.config.containsKey(Key.Four)) 
			this.classification = Classification.FourOfAKind;
		else if (this.config.containsKey(Key.Three)){
			if(this.config.containsKey(Key.BiggerTwo))
				this.classification = Classification.FullHouse;
			else 
				this.classification = Classification.ThreeOfAKind;
		}
		else if(this.config.containsKey(Key.BiggerTwo)){
			if(this.config.containsKey(Key.SmallerTwo)) {
				this.classification = Classification.TwoPair;
			}
			else this.classification = Classification.OnePair;
		}
		else 
			this.classification = Classification.HighCard;
	}
	
//	
//	public boolean isFullHouse(){
//		//return (this.nOfAKind[0] == 3 && this.nOfAKind[1] == 2);	
//		return (this.config.containsKey(Key.Three) && this.config.containsKey(Key.BiggerTwo));
//	}
//
//	public boolean isThreeOfAKind(){
//		//return (this.nOfAKind[0] == 3 && this.nOfAKind[1] == 1);
//		return (this.config.containsKey(Key.Three) && (!this.config.containsKey(Key.BiggerTwo)));
//	}
//	
//	public boolean isTwoPair(){
//		//return (this.nOfAKind[0] == 2 && this.nOfAKind[1] == 2);
//		return (this.config.containsKey(Key.BiggerTwo) && this.config.containsKey(Key.SmallerTwo));
//	}
//	
//	public boolean isOnePair(){
//		//eturn (this.nOfAKind[0] == 2 && this.nOfAKind[1] == 1);
//		return (this.config.containsKey(Key.BiggerTwo) && (!this.config.containsKey(Key.SmallerTwo)));
//	}
//	
//	public boolean isHighCard(){
//		//return ((!this.isStraight) && this.nOfAKind[0] == 1 && this.nOfAKind[1] == 1);
//		return ((!this.isStraight) && this.config.isEmpty());
//	}
	
	public void CheckIsStraight(){
		for(int i = 0; i < this.size - 1; i++) {
			if(cards.get(i + 1).getRank() != cards.get(i).getRank() + 1) {
				this.isStraight = false;
				break;
			}
		}
		this.isStraight = true;
	}
	
	public void CheckIsFlush(){
		for(int i = 0; i < this.size - 1; i++) {
			if(!cards.get(i + 1).getSuit().equals(cards.get(i).getSuit())) {
				this.isFlush = false;
				break;
			}
		}
		this.isFlush = true;
	}
	
	public void CheckNOfAKind(){
//		int[] resultKind = {1, 1};
//		int[] resultRanks = new int[this.size];
//		int[] resultBits = {-1, -1, -1};
		this.ranks = new int[this.size];
		for(int i = 0; i < this.ranks.length; i++){
			this.ranks[i] = cards.get(i).getRank(); //first 5 bits are the ranks
		}
		//no need to go forward if is straight
		if (this.isStraight) {
			//this.nOfAKind = resultKind;
			return;
		}

		int num = 1;
		for(int i = this.size - 1; i > 0; i--) {
			Card thisCard = cards.get(i);
			Card nextCard = cards.get(i - 1);
			//int nextCardRank = cards.get(i - 1).getRank();
			if(thisCard.getRank() == nextCard.getRank()) {
				num++;
			}
			else{
				//more than one of a kind
				if (num > 1){
//					if(resultKind[0] >= num) resultKind[1] = num;
//					else {
//						resultKind[1] = resultKind[0];
//						resultKind[0] = num;
//					}					
					if(num == 4){
						this.config.put("4", thisCard);
					}
					else if(num == 3) {
						this.config.put("3", thisCard);
					}
					else if(num == 2){
						if(this.config.containsKey("bigger2")){
							this.config.put("smaller2", thisCard);
						}
						else this.config.put("bigger2", thisCard);
					}
					num = 1;
				}
			}
		}
//		this.nOfAKind = resultKind;
//		this.ranks = resultRanks;
//		this.significantBits = resultBits;
	}
	
	public boolean isFourOfAKind(){
		//return (this.nOfAKind[0] == 4);
		return this.config.containsKey(Key.Four);
	}
	
	public boolean isFullHouse(){
		//return (this.nOfAKind[0] == 3 && this.nOfAKind[1] == 2);	
		return (this.config.containsKey(Key.Three) && this.config.containsKey(Key.BiggerTwo));
	}

	public boolean isThreeOfAKind(){
		//return (this.nOfAKind[0] == 3 && this.nOfAKind[1] == 1);
		return (this.config.containsKey(Key.Three) && (!this.config.containsKey(Key.BiggerTwo)));
	}
	
	public boolean isTwoPair(){
		//return (this.nOfAKind[0] == 2 && this.nOfAKind[1] == 2);
		return (this.config.containsKey(Key.BiggerTwo) && this.config.containsKey(Key.SmallerTwo));
	}
	
	public boolean isOnePair(){
		//eturn (this.nOfAKind[0] == 2 && this.nOfAKind[1] == 1);
		return (this.config.containsKey(Key.BiggerTwo) && (!this.config.containsKey(Key.SmallerTwo)));
	}
	
	public boolean isHighCard(){
		//return ((!this.isStraight) && this.nOfAKind[0] == 1 && this.nOfAKind[1] == 1);
		return ((!this.isStraight) && this.config.isEmpty());
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

    public @Override String toString() {
    	switch(this.classification){
    	case StraightFlush:
            	return this.cards.get(cards.size() - 1)+ "-high straight flush";
		case Flush:
				return this.;
		case FourOfAKind:
			break;
		case FullHouse:
			break;
		case HighCard:
			break;
		case OnePair:
			break;
		case Straight:
			break;
		case ThreeOfAKind:
			break;
		case TwoPair:
			break;
		default:
			break; 
    		
    	}
    	
    }
}
