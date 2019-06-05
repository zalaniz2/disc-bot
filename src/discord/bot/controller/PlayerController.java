package discord.bot.controller;

import java.util.List;

import discord.bot.domain.Item;
import discord.bot.domain.Player;
import discord.bot.service.PlayerService;

public class PlayerController {
	
	private PlayerService service = new PlayerService();

	public List<Player> selectAll(){
		System.out.println("Select all has occured, sending to service layer");
		return service.selectAll();
	}
	
	public int createPlayer(Player newPlayer) {
		System.out.println("Creating new player..");
		return service.createPlayer(newPlayer);
	}
	
	public List<Player> selectPlayer(String id){
		System.out.println("Grabbing player with id: " + id);
		return service.selectPlayer(id);
	}
	public int deletePlayer(String id) {
		System.out.println("Deleting player with id = " + id);
		return service.deletePlayer(id);
	}
	public int updatePosition(Player p) {
		System.out.println("Updating player position.");
		return service.updatePosition(p);
	}
	public int updatePlayerCombat(Player p) {
		System.out.println("Updating player after combat");
		return service.updatePlayerCombat(p);
	}
	public int updatePlayerInventory(Item i, Player p) {
		System.out.println("Adding item " + i.getName() + " to player " + p.getUsername() + " inv.");
		return service.updatePlayerInventory(i, p);
	}
	public int updateMoney(Player p) {
		System.out.println("Adding money to player " + p.getUsername());
		return service.updateMoney(p);
	}
	public int updateHp(Player p) {
		System.out.println("Adding hp back to player " + p.getUsername());
		return service.updateHp(p);
	}
	public int removeInventoryItem(Item i, Player p) {
		return service.removeInventoryItem(i, p);
	}
	public int equipPlayerItem(Player p, Item i) {
		return service.equipPlayerItem(p, i);
	}
	public int removePlayerEquip(Player p, Item i) {
		return service.removePlayerEquip(p, i);
	}
	public int updatePlayerStats(Player p) {
		return service.updatePlayerStats(p);
	}
	public int updateFloorAccess(Player p, String access) {
		return service.updateFloorAccess(p, access);
	}
	public List<Player> selectPlayerByUsername(String name){
		return service.selectPlayerByUsername(name);
	}
	

	
}
