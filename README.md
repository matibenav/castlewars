# Castlewars


Juego 
		(singleton)
    id 
    players[]
    turnOrder
    turn
        player
        Draw Card (1)
        Add Soldier (*)
        Use Card (1)
    Deck
        configure
        Cards { N }

Player
    attr: castle (100)
    attr: cards (5)
    attr: soldiers
    attr: wall (30)
    
Soldier
   life: 1
   damage: 40
    
Interface: Targeteable
    whenTarget


	interface
		gui/console
			paint
			show settings
			other menus

Card
    attr: rarity (70 / 20 / 10)
    target: { wall, castle, soldier, card }
    effect:
        Attack:
            Usa | soldiers |
            target: { wall || castle }
        Heal Castle: { 10 / 20 / 30 }
        Heal Wall: { 5 , 10 , 15 }
        Pierce Wall: 
            NO usa | soldiers | (Daño directo)
            target: wall
            damage: { 5, 10, 30 }
        Drop Card
            target: Card
            amount: { 1, 2, 3 }
        Steal Card
            target: Card
        Exchange Card (Random)
            target: Card
            amount: { 1, 2, 3 }
        Kill Soldier
            target: Soldiers
            amount: 1
        Soldier
        Turn:
            +1 Turno

