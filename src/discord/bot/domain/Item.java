package discord.bot.domain;

public class Item {
	
		private int id; 				
		private String name;				
		private int lvl;						
		private int att;					
		private int def;
		private int type;
		private int worth;			
	    private String classification;
	    private int hp;			       
        private double rate;					
		
		public Item() {}
		
		public Item(int aid, String iname, int ilvl, int iatt, int idef, int itype, int iworth, String iclassification, int ihp, double irate) {
			
			this.setId(aid);
			this.setName(iname);
			this.setLvl(ilvl);
			this.setAtt(iatt);
			this.setDef(idef);
			this.setType(itype);
			this.setWorth(iworth);
			this.setClassification(iclassification);
			this.setHp(ihp);
			this.setRate(irate);
			
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

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public int getWorth() {
			return worth;
		}

		public void setWorth(int worth) {
			this.worth = worth;
		}

		public String getClassification() {
			return classification;
		}

		public void setClassification(String classification) {
			this.classification = classification;
		}

		public int getHp() {
			return hp;
		}

		public void setHp(int hp) {
			this.hp = hp;
		}

		public double getRate() {
			return rate;
		}

		public void setRate(double rate) {
			this.rate = rate;
		}

}
