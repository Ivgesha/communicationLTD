package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.entity.Login;
import com.model.PasswordHandler;
import com.util.DBConnectionUtil;

public class LoginDAOImpl implements LoginDao {
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	int result = 0;
	PreparedStatement preparedStatement = null;
	PasswordHandler passwordHandler = null;

	@Override
	public String authenticate(Login login) {

		try { // create the sql query
			String sql = "SELECT * FROM tbl_login WHERE username=? ";

			// get a database connection
			connection = DBConnectionUtil.openConnection();

			// create a statement
			// loginstatement = connection.createStatement();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, login.getUsername());

			// execute the query
			// resultSet = statement.executeQuery(sql);
			resultSet = preparedStatement.executeQuery();
			Login currentUser = new Login();
			if (resultSet.next()) {

				currentUser.setId(resultSet.getInt("id"));
				currentUser.setEmail(resultSet.getString("email"));
				currentUser.setUsername(resultSet.getString("username"));
				currentUser.setSalt(resultSet.getString("salt"));
				currentUser.setPassword(resultSet.getString("password"));

			} else {
				return "false";
			}
			passwordHandler = new PasswordHandler();

			String password = currentUser.getSalt() + login.getPassword();
			String hashedPass = PasswordHandler.HmacSHA1(password, login.getUsername());
			System.out.println("hashedPass = " + hashedPass);
			System.out.println("currentUser.getPassword() " + currentUser.getPassword());

			if (hashedPass.equals(currentUser.getPassword())) {
				return "true";

			} else {
				return "false";
			}

		} catch (Exception e) {

			e.printStackTrace();
			return "error";
		}

		/*
		 * String sql = "select * from tbl_login where username=? and password=?";
		 * 
		 * try { // first lookup in the tbl_login Connection connection =
		 * DBConnectionUtil.openConnection(); PreparedStatement preparedStatement =
		 * connection.prepareStatement(sql); preparedStatement.setString(1,
		 * login.getUsername()); preparedStatement.setString(2, login.getPassword());
		 * ResultSet result = preparedStatement.executeQuery();
		 * 
		 * 
		 * 
		 * // if we are getting a result if (result.next()) {
		 * 
		 * return "true"; }
		 * 
		 * else {
		 * 
		 * return "false"; }
		 * 
		 * 
		 * } catch (Exception e) {
		 * 
		 * e.printStackTrace(); } // return error if our sql connection was not
		 * successfull return "error";
		 */
	}

	@Override
	public boolean insertUser(Login login) {

		String sql = "INSERT INTO tbl_login(username , password , salt , email)" + "VALUES(?,?,?,?)";

		try {
			Connection connection = DBConnectionUtil.openConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, login.getUsername());
			preparedStatement.setString(2, login.getPassword());
			preparedStatement.setString(3, login.getSalt());
			preparedStatement.setString(4, login.getEmail());
			preparedStatement.execute();
			System.out.println("inserted new user\n");
			return true;
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			System.out.println("could not insert new user(operator)\n");
			System.out.println("username allready taken.");
			e.printStackTrace();
			
		}
		return false;
	}

	public Login get(String username) {

		String sql = "select * from tbl_login where username=?";

		try {
			connection = DBConnectionUtil.openConnection();

			// create a statement
			// loginstatement = connection.createStatement();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);

			// execute the query
			// resultSet = statement.executeQuery(sql);
			resultSet = preparedStatement.executeQuery();
			Login currentUser = new Login();
			if (resultSet.next()) {

				currentUser.setId(resultSet.getInt("id"));
				currentUser.setEmail(resultSet.getString("email"));
				currentUser.setUsername(resultSet.getString("username"));
				currentUser.setSalt(resultSet.getString("salt"));
				currentUser.setPassword(resultSet.getString("password"));
				System.out.println("entered in loginDao.get and set the current user");
			}
			
			return currentUser;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}

	@Override
	public boolean updateUser(Login login) {
		String sql = "update tbl_login set password=?,salt=? where username=?";

		String password = login.getPassword();
		String salt = login.getSalt();
		String username = login.getUsername();
		System.out.println("username " + username + "password " + password + " salt " + salt );
		try {
			connection = DBConnectionUtil.openConnection();

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, salt);
			preparedStatement.setString(3, username);
			result = preparedStatement.executeUpdate();

			System.out.println("updated succesfull ");
			return true;
		} catch (Exception e) {
			System.out.println("fail to update");
			e.printStackTrace();
			return false;

		}

	}

	@Override
	public Login getUserNameEmail(String username, String email) {
		String sql = "SELECT * from tbl_login where username=? AND email=?";
		
		try {
			connection = DBConnectionUtil.openConnection();

			// create a statement
			// loginstatement = connection.createStatement();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, email);

			// execute the query
			// resultSet = statement.executeQuery(sql);
			resultSet = preparedStatement.executeQuery();
			Login currentUser = null;
			if (resultSet.next()) {
				currentUser = new Login();
				currentUser.setId(resultSet.getInt("id"));
				currentUser.setEmail(resultSet.getString("email"));
				currentUser.setUsername(resultSet.getString("username"));
				currentUser.setSalt(resultSet.getString("salt"));
				currentUser.setPassword(resultSet.getString("password"));
				
			}
			
			return currentUser;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
		
		
	}
}
