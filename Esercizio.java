package ntt.prova;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Esercizio {
	
	private Connection con;
	
     public static void main(String[] args) {
		
		Esercizio c = new Esercizio ();
		
		try {
			System.out.println(c.startConnection().isValid(100));
			System.out.println(c.startConnection().isClosed());
			c.esempioSelect();
			c.esempioInsert("Liam","Gallagher","liamgallagher@gmail.com");
			c.esempioDelete();
			c.esempioUpdate();
			c.esempioUpdate2();
			
		}  
		
		catch (SQLException e) {
			System.out.println("Siamo nel catch, c'è un errore di connessione");
			e.printStackTrace();
		}
     }
     Connection startConnection() throws SQLException {
 		
 		if (con == null) {
 			MysqlDataSource dataSource = new MysqlDataSource();
 			dataSource.setServerName("127.0.0.1");
 			dataSource.setPortNumber(3306);
 			dataSource.setUser("root");
 			dataSource.setPassword("root");
 			dataSource.setDatabaseName("esercizio_prova");
 			
 			con = dataSource.getConnection();
 		}
 		
		return con;	
     }
     
     private void esempioSelect() throws SQLException  {
    	 
 		String sql = "SELECT * FROM esercizio";
 				
 		PreparedStatement statement = startConnection().prepareStatement(sql);
 		
 		statement.executeQuery();
 		ResultSet rs = statement.executeQuery();
 		while (rs.next()) {
 			System.out.println("idUEsercizio:"+ rs.getInt(1));
 			System.out.println("nome:" + rs.getString(2));
 			System.out.println("cognome:" + rs.getString(3));
 			System.out.println("Email:" + rs.getString(4));
 			System.out.println("----------------------");
 		}
    }
     
     private void esempioInsert(String nome, String cognome, String email) throws SQLException { 
 		
 		String sql ="INSERT INTO esercizio(nome, cognome, email) VALUES ('"+nome+ "','"+cognome+"','"+email+"')";
 		PreparedStatement statement = startConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
         statement.executeUpdate();
         ResultSet rs = statement.getGeneratedKeys();
         rs.next();
         System.out.println("id:"+ rs.getInt(1));
     }
     
     private void esempioDelete() throws SQLException {
 		String sql = "DELETE FROM esercizio WHERE idEsercizio = 1";
 		PreparedStatement statement = startConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
 	    statement.executeUpdate();
 	}
     
     private void esempioUpdate() throws SQLException {
 		String sql = "UPDATE Esercizio SET cognome = 'Archer' WHERE idEsercizio= 6";
 		PreparedStatement statement = startConnection().prepareStatement(sql);
 		statement.executeUpdate();
 	}
     
     private void esempioUpdate2() throws SQLException {
  		String sql = "UPDATE Esercizio SET nome = 'Gem' WHERE idEsercizio= 5";
  		PreparedStatement statement = startConnection().prepareStatement(sql);
  		statement.executeUpdate();
  	}
}
