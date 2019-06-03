package discord.bot.domain;

public class Levels {
	
	private int lvl;
	private int exp;
	
	public Levels() {};
	
	public Levels(int plvl, int pexp) {
		this.setLvl(plvl);
		this.setExp(pexp);
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}
	

}
