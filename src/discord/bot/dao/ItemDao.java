package discord.bot.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import discord.bot.domain.Item;
import discord.bot.domain.Monster;
import discord.bot.domain.Player;
import discord.bot.tools.JDBCUtils;

public class ItemDao {
	
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	
	public List<Item> selectPlayerEquips(Player p) {
		try {
			String sql = "SELECT i.id, i.name, i.lvl, i.att, i.def, i.type, i.worth, i.classification FROM equip e join item i on e.iid=i.id join player p on e.pid=" + p.getId() + ";";
			List<Item> list = qr.query(sql, new BeanListHandler<>(Item.class));
			return list;
		}
		catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Select player current equips exception.");
		}
	}
	
	public List<Item> selectMonsterDrops(Monster m) {
		try {
			String sql = "SELECT * FROM droplist d join item i on d.iid=i.id where d.mid=" + m.getId() + ";";
			List<Item> list = qr.query(sql, new BeanListHandler<>(Item.class));
			return list;
		}
		catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Select monster drops exception.");
		}
	}
	public List<Item> selectPlayerItems(Player p) {
		try {
			String sql = "SELECT  i.id, i.name, i.lvl, i.att, i.def, i.type, i.worth, i.classification, i.hp, i.rate FROM inventory inv join item i on inv.iid=i.id where inv.pid="+p.getId() + ";";
			List<Item> list = qr.query(sql, new BeanListHandler<>(Item.class));
			return list;
		}
		catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Select monster drops exception.");
		}
	}


}
