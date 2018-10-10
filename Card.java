
public class Card implements Comparable{
	private String rank;
	private String suit;
	private int value;
	private String color;
	public Card(String a, String b, int n, String c){
		rank=a;
		suit=b;
		value=n;
		color=c;
	}
	public String getColor(){
		return color;
	}
	public String getRank(){
		return rank;
	}
	public String getSuit(){
		return suit;
	}
	public int getValue(){
		return value;
	}
	public void setValue(int a){
		value=a;
	}
	public boolean equals(Object c){
		if(((Card)c).getValue()==getValue()&&((Card)c).getColor().equals(getColor()))
			return true;
		return false;
	}
	public boolean equals2(Card c){
		if(c.getValue()==getValue()&&c.getRank().equals(getRank())&&c.getSuit().equals(getSuit()))
			return true;
		return false;
	}
	public boolean matc(Card c){
		if(c.getRank().equals(getRank()))
			return true;
		return false;
	}
	public String toString(){
		return rank+" of "+suit;
	}
	public int compareTo(Object c){
		if(((Card)c).getValue()<getValue())
			return 3;
		if(((Card)c).getValue()>getValue())
			return -3;
		return 0;
	}

}
