# disc-bot

Floor Bot (Discord bot)

Discord bot built in java that simulates a multiplayer RPG world within a discord server. Supports 26 commands that
range from combat to trading with other players. All player and game information is saved and accessed through
a SQL database. The bot.sql file is the stucture of the databse used to play the game.

Players move through floors, with different types of monsters and shops on each floor, earning exp, items, etc.
At the end of each floor is a boss that you must defeat to gain access to the next floor. As the floor increases, 
so does the quality of the items and difficulty. 

Supported commands include: 

     create <username> 
     Create a character with the given username if you do not already have one. 

    players 
     Shows a list of all current players and their level/floor. 

    char 
     Displays your current character's information. 

    location 
     Gives your current floor and map. 

    stats 
     Shows your current combat stats. 

    coins 
     Shows how much money you have. 

    delete 
     Deletes your character if you have one. 

    floorinfo 
     Shows floor and map information for your current floor. 

    floorup 
     Moves you to the next floor if you have access. 

    floordown 
     Moves you to the next floor down. 

    mm 
     Shows monsters available to fight on current map with in-depth information. 

    right 
     Move right to a new map. 

    left 
     Move left to a new map. 

    equips 
     Shows your current equipment w/ their stats. 

    dequip <item> 
     Take off an equipped item and put it back in your inventory. 

    inventory 
     Shows items in your bag. 

    checkitem <item name> 
     Shows in-depth information about an item in your inventory. 

    fight <monster> 
     Fight one of the monsters on your map. 

    potion 
     Use a potion from your inventory. 

    equip <weapon name> 
     Equip a weapon/armor from your inventory. 

    buy <item name> 
     Buy item from your floor shop. 

    sell <item name> 
     Sell inventory item to shop. 

    tradeitem <item name> <username> 
     Trade item to another player. 

    tradecoins <coin amount> <username> 
     Trade coins to another player. 

    bossinfo 
     In-depth info about the floor boss. 

    bossfight 
     Challenge the floor boss to gain access to the next floor.
