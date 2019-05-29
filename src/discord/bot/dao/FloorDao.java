package discord.bot.dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import discord.bot.domain.Floor;
import discord.bot.domain.Map;
import discord.bot.tools.JDBCUtils;

public class FloorDao {
	
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	
	public List<Floor> selectAll() {
		try {
			String sql = "SELECT * FROM floor";
			List<Floor> list = qr.query(sql, new BeanListHandler<>(Floor.class));
			return list;
		}
		catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Select all exception.");
		}
	}
	
	public List<Map> selectAllFloorMaps(int floor) {
		try {
			String sql = "SELECT * FROM map WHERE floor="+ floor + ";";
			List<Map> list = qr.query(sql, new BeanListHandler<>(Map.class));
			return list;
		}
		catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Select all maps for floor exception.");
		}
	}


}
