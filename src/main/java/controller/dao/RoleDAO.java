package controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Role;
import util.SQLConnetion;

public class RoleDAO {
	private Connection getConnection () {
		return new SQLConnetion().getSQLConnection();
	}
	public RoleDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Role> list () {
		List<Role> roles = new ArrayList<Role>();
		try (Connection con = this.getConnection()) {
			try(PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM roles")) {
				preparedStatement.execute();
				
				ResultSet result = preparedStatement.getResultSet();
				
				while (result.next()) {
					Role rol = new Role();
					rol.setId(result.getInt("IdRol"));
					rol.setName(result.getString("Name"));
					
					roles.add(rol);
				}
				
				return roles;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
	}
}
