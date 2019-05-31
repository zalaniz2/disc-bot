package discord.bot.domain;

public class Monster {
	
	private int id;		
    private String name;		
	private int lvl;				
    private int att;					
    private int def;					
    private int floor;					
    private int map;	
    private int hp;					
    private boolean isboss;
    private int coins;
    
    public Monster() {}
    
    public Monster(int mid, String mname, int mlvl, int matt, int mdef, int mfloor, int mmap, int mhp, boolean isboss, int mcoins) {
    	
    		this.setId(mid);
    		this.setName(mname);
    		this.setLvl(mlvl);
    		this.setAtt(matt);
    		this.setDef(mdef);
    		this.setFloor(mfloor);
    		this.setMap(mmap);
    		this.setHp(mhp);
    		this.setIsboss(isboss);
    		this.setCoins(mcoins);
    	
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
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

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public boolean isIsboss() {
		return isboss;
	}

	public void setIsboss(boolean isboss) {
		this.isboss = isboss;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}
    

}
