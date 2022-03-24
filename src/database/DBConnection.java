package database;

import java.sql.*;

public class DBConnection {
    private Connection connection = null;
    private static DBConnection dbConnection;

    private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String dbName = "CSC-CSD-S211_10407575";
    private static final String server = "hildur.ucn.dk";
    private static final int port = 1433;
    
    private static final String username = "CSC-CSC-S211_10407575";
    private static final String password = "Password1!";

    private DBConnection() {
        String url = String.format("jdbc:sqlserver://%s:%d;databaseName=%s;user=%s;password=%s", 
            server, port, dbName, username, password);
        
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
			System.err.println("Could not load JDBC driver");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Could not connect to database " + dbName + "@" + server + ":" + port + " as user " + username + " using password ******");
			System.out.println("Connection string was: " + url.substring(0, url.length() - password.length()) + "....");
			e.printStackTrace();
		}

    }

    public static DBConnection getInstance() {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public Connection getConnection() {
		return connection;
	}
	
	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    public void startTransaction() throws SQLException {
		connection.setAutoCommit(false);
	}
	
	public void commitTransaction() throws SQLException {
		connection.commit();
		connection.setAutoCommit(true);
	}
	
	public void rollbackTransaction() throws SQLException {
		connection.rollback();
		connection.setAutoCommit(true);
	}
	
	public int executeInsertWithIdentity(PreparedStatement ps) throws SQLException  {
		System.out.println("DBConnection, Inserting: " + ps);
		int res = -1;
		try {
			res = ps.executeUpdate();
			if(res > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return res;
	}
	
	public int executeInsertWithIdentity(String sql) throws SQLException  {
		System.out.println("DBConnection, Inserting: " + sql);
		int res = -1;
		try (Statement s = connection.createStatement()) {
			res = s.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			if(res > 0) {
				ResultSet rs = s.getGeneratedKeys();
				rs.next();
				res = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return res;
	}
	
	public int executeUpdate(String sql) throws SQLException {
		System.out.println("DBConnection, Updating: " + sql);
		int res = -1;
		try (Statement s = connection.createStatement()){
			res = s.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return res;
	}

	public int executeUpdate(PreparedStatement ps) throws SQLException {
		System.out.println("DBConnection, Updating: " + ps);
		int res = -1;
		try {
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return res;
	}

}
