import java.util.*;


public class Player implements Comparable<Player> {
    public int id = 0;
    public ArrayList<Card> cards = null;

    
    public Player(int id, ArrayList<Card> cards) {
        this.id = id;
        this.cards = cards;
        Collections.sort(this.cards);
    }

    public int isStraightFlush() {
        if((cards.get(0).suit != cards.get(1).suit)) return 0;
        if((cards.get(1).suit != cards.get(2).suit)) return 0;
        if((cards.get(2).suit != cards.get(3).suit)) return 0;
        if((cards.get(3).suit != cards.get(4).suit)) return 0;
        int rank = cards.get(0).rank;
        for(int i = 1; i < cards.size(); i++) {
            rank++;
            if(cards.get(i).rank != rank) return 0;
        }
        return cards.get(cards.size() - 1).rank;
    }

    public int isFourOfKind() {
        for(int i = 0; i < cards.size(); i++) {
            int rank = cards.get(i).rank;
            int n = 0;
            for(int j = 0; j < cards.size(); j++) {
                if(cards.get(j).rank == rank) n++;
            }
            if(n >= 4) return rank;
        }
        return 0;
    }

    public int[] isFullHouse() {
        int[] r = { 0, 0 };
        int maxn = 0, maxrank = 0;
        for(int i = 0; i < cards.size(); i++) {
            int rank = cards.get(i).rank;
            int n = 0;
            for(int j = 0; j < cards.size(); j++) {
                if(cards.get(j).rank == rank) n++;
            }
            if(maxn < n) {
                maxn = n;
                maxrank = rank;
            }
        }
        if(maxn == 3) {
            Card c1 = null, c2 = null;
            for(int i = 0; i < cards.size(); i++) {
                if(cards.get(i).rank != maxrank) {
                    if(c1 == null) c1 = cards.get(i);
                    else c2 = cards.get(i);
                }
            }
            if(c1.rank == c2.rank) {
                r[0] = maxrank;
                r[1] = c1.rank;
                return r;
            }
        }
        return null;
    }

    public int isFlush() {
        if((cards.get(0).suit != cards.get(1).suit)) return 0;
        if((cards.get(1).suit != cards.get(2).suit)) return 0;
        if((cards.get(2).suit != cards.get(3).suit)) return 0;
        if((cards.get(3).suit != cards.get(4).suit)) return 0;
        return cards.get(4).rank;
    }

    public int isStraight() {
        int rank = cards.get(0).rank;
        for(int i = 1; i < cards.size(); i++) {
            rank++;
            if(cards.get(i).rank != rank) return 0;
        }
        return cards.get(cards.size() - 1).rank;
    }

    public int isThreeOfKind() {
        int maxn = 0, maxrank = 0;
        for(int i = 0; i < cards.size(); i++) {
            int rank = cards.get(i).rank;
            int n = 0;
            for(int j = 0; j < cards.size(); j++) {
                if(cards.get(j).rank == rank) n++;
            }
            if(maxn < n) {
                maxn = n;
                maxrank = rank;
            }
        }
        if(maxn == 3) {
            Card c1 = null, c2 = null;
            for(int i = 0; i < cards.size(); i++) {
                if(cards.get(i).rank != maxrank) {
                    if(c1 == null) c1 = cards.get(i);
                    else c2 = cards.get(i);
                }
            }
            if(c1.rank != c2.rank) return maxrank;
        }
        return 0;
    }

    public int[] isTwoPair() {
        int[] r = { 0, 0, 0 };

        ArrayList<Card> cards1 = new ArrayList<Card>();
        for(int i = 0; i < cards.size(); i++) cards1.add(cards.get(i));
        
        int maxn1 = 0, maxrank1 = 0;
        for(int i = 0; i < cards1.size(); i++) {
            int rank = cards1.get(i).rank;
            int n = 0;
            for(int j = 0; j < cards1.size(); j++) {
                if(cards1.get(j).rank == rank) n++;
            }
            if(maxn1 < n) {
                maxn1 = n;
                maxrank1 = rank;
            }
        }
        if(maxn1 != 2) return null;

        int p = 0;
        while(p < cards1.size()) {
            if(cards1.get(p).rank == maxrank1) cards1.remove(p);
            else p++;
        }

        int maxn2 = 0;
        int maxrank2 = 0;
        for(int i = 0; i < cards1.size(); i++) {
            int rank = cards1.get(i).rank;
            int n = 0;
            for(int j = 0; j < cards1.size(); j++) {
                if(cards1.get(j).rank == rank) n++;
            }
            if(maxn2 < n) {
                maxn2 = n;
                maxrank2 = rank;
            }
        }
        if(maxn2 != 2) return null;

        if(maxrank1 < maxrank2) {
            r[0] = maxrank2;
            r[1] = maxrank1;
        }
        else {
            r[0] = maxrank1;
            r[1] = maxrank2;
        }
        for(int i = 0; i < cards.size(); i++) {
            if((cards.get(i).rank != maxrank1) && (cards.get(i).rank != maxrank2)) {
                r[2] = cards.get(i).rank;
                break;
            }
        }
        return r;
    }

