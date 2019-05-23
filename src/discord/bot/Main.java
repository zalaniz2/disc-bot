package discord.bot;

import discord.bot.commands.Commands;
import discord.bot.controller.PlayerController;
import discord.bot.domain.Player;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

public class Main {
	
	public static JDA jda;
	public static String prefix = ";";


	public static void main(String[] args) throws LoginException{
		
		//build & connect to bot
		jda = new JDABuilder(AccountType.BOT).setToken("NTgwODYwODY1NjA0MDkxOTEx.XOW3vg.Xc_SpFWGvk1OEWr3e8270sSMvoM").build(); 
		
		//set status of bot
		jda.getPresence().setStatus(OnlineStatus.ONLINE); //setting status of bot 

		//show it doing something
		jda.getPresence().setGame(Game.watching("Developing")); //set something its doing for you 

		//listen to commands from players
		jda.addEventListener(new Commands()); //listen for command events 

		

	}

}
