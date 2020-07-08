package com.dao;

import com.entity.Login;

public interface LoginDao {
	String authenticate(Login login);
	boolean insertUser(Login login);
	Login get(String username);
	boolean updateUser(Login login);
	Login getUserNameEmail(String username, String email);
}
