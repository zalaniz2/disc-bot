package discord.bot.commands;

import java.util.ArrayList;
import java.util.List;

import discord.bot.Main;
import discord.bot.controller.ItemController;
import discord.bot.controller.LevelsController;
import discord.bot.controller.FloorController;
import discord.bot.controller.MonsterController;
import discord.bot.controller.PlayerController;
import discord.bot.domain.Item;
import discord.bot.domain.Levels;
import discord.bot.domain.Floor;
import discord.bot.domain.Map;
import discord.bot.domain.Monster;
import discord.bot.domain.Player;
import discord.bot.game.Combat;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import java.util.Random;


public class Commands extends ListenerAdapter{
	
	private PlayerController pc = new PlayerController();
	private FloorController fc = new FloorController();
	private MonsterController mc = new MonsterController();
	private ItemController ic = new ItemController();
	private LevelsController lc = new LevelsController();
	private Random rand;

	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		//event for everytime a message is sent 
		String[] args = event.getMessage().getContentRaw().split(" ");
		
		
		if( args[0].equalsIgnoreCase( Main.prefix + "commands") ) { //check for >info command
											
			event.getChannel().sendTyping().queue(); //pretend bot is typing 
			event.getChannel().sendMessage("**Commands** \n \n " + 
										  "**create <username>** \n `Create a character with the given username if you do not already have one.` \n \n" +
										  "**players** \n `Shows a list of all current players and their level/floor.` \n \n" +
										   "**char** \n `Displays your current character's information.` \n \n"+
										  "**location** \n `Gives your current floor and map.` \n \n" +
										   "**stats** \n `Shows your current combat stats.` \n \n" +
										  "**coins** \n `Shows how much money you have.` \n \n" + 
										   "**delete** \n `Deletes your character if you have one.` \n \n" +
										   "**floorinfo** \n `Shows floor and map information for your current floor.` \n \n" +
										   "**floorup** \n `Moves you to the next floor if you have access.` \n \n" +
										   "**floordown** \n `Moves you to the next floor down.` \n \n" +
										   "**mm** \n `Shows monsters available to fight on current map with in-depth information.` \n \n" +
										   "**right** \n `Move right to a new map.` \n \n" +
										   "**left** \n `Move left to a new map.` \n \n" +
										   "**equips** \n `Shows your current equipment w/ their stats.` \n \n" +
										   "**dequip <item>** \n `Take off an equipped item and put it back in your inventory.` \n \n" +
										   "**inventory** \n `Shows items in your bag.` \n \n" +
										   "**checkitem <item name>** \n `Shows in-depth information about an item in your inventory.` \n \n" +
										   "**fight <monster>** \n `Fight one of the monsters on your map.` \n \n" + 
										   "**potion** \n `Use a potion from your inventory.` \n \n" +
										   "**equip <weapon name>** \n `Equip a weapon/armor from your inventory.` \n \n" + 
										   "**buy <item name>** \n `Buy item from your floor shop.` \n \n" + 
										   "**sell <item name>** \n `Sell inventory item to shop.` \n \n" +
										   "**tradeitem <item name> <username>** \n `Trade item to another player.` \n \n" +
										   "**tradecoins <coin amount> <username>** \n `Trade coins to another player.` \n \n" +
										   "**bossinfo** \n `In-depth info about the floor boss.` \n \n" +
										   "**bossfight** \n `Challenge the floor boss to gain access to the next floor.` \n \n"  
			).queue();
		
		}
		
		if( args[0].equalsIgnoreCase(Main.prefix + "create") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing 
			
			String discordUserId = event.getAuthor().getAvatarId();

			
			List<Player> check = new ArrayList<Player>();
			check = pc.selectPlayer(discordUserId);
			
			if(args.length < 2) {
				event.getChannel().sendMessage("`Invalid format for command, please provide a username.`").queue();
				return;
			}
			
			if( check.size() == 0) {
				
				Player newPlayer = new Player();
				newPlayer.setDiscid(discordUserId);
				newPlayer.setUsername(args[1]);
				
				List<Player> users = pc.selectPlayerByUsername(newPlayer.getUsername());
				
				if( users.size() == 0) {
					System.out.println("Creating new user: ID = " + newPlayer.getDiscid() + " Username: " + newPlayer.getUsername());
					
					int result = pc.createPlayer(newPlayer);
					System.out.println("Result is " + result);
					
					event.getChannel().sendMessage("`Your character has been created, welcome " + newPlayer.getUsername() + "!`").queue();
					
				}
				else {
					event.getChannel().sendMessage("`A player with the username '" + newPlayer.getUsername() + "' already exists.`").queue();
				}
				
			
				
			}
			else {
				event.getChannel().sendMessage("`You already have a character linked to your account.`").queue();

			}

		}
		
		if( args[0].equalsIgnoreCase(Main.prefix + "players")) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing 
			
			List<Player> players = new ArrayList<Player>();
			String plist = "**Current players are:** \n \n";
			
			players = pc.selectAll();
			
		
			for( int i = 0; i<players.size(); i++) {
				
				plist += "**" + players.get(i).getUsername() + "** \n `Level " + players.get(i).getLvl() + ", Floor " + players.get(i).getFloor() + "` \n \n";

			}
			
