package discord.bot.service;

import java.util.List;

import discord.bot.dao.PlayerDao;
import discord.bot.domain.Player;

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

}
