package Cards;

import Engine.Player;
import Utils.Targeteable;

enum Rarity {
    COMMON,
    MEDIUM,
    RARE
}

public abstract class Card {
    public Rarity rarity;
    public Utils.Targeteable target;

    public abstract void effect(Player player);

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public void setTarget(Targeteable target) {
        this.target = target;
    }
}

/**
 * Card
 *     attr: rarity (70 / 20 / 10)
 *     target: { wall, castle, soldier, card }
 *     effect:
 *         Attack:
 *             Usa | soldiers |
 *             target: { wall || castle }
 *         Heal Castle: { 10 / 20 / 30 }
 *         Heal Wall: { 5 , 10 , 15 }
 *         Pierce Wall:
 *             NO usa | soldiers | (Daño directo)
 *             target: wall
 *             damage: { 5, 10, 30 }
 *         Drop Card
 *             target: Card
 *             amount: { 1, 2, 3 }
 *         Steal Card
 *             target: Card
 *         Exchange Card (Random)
 *             target: Card
 *             amount: { 1, 2, 3 }
 *         Kill Soldier
 *             target: Soldiers
 *             amount: 1
 *         Soldier
 *         Turn:
 *             +1 Turno
 */
