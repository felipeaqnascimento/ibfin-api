/*
 * Created on 23 mai 2017 ( Time 18:19:53 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package br.com.ibrowse.business.service;

import java.util.List;

import br.com.ibrowse.bean.TiposTitulos;

/**
 * Business Service Interface for entity TiposTitulos.
 */
public interface TiposTitulosService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param oidTiposTitulos
	 * @return entity
	 */
	TiposTitulos findById( Long oidTiposTitulos  ) ;

	/**
	 * Loads all entities.
	 * @return all entities
	 */
	List<TiposTitulos> findAll();

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	TiposTitulos save(TiposTitulos entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	TiposTitulos update(TiposTitulos entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	TiposTitulos create(TiposTitulos entity);

	/**
	 * Deletes an entity using its Primary Key
	 * @param oidTiposTitulos
	 */
	void delete( Long oidTiposTitulos );


}
