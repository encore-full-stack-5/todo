package domain;

public class User {
	private int id;
	private String userId;
	private String password;
	private String name;
	
	public User() {
		
	}
	
	public User(int id, String userId, String password, String name) {
		this.id = id;
		this.userId = userId;
		this.password = password;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (!password.equals("")) this.password = password;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if (!name.equals("")) this.name = name;
	}
	
}
