package br.com.springsecurity.dao;

import javax.persistence.EntityManager;
import br.com.springsecurity.model.User;

public class UserDAO extends GenericDAO<User, Long> {

	public UserDAO(EntityManager em) {
		super(em);	
	}

}
