package discord.bot.dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import discord.bot.domain.Item;
import discord.bot.domain.Player;
import discord.bot.tools.JDBCUtils;

public class PlayerDao {
	
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

	public List<Player> selectAll() {
		try {
			String sql = "SELECT * FROM player";
			List<Player> list = qr.query(sql, new BeanListHandler<>(Player.class));
			return list;
		}
		catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Select all exception!");
		}
	}
	
	public List<Player> selectPlayer(String id) {
		try {
			String sql = "SELECT * FROM player where discid='"+id+"';" ;
			List<Player> list = qr.query(sql, new BeanListHandler<>(Player.class));
			return list;
		}
		catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Select all exception!");
		}
	}
	
	public int createPlayer(Player newPlayer) {
		try {
			String sql = "INSERT INTO player(discid, username, money, lvl, exp, percent, floor, map, att, def, hp, maxhp, fa1, fa2, fa3, fa4, fa5) VALUES ('" + 
							newPlayer.getDiscid() + "','" + newPlayer.getUsername() + "'," + newPlayer.getMoney() + "," + newPlayer.getLvl() + "," + newPlayer.getExp() + "," + newPlayer.getPercent() + "," + newPlayer.getFloor() + "," + newPlayer.getMap() + "," + 
							newPlayer.getAtt() + "," + newPlayer.getDef() + "," + newPlayer.getHp() + "," + newPlayer.getMaxhp() + "," + newPlayer.isFa1() + "," + newPlayer.isFa2() + "," + newPlayer.isFa3() + "," + newPlayer.isFa4() + "," + newPlayer.isFa5() + ");";
			int p = qr.update(sql);
			return p;
		} 
		catch (SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Create exception");
		}
	}
	public int deletePlayer(String id) {
		try {
			String sql = "DELETE FROM player where discid='"+id+"';";
			int p = qr.update(sql);
			return p;
		} 
		catch (SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Create exception");
		}
	}
	public int updatePosition(Player p) {
		try {
			String sql = "UPDATE player SET map=" + p.getMap() + " where discid='" + p.getDiscid() + "';";
			int play = qr.update(sql);
			return play;
		} 
		catch (SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Update position exception");
		}
	}
	public int updatePlayerCombat(Player p) {
		try {
			String sql = "UPDATE player SET lvl=" + p.getLvl() + ",exp=" + p.getExp() + ",percent=" + p.getPercent() + ",hp="+p.getHp() + ",maxHp="+p.getMaxhp() + ",att="+p.getAtt()+",def="+p.getDef()+ " where discid='" + p.getDiscid() + "';";   
			int play = qr.update(sql);
			return play;
		} 
		catch (SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Update player after combat exception");
		}
		
	}
	public int updatePlayerInventory(Item i, Player p) {
		try {
			String sql = "INSERT INTO inventory(pid, iid) VALUES(" + p.getId() + "," + i.getId() + ");";   
			int play = qr.update(sql);
			return play;
		} 
		catch (SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Update player inventory exception");
		}
		
	}
	public int updateMoney(Player p) {
		try {
			String sql = "UPDATE player SET money="+ p.getMoney() + " where id="+p.getId() + ";";   
			int play = qr.update(sql);
			return play;
		} 
		catch (SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Update player money exception");
		}
		
	}
	public int updateHp(Player p) {
		try {
			String sql = "UPDATE player SET hp="+ p.getHp() + " where id="+p.getId() + ";";   
			int play = qr.update(sql);
			return play;
		} 
		catch (SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Update player hp exception");
		}
		
	}
	public int removeInventoryItem(Item i, Player p) {
		try {
			String sql = "DELETE FROM inventory where iid="+i.getId() + " and pid="+p.getId() + " LIMIT 1"; 
			int play = qr.update(sql);
			return play;
		} 
		catch (SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Update player inventory removr exception");
		}
		
	}
	public int equipPlayerItem(Player p, Item i) {
		try {
			String sql =  "INSERT INTO equip(pid, iid) VALUES(" + p.getId() + "," + i.getId() + ");"; 
			int play = qr.update(sql);
			return play;
		} 
		catch (SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Update player current equips exception");
		}
		
	}
	public int removePlayerEquip(Player p, Item i) {
		try {
			String sql = "DELETE FROM equip where iid="+i.getId() + " and pid="+p.getId() + ";"; 
			int play = qr.update(sql);
			return play;
		} 
		catch (SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Remove player equip exception");
		}
		
	}
	public int updatePlayerStats(Player p) {
		try {
			String sql = "UPDATE player SET att="+ p.getAtt() + ",def=" + p.getDef() + " where id="+p.getId() + ";";   
			int play = qr.update(sql);
			return play;
		} 
		catch (SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Update player stats exception");
		}
		
	}
	public int updateFloorAccess(Player p, String access) {
		try {
			String sql = "UPDATE player SET " + access + "=true where id="+p.getId() + ";";   
			int play = qr.update(sql);
			return play;
		} 
		catch (SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Update player access exception");
		}
		
	}
	

}
