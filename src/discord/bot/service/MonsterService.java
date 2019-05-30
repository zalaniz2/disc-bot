package discord.bot.service;

import java.util.List;

import discord.bot.dao.MonsterDao;
import discord.bot.domain.Monster;

public class MonsterService {
	
	private MonsterDao md = new MonsterDao();
	
	public List<Monster> selectMonsters(int floor, int map){
		System.out.println("Getting needed monsters from DB.");
		return md.selectMonsters(floor, map);
	}


}
