package discord.bot.controller;

import java.util.List;

import discord.bot.domain.Player;
import discord.bot.service.PlayerService;

public class PlayerController {
	
	private PlayerService service = new PlayerService();

	public List<Player> selectAll(){
		System.out.println("Select all has occured, sending to service layer");
		return service.selectAll();
	}
	
	public int createPlayer(Player newPlayer) {
		System.out.println("Creating new player..");
		return service.createPlayer(newPlayer);
	}
	
	public List<Player> selectPlayer(String id){
		System.out.println("Grabbing player with id: " + id);
		return service.selectPlayer(id);
	}
	public int deletePlayer(String id) {
		System.out.println("Deleting player with id = " + id);
		return service.deletePlayer(id);
	}
	

	
}
