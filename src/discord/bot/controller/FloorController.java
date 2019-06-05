package discord.bot.controller;

import java.util.List;

import discord.bot.domain.Floor;
import discord.bot.domain.Map;
import discord.bot.service.FloorService;

public class FloorController {
	
	private FloorService service = new FloorService();
	
	public List<Floor> selectAll(){
		System.out.println("Getting all floors.");
		return service.selectAll();
	}
	
	public List<Map> selectAllFloorMaps(int floor){
		System.out.println("Getting all maps for current floor.");
		return service.selectAllFloorMaps(floor);
		
	}
	
	public List<Floor> selectFloor(int floor){
		System.out.println("Getting floor " + floor + " info.");
		return service.selectFloor(floor);
	}
	
	public List<Floor> selectTopFloor(){
		return service.selectTopFloor();
	}


}
