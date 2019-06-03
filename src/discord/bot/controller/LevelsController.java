package discord.bot.controller;

import java.util.List;

import discord.bot.domain.Levels;
import discord.bot.domain.Player;
import discord.bot.service.LevelsService;

public class LevelsController {
	
	private LevelsService service = new LevelsService();
	
	public List<Levels> selectPlayerLevel(Player p) {
		System.out.println("Getting level table for player " + p.getUsername());
		return service.selectPlayerLevel(p);
	}

}
