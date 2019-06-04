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
	public List<Item> selectInventoryPotions(Player p) {
		try {
			String sql = "SELECT  i.id, i.name, i.lvl, i.att, i.def, i.type, i.worth, i.classification, i.hp, i.rate FROM inventory inv join item i on inv.iid=i.id where inv.pid=" + p.getId() + " and i.name='Potion'";

			List<Item> list = qr.query(sql, new BeanListHandler<>(Item.class));
			return list;
		}
		catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Select monster drops exception.");
		}
	}
	
	public List<Item> selectInventoryEquip(Player p, String it) {
		try {
			String sql = "SELECT  i.id, i.name, i.lvl, i.att, i.def, i.type, i.worth, i.classification, i.hp, i.rate FROM inventory inv join item i on inv.iid=i.id where inv.pid=" + p.getId() + " and i.name='"+it+"';";
			List<Item> list = qr.query(sql, new BeanListHandler<>(Item.class));
			return list;
		}
		catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Select player equip exception.");
		}
	}
	public List<Item> selectPlayerEquipType(Player p, Item i) {
		try {
			String sql = "SELECT i.id, i.name, i.lvl, i.att, i.def, i.type, i.worth, i.classification FROM equip e join item i on e.iid=i.id join player p on e.pid=" + p.getId() + " where i.type=" + i.getType() + ";";
			List<Item> list = qr.query(sql, new BeanListHandler<>(Item.class));
			return list;
		}
		catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Select player equipped item exception.");
		}
	}
	public List<Item> selectShopItems(Player p) {
		try {
			String sql = "SELECT  i.id, i.name, i.lvl, i.att, i.def, i.type, i.worth, i.classification, i.hp, i.rate FROM shop s join item i on s.iid=i.id where s.fid="+p.getFloor() + ";";
			List<Item> list = qr.query(sql, new BeanListHandler<>(Item.class));
			return list;
		}
		catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Select shop items exception.");
		}
	}
	public List<Item> selectItemFromShop(Player p, String i) {
		try {
			String sql = "SELECT  i.id, i.name, i.lvl, i.att, i.def, i.type, i.worth, i.classification, i.hp, i.rate FROM shop s join item i on s.iid=i.id where i.name='"+ i + "' and s.fid=" + p.getFloor() + ";";
			List<Item> list = qr.query(sql, new BeanListHandler<>(Item.class));
			return list;
		}
		catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Check shop item exception.");
		}
	}


}
