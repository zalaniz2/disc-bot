package discord.bot.domain;

/**
 * JavaBean corresponding to the PLayer table in DB.
 * 
 * @author zachalaniz
 *
 */

public class Player {
	
	private int id;
	private int discid;
	private String username;

	
	public Player(int pid, int discordId, String pname) {
		this.id = pid;
		this.discid = discordId;
		this.username = pname;
		
	}
	
	public Player() {} //default
	
	public String showPlayer() {
		return "Player info: Id = " + this.id + " Discord ID = " + this.discid + " Username = " + username;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDiscid() {
		return discid;
	}
	public void setDiscid(int discid) {
		this.discid = discid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
