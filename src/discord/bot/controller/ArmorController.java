package discord.bot.controller;

import java.util.List;
import discord.bot.domain.Armor;
import discord.bot.domain.Player;
import discord.bot.service.ArmorService;

public class ArmorController {
	
	private ArmorService service = new ArmorService();
	
	public List<Armor> selectPlayerEquips(Player p){
		System.out.println("Getting current equips for player " + p.getDiscid());
		return service.selectPlayerEquips(p);
		
	}

	
	

}
