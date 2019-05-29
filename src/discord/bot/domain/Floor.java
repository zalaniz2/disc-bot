package discord.bot.domain;

public class Floor {
	
	private int id;
	private String name;
	private int requirement;
	private String bossname;
	private int maps;
	
	public Floor() {}
	
	public Floor(int fid, String fname, int req, String bn, int m) {
		
		this.id = fid;
		this.name = fname;
		this.requirement = req;
		this.bossname = bn;
		this.maps = m;
		
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
	public int getRequirement() {
		return requirement;
	}
	public void setRequirement(int requirement) {
		this.requirement = requirement;
	}
	public String getBossname() {
		return bossname;
	}
	public void setBossname(String bossname) {
		this.bossname = bossname;
	}
	public int getMaps() {
		return maps;
	}
	public void setMaps(int maps) {
		this.maps = maps;
	}

}
