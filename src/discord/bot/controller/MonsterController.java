package discord.bot.controller;

import java.util.List;

import discord.bot.domain.Monster;
import discord.bot.service.MonsterService;

public class MonsterController {
	
	private MonsterService service = new MonsterService();
	
	public List<Monster> selectMonsters(int floor, int map){
		System.out.println("Getting monsters on floor" + floor + " and map " + map);
		return service.selectMonsters(floor, map);
	}


}
