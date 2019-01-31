package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConn {

	private DbConn(){}
	
	static DbConn dbConn=null;
	
	public static DbConn getInstance(){
		if(dbConn==null){
			dbConn= new DbConn();
		}
		return dbConn;
	}
	
	Connection connection;
	Statement statement;
	public void openConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/exam","root","root");
			statement = connection.createStatement();
//			System.out.println("Connection Open");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("ERROR in Connection Open");
			e.printStackTrace();
		}
		
	}
	
	public void closeConnection(){
		try {
			connection.close();
//			System.out.println("Connection Close");
		} catch (SQLException e) {
			System.out.println("ERROR in Connection Close");
			e.printStackTrace();
		}
	}
	
	public int executeUpdate(String sql){
		int id=0;
		try {
			id=statement.executeUpdate(sql,statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			System.out.println("ERROR in Sql executeUpdate");
			e.printStackTrace();
		}
		return id;
	}
	
	public ResultSet executeQuery(String sql){
		ResultSet resultSet=null;
		 try {
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("ERROR in Sql executeQuery");
			e.printStackTrace();
		}
		 return resultSet;
	}
	
}