			event.getChannel().sendMessage(plist).queue();

		}
		
		if( args[0].equalsIgnoreCase(Main.prefix + "char") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing 
			
			String id = event.getAuthor().getAvatarId();
			
			List<Player> p = new ArrayList<Player>();
			
			p = pc.selectPlayer(id);

			
			if( p.size() == 0 ) {
				
				event.getChannel().sendMessage("`You do not have a character yet.`").queue();
				
			}
			else {
				String charInfo = "**Your character info:** ``` \n Username:  " + p.get(0).getUsername() + "\n Level:     " + p.get(0).getLvl() + "\n Exp:       " + p.get(0).getExp() + "\n Percent:   " + p.get(0).getPercent() + "% \n Cash:      " + p.get(0).getMoney() + "$ \n Location:  Floor " + p.get(0).getFloor() + " \n            Map   " + p.get(0).getMap() + "\n Attack:   " + p.get(0).getAtt() + "\n Defense:  " + 
						p.get(0).getDef() + " \n Health:   " + p.get(0).getHp() + "/"  + p.get(0).getMaxhp() + "\n Floor 1 Access: " + p.get(0).isFa1() + "\n Floor 2 Access: " + p.get(0).isFa2() + "\n Floor 3 Access: " + p.get(0).isFa3()  + "\n Floor 4 Access: " + p.get(0).isFa4() + "\n Floor 5 Access: " + p.get(0).isFa5() + "\n ```";
				
				event.getChannel().sendMessage(charInfo).queue();

			}
			
			
		}
		
		if( args[0].equalsIgnoreCase(Main.prefix + "delete")){
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing 
			
			String id = event.getAuthor().getAvatarId();
			
			int p = pc.deletePlayer(id);
			
			if( p == 1) {
				event.getChannel().sendMessage("`You character has been deleted.`").queue();
				
			}
			else {
				event.getChannel().sendMessage("`You don't have a character to delete.`").queue();
			}
			
		}
		
		
		if( args[0].equalsIgnoreCase(Main.prefix + "floorinfo") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing 
			
			String id = event.getAuthor().getAvatarId();
			List<Player> p = new ArrayList<Player>();
			p = pc.selectPlayer(id);
			
			Player play = p.get(0); //hold player
			
			List<Map> maps = new ArrayList<Map>();
			maps = fc.selectAllFloorMaps(p.get(0).getFloor()); //hold all floor maps
			
			List<Floor> playerFloor = new ArrayList<Floor>();
			playerFloor = fc.selectFloor(play.getFloor());
			
			Floor pf = playerFloor.get(0); //holds players floor
			
			List<Monster> bossMon = new ArrayList<Monster>();
			bossMon = mc.selectFloorBoss(play.getFloor());
			
			Monster boss = bossMon.get(0);
						
			String floorInfo = "**Floor " + play.getFloor() + "** ```Name: " + pf.getName() + " \n Levels: "
								+ pf.getRequirement() + "+ \n Boss: " + boss.getName() + ", Level " + boss.getLvl() + "\n Maps: " + pf.getMaps() + "``` \n";
			
			List<Monster> mapMonsters = new ArrayList<Monster>();
			
			for( int i = 0; i<maps.size(); i++) {
				
				Map cur = maps.get(i);
				floorInfo += "**Map " + cur.getNumber() + "** ``` \n Map Name: " + cur.getName() + " \n Monsters:  \n ";
				mapMonsters = mc.selectMonsters(cur.getFloor(), cur.getNumber());
				for( int j = 0; j<mapMonsters.size(); j++) {
					Monster m = mapMonsters.get(j);
					floorInfo += "Name: " + m.getName() + ", Level " + m.getLvl() + " \n ";
				}
				
				floorInfo += "``` \n";
			}
			
			event.getChannel().sendMessage(floorInfo).queue();
				
		}
		
		if( args[0].equalsIgnoreCase(Main.prefix + "mm") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing 
			
			String id = event.getAuthor().getAvatarId();
			List<Player> p = new ArrayList<Player>();
			p = pc.selectPlayer(id); //get player issuing command 
			
			List<Monster> monsters = new ArrayList<Monster>();
			monsters = mc.selectMonsters(p.get(0).getFloor(), p.get(0).getMap());
			
			String monsterInfo = "**Monsters in your map:** ``` \n";
			
			List<Item> monsterDrops = new ArrayList<Item>();
			
			for( int i = 0; i<monsters.size(); i++) {
				
				Monster m = monsters.get(i);
				monsterInfo += " Name: " + m.getName() + "\n Level:   " + m.getLvl() + "\n Attack:  " + m.getAtt() + "\n Defense: " + m.getDef() + "\n HP:      " + m.getHp() + "\n Coins:   " + m.getCoins() + "\n Exp:     " + m.getExp() + "\n Drops: \n";
				monsterDrops = ic.selectMonsterDrops(m);
				for( int j = 0; j<monsterDrops.size(); j++) {
					Item drop = monsterDrops.get(j);
					monsterInfo += "  " + drop.getName() + ", Drop Rate: " + drop.getRate() + "0% \n";
				}
				monsterInfo += "\n \n";
			}
			monsterInfo += "```";
			
			event.getChannel().sendMessage(monsterInfo).queue();

			
		}
		
		if( args[0].equalsIgnoreCase(Main.prefix + "right") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing
			
			String id = event.getAuthor().getAvatarId();
			List<Player> p = new ArrayList<Player>();
			p = pc.selectPlayer(id); //get player issuing command 
			
			Player pl = p.get(0);
			
			List<Floor> f = new ArrayList<Floor>();
			f = fc.selectFloor(p.get(0).getFloor());
			
			Floor fl = f.get(0);
			
			if( pl.getMap() < fl.getMaps()) {
				
				pl.setMap(pl.getMap() + 1);
				int playerUpdate = pc.updatePosition(pl);
				event.getChannel().sendMessage("`Moved to Map " + pl.getMap() + ", Floor " + pl.getFloor() + ".`").queue();
				
			}
			else {
				event.getChannel().sendMessage("`You are on the last map of this floor.`").queue();
			}
			
			
		}
		
		if( args[0].equalsIgnoreCase(Main.prefix + "left") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing
			
			String id = event.getAuthor().getAvatarId();
			List<Player> p = new ArrayList<Player>();
			p = pc.selectPlayer(id); //get player issuing command 
			
			Player pl = p.get(0);
			
			if( pl.getMap() == 1) {
				
				event.getChannel().sendMessage("`You are on the first map of this floor.`").queue();
				
			}
			else {
				
				pl.setMap(pl.getMap() - 1);
				int playerUpdate = pc.updatePosition(pl);
				event.getChannel().sendMessage("`Moved to Map " + pl.getMap() + ", Floor " + pl.getFloor() + ".`").queue();
			}
			
			
		}
		
		
		if( args[0].equalsIgnoreCase(Main.prefix + "equips") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing
			
			String id = event.getAuthor().getAvatarId();
			List<Player> p = new ArrayList<Player>();
			p = pc.selectPlayer(id); //get player issuing command
			
			List<Item> arm = new ArrayList<Item>();
			
			arm = ic.selectPlayerEquips(p.get(0));
			
			String equipInfo = "**Currently Equipped:** ``` \n";
			
			if(arm.size() == 0) {
				equipInfo += "Nothing. \n \n";
			}
			else {
				
				for( int i = 0; i<arm.size(); i++) {
					equipInfo += "Name: " + arm.get(i).getName() + "\n Attack:  +" + arm.get(i).getAtt() + "\n Defense: +" + arm.get(i).getDef() + "\n Class:   " + arm.get(i).getClassification() + "\n \n";
				}
				
			}
			
			equipInfo += "```";
			
			event.getChannel().sendMessage(equipInfo).queue();

		
		}
		
		if( args[0].equalsIgnoreCase(Main.prefix + "inventory") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing)
			
			String id = event.getAuthor().getAvatarId();
			List<Player> p = new ArrayList<Player>();
			p = pc.selectPlayer(id); //get player issuing command
			
			List<Item> inv = new ArrayList<Item>();
			inv = ic.selectPlayerItems(p.get(0));
			
			String out = "**Inventory:** ``` \n";
			
			if( inv.size() == 0) {
				out += "Nothing \n \n";
			}
			else {
				
				for( int i = 0; i<inv.size(); i++) {
					out += " - " + inv.get(i).getName() + "\n \n";
				}
				
			}
			
			out += "```";
			
			event.getChannel().sendMessage(out).queue();

			
		}
		
		if( args[0].equalsIgnoreCase(Main.prefix + "fight") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing)
			
			//get player fighting, and the mosters on their current map
			
			if( args.length < 3) {
				event.getChannel().sendMessage("`Invalid arguments, please provide a monster name.`").queue();
				return;
			}
			
			String id = event.getAuthor().getAvatarId();
			List<Player> p = new ArrayList<Player>();
			p = pc.selectPlayer(id); //get player issuing command
			
			Player play = p.get(0);

			
			List<Monster> monsters = new ArrayList<Monster>();
			
			String monName = args[1] + " " + args[2];
		
			
			monsters = mc.selectMonsterToFight(play, monName);
			
			if( monsters.size() == 0) {
				event.getChannel().sendMessage("`Invalid: Either that monster is not in your current floor/map, or you typed in the wrong name.`").queue();
				return;

			}
			
			Monster mon = monsters.get(0);
			
			String fightOut = "```";
			
			List<Levels> lvls = new ArrayList<Levels>();
			
			Combat c = new Combat();
			boolean won;
						
			lvls = lc.selectPlayerLevel(play);
		
		    won = c.fight(play, mon);

			
			if( won ) {
				
				fightOut += "You defeated a(n) " + mon.getName() + ". \n";
				
				fightOut = c.updateExp(play, mon.getExp(), (ArrayList<Levels>) lvls, fightOut);
				int ud = pc.updatePlayerCombat(play);
				List<Item> drops = ic.selectMonsterDrops(mon);
				fightOut = c.updateInventory(play, (ArrayList<Item>) drops, mon.getCoins(), fightOut);
				fightOut += "```";
				event.getChannel().sendMessage(fightOut).queue();
				
			}
			else {
				play.setHp(play.getMaxhp() / 2);
				int expLoss = play.getLvl() * mon.getExp();
				if( expLoss > play.getExp() ) {
					play.setExp(0);
					play.setPercent(0.00);
				}
				else {
					play.setExp(play.getExp() - expLoss);
					double pcnt = (double) play.getExp() / (double) lvls.get(0).getExp();
					play.setPercent(pcnt);
				}
				int d = pc.updatePlayerCombat(play);
				
				fightOut += "You died to a(n) " + mon.getName() + ".. \n";
				fightOut += "Lost " + expLoss + " exp.. \n";
				fightOut += "HP: " + play.getHp() + "/" + play.getMaxhp() + " ```";

				event.getChannel().sendMessage(fightOut).queue();
			}
			
			
		}
		
		if( args[0].equalsIgnoreCase(Main.prefix + "potion") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing)
			

			String id = event.getAuthor().getAvatarId();
			List<Player> p = new ArrayList<Player>();
			p = pc.selectPlayer(id); //get player issuing command
			
			Player play = p.get(0);
			
			List<Item> potions = ic.selectInventoryPotions(play);
			
			if( potions.size() == 0) {
				event.getChannel().sendMessage("`You have no potions.`").queue();

			}
			else {
				
				int newHp = play.getHp() + potions.get(0).getHp();
				int gained = potions.get(0).getHp();
				
				if( play.getHp() == play.getMaxhp()) {
					event.getChannel().sendMessage("`You already have full hp.`").queue();

				}
				
				else if( newHp > play.getMaxhp() ) {
					play.setHp(play.getMaxhp());
					gained-= (newHp - play.getMaxhp());
					event.getChannel().sendMessage("`Gained " + gained + " hp.`").queue();
					int hpUp = pc.updateHp(play);
					int remove = pc.removeInventoryItem(potions.get(0), play);
					
				}
				else {
					play.setHp(newHp);
					event.getChannel().sendMessage("`Gained " + gained + " hp.`").queue();
					int hpUp = pc.updateHp(play);
					int remove = pc.removeInventoryItem(potions.get(0), play);
				}
				

			}
			
			
		}
		
		
		if( args[0].equalsIgnoreCase(Main.prefix + "equip") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing)
			
			Combat c = new Combat();
			
			String id = event.getAuthor().getAvatarId();
			List<Player> p = new ArrayList<Player>();
			p = pc.selectPlayer(id); //get player issuing command
			
			Player play = p.get(0);
			
			if( args.length < 2) {
				event.getChannel().sendMessage("`Wrong formatting, type the name of a weapon/armor in your inventory, or the given item cannot be equipped.`").queue();

			}
			else if(args.length == 2){
				
				String it = args[1];
				System.out.println("User " + play.getUsername() + " wants to equip a " + it);
				List<Item> wep = ic.selectInventoryEquip(play, it);
				if( wep.size() == 0) {
					event.getChannel().sendMessage("`You do not have this item or you typed the name wrong.`").queue();

				}
				else {
					event.getChannel().sendMessage("`This item cannot be equipped.`").queue();

				}
				
			}
			else {
				String it = args[1] + " " + args[2];
				System.out.println("User " + play.getUsername() + " wants to equip a " + it);
				List<Item> wep = ic.selectInventoryEquip(play, it);
				if( wep.size() == 0) {
					event.getChannel().sendMessage("`You do not have this item or you typed the name wrong.`").queue();

				}
				else if ( wep.get(0).getType() > 6) {
					event.getChannel().sendMessage("`This item cannot be equipped.`").queue();

				}
				else{
					Item i = wep.get(0);
					System.out.println("Got item " + i.getName() + " of type " + i.getType());
					
					//next check for currently equipped helmet to see if it needs to be put back into inventory 
					List<Item> currentEquip = ic.selectPlayerEquipType(play, i);
					
					if( currentEquip.size() == 0) {
						System.out.println("No current item of this type equipped yet.");
						
						if( i.getLvl() > play.getLvl()) {
							event.getChannel().sendMessage("`You are too low of a level to equip this item.`").queue();
						}
						else {
							int equipThis = pc.equipPlayerItem(play, i); //add item to equips 
							int removeThis = pc.removeInventoryItem(i, play);
							List<Item> pe = ic.selectPlayerEquips(play);
							c.updateStats(play, (ArrayList<Item>) pe);
							event.getChannel().sendMessage("```Equipped: "+ i.getName() + "```").queue();
						}
					

					}
					else {
						
						
						
						Item cur = currentEquip.get(0);
						System.out.println("Player has an item to switch out.");
						if( i.getLvl() > play.getLvl()) {
							event.getChannel().sendMessage("`You are too low of a level to equip this item.`").queue();
						}
						else {
							int remEq = pc.removePlayerEquip(play, cur);
							//add removed item to inventory 
							int putBack = pc.updatePlayerInventory(cur, play);
							int equipThis = pc.equipPlayerItem(play, i); //add item to equips 
							int removeThis = pc.removeInventoryItem(i, play);
							List<Item> pe = ic.selectPlayerEquips(play);
							c.updateStats(play, (ArrayList<Item>) pe);
							event.getChannel().sendMessage("```Equipped: "+ i.getName() + "\nRemoved: " + cur.getName() + "```").queue();
							
						}
					


					}
					

				}
			}
			
			
		}
		
		if( args[0].equalsIgnoreCase(Main.prefix + "floorshop") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing)
			
			String id = event.getAuthor().getAvatarId();
			List<Player> p = new ArrayList<Player>();
			p = pc.selectPlayer(id); //get player issuing command
			
			Player play = p.get(0);
			
			List<Item> shopItems = ic.selectShopItems(play);
			
			String shop = "**Floor " + play.getFloor() + " Shop:**  ``` \n";
			
			for( int i = 0; i<shopItems.size(); i++) {
				shop+= "Item: " + shopItems.get(i).getName() + "\nCost: " + shopItems.get(i).getWorth() + "\nType: " + shopItems.get(i).getClassification() + " \n";
				if(shopItems.get(i).getType() < 7) {
					shop += "  Att: +" +  shopItems.get(i).getAtt() + "\n";
					shop += "  Def: +" +  shopItems.get(i).getDef() + "\n \n";
				}
				else {
					shop += "  Hp: +" +  shopItems.get(i).getHp() + "\n \n";
				}
			}
			
			shop += "``` \n";
			
			event.getChannel().sendMessage(shop).queue();

				
		}
		
		if(args[0].equalsIgnoreCase(Main.prefix + "buy") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing)
			
			String id = event.getAuthor().getAvatarId();
			List<Player> p = new ArrayList<Player>();
			p = pc.selectPlayer(id); //get player issuing command
			
			Player play = p.get(0);
			
			if( args.length < 2) {
				event.getChannel().sendMessage("`Wrong formatting for command, please give an item name from the shop.`").queue();

			}
			else if(args.length == 2){
				String buy = args[1]; 
				System.out.println("Player wants a " + buy);
				List<Item> shopItem = ic.selectItemFromShop(play, buy);
				if(shopItem.size() == 0) {
					event.getChannel().sendMessage("`That Item isn't in the shop.`").queue();
				}
				else {
					Item i = shopItem.get(0);
					if( i.getWorth() > play.getMoney()) {
						event.getChannel().sendMessage("`You don't have enough for this item.`").queue();
					}
					else {
						play.setMoney(play.getMoney() - i.getWorth());
						int add = pc.updatePlayerInventory(i, play);
						int newMoney = pc.updateMoney(play);
						event.getChannel().sendMessage("`Bought item " + i.getName() + ".`").queue();
					}
				}
			}
			else {
				String buy = args[1] + " " + args[2]; 
				System.out.println("Player wants a " + buy);
				List<Item> shopItem = ic.selectItemFromShop(play, buy);
				if(shopItem.size() == 0) {
					event.getChannel().sendMessage("`That Item isn't in the shop.`").queue();
				}
				else {
					Item i = shopItem.get(0);
					if( i.getWorth() > play.getMoney()) {
						event.getChannel().sendMessage("`You don't have enough for this item.`").queue();
					}
					else {
						play.setMoney(play.getMoney() - i.getWorth());
						int add = pc.updatePlayerInventory(i, play);
						int newMoney = pc.updateMoney(play);
						event.getChannel().sendMessage("`Bought item " + i.getName() + ".`").queue();
					}
					
				}
				
			}
			

		}
		
		
		//add a check for if bag is empty
		if(args[0].equalsIgnoreCase(Main.prefix + "sell") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing)
			
			
			String id = event.getAuthor().getAvatarId();
			List<Player> p = new ArrayList<Player>();
			p = pc.selectPlayer(id); //get player issuing command
			
			Player play = p.get(0);
			
			if( args.length < 2) {
				event.getChannel().sendMessage("`Wrong formatting, please give an item name from your inventory to sell.`").queue();

			}
			else if(args.length == 2){
				
				String playerItem = args[1];
				List<Item> pi = ic.selectInventoryEquip(play, playerItem);
				
				if( pi.size() == 0) {
					event.getChannel().sendMessage("`You don't have that item in your inventory.`").queue();
				}
				else {
					Item i = pi.get(0);
					play.setMoney(play.getMoney() + i.getWorth());
					int rm = pc.removeInventoryItem(i, play);
					int pm = pc.updateMoney(play);
					event.getChannel().sendMessage("`Sold item " + i.getName() + ", got Coins +" + i.getWorth() + ".`").queue();
				}
				
			}
			else {
				String playerItem = args[1] + " " + args[2];
				List<Item> pi = ic.selectInventoryEquip(play, playerItem);
				
				if( pi.size() == 0) {
					event.getChannel().sendMessage("`You don't have that item in your inventory.`").queue();
				}
				else {
					
					Item i = pi.get(0);
					play.setMoney(play.getMoney() + i.getWorth());
					int rm = pc.removeInventoryItem(i, play);
					int pm = pc.updateMoney(play);
					event.getChannel().sendMessage("`Sold item " + i.getName() + ", got Coins +" + i.getWorth() + ".`").queue();
				}
				
			}
			
			
		}
		
		if(args[0].equalsIgnoreCase(Main.prefix + "bossinfo") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing)
			
			String id = event.getAuthor().getAvatarId();
			List<Player> p = new ArrayList<Player>();
			p = pc.selectPlayer(id); //get player issuing command
			
			Player play = p.get(0);
			
			List<Monster> b = mc.selectFloorBoss(play.getFloor());
			
			Monster boss = b.get(0);
			
			List<Item> bossDrops = new ArrayList<Item>();
			
			String bossInfo = "**Floor " + play.getFloor() + " Boss Info:** ``` \n";
			bossInfo+= "Name: " + boss.getName() + "\n";
			bossInfo+= "Level: " + boss.getLvl() + "\n";
			bossInfo += "HP: " + boss.getHp() + "\n";
			bossInfo += "Att: " + boss.getHp() + "\n";
			bossInfo += "Def: " + boss.getHp() + "\n Drop(s): \n";
			
			bossDrops = ic.selectMonsterDrops(boss);
			
			for( int i = 0; i<bossDrops.size(); i++) {
				bossInfo += "  " + bossDrops.get(i).getName() + ", Rate:  " + bossDrops.get(i).getRate() + "0% \n";
			}
			
			bossInfo += "\n ```";
			
			event.getChannel().sendMessage(bossInfo).queue();

			
			
		}
		
		
		if(args[0].equalsIgnoreCase(Main.prefix + "bossfight") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing)
			
			String id = event.getAuthor().getAvatarId();
			List<Player> p = new ArrayList<Player>();
			p = pc.selectPlayer(id); //get player issuing command
			
			Player play = p.get(0);
			
			List<Floor> playerFloor = fc.selectFloor(play.getFloor());
			
			Floor bf = playerFloor.get(0);
			
			if( play.getMap() == bf.getMaps() ) {
				
				List<Monster> boss = mc.selectFloorBoss(play.getFloor());
				Monster b = boss.get(0);
				System.out.println("Floor boss for floor " + play.getFloor() + " is " + b.getName());
				Combat c = new Combat();
				boolean won;
				String fightOut = "```";
				won = c.fight(play, b);
				
				
				List<Levels> lvls = new ArrayList<Levels>();
				lvls = lc.selectPlayerLevel(play);
				
				if( won ) {
					
					fightOut += "You defeated the boss: " + b.getName() + ", Floor " + b.getFloor() + "! \n";
					fightOut = c.updateExp(play, b.getExp(), (ArrayList<Levels>) lvls, fightOut);
					int ud = pc.updatePlayerCombat(play);
					List<Item> drops = ic.selectMonsterDrops(b);
					fightOut = c.updateInventory(play, (ArrayList<Item>) drops, b.getCoins(), fightOut);
					if( play.getFloor() == 5) {
						fightOut += "\n..YOU DEFEATED THE FINAL BOSS.. \n";
						fightOut += "You have access to every floor :) ```";
						event.getChannel().sendMessage(fightOut).queue();
					}
					else {
						String acc = "fa" + (play.getFloor() + 1);
						int ga = pc.updateFloorAccess(play, acc);
						fightOut += "\nYou now have access to floor " + (play.getFloor() + 1) + ". ```";
						event.getChannel().sendMessage(fightOut).queue();
						
					}
				
					
				}
				else {
					play.setHp(play.getMaxhp() / 2);
					int expLoss = play.getLvl() * b.getExp();
					if( expLoss > play.getExp() ) {
						play.setExp(0);
						play.setPercent(0.00);
					}
					else {
						play.setExp(play.getExp() - expLoss);
						double pcnt = (double) play.getExp() / (double) lvls.get(0).getExp();
						play.setPercent(pcnt);
					}
					int d = pc.updatePlayerCombat(play);
					fightOut += "You were DEFEATED...you couldn't handle " + b.getName() + " yet.. \n";
					fightOut += "Lost " + expLoss + " exp.. \n";
					fightOut += "HP: " + play.getHp() + "/" + play.getMaxhp() + " ```";
					event.getChannel().sendMessage(fightOut).queue();
					
				}
				
				
			}
			else {
				event.getChannel().sendMessage("`You have to be on the last map of the floor to challenge the boss.`").queue();

			}
			
			
			
			
			
		}
		
		if(args[0].equalsIgnoreCase(Main.prefix + "location") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing

			String id = event.getAuthor().getAvatarId();
			List<Player> p = new ArrayList<Player>();
			p = pc.selectPlayer(id); //get player issuing command
			
			Player play = p.get(0);
			
			event.getChannel().sendMessage("`Map " + play.getMap() + ", Floor " + play.getFloor() + ".`").queue();

			
			
			
		}
		
		if(args[0].equalsIgnoreCase(Main.prefix + "stats") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing

			String id = event.getAuthor().getAvatarId();
			List<Player> p = new ArrayList<Player>();
			p = pc.selectPlayer(id); //get player issuing command
			
			Player play = p.get(0);
			
			String statOut = "```";
			statOut += "Base Att/Def = " + (play.getLvl() + 4) + "\n";
			statOut += "HP:  " + play.getHp() + "/" + play.getMaxhp() + "\n";
			statOut += "Att: " + play.getAtt() + "\n";
			statOut += "Def: " + play.getDef() + "```";
			
			event.getChannel().sendMessage(statOut).queue();

			
			
			
		}
		
	    if(args[0].equalsIgnoreCase(Main.prefix + "checkitem") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing
			
			String id = event.getAuthor().getAvatarId();
			List<Player> p = new ArrayList<Player>();
			p = pc.selectPlayer(id); //get player issuing command
			
			Player play = p.get(0);
			
			List<Item> itemCheck = new ArrayList<Item>();
			
			if( args.length < 2) {
				event.getChannel().sendMessage("`Invalid format, please give an item name.`").queue();
				
			}
			else if(args.length == 2){
				String itemName = args[1];
				itemCheck = ic.selectInventoryEquip(play, itemName);
				if( itemCheck.size() == 0) {
					event.getChannel().sendMessage("`This item is not in your inventory.`").queue();

				}
				else {
					Item i = itemCheck.get(0);
					String itemInfo = "```";
					itemInfo += "Name: " + i.getName() + "\n";
					itemInfo += "Type: " + i.getClassification() + "\n";
					itemInfo += "Worth: " + i.getWorth() + " coins \n";
					itemInfo += "Drop Rate: " + i.getRate() + "\n";
					itemInfo += "Heals " + i.getHp() + " hp. ```";
					event.getChannel().sendMessage(itemInfo).queue();
				}
			}
			else {
				String itemName = args[1] + " " + args[2];
				itemCheck = ic.selectInventoryEquip(play, itemName);
				if( itemCheck.size() == 0) {
					event.getChannel().sendMessage("`This item is not in your inventory.`").queue();

				}
				else {
					Item i = itemCheck.get(0);
					String itemInfo = "```";
					itemInfo += "Name: " + i.getName() + "\n";
					itemInfo += "Type: " + i.getClassification() + "\n";
					itemInfo += "Worth: " + i.getWorth() + " coins \n";
					itemInfo += "Drop Rate: " + i.getRate() + "\n";
					itemInfo += "Level Req.: " + i.getLvl() + "\n";
					itemInfo += "Att: +" + i.getAtt() + "\n";
					itemInfo += "Def: +" + i.getDef() + "``` \n";
					event.getChannel().sendMessage(itemInfo).queue();
				}
				
			}
			
	    }
	    
	    if(args[0].equalsIgnoreCase(Main.prefix + "coins") ) {
			
				event.getChannel().sendTyping().queue(); //pretend bot is typing
				
				String id = event.getAuthor().getAvatarId();
				List<Player> p = new ArrayList<Player>();
				p = pc.selectPlayer(id); //get player issuing command
				
				Player play = p.get(0);
				
				event.getChannel().sendMessage("`You have " + play.getMoney() + "$`").queue();

				
				
				
				
	    }
	    
	    if(args[0].equalsIgnoreCase(Main.prefix + "dequip") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing
			
			String id = event.getAuthor().getAvatarId();
			List<Player> p = new ArrayList<Player>();
			p = pc.selectPlayer(id); //get player issuing command
			
			Player play = p.get(0);
			
			if( args.length < 3) {
				event.getChannel().sendMessage("`Invalid format/item, please provide the name of an item you have equipped.`").queue();
			}
			else {
				String itm = args[1] + " " + args[2];
				List<Item> haveItem = ic.selectPlayerEquipName(play, itm);
				if( haveItem.size() == 0) {
					event.getChannel().sendMessage("`You don't have this item equipped.`").queue();

				}
				else {
					Combat c = new Combat();
					Item cur = haveItem.get(0);
					int remEq = pc.removePlayerEquip(play, cur);
					int putBack = pc.updatePlayerInventory(cur, play);
					List<Item> pe = ic.selectPlayerEquips(play);
					c.updateStats(play, (ArrayList<Item>) pe);
					event.getChannel().sendMessage("```Removed: " + cur.getName() + "```").queue();

				}
			}
			
	
			
    }
	    if(args[0].equalsIgnoreCase(Main.prefix + "floorup") ) {
			
	 			event.getChannel().sendTyping().queue(); //pretend bot is typing
	 			
	 			String id = event.getAuthor().getAvatarId();
				List<Player> p = new ArrayList<Player>();
				p = pc.selectPlayer(id); //get player issuing command
				
				Player play = p.get(0);
				
				List<Boolean> canMove = new ArrayList<Boolean>();
				
				canMove.add(play.isFa1());
				canMove.add(play.isFa2());
				canMove.add(play.isFa3());
				canMove.add(play.isFa4());
				canMove.add(play.isFa5());
				
				List<Floor> playerFloor = fc.selectFloor(play.getFloor());
				Floor f = playerFloor.get(0);
				
				
				if( (play.getFloor()+1) > 5) {
					event.getChannel().sendMessage("`You are already on the highest floor.`").queue();

				}
				else if( canMove.get(play.getFloor()) ) {
					if( play.getMap() == f.getMaps()) {
						play.setFloor(play.getFloor() + 1);
						play.setMap(1);
						int newf = pc.updateFloor(play);
						event.getChannel().sendMessage("``` You are now on Floor " + play.getFloor() + ". ```").queue();
					}
					else {
						event.getChannel().sendMessage("`You must be on the last map of the floor to move to the next.`").queue();

					}
					
				}
				else {
					event.getChannel().sendMessage("`You do not have access to the next floor.`").queue();
				}
	
	 			
	 			
	    }
	    
	    if(args[0].equalsIgnoreCase(Main.prefix + "floordown") ) {
			
 			event.getChannel().sendTyping().queue(); //pretend bot is typing
 			
 			String id = event.getAuthor().getAvatarId();
			List<Player> p = new ArrayList<Player>();
			p = pc.selectPlayer(id); //get player issuing command
			
			Player play = p.get(0);
			
			
			
			
			if( (play.getFloor()-1) < 1) {
				event.getChannel().sendMessage("`You are already on the lowest floor.`").queue();

			}
			else if(play.getMap() > 1){
				event.getChannel().sendMessage("`You must be on the first map of the floor to move down.`").queue();
			}
			else {
				List<Floor> playerFloor = fc.selectFloor(play.getFloor()-1);
				Floor f = playerFloor.get(0);
				
				play.setFloor(play.getFloor()-1);
				play.setMap(f.getMaps());
				
				int newf = pc.updateFloor(play);

				event.getChannel().sendMessage("``` You are now on Floor " + play.getFloor() + ". ```").queue();
				
			}

 			
 			
	    }
	    
	    if(args[0].equalsIgnoreCase(Main.prefix + "tradeitem") ) {
			
 			event.getChannel().sendTyping().queue(); //pretend bot is typing
 			
 			if( args.length < 3) {
				event.getChannel().sendMessage("`Invalid trade, please give the name of an item and the name of the player to trade with.`").queue();
				return;

 			}
 			
 			String id = event.getAuthor().getAvatarId();
			List<Player> p = new ArrayList<Player>();
			p = pc.selectPlayer(id); //get player issuing command
			
			Player play = p.get(0);
			
			if( args.length == 3) {
				
				String it = args[1];
				List<Item> invItem = ic.selectInventoryEquip(play, it);
				if( invItem.size() == 0) {
					event.getChannel().sendMessage("`This item is not in your inventory.`").queue();
					return;
				}
				Item i = invItem.get(0);
				
				String user = args[2];
				p = pc.selectPlayerByUsername(user);
				
				if( p.size() == 0) {
					event.getChannel().sendMessage("`Player you want to trade to doesn't exist.`").queue();
					return;
				}
				
				Player op = p.get(0); //other player
				if( play.getFloor() != op.getFloor() || play.getMap() != op.getMap()) {
					event.getChannel().sendMessage("`To trade you must be on the same floor and map.`").queue();
					return;
				}
				
				//do the trade
				int removeInv = pc.removeInventoryItem(i, play);
				int giveIt = pc.updatePlayerInventory(i, op);
				
				event.getChannel().sendMessage("```Transaction Complete: \n  You lost: " + i.getName() + " \n  " + op.getUsername() + " recieved: " + i.getName() + "```").queue();
				

			}
 			
 			
 			
	    }
	    
	    if(args[0].equalsIgnoreCase(Main.prefix + "tradecoins") ) {
			
 			event.getChannel().sendTyping().queue(); //pretend bot is typing
 			
 			if( args.length < 3) {
 				
				event.getChannel().sendMessage("`Invalid trade, please give the name the coin amount and the name of the player to trade with.`").queue();
				return;

 			}
 			
 			String id = event.getAuthor().getAvatarId();
			List<Player> p = new ArrayList<Player>();
			p = pc.selectPlayer(id); //get player issuing command
			
			Player play = p.get(0);
			
			String coinAmt = args[1];
			
			 try {
			        int tradeAmt = Integer.parseInt( coinAmt );
			        if( tradeAmt > play.getMoney() ) {
						event.getChannel().sendMessage("`You do not have this much money to trade.`").queue();
						return;

			        }
			        
			        String user = args[2];
					p = pc.selectPlayerByUsername(user);
					
					if( p.size() == 0) {
						event.getChannel().sendMessage("`Player you want to trade to doesn't exist.`").queue();
						return;
					}
					
					Player op = p.get(0); //other player
					if( play.getFloor() != op.getFloor() || play.getMap() != op.getMap()) {
						event.getChannel().sendMessage("`To trade you must be on the same floor and map.`").queue();
						return;
					}
					
					play.setMoney(play.getMoney() - tradeAmt);
					op.setMoney(op.getMoney() + tradeAmt);
					int first = pc.updateMoney(play);
					int second = pc.updateMoney(op);
					event.getChannel().sendMessage("```Transaction Complete: \n  You lost: " + tradeAmt + " coins \n  " + op.getUsername() + " recieved: " + tradeAmt + " coins ```").queue();

			        
			    }
			 catch( NumberFormatException e ) {
					event.getChannel().sendMessage("`You entered an invalid coin amount, please use integers i.e. 500.`").queue();
					return;
			        
			 }
			
			
	    }
		
		
		
	}

}
