package discord.bot.domain;

import discord.bot.Main;

/**
 * JavaBean corresponding to the PLayer table in DB.
 * 
 * @author zachalaniz
 *
 */

public class Player {
	
	private int id;
	private String discid;
	private String username;
	private int money;
	private int	lvl;
	private double exp;
	private int floor;
	private int map;

	
	public Player(int pid, String discordId, String pname, int money, int lvl, double exp, int floor, int map) {
		this.id = pid;
		this.discid = discordId;
		this.username = pname;
		this.money = money;
		this.lvl = lvl;
		this.exp = exp;
		this.floor = floor;
		this.map = map;
		
	}
	
	public Player() {
		this.money = 0;
		this.lvl = 1;
		this.exp = 0.00;
		this.floor = 1;
		this.map = 1;
	}
		

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDiscid() {
		return discid;
	}
	public void setDiscid(String discid) {
		this.discid = discid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getLvl() {
		return lvl;
	}
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	public double getExp() {
		return exp;
	}
	public void setExp(double exp) {
		this.exp = exp;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public int getMap() {
		return map;
	}
	public void setMap(int map) {
		this.map = map;
	}
	
	public boolean canMoveRight() {
		
		boolean cm = false;
		
		if(  (getMap() + 1) <= Main.gb.getMaps() ) { 
			setMap(getMap() + 1);
			cm = true;
		}
		return cm;
		
	}
	public boolean canMoveLeft() {
		
		boolean cm = false;
		
		if(  (getMap() - 1) > 0 ) { 
			setMap(getMap() - 1);
			cm = true;
		}
		return cm;
		
	}
}
