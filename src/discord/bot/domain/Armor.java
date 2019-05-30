package discord.bot.domain;

public class Armor {
	
		private int id; 				
		private String name;				
		private int lvl;						
		private int att;					
		private int def;
		private int type;
		
		public Armor() {}
		
		public Armor(int aid, String aname, int alvl, int aatt, int adef, int atype) {
			
			this.setId(aid);
			this.setName(aname);
			this.setLvl(alvl);
			this.setAtt(aatt);
			this.setDef(adef);
			this.setType(atype);
			
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

}
