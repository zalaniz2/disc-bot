package discord.bot.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import discord.bot.domain.Levels;
import discord.bot.domain.Player;
import discord.bot.tools.JDBCUtils;

public class LevelsDao {
	
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	
	public List<Levels> selectPlayerLevel(Player p) {
		try {
			String sql = "SELECT * FROM levels where lvl="+(p.getLvl()+1) + ";";
			List<Levels> list = qr.query(sql, new BeanListHandler<>(Levels.class));
			return list;
		}
		catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Select player level table exception.");
		}
	}

}
