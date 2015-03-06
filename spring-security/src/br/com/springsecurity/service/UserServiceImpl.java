package br.com.springsecurity.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.springsecurity.dao.UserDAO;
import br.com.springsecurity.interfaces.UserService;
import br.com.springsecurity.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO udao;
	
	@Override
	public User getUser(String login) {				
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", login);
		return udao.findWithNamedQuerySingle("User.findByUserUsername", params);
	}

}
