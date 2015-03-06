package br.com.springsecurity.dao;

import javax.persistence.EntityManager;
import br.com.springsecurity.model.Role;

public class RoleDAO extends GenericDAO<Role, Long> {

	public RoleDAO(EntityManager em) {
		super(em);
	}

}
