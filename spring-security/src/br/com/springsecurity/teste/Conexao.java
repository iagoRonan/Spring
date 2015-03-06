package br.com.springsecurity.teste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.springsecurity.dao.RoleDAO;
import br.com.springsecurity.dao.UserDAO;
import br.com.springsecurity.model.Role;
import br.com.springsecurity.model.User;

public class Conexao {
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("spring-security");
		EntityManager em = emf.createEntityManager();
		
		/*
		String[] roles_description = new String[2];
		roles_description[0] = "ROLE_ADMIN";
		roles_description[1] = "ROLE_USER";
		*/				
		
		RoleDAO rdao = new RoleDAO(em);		
		List<Role> roles = rdao.findWithNamedQuery("Role.findAll", null);
		
		User user = new User();
		user.setUsername("iago");
		user.setPassword("iago123");
		user.setRoles(roles);
		
		UserDAO udao = new UserDAO(em);
		try
		{
			udao.insert(user);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		em.close();
		emf.close();
	}

}
