package Utils;

import java.util.Comparator;
import Cards.Card;

public class CardComparator implements Comparator<Card> {

  public int compare(Card c1, Card c2) {
    return c1.getDescription().compareTo(c2.getDescription());
  } 
  
}
