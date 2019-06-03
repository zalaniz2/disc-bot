package discord.bot.game;


import java.util.ArrayList;

import discord.bot.controller.LevelsController;
import discord.bot.controller.PlayerController;
import discord.bot.domain.Monster;
import discord.bot.domain.Player;
import discord.bot.domain.Item;
import discord.bot.domain.Levels;

public class Combat {
	
	private PlayerController pc = new PlayerController();
	private LevelsController lc = new LevelsController();
	
	public Combat() {}
	
	public boolean fight(Player p, Monster m) {
		
		
		
		Double playerChance = (double)  1 - ((double) m.getDef() / (double) p.getAtt());
		Double monsterChance = ((double) 1 -  ( ( (double) p.getDef() / (double) m.getAtt()) ) ) / 2;
		int playerDamage = p.getAtt() / 2;
		int monsterDamage = m.getAtt() / 2;
		Double hit;
		int dmg;
		
		if( monsterChance <= 0) {
			monsterChance = 0.05;
		}
		if( playerChance <= 0) {
			playerChance = 0.10;
		}
		if( monsterDamage == 0) {
			monsterDamage = 1;
		}
		
		System.out.println("Players chance to do damage is " + playerChance + " their max damage possibility is " + playerDamage);
		System.out.println("Monsters chance to do damage is " + monsterChance + " their max dmg possibility is " + monsterDamage + " monster is " + m.getName());
		
		/*
		for( int i = 0; i<3; i++) {
			dmg = (int) (Math.random() * (playerDamage + 1) );
			System.out.println("Player damage roll is" + dmg);

		}
		*/
		
		
		while( true ) {
			
			if( p.getHp() <= 0 || m.getHp() <= 0) {
				break;
			}
			
			hit = Math.random();
			
			if( hit <= playerChance ) {
				
				dmg = (int) (Math.random() * (playerDamage + 1) );
				m.setHp(m.getHp() - dmg);
				
			}
			
			hit = Math.random();
			
			if( hit <= monsterChance ) {
				
				dmg = (int) (Math.random() * (monsterDamage + 1) );
				p.setHp(p.getHp() - dmg);
				
			}
			
			
		}
		
		System.out.println("Combat is over, player hp after is "+ p.getHp() + " and monster hp is " + m.getHp());
		
		if( m.getHp() <= 0) {
			return true;
		}
		else {
			return false;
		}
		
		
		
	}
	
	public void updateExp(Player p, int exp, ArrayList<Levels> lvls) {
		
		System.out.println("Exp gain is +" + exp);
		
				
		System.out.println("Player level is " + p.getLvl() + " to next level u need " + lvls.get(0).getExp());
		
		p.setExp(p.getExp() + exp);
		
		if ( p.getExp() >= lvls.get(0).getExp()) {
			System.out.println("Level up!");
			p.setLvl(p.getLvl() + 1);
			p.setExp(0);
			p.setPercent(0.00);
			p.setMaxhp(p.getMaxhp() + 1);
			p.setHp(p.getMaxhp());
			p.setAtt(p.getAtt() + 1);
			p.setDef(p.getDef() + 1);
			System.out.println("New level is level: " + p.getLvl());
		}
		else {
			System.out.println("How  much exp left to get: " + (lvls.get(0).getExp() - p.getExp() ));
			double pcnt = (double) p.getExp() / (double) lvls.get(0).getExp();
			p.setPercent(pcnt);
			System.out.println("Percentage is %" + pcnt);
		}
		
		
	}
	
	public void updateInventory(Player p, ArrayList<Item> drops, int money) {
		
		Double hit;
				
		System.out.println("Possible items: " + drops.size());
		
		for( int i = 0; i<drops.size(); i++) {
			System.out.println("Item check: " + drops.get(i).getName());
			hit = Math.random();
			if( hit <= drops.get(i).getRate()) {
				System.out.println("Got item: " + drops.get(i).getName());
				int in = pc.updatePlayerInventory(drops.get(i), p);
			}
			else {
				System.out.println("didnt get item: " + drops.get(i).getName());
			}
		}
		
		int gain =  (int)(Math.random() *  money );
		
		System.out.println("Max coins to get: " + money + " coins gotten: " + gain );
		
		p.setMoney(p.getMoney() + gain);
		
		int add = pc.updateMoney(p);
		
	}
	

}
