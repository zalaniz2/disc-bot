package discord.bot.domain;

public class Monster {
	
	private int id;		
    private String name;		
	private int lvl;				
    private int att;					
    private int def;					
    private int floor;					
    private int map;		
    
    public Monster() {}
    
    public Monster(int mid, String mname, int mlvl, int matt, int mdef, int mfloor, int mmap) {
    	
    		this.setId(mid);
    		this.setName(mname);
    		this.setLvl(mlvl);
    		this.setAtt(matt);
    		this.setDef(mdef);
    		this.setFloor(mfloor);
    		this.setMap(mmap);
    	
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
    

}
