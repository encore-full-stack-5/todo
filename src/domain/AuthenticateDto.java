package domain;

public class AuthenticateDto {
	
	private String id;
	private String pw;
	
	public AuthenticateDto(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}

	public String getId() {
		return id;
	}
	
	public String getPw() {
		return pw;
	}
	
}
