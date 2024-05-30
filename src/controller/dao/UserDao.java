package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Rol;
import model.User;
import util.DbConnection;

public class UserDao {
    
    private DbConnection conexion;
    
    private static final String INSERT_USER_SQL = "INSERT INTO user(name, email, address, rol_id) VALUES(?,?,?,?);";
    private static final String DELETE_USER_SQL = "DELETE FROM user WHERE id = ?;";
    private static final String UPDATE_USER_SQL = "UPDATE user SET name = ?, email = ?, address = ?, rol_id = ? WHERE id = ?;";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id = ?;";
    private static final String SELECT_ALL_USERS = "SELECT * FROM user;";
    
    public UserDao() {
        this.conexion = DbConnection.getConexion();
    }
    
    public void insert(User user) throws SQLException {
        try {
            PreparedStatement preparedStatement = conexion.setPreparedStatement(INSERT_USER_SQL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getAddress());
            preparedStatement.setInt(4, user.getRol_id().getId());
            conexion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void delete(int id) throws SQLException {
        try {
            PreparedStatement preparedStatement = conexion.setPreparedStatement(DELETE_USER_SQL);
            preparedStatement.setInt(1, id);
            conexion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void update(User user) throws SQLException {
        try {
            PreparedStatement preparedStatement = conexion.setPreparedStatement(UPDATE_USER_SQL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getAddress());
            preparedStatement.setInt(4, user.getRol_id().getId());
            preparedStatement.setInt(5, user.getId());
            conexion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<User> selectAll() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_ALL_USERS);
            ResultSet rs = conexion.query();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                int rolId = rs.getInt("rol_id");
                Rol rol = new Rol(); // Asumiendo que tienes una clase Rol con su correspondiente setter
                rol.setId(rolId);
                users.add(new User(id, name, email, address, rol));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    
    public User select(int id) {
        User user = null;
        try {
            PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = conexion.query();
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                int rolId = rs.getInt("rol_id");
                Rol rol = new Rol(); // Asumiendo que tienes una clase Rol con su correspondiente setter
                rol.setId(rolId);
                user = new User(id, name, email, address, rol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
