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
	private int att;
	private int def;
	private int hp;					
	private int maxhp;
	private boolean fa1;
	private boolean fa2;
	private boolean fa3;
	private boolean fa4;
	private boolean fa5;

	  
	public Player(int pid, String discordId, String pname, int money, int lvl, double exp, int floor, int map, int patt, int pdef, int php, int pmaxhp, boolean fa1, boolean fa2, boolean fa3, boolean fa4, boolean fa5) {
		this.id = pid;
		this.discid = discordId;
		this.username = pname;
		this.money = money;
		this.lvl = lvl;
		this.exp = exp;
		this.floor = floor;
		this.map = map;
		this.att = patt;
		this.def = pdef;
		this.hp = php;
		this.maxhp = pmaxhp;
		this.fa1 = fa1;
		this.fa2 = fa2;
		this.fa3 = fa3;
		this.fa4 = fa4;
		this.fa5 = fa5;
	}
	
	public Player() {
		
		this.money = 0;
		this.lvl = 1;
		this.exp = 0.00;
		this.floor = 1;
		this.map = 1;
		this.att = 5;
		this.def = 5;
		this.hp = 10;
		this.maxhp = 10;
		this.fa1 = true;
		this.fa2 = false;
		this.fa3 = false;
		this.fa4 = false;
		this.fa5 = false;
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

	public int getAtt() {
		return att;
	}

	public void setAtt(int att) {
		this.att = att;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMaxhp() {
		return maxhp;
	}

	public void setMaxhp(int maxhp) {
		this.maxhp = maxhp;
	}

	public boolean isFa1() {
		return fa1;
	}

	public void setFa1(boolean fa1) {
		this.fa1 = fa1;
	}

	public boolean isFa2() {
		return fa2;
	}

	public void setFa2(boolean fa2) {
		this.fa2 = fa2;
	}

	public boolean isFa3() {
		return fa3;
	}

	public void setFa3(boolean fa3) {
		this.fa3 = fa3;
	}

	public boolean isFa4() {
		return fa4;
	}

	public void setFa4(boolean fa4) {
		this.fa4 = fa4;
	}

	public boolean isFa5() {
		return fa5;
	}

	public void setFa5(boolean fa5) {
		this.fa5 = fa5;
	}
	
	
}
