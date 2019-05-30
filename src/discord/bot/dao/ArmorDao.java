package discord.bot.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import discord.bot.domain.Armor;
import discord.bot.domain.Player;
import discord.bot.tools.JDBCUtils;

public class ArmorDao {
	
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	
	public List<Armor> selectPlayerEquips(Player p) {
		try {
			String sql = "SELECT  a.id, a.name, a.lvl, a.att, a.def, a.type FROM equip e join armor a on e.aid=a.id join player p on e.pid=" + p.getId() + ";";
			List<Armor> list = qr.query(sql, new BeanListHandler<>(Armor.class));
			return list;
		}
		catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Select player current equips exception.");
		}
	}


}
