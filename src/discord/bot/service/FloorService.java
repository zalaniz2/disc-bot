package discord.bot.service;

import java.util.List;
import discord.bot.dao.FloorDao;
import discord.bot.domain.Floor;
import discord.bot.domain.Map;


public class FloorService {
	
	private FloorDao fd = new FloorDao();
	
	public List<Floor> selectAll(){
		System.out.println("Getting all floors from DB.");
		return fd.selectAll();
	}
	
	public List<Map> selectAllFloorMaps(int floor){
		System.out.println("Getting floor " + floor + " maps from DB.");
		return fd.selectAllFloorMaps(floor);
	}
	public List<Floor> selectFloor(int floor){
		System.out.println("Getting floor from DB.");
		return fd.selectFloor(floor);
	}


}
