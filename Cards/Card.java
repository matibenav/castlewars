package Cards;

import java.io.Serializable;

import Utils.CardRarities;

public abstract class Card implements Serializable, Comparable <Card>{

  private static final long serialVersionUID = 2264124275339340965L;

  protected String description;
  
  private CardRarities cardRarities;
  
  public Card (String description) {
    this.description = description;
  }
  
  public abstract void effect();

  public int compareTo(Card c) {
    return this.description.compareTo(c.getDescription());
  }

  public String getDescription() {
    return description;
  }

  public CardRarities getCardRarities() {
    return cardRarities;
  }

  public void setCardRarities(CardRarities cardRarities) {
    this.cardRarities = cardRarities;
  }

}
