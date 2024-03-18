package domain;

public class Request {
	private int id;
	private int userId;
	private int requestId;
	
	public Request(int id, int userId, int requestId) {
		super();
		this.id = id;
		this.userId = userId;
		this.requestId = requestId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	
	
}
