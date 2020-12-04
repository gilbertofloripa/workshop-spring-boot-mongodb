package com.nelioalves.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.repository.UserRepository;
import com.nelioalves.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired  //Instacia automaticamente o UserRepository para o repo
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll(); // Acessa todas as funcioes do mongo atraves da instancia UserRepository
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));		
	}
}
