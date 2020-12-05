package com.nelioalves.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.nelioalves.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	// Forma 1 de pesuisa um string dentro do campo
	// a forma como escreve o comando indi o que vai fazer ex: findByTitleContainingIgnoreCase
	// Query methods findByTitleContainingIgnoreCase
	// Conforme agente escreve ele faz a funcao
	List<Post> findByTitleContainingIgnoreCase(String text);

	// Forma 2 de pesuisa um string dentro do campo
	// { <field>: { $regex: /pattern/, $options: '<options>' } }
	// a forma como escreve  searchTitle po ser qq nome
	// 'title' -> Nome do campo que vai ser pesquisado
	// ?0 qual informacao recebe 1,2,3 .. no caso 0-text
	// 'i'-> inginora maiusculas e minusculas
	@Query("{ 'title' : { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);

	// Forma 3 pesquisa um texto dentro de todos os campos (titulo, çosts e comentarios entre duas data
	// Busca (text dentro de title OR post OR comment) AND date >= minDate and date <= maxDate
	@Query("{ $and: [ {date: {$gte: ?1} }, {date: {$lte: ?2} }, { $or: ["
			+ " { 'title' : { $regex: ?0, $options: 'i' } },"
			+ " { 'body' : { $regex: ?0, $options: 'i' } },"
			+ " { 'comments.text' : { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
	
	
}
