
public class Card implements Comparable<Card> {
    public int rank = 0;
    public char suit = 0;
    public char name = 0;

    public Card() {}

    public Card(char suit, char name) {
        this.suit = suit;
        this.name = name;
        if((name >= '2') && (name <= '9')) rank = name - '0';
        else if(name == 'T') rank = 10;
        else if(name == 'J') rank = 11;
        else if(name == 'Q') rank = 12;
        else if(name == 'K') rank = 13;
        else if(name == 'A') rank = 14;
    }

    public int compareTo(Card anotherCard) {
        if(rank < anotherCard.rank) return -1;
        else if(rank > anotherCard.rank) return 1;
        else return 0;
    }

    public String getName() {
        if((name >= '2') && (name <= '9')) return String.valueOf(name);
        else if(name == 'T') return "10";
        else if(name == 'J') return "Jack";
        else if(name == 'Q') return "Queen";
        else if(name == 'K') return "King";
        else if(name == 'A') return "Ace";
        return "";
    }

}

