package discord.bot;

import discord.bot.controller.PlayerController;
import discord.bot.domain.Player;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		List<Player> result = new ArrayList<Player>();
		PlayerController pc = new PlayerController();
		
		result = pc.selectAll();
		System.out.println("Only current player is " + result.get(0).getUsername());

	}

}
