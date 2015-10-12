
import java.util.*;


public class Poker {

    public Poker(ArrayList<Card> cards) {
        ArrayList<Player> players = new ArrayList<Player>();
        for(int i = 0; i < cards.size(); i += 5) {
            int id = (i / 5) + 1;
            ArrayList<Card> lst = new ArrayList<Card>();
            lst.add(cards.get(i));
            lst.add(cards.get(i + 1));
            lst.add(cards.get(i + 2));
            lst.add(cards.get(i + 3));
            lst.add(cards.get(i + 4));
            players.add(new Player(id, lst));
        }

        for(int i = 0; i < players.size(); i++) {
            System.out.println("Player " + (i + 1) + ": " + players.get(i).toString());
        }

        if(players.size() > 1) {
            Collections.sort(players);
            boolean draw = true;
            for(int i = 0; i < players.size() - 1; i++) {
                if(players.get(i).compareTo(players.get(i + 1)) != 0) draw = false;
            }
            if(draw) {
                System.out.print("Players 1");
                for(int i = 1; i < players.size() - 1; i++) System.out.print(", " + (i + 1));
                System.out.println(" and " + players.size() + " draw.");
            }
            else {
                System.out.println("Player " + players.get(players.size() - 1).id + " wins.");
            }
        }
    }

    private static ArrayList<Card> createDeck() {
        ArrayList<Card> r = new ArrayList<Card>();
        for(char i = '2'; i <= '9'; i++) {
            r.add(new Card('C', i));
            r.add(new Card('D', i));
            r.add(new Card('H', i));
            r.add(new Card('S', i));
        }
        r.add(new Card('C', 'T')); r.add(new Card('D', 'T')); r.add(new Card('H', 'T')); r.add(new Card('S', 'T'));
        r.add(new Card('C', 'J')); r.add(new Card('D', 'J')); r.add(new Card('H', 'J')); r.add(new Card('S', 'J'));
        r.add(new Card('C', 'Q')); r.add(new Card('D', 'Q')); r.add(new Card('H', 'Q')); r.add(new Card('S', 'Q'));
        r.add(new Card('C', 'K')); r.add(new Card('D', 'K')); r.add(new Card('H', 'K')); r.add(new Card('S', 'K'));
        r.add(new Card('C', 'A')); r.add(new Card('D', 'A')); r.add(new Card('H', 'A')); r.add(new Card('S', 'A'));
        return r;
    }

    private static boolean existCard(ArrayList<Card> deck, Card card) {
        for(int i = 0; i < deck.size(); i++) {
            if((deck.get(i).name == card.name) && (deck.get(i).suit == card.suit)) {
                deck.remove(i);
                return true;
            }
        }
        return false;
    }

    private static ArrayList<Card> parseArguments(String[] args) {
        ArrayList<Card> r = new ArrayList<Card>();
        if(args.length == 0) {
            System.out.println("Error: wrong number of arguments; must be a multiple of 5!");
            return null;
        }
        if((args.length % 5) != 0) {
            System.out.println("Error: wrong number of arguments; must be a multiple of 5!");
            return null;
        }
        ArrayList<Card> deck = createDeck();
        for(int i = 0; i < args.length; i++) {
            String s = args[i].toUpperCase();
            if(s.length() != 2) return null;
            char name = s.charAt(0);
            char suit = s.charAt(1);
            if(!(((name >= '2') && (name <= '9')) || (name == 'T') || (name == 'J') ||
                    (name == 'Q') || (name == 'K') || (name == 'A'))) {
                System.out.println("Error: invalid card name '" + args[i] + "'");
                return null;
            }
            if(!((suit == 'C') || (suit == 'D') || (suit == 'H') || (suit == 'S'))) {
                System.out.println("Error: invalid card suit '" + args[i] + "'");
                return null;
            }
            Card card = new Card(suit, name);
            if(!existCard(deck, card)) {
                System.out.println("Error: card '" + args[i] + "' not exist");
                return null;
            }
            r.add(new Card(suit, name));
        }
        return r;
    }

    public static void main(String[] args) {
        ArrayList<Card> r = parseArguments(args);
        if(r != null) new Poker(r);
    }
}
