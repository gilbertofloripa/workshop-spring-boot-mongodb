package com.nelioalves.workshopmongo.resources;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.UserDTO;
import com.nelioalves.workshopmongo.services.UserService;
import com.nelioalves.workshopmongo.services.exceptions.ObjectNotFoundException;

@RestController // Indica um entrada via we End point
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	// Utilizando o DTO tem a vantagem de puxar ou grava do BD somente o que precisa.
	@RequestMapping(method = RequestMethod.GET)
	//public ResponseEntity<List<User>> findAll() {  // ResponseEntity<List<User>> Apresenta a lista estruturada
	public ResponseEntity<List<UserDTO>> findAll() {  // ResponseEntity<List<User>> Apresenta a lista estruturada
		//User maria = new User("1", "Maria Brown", "maria@gmail.com");
		//User alex = new User("2", "Alex Green", "alex@gmail.com");
		//List<User> list = new ArrayList<>();
		//list.addAll(Arrays.asList(maria, alex));
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}

}
