package prototype.domain;

import javax.validation.constraints.NotBlank;

public class Account {
	
	/*
	 * ID */
	private int id;
	
	/*
	 * ユーザー登録名　*/
	@NotBlank
	private String userName;
	
	/*
	 * パスワード　*/
	@NotBlank
	private String password;
	
	/*
	 * ユーザー権限　*/
	private String authority;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	@Override
    public String toString() {
        return "Account [id=" + id + ", userName=" + userName + ", password=" + password + ", authority=" + authority + "]";
    }

	

}
