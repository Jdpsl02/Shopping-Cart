package security.payload.response;

import java.util.List;

public class UserInfoResponse {
	  private String id;
	  private String username;
	  private String email;
	  private List<String> roles;
	  private String token;
	  private String type = "Bearer";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public UserInfoResponse(String id, String username, String email, List<String> roles, String token) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.token = token;
//		this.type = type;
	}
	public UserInfoResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserInfoResponse [id=" + id + ", username=" + username + ", email=" + email + ", roles=" + roles
				+ ", token=" + token + ", type=" + type + "]";
	}
	  
	  

	}
