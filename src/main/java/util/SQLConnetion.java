package util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class SQLConnetion {
	private DataSource datasource;
	
	public SQLConnetion () {
		//Creamos un pool de conexiones
		ComboPooledDataSource pool = new ComboPooledDataSource();
		pool.setJdbcUrl("jdbc:mysql://localhost/market_plus?useTimeZone=true&serverTimeZone=UTC");
		pool.setUser("miguecode");
		pool.setPassword("miguecode");
		//Asginar el maximo de conexiones en paralelo
		pool.setMaxPoolSize(30);
		
		this.datasource = pool;
	}
	
	public Connection getSQLConnection() {
		try {
			return this.datasource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
