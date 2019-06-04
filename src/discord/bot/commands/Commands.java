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
			event.getChannel().sendMessage("create <username>: Creates a character with given username if you don't already have one.\n" +
										   "players: Shows a list of all players. \n" +
										   "char: Displays your current character's info. \n"+
										   "delete: Deletes your character if you have one. \n" +
										   "floors: Shows overview of each available floor. \n" +
										   "floorinfo: Shows name and number of each map on your current floor. \n" +
										   "monsterlist: Shows monsters available to fight on current map. \n" +
										   "right: Move right to a new map. \n" +
										   "left: Move left to a new map. \n" +
										   "equips: Shows your current equipment. \n" +
										   "droplist: Shows what monster drops what items. \n" +
										   "inventory: Shows what's in your current bag. \n" +
										   "fight: Fight one of the monsters on your map. \n" + 
										   "potion: Use a potion from your inventory. \n" +
										   "equip <weapon>: Equip a weapon/armor from your inventory. \n" + 
										   "buy: Buy item from your floor shop. \n" + 
										   "sell: Sell inventory item to shop. \n" +
										   "bossfight: Challenge the floor boss to gain access to the next floor."
			).queue();
		
		}
		
		if( args[0].equalsIgnoreCase(Main.prefix + "create") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing 
			
			String discordUserId = event.getAuthor().getAvatarId();

			
			List<Player> check = new ArrayList<Player>();
			check = pc.selectPlayer(discordUserId);
			
			if( check.size() == 0) {
				
				Player newPlayer = new Player();
				newPlayer.setDiscid(discordUserId);
				newPlayer.setUsername(args[1]);
				
				System.out.println("Creating new user: ID = " + newPlayer.getDiscid() + " Username: " + newPlayer.getUsername());
				
				int result = pc.createPlayer(newPlayer);
				System.out.println("Result is " + result);
				
				event.getChannel().sendMessage("Your character has been created, welcome " + newPlayer.getUsername()).queue();
				
			}
			else {
				event.getChannel().sendMessage("You already have a character linked to your account.").queue();

			}

		}
		
		if( args[0].equalsIgnoreCase(Main.prefix + "players")) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing 
			
			List<Player> players = new ArrayList<Player>();
			String plist = "";
			
			players = pc.selectAll();
			
			event.getChannel().sendMessage("Current players are:\n").queue();

		
			for( int i = 0; i<players.size(); i++) {
				
				plist += players.get(i).getUsername() + "\n";

			}
			
			event.getChannel().sendMessage(plist).queue();

		}
		
		if( args[0].equalsIgnoreCase(Main.prefix + "char") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing 
			
			String id = event.getAuthor().getAvatarId();
			
			List<Player> p = new ArrayList<Player>();
			
			p = pc.selectPlayer(id);

			
			if( p.size() == 0 ) {
				
				event.getChannel().sendMessage("You do not have a character yet.").queue();
				
			}
			else {
				event.getChannel().sendMessage("Your character info:\n").queue();
				event.getChannel().sendMessage("Username: " + p.get(0).getUsername() + "\n Level: " + p.get(0).getLvl() + "\n Exp: " + p.get(0).getExp() + "\n Percent: " + p.get(0).getPercent() + " \n Money: " + p.get(0).getMoney() + "\n Location: Floor " + p.get(0).getFloor() + ", Map " + p.get(0).getMap() + "\n Attack: " + p.get(0).getAtt() + "\n Defense: " + 
				p.get(0).getDef() + "\n Health: " + p.get(0).getHp() + "/" + p.get(0).getMaxhp() + "\n Floor Access: \n   1 = " + p.get(0).isFa1() + "\n   2 = " + p.get(0).isFa2()).queue();
			}
			
			
		}
		
		if( args[0].equalsIgnoreCase(Main.prefix + "delete")){
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing 
			
			String id = event.getAuthor().getAvatarId();
			
			int p = pc.deletePlayer(id);
			
			if( p == 1) {
				event.getChannel().sendMessage("You character has been deleted.").queue();
				
			}
			else {
				event.getChannel().sendMessage("You don't have a character to delete.").queue();
			}
			
		}
		
		if( args[0].equalsIgnoreCase(Main.prefix + "floors") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing 
			
			List<Floor> floors = new ArrayList<Floor>();
			
			floors = fc.selectAll();
			
			String floorInfo = "";
			
			for( int i = 0; i<floors.size(); i++) {
				floorInfo += "Floor " + (i+1) + "\n  Name: " + floors.get(i).getName() + " \n  Level: " + floors.get(i).getRequirement() + "\n  Boss Name: " + floors.get(i).getBossname() + "\n  Maps: " + floors.get(i).getMaps() + "\n \n";
			}
			
			event.getChannel().sendMessage(floorInfo).queue();
				
		}
		
		
		if( args[0].equalsIgnoreCase(Main.prefix + "floorinfo") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing 
			
			String id = event.getAuthor().getAvatarId();
			List<Player> p = new ArrayList<Player>();
			p = pc.selectPlayer(id);
			
			List<Map> maps = new ArrayList<Map>();
			maps = fc.selectAllFloorMaps(p.get(0).getFloor());
			
			String mapsInfo = "";
			
			for( int i = 0; i<maps.size(); i++) {
				mapsInfo += "Map " + maps.get(i).getNumber() + "\n  Name: " + maps.get(i).getName() + " \n \n";
			}
			
			event.getChannel().sendMessage(mapsInfo).queue();
				
		}
		
		//show diff info
		if( args[0].equalsIgnoreCase(Main.prefix + "monsterlist") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing 
			
			String id = event.getAuthor().getAvatarId();
			List<Player> p = new ArrayList<Player>();
			p = pc.selectPlayer(id); //get player issuing command 
			
			List<Monster> monsters = new ArrayList<Monster>();
			monsters = mc.selectMonsters(p.get(0).getFloor(), p.get(0).getMap());
			
			String monsterInfo = "Monsters in your map include: \n \n";
			
			for( int i = 0; i<monsters.size(); i++) {
				monsterInfo += "Name: " + monsters.get(i).getName() + "\n   Level: " + monsters.get(i).getLvl() + "\n   Attack: " + monsters.get(i).getAtt() + "\n   Defense: " + monsters.get(i).getDef() + "\n   HP: " + monsters.get(i).getHp() + "\n   Coins: " + monsters.get(0).getCoins() + "\n \n ";
			}
			
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
				
			}
			else {
				event.getChannel().sendMessage("You are on the last map of this floor.").queue();
			}
			
			
		}
		
		if( args[0].equalsIgnoreCase(Main.prefix + "left") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing
			
			String id = event.getAuthor().getAvatarId();
			List<Player> p = new ArrayList<Player>();
			p = pc.selectPlayer(id); //get player issuing command 
			
			Player pl = p.get(0);
			
			if( pl.getMap() == 1) {
				
				event.getChannel().sendMessage("You are on the first map of this floor.").queue();
				
			}
			else {
				
				pl.setMap(pl.getMap() - 1);
				int playerUpdate = pc.updatePosition(pl);
			}
			
			
		}
		
		
		if( args[0].equalsIgnoreCase(Main.prefix + "equips") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing
			
			String id = event.getAuthor().getAvatarId();
			List<Player> p = new ArrayList<Player>();
			p = pc.selectPlayer(id); //get player issuing command
			
			List<Item> arm = new ArrayList<Item>();
			
			arm = ic.selectPlayerEquips(p.get(0));
			
			String equipInfo = "Your currently equipped armor: \n \n";
			
			for( int i = 0; i<arm.size(); i++) {
				equipInfo += "Name: " + arm.get(i).getName() + "\n Attack: " + arm.get(i).getAtt() + "\n Defense: " + arm.get(i).getDef() + "\n Type: " + arm.get(i).getType() + "\n Worth: " + arm.get(i).getWorth() + "\n Class: " + arm.get(i).getClassification() + "\n \n";
			}
			
			event.getChannel().sendMessage(equipInfo).queue();

		
		}
		
		//droplist for monsters on your map, not overall monsters everywhere
		if( args[0].equalsIgnoreCase(Main.prefix + "droplist") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing)
			
			List<Monster> monsters = new ArrayList<Monster>();
			monsters = mc.selectAll();
			
			for( int i = 0; i<monsters.size(); i++) {
				
				Monster m = monsters.get(i);
				List<Item> drops = new ArrayList<Item>();
				drops = ic.selectMonsterDrops(m);
				event.getChannel().sendMessage("Monster: " + m.getName() + " drops- \n").queue();
				
				for( int j = 0; j<drops.size(); j++) {
					event.getChannel().sendMessage("  Item: " + drops.get(j).getName() + "\n").queue();
				}
			}
			
			
		}
		
		//add check for if bag is empty, show items as potions 4x, instea dof listing one by one
		if( args[0].equalsIgnoreCase(Main.prefix + "inventory") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing)
			
			String id = event.getAuthor().getAvatarId();
			List<Player> p = new ArrayList<Player>();
			p = pc.selectPlayer(id); //get player issuing command
			
			List<Item> inv = new ArrayList<Item>();
			inv = ic.selectPlayerItems(p.get(0));
			
			String out = "Inventory: \n";
			
			for( int i = 0; i<inv.size(); i++) {
				out += "  " + inv.get(i).getName() + "\n";
			}
			event.getChannel().sendMessage(out).queue();

			
		}
		
		if( args[0].equalsIgnoreCase(Main.prefix + "fight") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing)
			
			//get player fighting, and the mosters on their current map
			
			String id = event.getAuthor().getAvatarId();
			List<Player> p = new ArrayList<Player>();
			p = pc.selectPlayer(id); //get player issuing command
			
			List<Monster> monsters = new ArrayList<Monster>();
			monsters = mc.selectMonsters(p.get(0).getFloor(), p.get(0).getMap());
			
			List<Levels> lvls = new ArrayList<Levels>();
			
			Combat c = new Combat();
			boolean won;
			int num = (int) (Math.random() *  monsters.size() );
			
			Monster mon = monsters.get(num);
			Player play = p.get(0);
			
			lvls = lc.selectPlayerLevel(play);
		
		    won = c.fight(play, mon);

			
			if( won ) {
				
				c.updateExp(play, mon.getExp(), (ArrayList<Levels>) lvls);
				int ud = pc.updatePlayerCombat(play);
				List<Item> drops = ic.selectMonsterDrops(mon);
				c.updateInventory(play, (ArrayList<Item>) drops, mon.getCoins());
				event.getChannel().sendMessage("You won!").queue();
				
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
				
				event.getChannel().sendMessage("You died..").queue();
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
				event.getChannel().sendMessage("You have no potions.").queue();

			}
			else {
				
				int newHp = play.getHp() + potions.get(0).getHp();
				int gained = potions.get(0).getHp();
				
				if( play.getHp() == play.getMaxhp()) {
					event.getChannel().sendMessage("You already have full hp.").queue();

				}
				
				else if( newHp > play.getMaxhp() ) {
					play.setHp(play.getMaxhp());
					gained-= (newHp - play.getMaxhp());
					event.getChannel().sendMessage("Gained " + gained + " hp").queue();
					int hpUp = pc.updateHp(play);
					int remove = pc.removeInventoryItem(potions.get(0), play);
					
				}
				else {
					play.setHp(newHp);
					event.getChannel().sendMessage("Gained " + gained + " hp").queue();
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
			
			if( args.length < 3) {
				event.getChannel().sendMessage("Wrong formatting.").queue();

			}
			else {
				String it = args[1] + " " + args[2];
				System.out.println("User " + play.getUsername() + " wants to equip a " + it);
				List<Item> wep = ic.selectInventoryEquip(play, it);
				if( wep.size() == 0) {
					event.getChannel().sendMessage("You do not have this item or you typed the name wrong.").queue();

				}
				else {
					Item i = wep.get(0);
					System.out.println("Got item " + i.getName() + " of type " + i.getType());
					
					//next check for currently equipped helmet to see if it needs to be put back into inventory 
					List<Item> currentEquip = ic.selectPlayerEquipType(play, i);
					
					if( currentEquip.size() == 0) {
						System.out.println("No current item of this type equipped yet.");
						
						if( i.getLvl() > play.getLvl()) {
							event.getChannel().sendMessage("You are too low of a level to equip this item.").queue();
						}
						else {
							int equipThis = pc.equipPlayerItem(play, i); //add item to equips 
							int removeThis = pc.removeInventoryItem(i, play);
							List<Item> pe = ic.selectPlayerEquips(play);
							c.updateStats(play, (ArrayList<Item>) pe);
							event.getChannel().sendMessage("Equipped: "+ i.getName() + ".").queue();
						}
					

					}
					else {
						
						
						
						Item cur = currentEquip.get(0);
						System.out.println("Player has an item to switch out.");
						if( i.getLvl() > play.getLvl()) {
							event.getChannel().sendMessage("You are too low of a level to equip this item.").queue();
						}
						else {
							int remEq = pc.removePlayerEquip(play, cur);
							//add removed item to inventory 
							int putBack = pc.updatePlayerInventory(cur, play);
							int equipThis = pc.equipPlayerItem(play, i); //add item to equips 
							int removeThis = pc.removeInventoryItem(i, play);
							List<Item> pe = ic.selectPlayerEquips(play);
							c.updateStats(play, (ArrayList<Item>) pe);
							event.getChannel().sendMessage("Equipped: "+ i.getName() + "\n Removed: " + cur.getName()).queue();
							
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
			
			String shop = "Floor " + play.getFloor() + " Shop: \n";
			
			for( int i = 0; i<shopItems.size(); i++) {
				shop+= "Item: " + shopItems.get(i).getName() + " Cost: " + shopItems.get(i).getWorth() + " Type: " + shopItems.get(i).getClassification() + " \n";
			}
			
			event.getChannel().sendMessage(shop).queue();

				
		}
		
		if(args[0].equalsIgnoreCase(Main.prefix + "buy") ) {
			
			event.getChannel().sendTyping().queue(); //pretend bot is typing)
			
			String id = event.getAuthor().getAvatarId();
			List<Player> p = new ArrayList<Player>();
			p = pc.selectPlayer(id); //get player issuing command
			
			Player play = p.get(0);
			
			if( args.length < 2) {
				event.getChannel().sendMessage("Wrong formatting for command.").queue();

			}
			else if(args.length == 2){
				String buy = args[1]; 
				System.out.println("Player wants a " + buy);
				List<Item> shopItem = ic.selectItemFromShop(play, buy);
				if(shopItem.size() == 0) {
					event.getChannel().sendMessage("That Item isn't in the shop.").queue();
				}
				else {
					Item i = shopItem.get(0);
					if( i.getWorth() > play.getMoney()) {
						event.getChannel().sendMessage("You don't have enough for this item.").queue();
					}
					else {
						play.setMoney(play.getMoney() - i.getWorth());
						int add = pc.updatePlayerInventory(i, play);
						int newMoney = pc.updateMoney(play);
					}
				}
			}
			else {
				String buy = args[1] + " " + args[2]; 
				System.out.println("Player wants a " + buy);
				List<Item> shopItem = ic.selectItemFromShop(play, buy);
				if(shopItem.size() == 0) {
					event.getChannel().sendMessage("That Item isn't in the shop.").queue();
				}
				else {
					Item i = shopItem.get(0);
					if( i.getWorth() > play.getMoney()) {
						event.getChannel().sendMessage("You don't have enough for this item.").queue();
					}
					else {
						play.setMoney(play.getMoney() - i.getWorth());
						int add = pc.updatePlayerInventory(i, play);
						int newMoney = pc.updateMoney(play);
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
				event.getChannel().sendMessage("Wrong formatting for command.").queue();

			}
			else if(args.length == 2){
				
				String playerItem = args[1];
				List<Item> pi = ic.selectInventoryEquip(play, playerItem);
				
				if( pi.size() == 0) {
					event.getChannel().sendMessage("You don't have that item in your inventory.").queue();
				}
				else {
					Item i = pi.get(0);
					play.setMoney(play.getMoney() + i.getWorth());
					int rm = pc.removeInventoryItem(i, play);
					int pm = pc.updateMoney(play);
				}
				
			}
			else {
				String playerItem = args[1] + " " + args[2];
				List<Item> pi = ic.selectInventoryEquip(play, playerItem);
				
				if( pi.size() == 0) {
					event.getChannel().sendMessage("You don't have that item in your inventory.").queue();
				}
				else {
					
					Item i = pi.get(0);
					play.setMoney(play.getMoney() + i.getWorth());
					int rm = pc.removeInventoryItem(i, play);
					int pm = pc.updateMoney(play);
				}
				
			}
			
			
		}
		
		//important: add check for when user is at the top floor with no more accesses to get.
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
				won = c.fight(play, b);
				
				List<Levels> lvls = new ArrayList<Levels>();
				lvls = lc.selectPlayerLevel(play);
				
				if( won ) {
					
					c.updateExp(play, b.getExp(), (ArrayList<Levels>) lvls);
					int ud = pc.updatePlayerCombat(play);
					List<Item> drops = ic.selectMonsterDrops(b);
					c.updateInventory(play, (ArrayList<Item>) drops, b.getCoins());
					String acc = "fa" + (play.getFloor() + 1);
					int ga = pc.updateFloorAccess(play, acc);
					event.getChannel().sendMessage("You beat the Floor " + play.getFloor() + " boss! You have gained access to floor #" +  (play.getFloor() +1) ).queue();
					
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
					
					event.getChannel().sendMessage("DEFEATED...You couldn't handle the boss yet..").queue();
					
				}
				
				
			}
			else {
				event.getChannel().sendMessage("You have to be on the last map of the floor to challenge the boss.").queue();

			}
			
			
			
			
			
		}
		
		
		
		
		


		
	}

}
