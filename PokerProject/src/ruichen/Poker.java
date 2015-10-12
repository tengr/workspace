package ruichen;

import java.util.ArrayList;
import java.util.Collections;


public class Poker {
    private ArrayList<Player> players;

    public Poker(ArrayList<Card> cards) {
    	players = new ArrayList<Player>();
        for(int i = 0; i < cards.size(); i += 5) {
            int id = (i / 5) + 1;
            players.add(new Player(id, new Hand((ArrayList<Card>) cards.subList(i, i + 4))));
        }

        for(int i = 0; i < players.size(); i++) {
            System.out.println("Player " + (i + 1) + ": " + players.get(i).toString());
        }

        if(players.size() > 1) {
            Collections.sort(players);
            int numberOfWinners = 1;
            //boolean draw = true;
            for(int i = players.size() - 1; i > 0; i--) {
                if(players.get(i).compareTo(players.get(i - 1)) == 0) numberOfWinners++;
                else break;
            }
            if(numberOfWinners == 1) {
                System.out.println("Player " + players.get(players.size() - 1).id + " wins.");
            }
            else {
            	System.out.println("Players " + players.get(players.size() - numberOfWinners).id);
                for(int i = players.size() - numberOfWinners + 1; i < players.size() - 1; i++) 
                	System.out.print(", " + players.get(i).id);
                System.out.println(" and " + players.get(players.size() - 1) + " draw.");
            }
         
        }
    }



    private static ArrayList<Card> parseArguments(String[] args) {
        ArrayList<Card> r = new ArrayList<Card>();
        if(args.length == 0 || (args.length % 5) != 0) {
            System.out.println("Error: wrong number of arguments; must be a multiple of 5");
            return null;
        }
        for(int i = 0; i < args.length; i++) {
            String cardString = args[i].toUpperCase();
            
            if(cardString.length() != 2) return null;
  
            Card aCard = new Card(Enum.valueOf(Rank.class, "_" + cardString.substring(0,0)), Enum.valueOf(Suit.class, cardString.substring(1, 1)));

            r.add(aCard);
        }
        return r;
    }

    public static void main(String[] args) {
        ArrayList<Card> r = parseArguments(args);
		Card aCard = new Card(Enum.valueOf(Rank.class,"_2"), Enum.valueOf(Suit.class,"C"));
		System.out.println(aCard.suit + " " + aCard.rank);
        //if(r != null) new Poker(r);

    }
}

