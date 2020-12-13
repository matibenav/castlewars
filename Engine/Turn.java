package Engine;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Cards.Card;

public class Turn implements Serializable {

  private static final long serialVersionUID = -1703386930793100181L;

  private String description;
  private Player owner;
  private boolean pickedFromDeck;
  private Card cardPlayed;

  public Turn(Player owner, String description, boolean pickedFromDeck, Card cardPlayed) {

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = Calendar.getInstance().getTime();
    String strDate = dateFormat.format(date);

    this.owner = owner;
    this.cardPlayed = cardPlayed;
    this.pickedFromDeck = pickedFromDeck;
    this.description = strDate + " - " + owner.getName() + " " + description;
  }

  /**
   * @return the cardPlayed
   */
  public Card getCardPlayed() {
    return cardPlayed;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @return the owner
   */
  public Player getOwner() {
    return owner;
  }

  /**
   * @return the pickedFromDeck
   */
  public boolean isPickedFromDeck() {
    return pickedFromDeck;
  }

}
