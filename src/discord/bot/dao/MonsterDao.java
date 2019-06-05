package discord.bot.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import discord.bot.domain.Map;
import discord.bot.domain.Monster;
import discord.bot.domain.Player;
import discord.bot.tools.JDBCUtils;

public class MonsterDao {
	
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	
	public List<Monster> selectMonsters(int floor, int map) {
		try {
			String sql = "SELECT * FROM monster WHERE floor="+ floor + " and map=" + map + " and isboss=false;";
			List<Monster> list = qr.query(sql, new BeanListHandler<>(Monster.class));
			return list;
		}
		catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Select monsters exception.");
		}
	}
	public List<Monster> selectAll() {
		try {
			String sql = "SELECT * FROM monster;";
			List<Monster> list = qr.query(sql, new BeanListHandler<>(Monster.class));
			return list;
		}
		catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Select all monsters exception.");
		}
	}
	
	public List<Monster> selectFloorBoss(int floor) {
		try {
			String sql = "SELECT * FROM monster where floor=" + floor + " and isboss=true;";
			List<Monster> list = qr.query(sql, new BeanListHandler<>(Monster.class));
			return list;
		}
		catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Select floor boss exception.");
		}
	}

	public List<Monster> selectMonsterToFight(Player p, String mon) {
		try {
			String sql = "SELECT * FROM monster where floor=" + p.getFloor() + " and map=" + p.getMap() + " and name='" + mon + "';";
			List<Monster> list = qr.query(sql, new BeanListHandler<>(Monster.class));
			return list;
		}
		catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Select monster from map to fight exception.");
		}
	}

}
