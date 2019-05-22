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

}
