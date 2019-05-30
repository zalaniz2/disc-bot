package discord.bot.service;

import java.util.List;

import discord.bot.dao.ArmorDao;
import discord.bot.domain.Armor;
import discord.bot.domain.Player;

public class ArmorService {
	
	private ArmorDao fd = new ArmorDao();
	
	public List<Armor> selectPlayerEquips(Player p){
		System.out.println("Getting player equips from DB.");
		return fd.selectPlayerEquips(p);
	}

	
	

}
