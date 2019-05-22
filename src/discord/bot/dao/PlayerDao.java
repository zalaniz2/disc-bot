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

}
