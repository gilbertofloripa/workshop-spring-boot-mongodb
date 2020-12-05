package com.nelioalves.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.repository.PostRepository;
import com.nelioalves.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired  //Instacia automaticamente o UserRepository para o repo
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));		
	}
	
	public List<Post> findByTitle(String text){
		// forma 1 de pesquisa
		//return repo.findByTitleContainingIgnoreCase(text);

		// forma 2 de pesquisa
		return repo.searchTitle(text);
	}
}
