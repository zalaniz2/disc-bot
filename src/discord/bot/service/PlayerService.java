package discord.bot.service;

import java.util.List;

import discord.bot.dao.PlayerDao;
import discord.bot.domain.Player;
import discord.bot.domain.Item;

public class PlayerService {
	
	private PlayerDao pd = new PlayerDao();

	public List<Player> selectAll() {
		System.out.println("Service layer accessing DAO to do select all.");
		return pd.selectAll();
	}
	
	public int createPlayer(Player newPlayer) {
		System.out.println("Service layer send Player to DB to be created..");
		return pd.createPlayer(newPlayer);
	}
	
	public List<Player> selectPlayer(String id){
		System.out.println("Service layer getting player from DB.");
		return pd.selectPlayer(id);
	}
	public int deletePlayer(String id) {
		System.out.println("Service layer deleting player from DB.");
		return pd.deletePlayer(id);
	}
	public int updatePosition(Player p) {
		System.out.println("Updating position in DB.");
		return pd.updatePosition(p);
	}
	public int updatePlayerCombat(Player p) {
		System.out.println("Updating player in DB.");
		return pd.updatePlayerCombat(p);
	}
	public int updatePlayerInventory(Item i, Player p) {
		System.out.println("Updating inv. in DB.");
		return pd.updatePlayerInventory(i, p);
	}
	public int updateMoney(Player p) {
		System.out.println("Updating money in DB.");
		return pd.updateMoney(p);
	}
	public int updateHp(Player p) {
		return pd.updateHp(p);
	}
	public int removeInventoryItem(Item i, Player p) {
		return pd.removeInventoryItem(i, p);
	}
	public int equipPlayerItem(Player p, Item i) {
		return pd.equipPlayerItem(p, i);
	}
	public int removePlayerEquip(Player p, Item i) {
		return pd.removePlayerEquip(p, i);
	}

}
