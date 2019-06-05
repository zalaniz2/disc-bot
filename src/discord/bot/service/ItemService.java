package discord.bot.service;

import java.util.List;

import discord.bot.dao.ItemDao;
import discord.bot.domain.Item;
import discord.bot.domain.Monster;
import discord.bot.domain.Player;

public class ItemService {
	
	private ItemDao id = new ItemDao();
	
	public List<Item> selectPlayerEquips(Player p){
		System.out.println("Getting player equips from DB.");
		return id.selectPlayerEquips(p);
	}
	public List<Item> selectMonsterDrops(Monster m){
		System.out.println("Getting drops from DB.");
		return id.selectMonsterDrops(m);

	}
	public List<Item> selectPlayerItems(Player p){
		System.out.println("Getting inventory.");
		return id.selectPlayerItems(p);
	}
	public List<Item> selectInventoryPotions(Player p){
		return id.selectInventoryPotions(p);
	}
	public List<Item> selectInventoryEquip(Player p, String it){
		return id.selectInventoryEquip(p, it);
	}
	public List<Item> selectPlayerEquipType(Player p, Item i){
		return id.selectPlayerEquipType(p, i);
	}
	public List<Item> selectShopItems(Player p){
		return id.selectShopItems(p);
	}
	public List<Item> selectItemFromShop(Player p, String i){
		return id.selectItemFromShop(p, i);
	}
	public List<Item> selectPlayerEquipName(Player p, String name){
		return id.selectPlayerEquipName(p, name);
	}

	
	

}
