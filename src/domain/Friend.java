package domain;

public class Friend {
	private int id;
	private int userId;
	private int friendId;
	
	public Friend() {
		
	}
		
	public Friend(int id, int userId, int friendId) {
		super();
		this.id = id;
		this.userId = userId;
		this.friendId = friendId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
