package discord.bot.controller;

import java.util.List;
import discord.bot.domain.Item;
import discord.bot.domain.Monster;
import discord.bot.domain.Player;
import discord.bot.service.ItemService;

public class ItemController {
	
	private ItemService service = new ItemService();
	
	public List<Item> selectPlayerEquips(Player p){
		System.out.println("Getting current equips for player " + p.getDiscid());
		return service.selectPlayerEquips(p);
		
	}
	
	public List<Item> selectMonsterDrops(Monster m){
		System.out.println("Getting all monster drop list.");
		return service.selectMonsterDrops(m);

	}
	
	public List<Item> selectPlayerItems(Player p){
		System.out.println("Getting inventory for player " + p.getUsername());
		return service.selectPlayerItems(p);
		
	}
	
	public List<Item> selectInventoryPotions(Player p){
		System.out.println("Checking for potions from player " + p.getUsername());
		return service.selectInventoryPotions(p);
	}
	
	public List<Item> selectInventoryEquip(Player p, String it){
		return service.selectInventoryEquip(p, it);
	}
	public List<Item> selectPlayerEquipType(Player p, Item i){
		return service.selectPlayerEquipType(p, i);
	}
	public List<Item> selectShopItems(Player p){
		return service.selectShopItems(p);
	}
	public List<Item> selectItemFromShop(Player p, String i){
		return service.selectItemFromShop(p, i);
	}

	
	

}
