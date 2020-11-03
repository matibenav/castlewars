package Cards;

import Utils.Rarity;

public abstract class Card {
  
    private Rarity rarity;
    
    // opt index ONLY used on killSoldier & exchange
    // player = self (healCastle, healWall, extraCard & soldier)
    public abstract void effect();

    public Rarity getRarity() {
      return rarity;
    }
    
    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

}

