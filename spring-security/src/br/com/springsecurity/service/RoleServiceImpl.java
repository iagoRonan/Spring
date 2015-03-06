package br.com.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.springsecurity.dao.RoleDAO;
import br.com.springsecurity.interfaces.RoleService;
import br.com.springsecurity.model.Role;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDAO rdao;

	@Override
	public Role getRole(Long id) {
		return rdao.find(Role.class, id);
	}
	
	
}
