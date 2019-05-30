package discord.bot.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import discord.bot.domain.Map;
import discord.bot.domain.Monster;
import discord.bot.tools.JDBCUtils;

public class MonsterDao {
	
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	
	public List<Monster> selectMonsters(int floor, int map) {
		try {
			String sql = "SELECT * FROM monster WHERE floor="+ floor + " and map=" + map + ";";
			List<Monster> list = qr.query(sql, new BeanListHandler<>(Monster.class));
			return list;
		}
		catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Select monsters exception.");
		}
	}


}
