package client;

public class Configuration {
	String username;
	String password;
	
	/**
	 * The login details that can be used after logging in the system
	 * @param username
	 * @param password
	 */
	public Configuration(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {  
		return password;
	}
	
}
