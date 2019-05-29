package discord.bot.commands;

import java.util.ArrayList;
import java.util.List;

import discord.bot.Main;
import discord.bot.controller.FloorController;
import discord.bot.controller.PlayerController;
import discord.bot.domain.Floor;
import discord.bot.domain.Map;
import discord.bot.domain.Player;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter{
	
	private PlayerController pc = new PlayerController();
	private FloorController fc = new FloorController();

	
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
										   "floorinfo: Shows name and number of each map on your current floor."
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
				event.getChannel().sendMessage("Username: " + p.get(0).getUsername() + "\n Level: " + p.get(0).getLvl() + "\n Exp: " + p.get(0).getExp() + "% \n Money: " + p.get(0).getMoney() + "\n Location: Floor " + p.get(0).getFloor() + ", Map " + p.get(0).getMap()).queue();
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
		
		
		
		
		
		
		
	}

}
