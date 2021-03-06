package discord.bot.controller;

import java.util.List;

import discord.bot.domain.Monster;
import discord.bot.domain.Player;
import discord.bot.service.MonsterService;

public class MonsterController {
	
	private MonsterService service = new MonsterService();
	
	public List<Monster> selectMonsters(int floor, int map){
		System.out.println("Getting monsters on floor" + floor + " and map " + map);
		return service.selectMonsters(floor, map);
	}
	public List<Monster> selectAll(){
		System.out.println("Getting all monsters in game.");
		return service.selectAll();
	}
	public List<Monster> selectFloorBoss(int floor){
		return service.selectFloorBoss(floor);
	}
	public List<Monster> selectMonsterToFight(Player p, String mon){
		return service.selectMonsterToFight(p, mon);
	}


}
