import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BlackJack implements Game {
	private ArrayList<Card> player_cards=new ArrayList<Card>();
	private ArrayList<Card> dealer_cards=new ArrayList<Card>();
	private Deck deckUsed;
	private int times;
	
	public BlackJack(){
		String[] ranks={"ace","2","3","4","5","6","7","8","9","10","jack","queen","king"};
		String[] suits={"Hearts","Spades","Diamonds","Clubs"};
		int[] values={11,2,3,4,5,6,7,8,9,10,10,10,10};
		deckUsed=new Deck(ranks,suits,values);
		deckUsed.shuffle();
	}
	public Deck deckk(){
		return deckUsed;
	}
	public void showCards(ArrayList<Card> cards){
		System.out.println(cards);
	}
	public void sortCards(ArrayList<Card> cards){
		int count=0;
		Collections.sort(cards);
		for(int i=cards.size()-1;i>=0;i--){
			cards.set(count,cards.get(i));
			count++;
		}
	}
	public int totalCards(ArrayList<Card> cards){
		int total=0;
		for(int i=0;i<cards.size();i++){
			total=total+cards.get(i).getValue();
		}
		for(int k=0;k<cards.size()&&total>21;k++)
			if(cards.get(k).getValue()==11)
				total=total-11;
		return total;
	}
	public boolean playerTurn(){
		Scanner kb=new Scanner(System.in);
		boolean hit=true;
		int total=totalCards(player_cards);
		showCards(player_cards);
		  while(total<21&&hit){
			System.out.println();
			System.out.println("do you want another card?");
			String a=kb.nextLine();
			if(!a.equals("yes"))
				hit=false;
			else{
				player_cards.add(deckUsed.deal());
				total=totalCards(player_cards);
				showCards(player_cards);
			}
		}
		if(total==21)
			gameOver("BLACKJACK! you");
		else if(total>21){
		    System.out.println("bust");
		    gameOver("the dealer");
		}
		return hit;
	}
	
	public void dealerTurn(){
		int total=totalCards(dealer_cards);
		while(total<17){
			dealer_cards.add(deckUsed.deal());
			total=totalCards(dealer_cards);
		}
	}
		
	public void setUp(){
		player_cards.add(deckUsed.deal());
		player_cards.add(deckUsed.deal());
		dealer_cards.add(deckUsed.deal());
		dealer_cards.add(deckUsed.deal());
	}
	public void playGame(){
		if(times==0){
			setUp();
			System.out.println(goal());
			times++;
		}
		System.out.println();
		System.out.println("dealer's hand:");
		System.out.println(dealer_cards.get(0));
		System.out.println();
		System.out.println("Your hand:");
		playerTurn();
		if(totalCards(dealer_cards)<21&&totalCards(player_cards)<21){
			System.out.println();
			System.out.println("Dealer's turn\n");
			dealerTurn();
			if(totalCards(dealer_cards)<17){
				System.out.println("Your turn again");
				playGame();
			}
			if(totalCards(dealer_cards)>=17&&totalCards(dealer_cards)<21){
				if(totalCards(player_cards)==totalCards(dealer_cards))
					gameOver("no one");
				else if(totalCards(player_cards)>totalCards(dealer_cards))
					gameOver("you");
				else if(totalCards(player_cards)<totalCards(dealer_cards))
					gameOver("the dealer");
				showCards(dealer_cards);
			}
			if(totalCards(dealer_cards)==21){
				System.out.println("Dealer BlackJack");
				gameOver("the dealer");
				System.out.println("dealer's cards were:");
				showCards(dealer_cards);
			}
			if(totalCards(dealer_cards)>21){
				System.out.println("Blackjack! The dealer busted");
				gameOver("you");
				System.out.println("dealer's cards were:");
				showCards(dealer_cards);
			}
		}
	}
	
	public String goal(){
		return "BlackJack: to win you have to get as close to 21 as you can without going over";
	}
	public void gameOver(String winner){
		System.out.println(winner+" won the game");
	}
}
