package br.edu.insper;

public class User {
	
	private Integer id;
	private Integer note_id;
	private String username;
	private String nickname;
	private String password;
	private String note;
	
	public Integer getId() {return id;}
	public void setId(Integer id) {this.id =id;}
	
	public Integer getNoteId() {return note_id;}
	public void setNoteId(Integer note_id) {this.note_id =note_id;}
	
	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}
	
	public String getNickname() {return nickname;}
	public void setNickname(String nickname) {this.nickname= nickname;}
	
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password= password;}
	
	public String getNote() {return note;}
	public void setNote(String note) {this.note= note;}
	
	

}
