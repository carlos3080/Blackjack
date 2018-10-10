import java.util.ArrayList;

public class Deck{
	private Card[] deck=new Card[53];
	private Card[] deckNoChange;
	private ArrayList<Card> discarded=new ArrayList<Card>();
	private int size;
	
	public Deck(){
		String[] ranks={"ace","2","3","4","5","6","7","8","9","10","jack","queen","king"};
		String[] suits={"Hearts","Spades","Diamonds","Clubs"};
		int count=0;
		for(int i=0;i<suits.length;i++)
			for(int k=0;k<ranks.length;k++){
				if(suits[i].equals("Hearts")||suits[i].equals("Diamonds")){
				deck[count]=new Card(ranks[k],suits[i],k+1,"red");
				count++;
				}
				else{
					deck[count]=new Card(ranks[k],suits[i],k+1,"black");
					count++;
				}
			}
		//deck [52]=new Card("joker","joker",00);
		count++;
		size=count;
		shuffle();
	}
	public Deck(String[] a,String[] b,int[] n){
		int count=0;
		for(int i=0;i<b.length;i++)
			for(int k=0;k<a.length;k++){
				Card c=new Card(a[k],b[i],n[k],"red");
				deck[count]=c;
				count++;
				}
		shuffle();
		size=count;
		deckNoChange=deck;
	}
	public void shuffle(){
		for(int i=0;i<size/2;i++){
			int a=(int)(Math.random()*size);
			int b=(int)(Math.random()*size);
			Card c=deck[a];
			deck[a]=deck[b];
			deck[b]=c;
		}
	}
	public Card deal(){
		for(int i=0;i<deck.length;i++){
			if(deck[i]!=null){
				discarded.add(deck[i]);
				Card c=deck[i];
				deck[i]=null;
				size--;
				return c;
			}
		}
		return null;
	}
	public Card[] deal(int n, int num){
		Card[] a=new Card[num];
		if(size<n)
			throw new IllegalArgumentException("yo chill");
		else{
			for(int k=0;k<n;k++)
				for(int i=0;i<deck.length;i++)
					if(deck[i]!=null){
						a[k]=deck[i];
						discarded.add(deck[i]);
						deck[i]=null;
						i=deck.length;
					}
			size=size-n;
		}
		return a;
	}
	public void shuffleFullDeck(){
		deck=deckNoChange;
		shuffle();
		size=deck.length;
		discarded=null;
	}
	public int size(){
		return size;
	}
	public boolean isEmpty(){
		if(size==0)
			return true;
		return false;
	}
	public String toString(){
		int count=1;
		String str="";
		for(Card c:deck)
			if(c!=null){
				str=str+count+"."+c.toString()+", ";
				count++;
			}
		return str;
	}
	public void showDiscardedCards(){
		System.out.println(discarded);
	}
}
