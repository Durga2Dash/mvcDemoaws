package com.mindtree.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mindtree.dao.ValidationDao;
import com.mindtree.entity.User;
import com.mindtree.service.ValidationSevice;

@Component
public class ValidationServiceImpl implements ValidationSevice {

	private ValidationDao validationDaoImpl;

	/*----------------------------------------Getter and Setter Methods----------------------------------------*/

	@Autowired
	public void setValidationDaoImpl(ValidationDao validationDaoImpl) {
		this.validationDaoImpl = validationDaoImpl;
	}

	/*---------------------------------------------CRUD Operations---------------------------------------------*/

	public String validateUser(String userName, String password) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		return validationDaoImpl.validateUser(user);
	}
}