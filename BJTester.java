import java.util.Scanner;

public class BJTester {
	public static void main(String[] args){
		Scanner kb=new Scanner(System.in);
		boolean b=true;
		int cards=10;
		while(cards>7&&b){
			BlackJack a=new BlackJack();
			a.playGame();
			System.out.println();
			System.out.println("Would you like to play again?");
			String s=kb.nextLine();
			if(!s.equals("yes"))
				b=false;
			cards=a.deckk().size();
		}
	}
}
