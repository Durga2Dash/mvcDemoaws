package com.mindtree.dao.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mindtree.dao.ValidationDao;
import com.mindtree.entity.User;

@Component
public class ValidationDaoImpl implements ValidationDao {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	/*----------------------------------------Getter and Setter Methods----------------------------------------*/

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/*---------------------------------------------CRUD Operations---------------------------------------------*/

	public String validateUser(User checkUser) {
		User user = getUserByUserName(checkUser.getUserName());
		if (user == null) {
			return "No such User exists";
		}
		if (!(user.getPassword().equals(checkUser.getPassword()))) {
			return "Incorrect Password";
		}
		System.out.println(user);
		return "Congrats!!! You have entered the correct User Name and password";
	}

	public User getUserByUserName(String userName) {
		String query = "select * from user_data where username = ?";
		List<User> users = jdbcTemplate.query(query, new Object[] { userName }, new UserMapper());
		if (users.size() == 1) {
			return users.get(0);
		}
		return null;
	}

	public List<User> getAllUsers() {
		String query = "select * from user_data";
		List<User> users = new ArrayList<User>();
		users = jdbcTemplate.query(query, new UserMapper());
		return users;
	}

	public static final class UserMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUserName(rs.getString(1));
			user.setPassword(rs.getString(2));
			return user;
		}
	}
}