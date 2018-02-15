/*
 * Created on 23 mai 2017 ( Time 18:19:53 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package br.com.ibrowse.business.service;

import java.util.List;

import br.com.ibrowse.bean.Pessoas;

/**
 * Business Service Interface for entity Pessoas.
 */
public interface PessoasService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param oidPessoas
	 * @return entity
	 */
	Pessoas findById( Long oidPessoas  ) ;

	/**
	 * Loads all entities.
	 * @return all entities
	 */
	List<Pessoas> findAll();

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	Pessoas save(Pessoas entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	Pessoas update(Pessoas entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	Pessoas create(Pessoas entity);

	/**
	 * Deletes an entity using its Primary Key
	 * @param oidPessoas
	 */
	void delete( Long oidPessoas );


}
