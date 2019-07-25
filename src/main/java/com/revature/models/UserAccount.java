package com.revature.models;

import java.util.Arrays;

public class UserAccount {

	private Integer id;
	private String username,
				   password;
	
	private Recipe[] favorites,
					 curList;
	
	public UserAccount() {
	}

	public UserAccount(Integer id, String username, String password, Recipe[] favorites, Recipe[] curList) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.favorites = favorites;
		this.curList = curList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Recipe[] getFavorites() {
		return favorites;
	}

	public void setFavorites(Recipe[] favorites) {
		this.favorites = favorites;
	}

	public Recipe[] getCurList() {
		return curList;
	}

	public void setCurList(Recipe[] curList) {
		this.curList = curList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(curList);
		result = prime * result + Arrays.hashCode(favorites);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAccount other = (UserAccount) obj;
		if (!Arrays.equals(curList, other.curList))
			return false;
		if (!Arrays.equals(favorites, other.favorites))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserAccount [id=" + id + ", username=" + username + ", password=" + password + ", favorites="
				+ Arrays.toString(favorites) + ", curList=" + Arrays.toString(curList) + "]";
	}

}
