package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnection {
    private Connection con = null;
    private static DbConnection db;
    private PreparedStatement preparedStatement;

    private static final String url = "jdbc:mysql://localhost:3306/";
    private static final String dbName = "market_plus";
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Actualización del driver
    private static final String userName = "root";
    private static final String password = "";

    // Constructor privado para Singleton
    private DbConnection() {
        try {
            Class.forName(driver); // Cargar el driver
            con = DriverManager.getConnection(url + dbName, userName, password); // Establecer la conexión
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener la instancia única de la conexión
    public static DbConnection getConexion() {
        if (db == null) {
            db = new DbConnection();
        }
        return db;
    }

    // Método para cerrar la conexión
    public void cerrarConexion() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para ejecutar una consulta y obtener el ResultSet
    public ResultSet query() throws SQLException {
        return preparedStatement.executeQuery();
    }

    // Método para ejecutar una actualización (INSERT, UPDATE, DELETE)
    public int execute() throws SQLException {
        return preparedStatement.executeUpdate();
    }

    // Método para preparar la declaración
    public PreparedStatement setPreparedStatement(String sql) throws SQLException {
        this.preparedStatement = con.prepareStatement(sql);
        return this.preparedStatement;
    }

    // Método para obtener la conexión
    public Connection getCon() {
        return this.con;
    }
}