    public int[] isOnePair() {
        int[] r = { 0, 0, 0, 0 };

        ArrayList<Card> cards1 = new ArrayList<Card>();
        for(int i = 0; i < cards.size(); i++) cards1.add(cards.get(i));
        
        int maxn = 0, maxrank1 = 0;
        for(int i = 0; i < cards1.size(); i++) {
            int rank = cards1.get(i).rank;
            int n = 0;
            for(int j = 0; j < cards1.size(); j++) {
                if(cards1.get(j).rank == rank) n++;
            }
            if(maxn < n) {
                maxn = n;
                maxrank1 = rank;
            }
        }
        if(maxn != 2) return null;
        
        int p = 0;
        while(p < cards1.size()) {
            if(cards1.get(p).rank == maxrank1) cards1.remove(p);
            else p++;
        }

        int maxn2 = 0;
        int maxrank2 = 0;
        for(int i = 0; i < cards1.size(); i++) {
            int rank = cards1.get(i).rank;
            int n = 0;
            for(int j = 0; j < cards1.size(); j++) {
                if(cards1.get(j).rank == rank) n++;
            }
            if(maxn2 < n) {
                maxn2 = n;
                maxrank2 = rank;
            }
        }
        if(maxn2 > 1) return null;

        r[0] = maxrank1;
        r[1] = cards1.get(0).rank;
        r[2] = cards1.get(1).rank;
        r[3] = cards1.get(2).rank;

        if(r[1] < r[2]) {
            int t = r[1];
            r[1] = r[2];
            r[2] = t;
        }
        if(r[3] > r[1]) {
            int t = r[3];
            r[3] = r[2];
            r[2] = r[1];
            r[1] = t;
        }
        else if(r[3] > r[2]) {
            int t = r[3];
            r[3] = r[2];
            r[2] = t;
        }
        return r;
    }

    private String convertToName(int rank) {
        if((rank >= 2) && (rank <= 9)) return String.valueOf((char)(48 + rank));
        else if(rank == 10) return "10";
        else if(rank == 11) return "Jack";
        else if(rank == 12) return "Queen";
        else if(rank == 13) return "King";
        else if(rank == 14) return "Ace";
        return "";
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

    public int compareTo(Player anotherPlayer) {
        int rank1 = this.isStraightFlush();
        int rank2 = anotherPlayer.isStraightFlush();
        if((rank1 > 0) && (rank2 > 0)) {
            if(rank1 < rank2) return -1;
            else if(rank1 > rank2) return 1;
            else return 0;
        }
        else if(rank2 > 0) return -1;
        else if(rank1 > 0) return 1;
        
        rank1 = this.isFourOfKind();
        rank2 = anotherPlayer.isFourOfKind();
        if((rank1 > 0) && (rank2 > 0)) {
            if(rank1 < rank2) return -1;
            else if(rank1 > rank2) return 1;
            else return 0;
        }
        else if(rank2 > 0) return -1;
        else if(rank1 > 0) return 1;

        int[] r1 = this.isFullHouse();
        int[] r2 = anotherPlayer.isFullHouse();
        if((r1 != null) && (r2 != null)) {
            if(r1[0] < r2[0]) return -1;
            if(r1[0] > r2[0]) return 1;
            else return 0;
        }
        else if(r2 != null) return -1;
        else if(r1 != null) return 1;

        rank1 = this.isFlush();
        rank2 = anotherPlayer.isFlush();
        if((rank1 > 0) && (rank2 > 0)) {
            for(int i = 4; i >= 0; i--) {
                rank1 = cards.get(i).rank;
                rank2 = anotherPlayer.cards.get(i).rank;
                if(rank1 <rank2) return -1;
                else if(rank1 > rank2) return 1;
            }
            return 0;
        }
        else if(rank2 > 0) return -1;
        else if(rank1 > 0) return 1;

        rank1 = this.isStraight();
        rank2 = anotherPlayer.isStraight();
        if((rank1 > 0) && (rank2 > 0)) {
            if(rank1 < rank2) return -1;
            else if(rank1 > rank2) return 1;
            else return 0;
        }
        else if(rank2 > 0) return -1;
        else if(rank1 > 0) return 1;

        rank1 = this.isThreeOfKind();
        rank2 = anotherPlayer.isThreeOfKind();
        if((rank1 > 0) && (rank2 > 0)) {
            if(rank1 < rank2) return -1;
            else if(rank1 > rank2) return 1;
            else return 0;
        }
        else if(rank2 > 0) return -1;
        else if(rank1 > 0) return 1;
        
        r1 = this.isTwoPair();
        r2 = anotherPlayer.isTwoPair();
        if((r1 != null) && (r2 != null)) {
            if(r1[0] < r2[0]) return -1;
            else if(r1[0] > r2[0]) return 1;
            else {
                if(r1[1] < r2[1]) return -1;
                else if(r1[1] > r2[1]) return 1;
                else {
                    if(r1[2] < r2[2]) return -1;
                    else if(r1[2] > r2[2]) return 1;
                    else return 0;
                }
            }
        }
        else if(r2 != null) return -1;
        else if(r1 != null) return 1;
        
        r1 = this.isOnePair();
        r2 = anotherPlayer.isOnePair();
        if((r1 != null) && (r2 != null)) {
            if(r1[0] < r2[0]) return -1;
            else if(r1[0] > r2[0]) return 1;
            else {
                if(r1[1] < r2[1]) return -1;
                else if(r1[1] > r2[1]) return 1;
                else {
                    if(r1[2] < r2[2]) return -1;
                    else if(r1[2] > r2[2]) return 1;
                    else {
                        if(r1[3] < r2[3]) return -1;
                        else if(r1[3] > r2[3]) return 1;
                        else return 0;
                    }
                }
            }
        }
        else if(r2 != null) return -1;
        else if(r1 != null) return 1;

        for(int i = 4; i >= 0; i--) {
            rank1 = cards.get(i).rank;
            rank2 = anotherPlayer.cards.get(i).rank;
            if(rank1 < rank2) return -1;
            else if(rank1 > rank2) return 1;
        }
        return 0;
    }

}
