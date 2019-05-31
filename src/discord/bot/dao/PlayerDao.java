package discord.bot.dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
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
			String sql = "INSERT INTO player(discid, username, money, lvl, exp, floor, map, att, def, hp, maxhp, fa1, fa2, fa3, fa4, fa5) VALUES ('" + 
							newPlayer.getDiscid() + "','" + newPlayer.getUsername() + "'," + newPlayer.getMoney() + "," + newPlayer.getLvl() + "," + newPlayer.getExp() + "," + newPlayer.getFloor() + "," + newPlayer.getMap() + "," + 
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

}
