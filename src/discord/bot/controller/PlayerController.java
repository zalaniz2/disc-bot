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
	

	
}
