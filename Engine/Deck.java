package Engine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import Cards.*;
import Utils.CardRarities;

public class Deck implements Serializable {

  private static final long serialVersionUID = -1752266628716769694L;
  
  private ArrayList<Card> cards = new ArrayList<Card>();

  /**
   * creates cards based on game settings
   * <br> when needed, assigns them random rarity
   */
  public Deck() {

    for (int i = 0; i < Game.getAmount_cards_attack(); i++) {
      Card_Attack c = new Card_Attack();
      cards.add(c);
    }

    for (int i = 0; i < Game.getAmount_cards_heal_castle(); i++) {
      Card_HealCastle c = new Card_HealCastle();
      c.setCardRarities(generateCardRarities());
      cards.add(c);
    }

    for (int i = 0; i < Game.getAmount_cards_heal_wall(); i++) {
      Card_HealWall c = new Card_HealWall();
      c.setCardRarities(generateCardRarities());
      cards.add(c);
    }

    for (int i = 0; i < Game.getAmount_cards_pierce_wall(); i++) {
      Card_PierceWall c = new Card_PierceWall();
      c.setCardRarities(generateCardRarities());
      cards.add(c);
    }

    for (int i = 0; i < Game.getAmount_cards_drop_cards(); i++) {
      Card_DropCards c = new Card_DropCards();
      c.setCardRarities(generateCardRarities());
      cards.add(c);
    }

    for (int i = 0; i < Game.getAmount_cards_steal_card(); i++) {
      Card_StealCard c = new Card_StealCard();
      cards.add(c);
    }

    for (int i = 0; i < Game.getAmount_cards_swap_card(); i++) {
      Card_SwapCard c = new Card_SwapCard();
      cards.add(c);
    }

    for (int i = 0; i < Game.getAmount_cards_extra_card(); i++) {
      Card_ExtraCard c = new Card_ExtraCard();
      cards.add(c);
    }

    for (int i = 0; i < Game.getAmount_cards_soldier(); i++) {
      Card_Soldier c = new Card_Soldier();
      c.setCardRarities(generateCardRarities());
      cards.add(c);
    }

    for (int i = 0; i < Game.getAmount_cards_kill_soldier(); i++) {
      Card_KillSoldier c = new Card_KillSoldier();
      cards.add(c);
    }
  }

/**
 * @param cards to add to deck
 * used when loading game
 */
  public Deck(ArrayList<Card> cards) {
    this.cards = cards;
  }

  /**
   * @return rarity generated
   */
  public CardRarities generateCardRarities() {
    Random rand = new Random();
    int i = rand.nextInt(100);
    CardRarities r;
    if (i < 70) {
      r = CardRarities.COMMON;
    } else if (i > 90) {
      r = CardRarities.RARE;
    } else {
      r = CardRarities.MEDIUM;
    }
    return r;
  }

  /**
   * deals cards to players
   */
  public void dealCards() {
    for (Player p : Game.getInstance().getPlayers()) {
      for (int i = 0; i < Game.getStarting_Cards(); i++) {
        p.pickCard();
      }
    }
  }

  /**
   * @param card to add to bottom of deck
   */
  public void addCard(Card card) {
    cards.add(card);
  }

/**
 * @return top card, which is removed from deck 
 */
  public Card getTopCard() {
    return this.cards.remove(0);
  }

  /**
   * shuffles cards in deck
   */
  public void shuffle() {
    Collections.shuffle(cards);
  }

}
