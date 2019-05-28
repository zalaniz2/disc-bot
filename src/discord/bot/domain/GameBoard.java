package discord.bot.domain;

public class GameBoard {
	
	private int[][] world;
	private int floors;
	private int maps;
	
	public GameBoard() {}
	
	public GameBoard( int floors, int maps) {
		
		world = new int[floors][maps]; // create a map of floors x map (5x5)
		this.floors = floors; //max floors
		this.maps = maps; //max maps per floor
	}
	
	 public void createGameBoard() {
		
		for( int i = 0; i<floors; i++) { //make board symmetrical
			for( int j = 0; j<maps; j++) {
				world[i][j] = 1;
			}
		}
		
	}
	
	public int getFloors() {
		return floors;
	}
	public void setFloors(int floors) {
		this.floors = floors;
	}
	public int getMaps() {
		return maps;
	}
	public void setMaps(int maps) {
		this.maps = maps;
	}
	
	public int[][] getWorld() {
		return world;
	}
	

}
