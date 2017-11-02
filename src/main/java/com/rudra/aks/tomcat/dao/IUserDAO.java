package com.rudra.aks.tomcat.dao;

import com.rudra.aks.tomcat.model.UserDTO;

public interface IUserDAO {

	UserDTO	getUser(int userid);

	void getPoolProperties();

	int getNoOfUsers();
}
