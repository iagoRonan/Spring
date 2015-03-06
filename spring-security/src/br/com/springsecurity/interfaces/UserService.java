package br.com.springsecurity.interfaces;

import br.com.springsecurity.model.User;

public interface UserService {

	public User getUser(String login);
}
