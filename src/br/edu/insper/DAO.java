package br.edu.insper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	private Connection connection  = null;
	
	public DAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/meus_dados", "root", "Leonardo@123");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<User> getLista(){
	
		
		List<User> users = new ArrayList<User>();
		
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement("SELECT * FROM User;");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("senha"));
				user.setNickname(rs.getString("nickname"));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
		
		
	}
	public List<User> getNotes(String username){
	
		
		List<User> notes= new ArrayList<User>();
		
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement("SELECT User.username, Notes.id, Notes.content FROM User JOIN Notes ON Notes.user_id = User.id WHERE User.username = '" + username + "'");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setNote(rs.getString("content"));
				user.setNoteId(rs.getInt("id"));
				notes.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notes;
		
		
	}
	
	public void adicionaUser(User user) {
		String sql = "INSERT INTO User (username, nickname, senha) VALUES (?,?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1,user.getUsername());
			stmt.setString(2, user.getNickname());
			stmt.setString(3, user.getPassword());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void adicionaNota(User user) {
		String sql = "INSERT INTO Notes (user_id, content) VALUES (?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1,user.getId());
			stmt.setString(2, user.getNote());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void editaNota(String note, int noteId) {
		String sql = "UPDATE Notes  SET content = ? WHERE id = " + noteId + ";";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, note);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void removeNota(int noteId) {
		String sql = "DELETE FROM Notes WHERE id =?;";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, noteId);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}