package discord.bot.tools;


import org.apache.commons.dbcp.BasicDataSource;

public class JDBCUtils {
	
	private static BasicDataSource datasource = new BasicDataSource();
	
	//initialize static variables, executed when class is loaded into memory
	static {
		
		datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		
		//setTimeZone fix
		datasource.setUrl("jdbc:mysql://localhost:3306/disc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
		
		//username into database
		datasource.setUsername("discordbot");
		
		//db pass
		datasource.setPassword("mydiscbot225");
		
		//max # of connections at one time
		datasource.setMaxActive(10);
		
		//max number of connections that can remain idle
		datasource.setMaxIdle(5);
		
		//min number of connections that can remain idle
		datasource.setMinIdle(2);
		
		//size of initial connection pool
		datasource.setInitialSize(10);
		
	}
	
	//return DataSource object 
	public static BasicDataSource getDataSource() {
		return datasource;
	}

}
