package com.nelioalves.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.services.UserService;

@RestController // Indica um entrada via we End point
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll() {  // ResponseEntity<List<User>> Apresenta a lista estruturada
		//User maria = new User("1", "Maria Brown", "maria@gmail.com");
		//User alex = new User("2", "Alex Green", "alex@gmail.com");
		//List<User> list = new ArrayList<>();
		//list.addAll(Arrays.asList(maria, alex));
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}