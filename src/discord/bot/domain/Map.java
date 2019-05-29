package discord.bot.domain;

public class Map {
	
	private int id;
	private String name;
	private int floor;
	private int number;
	
	public Map() {}
	
	public Map(int mid, String mname, int mfloor, int mnumber) {
		
		this.id = mid;
		this.name = mname;
		this.floor = mfloor;
		this.number = number;
		
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
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public int getNumber() {
		return id;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
	

}
