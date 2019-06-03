package discord.bot.service;

import java.util.List;

import discord.bot.domain.Levels;
import discord.bot.dao.LevelsDao;
import discord.bot.domain.Player;

public class LevelsService {
	
	private LevelsDao ld = new LevelsDao();
	
	public List<Levels> selectPlayerLevel(Player p) {
		System.out.println("Getting their level from DB.");
		return ld.selectPlayerLevel(p);
	}

}
