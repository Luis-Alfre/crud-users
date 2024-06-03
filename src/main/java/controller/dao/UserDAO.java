package controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Role;
import model.User;
import util.SQLConnetion;

public class UserDAO {
	private Connection getConnection () {
		return new SQLConnetion().getSQLConnection();
	}
	
	public UserDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean save (User user) {
		try (Connection con = this.getConnection()) {
			try(PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO USER(Name, Email, DocumentId, Address, Rol_Id) VALUES(?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
				preparedStatement.setString(1, user.getName());
				preparedStatement.setString(2, user.getEmail());
				preparedStatement.setLong(3, user.getDocumentId());
				preparedStatement.setString(4, user.getAddress());
				preparedStatement.setInt(5, user.getRol_id());
				preparedStatement.execute();
				
							
				try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
					while (resultSet.next()) {
						user.setId(resultSet.getInt(1));
						System.out.println(String.format("Fue insertado el user %s: ", user));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return true;
	}
	
	public User getByDocument (Long document) {
		try (Connection con = this.getConnection()) {
			try(PreparedStatement preparedStatement = con.prepareStatement("SELECT U.*, R.* FROM user U INNER JOIN Roles R ON R.IdRol = U.Rol_Id WHERE U.DocumentId = ?")) {
				preparedStatement.setLong(1, document);
				preparedStatement.execute();
				
				ResultSet result = preparedStatement.getResultSet();
				
				while (result.next()) {
					User user = new User(result.getInt("U.Id"), result.getLong("U.DocumentId") , result.getString("U.Name"), result.getString("U.Email"), result.getString("U.Address"), result.getInt("U.Rol_Id"));
					Role role = new Role(result.getInt("R.IdRol"), result.getString("R.Name"));
					user.setRole(role);
					return user;
				}
				
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
	}
	
	public boolean delete (Long documentId) {
		try(Connection con = this.getConnection()) {
			try (PreparedStatement statement = con.prepareStatement("DELETE FROM USER WHERE DocumentId = ?")){
				statement.setLong(1, documentId);
				statement.execute();
				
				
				return true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean update (User user) {
		try(Connection con = this.getConnection()) {
			try(PreparedStatement statement = con.prepareStatement("UPDATE USER SET DOCUMENTID=?, NAME=?, EMAIL=?, ADDRESS=?, ROL_ID=? WHERE ID=?")) {
				statement.setLong(1, user.getDocumentId());
				statement.setString(2, user.getName());
				statement.setString(3, user.getEmail());
				statement.setString(4, user.getAddress());
				statement.setLong(5, user.getRol_id());
				statement.setLong(6, user.getId());
				statement.execute();
				
				return true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<User> list () {
		List<User> users = new ArrayList<User>();
		try (Connection con = this.getConnection()) {
			try(PreparedStatement preparedStatement = con.prepareStatement("SELECT U.*, R.* FROM user U INNER JOIN Roles R ON R.IdRol = U.Rol_Id")) {
				preparedStatement.execute();
				
				ResultSet result = preparedStatement.getResultSet();
				
				while (result.next()) {
					User user = new User(result.getInt("U.Id"), result.getLong("U.DocumentId") , result.getString("U.Name"), result.getString("U.Email"), result.getString("U.Address"), result.getInt("U.Rol_Id"));
					Role role = new Role(result.getInt("R.IdRol"), result.getString("R.Name"));
					user.setRole(role);
					users.add(user);
				}
				
				return users;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
	}
	
	public List<User> listByRole(Integer roleId) {
		List<User> users = new ArrayList<User>();
		try (Connection con = this.getConnection()) {
			try(PreparedStatement preparedStatement = con.prepareStatement("SELECT U.*, R.* FROM user U INNER JOIN Roles R ON R.IdRol = U.Rol_Id WHERE U.Rol_Id = ?")) {
				preparedStatement.setInt(1, roleId);
				preparedStatement.execute();
				ResultSet result = preparedStatement.getResultSet();
				
				while (result.next()) {
					User user = new User(result.getInt("U.Id"), result.getLong("U.DocumentId") , result.getString("U.Name"), result.getString("U.Email"), result.getString("U.Address"), result.getInt("U.Rol_Id"));
					Role role = new Role(result.getInt("R.IdRol"), result.getString("R.Name"));
					user.setRole(role);
					users.add(user);
				}
				
				return users;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
	}

}
